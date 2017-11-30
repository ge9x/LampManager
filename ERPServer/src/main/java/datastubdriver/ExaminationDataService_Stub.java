package datastubdriver;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import util.ResultMessage;

public class ExaminationDataService_Stub implements ExaminationDataService{
	ArrayList<BillPO> history = new ArrayList<BillPO>();
	
	public ArrayList<BillPO> finds(String startDate, String endDate) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<BillPO> target = new ArrayList<BillPO>();
		for(BillPO po :  history){
			if(!LocalDate.parse(po.getDate()).isBefore(LocalDate.parse(startDate))&&!LocalDate.parse(po.getDate()).isAfter(LocalDate.parse(endDate))){
				target.add(po);
			}
		}
		return target;
	}

	public ResultMessage add(BillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(BillPO i :  history){
			if(i.getID() == po.getID()){
				System.out.println("The same bill information exist!");
				return ResultMessage.FAILED;
			}
		}
		history.add(po);
		System.out.println("Examination history saved!");
		return ResultMessage.SUCCESS;
	}

	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		history.clear();
		System.out.println("Initial success!");
	}

    public BillPO find(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		for(BillPO po :  history){
			if(po.getID() == ID){
				return po;
			}
		}
		return null;
	}

}
