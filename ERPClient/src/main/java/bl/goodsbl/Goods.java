package bl.goodsbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import bl.classificationbl.ClassificationBLFactory;
import bl.logbl.LogBLFactory;
import bl.salesbl.SalesBLFactory;
import blservice.classificationblservice.ClassificationInfo;
import blservice.logblservice.LogInfo;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import dataservice.goodsdataservice.GoodsDataService;
import po.ClassificationPO;
import po.GoodsPO;
import rmi.GoodsRemoteHelper;
import util.Criterion;
import util.OperationObjectType;
import util.OperationType;
import util.QueryMode;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class Goods {
	private GoodsDataService goodsDataService;
	private ClassificationInfo classificationInfo;
	private LogInfo logInfo;

	protected Goods() {
		goodsDataService = GoodsRemoteHelper.getInstance().getGoodsDataService();
		classificationInfo = ClassificationBLFactory.getInfo();
		logInfo = LogBLFactory.getInfo();
	}

	public ArrayList<GoodsVO> show() throws RemoteException {
		ArrayList<GoodsPO> pos = goodsDataService.show();
		ArrayList<GoodsVO> ret = new ArrayList<>();
		for (GoodsPO po : pos) {
			ret.add(poToVO(po));
		}
		return ret;
	}

	public ArrayList<GoodsVO> find(String keyword) throws RemoteException {
		ArrayList<GoodsPO> pos = goodsDataService.show();
		ArrayList<GoodsVO> ret = new ArrayList<>();
		for (GoodsPO po : pos) {
			if (po.buildID().contains(keyword) || po.getName().contains(keyword) || po.getModel().contains(keyword)) {
				ret.add(poToVO(po));
			}
		}
		return ret;
	}

	public GoodsVO showDetails(String ID) throws NumberFormatException, RemoteException {
		GoodsPO found = goodsDataService.find(ID);
		return poToVO(found);
	}

	public ResultMessage add(GoodsVO vo) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("name", vo.name, QueryMode.FULL));
		criteria.add(new Criterion("model", vo.model, QueryMode.FULL));
		ArrayList<GoodsPO> repeated = goodsDataService.advancedQuery(criteria);
		if (!repeated.isEmpty()) {
			return ResultMessage.EXIST;
		}
		else {
			GoodsPO toAdd = voToPO(vo);
			ResultMessage ret = goodsDataService.add(toAdd);
			if (ret == ResultMessage.SUCCESS) {
				logInfo.record(OperationType.ADD, OperationObjectType.GOODS, toAdd.toString());
			}
			return ret;
		}
	}

	public ResultMessage delete(String ID) throws NumberFormatException, RemoteException {
		GoodsPO found = goodsDataService.find(ID);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else if (found.countAmount() > 0) {
			return ResultMessage.EXIST;
		}
		else {
			SalesInfo salesInfo = SalesBLFactory.getSalesInfo();
			PurchaseInfo purchaseInfo = SalesBLFactory.getPurchaseInfo();
			LocalDate now = LocalDate.now();
			String start = now.minusYears(1).toString();
			String end = now.toString();
			ArrayList<SalesVO> salesVOs = salesInfo.getAllSalesOrder(start, end);
			ArrayList<SalesVO> salesReturnVOs = salesInfo.getAllSalesReturnOrder(start, end);
			ArrayList<PurchaseVO> purchaseVOs = purchaseInfo.getPurchaseByDate(start, end);
			for (SalesVO salesVO : salesVOs) {
				if (this.isInvolvedGoods(salesVO.goodsItemList, ID)) {
					return ResultMessage.EXIST;
				}
			}
			for (SalesVO salesReturnVO : salesReturnVOs) {
				if (this.isInvolvedGoods(salesReturnVO.goodsItemList, ID)) {
					return ResultMessage.EXIST;
				}
			}
			for (PurchaseVO purchaseVO : purchaseVOs) {
				if (this.isInvolvedGoods(purchaseVO.goodsItemList, ID)) {
					return ResultMessage.EXIST;
				}
			}
			return goodsDataService.delete(found);
		}
	}

	private boolean isInvolvedGoods(ArrayList<GoodsItemVO> list, String goodsID) {
		for (GoodsItemVO vo : list) {
			if (vo.ID.equals(goodsID)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 约定：对商品的修改只能修改：<br>
	 * 名字(name)、型号(model)、警戒数量(alarmAmount)、进价(buyingPrice)和零售价(retailPrice)
	 */
	public ResultMessage update(GoodsVO vo) throws NumberFormatException, RemoteException {
		GoodsPO toUpdate = goodsDataService.find(vo.ID);
		if (toUpdate == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			if (!toUpdate.getName().equals(vo.name) || !toUpdate.getModel().equals(vo.model)) { // 若要改名
				ArrayList<Criterion> criteria = new ArrayList<>();
				criteria.add(new Criterion("name", vo.name, QueryMode.FULL));
				criteria.add(new Criterion("model", vo.model, QueryMode.FULL));
				ArrayList<GoodsPO> repeated = goodsDataService.advancedQuery(criteria);
				if (!repeated.isEmpty()) { // 重名
					return ResultMessage.EXIST;
				}
			}
			toUpdate.setName(vo.name);
			toUpdate.setModel(vo.model);
			toUpdate.setAlarmAmount(vo.alarmAmount);
			toUpdate.setBuyingPrice(vo.buyingPrice);
			toUpdate.setRetailPrice(vo.retailPrice);
			ResultMessage ret = goodsDataService.update(toUpdate);
			if (ret == ResultMessage.SUCCESS) {
				logInfo.record(OperationType.UPDATE, OperationObjectType.GOODS, toUpdate.toString());
			}
			return ret;
		}
	}

	public String getNewID(String classificationID) throws RemoteException {
		return goodsDataService.getNewID(classificationID);
	}

	public static GoodsVO poToVO(GoodsPO po) {
		String classificationName = po.getClassification().getName();
		GoodsVO ret = new GoodsVO(po.buildID(), po.getName(), po.getModel(), classificationName, po.countAmount(),
				po.getAlarmAmount(), po.getBuyingPrice(), po.getRetailPrice(), po.getRecentBuyingPrice(),
				po.getRecentRetailPrice());
		return ret;
	}

	/**
	 * 仅限增加商品时调用
	 */
	private GoodsPO voToPO(GoodsVO vo) {
		ClassificationPO classification = classificationInfo.getClassificationByName(vo.classification);
		int turn = Integer.parseInt(vo.ID.substring(2));
		GoodsPO ret = new GoodsPO(vo.name, vo.model, classification, vo.alarmAmount, vo.buyingPrice, vo.retailPrice,
				vo.buyingPrice, vo.retailPrice, turn);
		return ret;
	}

	protected GoodsPO getGoodsByID(String ID) throws NumberFormatException, RemoteException {
		return goodsDataService.find(ID);
	}

	public ResultMessage updateRecentBuyingPrice(String goodsID, double recentBuyingPrice) throws NumberFormatException, RemoteException {
		GoodsPO found = goodsDataService.find(goodsID);
		if(found == null){
			return ResultMessage.NOT_EXIST;
		}
		else{
			found.setRecentBuyingPrice(recentBuyingPrice);
			return goodsDataService.update(found);
		}
	}

	public ResultMessage updateRecentRetailPrice(String goodsID, double recentRetailPrice) throws NumberFormatException, RemoteException {
		GoodsPO found = goodsDataService.find(goodsID);
		if(found == null){
			return ResultMessage.NOT_EXIST;
		}
		else{
			found.setRecentBuyingPrice(recentRetailPrice);
			return goodsDataService.update(found);
		}
	}
}
