package dataimpl.goodsdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import po.ClassificationPO;
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

	@Override
	public String getNewID(String classificationID) throws RemoteException {
		ClassificationPO classification = ClassificationDataServiceImpl.getInstance().findByID(Integer.parseInt(classificationID));
		return String.format("%06d", goodsDataHelper.fullyQuery("classification", classification).size() + 1);
	}

	@Override
	public ArrayList<GoodsPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return goodsDataHelper.multiQuery(criteria);
	}
}
