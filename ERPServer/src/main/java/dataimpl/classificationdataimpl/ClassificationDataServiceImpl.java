package dataimpl.classificationdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
	
	public static ClassificationDataServiceImpl getInstance(){
		if(classificationDataServiceImpl == null){
			classificationDataServiceImpl = new ClassificationDataServiceImpl();
		}
		return classificationDataServiceImpl;
	}
	
	private ClassificationDataServiceImpl(){
		
	}

	public ArrayList<ClassificationPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassificationPO find(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage add(ClassificationPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage delete(ClassificationPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage update(ClassificationPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
