package bl.goodsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import util.ResultMessage;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class GoodsController implements GoodsDataService{
	
	private Goods goods;
	
	public GoodsController(){
		goods = new Goods();
	}

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
