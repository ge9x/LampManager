package bl.goodsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.classificationbl.ClassificationController;
import blservice.classificationblservice.ClassificationInfo;
import dataservice.goodsdataservice.GoodsDataService;
import po.ClassificationPO;
import po.GoodsPO;
import rmi.GoodsRemoteHelper;
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

	public ArrayList<GoodsVO> find(String keyword) {
		return null;
	}

	public GoodsVO showDetails(String ID) throws NumberFormatException, RemoteException {
		int poID = Integer.parseInt(ID.substring(2));
		GoodsPO found = goodsDataService.find(poID);
		return poToVO(found);
	}

	public ResultMessage add(GoodsVO vo) throws RemoteException {
		GoodsPO toAdd = voToPO(vo);
		return goodsDataService.add(toAdd);
	}

	public ResultMessage delete(String ID) {
		return null;
	}

	public ResultMessage update(GoodsVO vo) {
		return null;
	}

	public String getNewID(String classificationID) {
		return null;
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
