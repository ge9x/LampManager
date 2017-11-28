package dataimpl.goodsdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import util.Criterion;
import util.ResultMessage;

/**
 * Created on 2017/11/25
 * @author 巽
 *
 */
public class GoodsDataServiceImpl implements GoodsDataService{
	private static GoodsDataServiceImpl goodsDataServiceImpl;
	private DataHelper<GoodsPO> goodsDataHelper;
	
	public static GoodsDataServiceImpl getInstance(){
		if(goodsDataServiceImpl == null){
			goodsDataServiceImpl = new GoodsDataServiceImpl();
		}
		return goodsDataServiceImpl;
	}
	
	private GoodsDataServiceImpl(){
		goodsDataHelper = new HibernateDataHelper<GoodsPO>(GoodsPO.class);
	}

	@Override
	public ArrayList<GoodsPO> show() throws RemoteException {
		return goodsDataHelper.fullyQuery(null, null);
	}

	@Override
	public GoodsPO find(int ID) throws RemoteException {
		return goodsDataHelper.exactlyQuery("id", ID);
	}

	@Override
	public ResultMessage add(GoodsPO po) throws RemoteException {
		return goodsDataHelper.save(po);
	}

	@Override
	public ResultMessage delete(GoodsPO po) throws RemoteException {
		return goodsDataHelper.delete(po);
	}

	@Override
	public ResultMessage update(GoodsPO po) throws RemoteException {
		return goodsDataHelper.update(po);
	}
}
