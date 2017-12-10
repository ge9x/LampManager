package bl.goodsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.classificationbl.ClassificationController;
import blservice.classificationblservice.ClassificationInfo;
import dataservice.goodsdataservice.GoodsDataService;
import po.ClassificationPO;
import po.GoodsPO;
import rmi.GoodsRemoteHelper;
import util.Criterion;
import util.QueryMode;
import util.ResultMessage;
import vo.GoodsVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class Goods {
	private GoodsDataService goodsDataService;
	private ClassificationInfo classificationInfo;
	
	public Goods(){
		goodsDataService = GoodsRemoteHelper.getInstance().getGoodsDataService();
		classificationInfo = new ClassificationController();
	}

	public ArrayList<GoodsVO> show() throws RemoteException {
		ArrayList<GoodsPO> pos = goodsDataService.show();
		ArrayList<GoodsVO> ret = new ArrayList<>();
		for(GoodsPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
	}

	public ArrayList<GoodsVO> find(String keyword) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(
				new Criterion(
						new Criterion(
								new Criterion("ID", keyword, QueryMode.FUZZY),
								new Criterion("name", keyword, QueryMode.FUZZY)),
						new Criterion("model", keyword, QueryMode.FUZZY)));
		ArrayList<GoodsPO> pos = goodsDataService.advancedQuery(criteria);
		ArrayList<GoodsVO> ret = new ArrayList<>();
		for(GoodsPO po : pos){
			ret.add(poToVO(po));
		}
		return ret;
	}

	public GoodsVO showDetails(String ID) throws NumberFormatException, RemoteException {
		int poID = Integer.parseInt(ID.substring(2));
		GoodsPO found = goodsDataService.find(poID);
		return poToVO(found);
	}

	public ResultMessage add(GoodsVO vo) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("name", vo.name, QueryMode.FULL));
		criteria.add(new Criterion("model", vo.model, QueryMode.FULL));
		ArrayList<GoodsPO> repeated = goodsDataService.advancedQuery(criteria);
		if(!repeated.isEmpty()){
			return ResultMessage.EXIST;
		}
		else{
			GoodsPO toAdd = voToPO(vo);
			return goodsDataService.add(toAdd);
		}
	}

	public ResultMessage delete(String ID) throws NumberFormatException, RemoteException {
		GoodsPO found = goodsDataService.find(Integer.parseInt(ID.substring(2)));
		if(found == null){
			return ResultMessage.NOT_EXIST;
		}
		else{
			
		}
		return null;
	}

	public ResultMessage update(GoodsVO vo) {
		return null;
	}

	public String getNewID(String classificationID) throws RemoteException {
		return goodsDataService.getNewID(classificationID);
	}
	
	public static GoodsVO poToVO(GoodsPO po){
		String classificationName = po.getClassification().getName();
		GoodsVO ret = new GoodsVO(po.buildID(), po.getName(), po.getModel(), classificationName, po.countAmount(), po.getAlarmAmount(), po.getBuyingPrice(), po.getRetailPrice(), po.getRecentBuyingPrice(), po.getRecentRetailPrice());
		return ret;
	}
	
	private GoodsPO voToPO(GoodsVO vo){
		ClassificationPO classification = classificationInfo.getClassificationByName(vo.classification);
		int turn = Integer.parseInt(vo.ID.substring(2));
		GoodsPO ret = new GoodsPO(vo.name, vo.model, classification, vo.alarmAmount, vo.buyingPrice, vo.retailPrice, vo.buyingPrice, vo.retailPrice, turn);
		return ret;
	}
}
