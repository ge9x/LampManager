package bl.goodsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.goodsblservice.GoodsBLService;
import blservice.goodsblservice.GoodsInfo;
import po.GoodsPO;
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
		try {
			return goods.find(keyword);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public GoodsVO showDetails(String ID) {
		try {
			return goods.showDetails(ID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(GoodsVO vo) {
		try {
			return goods.add(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage delete(String ID) {
		try {
			return goods.delete(ID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage update(GoodsVO vo) {
		try {
			return goods.update(vo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public String getNewID(String classificationID) {
		try {
			return goods.getNewID(classificationID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
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

	@Override
	public GoodsPO getGoodsByID(String ID) {
		try {
			return goods.getGoodsByID(ID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
