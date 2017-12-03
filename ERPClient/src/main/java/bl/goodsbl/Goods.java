package bl.goodsbl;

import java.util.ArrayList;

import bl.classificationbl.Classification;
import blservice.classificationblservice.ClassificationInfo;
import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import util.ResultMessage;
import vo.GoodsIdentityVO;
import vo.GoodsVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class Goods {
	private GoodsVO vo;
	private GoodsDataService goodsDataService;
	private ClassificationInfo classificationInfo;

	public ArrayList<GoodsVO> show() {
		return null;
	}

	public ArrayList<GoodsVO> find(String keyword) {
		return null;
	}

	public GoodsVO showDetails(String ID) {
		return null;
	}

	public ResultMessage add(GoodsVO vo) {
		return null;
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
		// TODO 不需要GoodsVO的Inventory成员变量
		GoodsVO ret = new GoodsVO(po.buildID(), po.getName(), po.getModel(), classificationName, "", po.countAmount(), po.getAlarmAmount(), po.getBuyingPrice(), po.getRetailPrice(), po.getRecentBuyingPrice(), po.getRecentRetailPrice());
		return null;
	}
}
