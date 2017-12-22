package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.initializationdataimpl.InitializationDataServiceImpl;
import dataservice.initializationdataservice.InitializationDataService;
import po.InitializationPO;
import util.ResultMessage;

public class InitializationDataRemoteObject  extends UnicastRemoteObject implements InitializationDataService{
	private static final long serialVersionUID = 222410765716232202L;
	InitializationDataService initializationDataService;
	
	protected InitializationDataRemoteObject() throws RemoteException{
		super();
		initializationDataService=InitializationDataServiceImpl.getInstance();
	}

	@Override
	public ResultMessage init(InitializationPO po) {
		return initializationDataService.init(po);
	}

	@Override
	public ArrayList<String> getAllInitDates() {
		return initializationDataService.getAllInitDates();
	}

	@Override
	public InitializationPO getInitializationByDate(String date) {
		return initializationDataService.getInitializationByDate(date);
	}
  
}
