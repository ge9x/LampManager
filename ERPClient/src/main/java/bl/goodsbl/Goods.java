package bl.goodsbl;

import java.util.ArrayList;

import blservice.classificationblservice.ClassificationInfo;
import dataservice.goodsdataservice.GoodsDataService;
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
	
}
