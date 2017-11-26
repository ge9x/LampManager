package dataimpl.classificationdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;
import util.Criterion;
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

	public ArrayList<ClassificationPO> show() throws RemoteException {
		return classificationDataHelper.multiQuery(new ArrayList<Criterion>());
	}

	public ClassificationPO find(String ID) throws RemoteException {
		return classificationDataHelper.exactlyQuery("id", Integer.parseInt(ID));
	}

	public ResultMessage add(ClassificationPO po) throws RemoteException {
		return classificationDataHelper.save(po);
	}

	public ResultMessage delete(ClassificationPO po) throws RemoteException {
		return classificationDataHelper.delete(po);
	}

	public ResultMessage update(ClassificationPO po) throws RemoteException {
		return classificationDataHelper.update(po);
	}
}
