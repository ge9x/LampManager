package dataimpl.examinationdataimpl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import util.BillState;
import util.Criterion;
import util.QueryMode;

public class ExaminationDataServiceImpl implements ExaminationDataService{
    private static ExaminationDataServiceImpl examinationDataServiceimpl;
    private DataHelper<BillPO> billDataHelper;
    
    public static ExaminationDataServiceImpl getInstance(){
    	if(examinationDataServiceimpl==null){
    		examinationDataServiceimpl = new ExaminationDataServiceImpl();
    	}
    	return examinationDataServiceimpl;
    }
    
    private ExaminationDataServiceImpl(){
    	this.billDataHelper=new HibernateDataHelper<BillPO>(BillPO.class);
    }

	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException{
		ArrayList<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(
				new Criterion(
					new Criterion("state",BillState.PASS,QueryMode.FULL),
					new Criterion("state",BillState.FAILED,QueryMode.FULL)
						)
				);
		ArrayList<BillPO> billList=billDataHelper.multiQuery(criteria);
		for(BillPO po:billList){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date dateTime= dateFormat.parse(po.getDate());
				if(((dateTime.compareTo(startDate))<0)||(dateTime.compareTo(endDate))>0){
					billList.remove(po);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return billList;
	}

	public ArrayList<BillPO> show() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(
				new Criterion("state",BillState.SUBMITTED,QueryMode.FULL)
				);
		return billDataHelper.multiQuery(criteria);
   }
}
