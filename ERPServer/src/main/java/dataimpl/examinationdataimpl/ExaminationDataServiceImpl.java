package dataimpl.examinationdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import datahelper.DataHelper;
import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import util.ResultMessage;

public class ExaminationDataServiceImpl implements ExaminationDataService{
    private static ExaminationDataServiceImpl examinationDataServiceimpl;
    private DataHelper<BillPO> billDataHelper;
    
    public static ExaminationDataServiceImpl getInstance(){
    	if(examinationDataServiceimpl==null){
    		examinationDataServiceimpl = new ExaminationDataServiceImpl();
    	}
    	return examinationDataServiceimpl;
    }

	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException {
		return null;
	}

	public BillPO find(String ID) throws RemoteException {
		return billDataHelper.exactlyQuery("id", Integer.parseInt(ID));
	}

	public ResultMessage add(BillPO po) throws RemoteException {
		return billDataHelper.save(po);
	}

	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
