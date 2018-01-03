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
import util.QueryMode;
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
	public GoodsPO find(String ID) throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		ClassificationPO classification = ClassificationDataServiceImpl.getInstance().findByID(Integer.parseInt(ID.substring(0, 2)));
		criteria.add(new Criterion("classification", classification, QueryMode.FULL));
		criteria.add(new Criterion("turn", Integer.parseInt(ID.substring(2)), QueryMode.FULL));
		ArrayList<GoodsPO> found = goodsDataHelper.multiQuery(criteria);
		if(found.isEmpty()){
			return null;
		}
		else if(found.size()>1){
			System.out.println("警告：数据库中出现一样的商品：" + ID);
		}
		return found.get(0);
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
		return classificationID + String.format("%06d", goodsDataHelper.fullyQuery("classification", classification).size() + 1);
	}

	@Override
	public ArrayList<GoodsPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return goodsDataHelper.multiQuery(criteria);
	}
}
