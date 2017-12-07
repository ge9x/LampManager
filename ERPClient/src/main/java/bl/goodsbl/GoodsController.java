package bl.goodsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.goodsblservice.GoodsBLService;
import blservice.goodsblservice.GoodsInfo;
import util.ResultMessage;
import vo.GoodsIdentityVO;
import vo.GoodsVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class GoodsController implements GoodsBLService, GoodsInfo{
	
	private Goods goods;
	
	public GoodsController(){
		goods = new Goods();
	}

	public ArrayList<GoodsVO> show() {
		try {
			return goods.show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<GoodsVO> find(String keyword) {
		return goods.find(keyword);
	}

	public GoodsVO showDetails(String ID) {
//		return goods.showDetails(ID);
		return null;
	}

	public ResultMessage add(GoodsVO vo) {
//		return goods.add(vo);
		return null;
	}

	public ResultMessage delete(String ID) {
		return goods.delete(ID);
	}

	public ResultMessage update(GoodsVO vo) {
		return goods.update(vo);
	}

	public String getNewID(String classificationID) {
		return goods.getNewID(classificationID);
	}

	public ArrayList<GoodsVO> getAllGoods() {
		return show();
	}

	public ArrayList<GoodsIdentityVO> getAllGoodsIdentity() {
		ArrayList<GoodsVO> vos = show();
		ArrayList<GoodsIdentityVO> ret = new ArrayList<>();
		for(GoodsVO vo : vos){
			ret.add(new GoodsIdentityVO(vo.ID, vo.name, vo.model));
		}
		return ret;
	}

	public Double getBuyingPriceByID(String ID) {
		return showDetails(ID).buyingPrice;
	}

	public Double getRetailPriceByID(String ID) {
		return showDetails(ID).retailPrice;
	}

}
