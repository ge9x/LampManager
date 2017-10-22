package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import dataservice.examinationdataservice.ExaminationDataService;
import po.ExaminationPO;
import util.ResultMessage;

public class ExaminationDataService_Stub implements ExaminationDataService{
	ArrayList<ExaminationPO> history = new ArrayList<ExaminationPO>();
	
	public ArrayList<ExaminationPO> finds(Date startDate, Date endDate) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<ExaminationPO> target = new ArrayList<ExaminationPO>();
		for(ExaminationPO po :  history){
			if(!po.getExaminationDate().before(startDate)&&!po.getExaminationDate().after(endDate)){
				target.add(po);
			}
		}
		return target;
	}

	public ResultMessage add(ExaminationPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(ExaminationPO i :  history){
			if(i.getBill().getID().equals(po.getBill().getID())){
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
