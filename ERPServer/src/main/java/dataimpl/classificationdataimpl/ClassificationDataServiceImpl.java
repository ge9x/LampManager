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
	private DataHelper<ClassificationPO> ClassificationDataHelper;
	
	public static ClassificationDataServiceImpl getInstance(){
		if(classificationDataServiceImpl == null){
			classificationDataServiceImpl = new ClassificationDataServiceImpl();
		}
		return classificationDataServiceImpl;
	}
	
	private ClassificationDataServiceImpl(){
		this.ClassificationDataHelper = new HibernateDataHelper<ClassificationPO>();
	}

	public ArrayList<ClassificationPO> show() throws RemoteException {
		return ClassificationDataHelper.fullyQuery("ID", null);
	}

	public ClassificationPO find(String ID) throws RemoteException {
		return ClassificationDataHelper.exactlyQuery("ID", Integer.parseInt(ID));
	}

	public ResultMessage add(ClassificationPO po) throws RemoteException {
		return ClassificationDataHelper.save(po);
	}

	public ResultMessage delete(ClassificationPO po) throws RemoteException {
		return ClassificationDataHelper.delete(po);
	}

	public ResultMessage update(ClassificationPO po) throws RemoteException {
		return ClassificationDataHelper.update(po);
	}
}
