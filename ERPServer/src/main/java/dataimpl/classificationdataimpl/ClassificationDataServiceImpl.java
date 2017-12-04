package dataimpl.classificationdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;
import util.ResultMessage;

/**
 * Created on 2017/11/23
 * @author 巽
 *
 */
public class ClassificationDataServiceImpl implements ClassificationDataService{
	private static ClassificationDataServiceImpl classificationDataServiceImpl;
	private DataHelper<ClassificationPO> classificationDataHelper;
	
	public static ClassificationDataServiceImpl getInstance(){
		if(classificationDataServiceImpl == null){
			classificationDataServiceImpl = new ClassificationDataServiceImpl();
		}
		return classificationDataServiceImpl;
	}
	
	private ClassificationDataServiceImpl(){
		this.classificationDataHelper = new HibernateDataHelper<ClassificationPO>(ClassificationPO.class);
	}

	@Override
	public ArrayList<ClassificationPO> show() throws RemoteException {
		return classificationDataHelper.fullyQuery(null, null);
	}

	@Override
	public ClassificationPO find(int ID) throws RemoteException {
		return classificationDataHelper.exactlyQuery("id", ID);
	}

	@Override
	public ResultMessage add(ClassificationPO po) throws RemoteException {
		return classificationDataHelper.save(po);
	}

	@Override
	public ResultMessage delete(ClassificationPO po) throws RemoteException {
		return classificationDataHelper.delete(po);
	}

	@Override
	public ResultMessage update(ClassificationPO po) throws RemoteException {
		return classificationDataHelper.update(po);
	}

	@Override
	public String getNewID() throws RemoteException {
		return String.format("%02d", classificationDataHelper.count() + 1);
	}

	@Override
	public ArrayList<ClassificationPO> findByName(String keyword) throws RemoteException {
		return classificationDataHelper.fuzzyQuery("name", keyword);
	}
}
