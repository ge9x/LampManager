package datastubdriver;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import util.BillState;
import util.BillType;
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

	@Override
	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException {
		ArrayList<BillPO> target = new ArrayList<BillPO>();
		for(BillPO po :  history){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateTime = null;
			try {
				dateTime = dateFormat.parse(po.getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(!(((dateTime.compareTo(startDate))<0)||(dateTime.compareTo(endDate))>0)){
				target.add(po);
			}
		}
		return target;
	}

	@Override
	public ArrayList<BillPO> show() throws RemoteException {
		ArrayList<BillPO> billList=new ArrayList<BillPO>();
		BillPO bill=new BillPO("2017-12-2", BillType.CASH, BillState.SUBMITTED, 3);
		billList.add(bill);
		return billList;
	}

}
