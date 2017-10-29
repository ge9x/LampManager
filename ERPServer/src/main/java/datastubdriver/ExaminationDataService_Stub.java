package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import util.ResultMessage;

public class ExaminationDataService_Stub implements ExaminationDataService{
	ArrayList<BillPO> history = new ArrayList<BillPO>();
	
	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<BillPO> target = new ArrayList<BillPO>();
		for(BillPO po :  history){
			if(!po.getDate().before(startDate)&&!po.getDate().after(endDate)){
				target.add(po);
			}
		}
		return target;
	}

	public ResultMessage add(BillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(BillPO i :  history){
			if(i.getID().equals(po.getID())){
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

}
