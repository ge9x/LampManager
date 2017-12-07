package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataservice.classificationdataservice.ClassificationDataService;
import po.ClassificationPO;
import util.ResultMessage;

/**
 * Created on 2017/12/4
 * @author 巽
 *
 */
public class ClassificationDataRemoteObject extends UnicastRemoteObject implements ClassificationDataService{
	private static final long serialVersionUID = -9048255564192468097L;
	ClassificationDataService classificationDataService;

	protected ClassificationDataRemoteObject() throws RemoteException {
		super();
		classificationDataService = ClassificationDataServiceImpl.getInstance();
	}

	@Override
	public ArrayList<ClassificationPO> show() throws RemoteException {
		return classificationDataService.show();
	}

	@Override
	public ClassificationPO findByID(int ID) throws RemoteException {
		return classificationDataService.findByID(ID);
	}

	@Override
	public ResultMessage add(ClassificationPO po) throws RemoteException {
		return classificationDataService.add(po);
	}

	@Override
	public ResultMessage delete(ClassificationPO po) throws RemoteException {
		return classificationDataService.delete(po);
	}

	@Override
	public ResultMessage update(ClassificationPO po) throws RemoteException {
		return classificationDataService.update(po);
	}

	@Override
	public String getNewID() throws RemoteException {
		return classificationDataService.getNewID();
	}

	@Override
	public ArrayList<ClassificationPO> findFuzzilyByName(String keyword) throws RemoteException {
		return classificationDataService.findFuzzilyByName(keyword);
	}

	@Override
	public ArrayList<ClassificationPO> findFullyByName(String name) throws RemoteException {
		return classificationDataService.findFullyByName(name);
	}

}
