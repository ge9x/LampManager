package bl.goodsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.classificationblservice.ClassificationInfo;
import blservice.goodsblservice.GoodsBLService;
import po.GoodsPO;
import util.ResultMessage;
import vo.GoodsVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class Goods {
	private GoodsVO vo;
	private GoodsBLService goodsBLService;
	private ClassificationInfo classificationInfo;

	public ArrayList<GoodsPO> show() throws RemoteException {
		return null;
	}

	public GoodsPO find(String ID) throws RemoteException {
		return null;
	}

	public ResultMessage add(GoodsPO po) throws RemoteException {
		return null;
	}

	public ResultMessage delete(GoodsPO po) throws RemoteException {
		return null;
	}

	public ResultMessage update(GoodsPO po) throws RemoteException {
		return null;
	}
}
