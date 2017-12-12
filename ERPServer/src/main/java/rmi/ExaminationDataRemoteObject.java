package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import dataimpl.examinationdataimpl.ExaminationDataServiceImpl;
import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;

public class ExaminationDataRemoteObject extends UnicastRemoteObject implements ExaminationDataService{

	private ExaminationDataService examinationDataService;
	
	protected ExaminationDataRemoteObject() throws RemoteException{
		examinationDataService = ExaminationDataServiceImpl.getInstance();
	}

	@Override
	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException {
		// TODO Auto-generated method stub
		return examinationDataService.finds(startDate, endDate);
	}

	@Override
	public ArrayList<BillPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return examinationDataService.show();
	}
}
