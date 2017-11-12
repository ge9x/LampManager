package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import po.CustomerPO;

public class CustomerDataService_Stub implements CustomerDataService{
	ArrayList<CustomerPO> customerData=new ArrayList<CustomerPO>();
		{
		CustomerPO c1=new CustomerPO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,400);
		CustomerPO c2=new CustomerPO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
					"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,500);
		CustomerPO c3=new CustomerPO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商2","15244358373",
				"南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,600);
		customerData.add(c1);
		customerData.add(c2);
		customerData.add(c3);
	}

	public ResultMessage add(CustomerPO po) throws RemoteException {
		for(CustomerPO cus:customerData){
			if(po.getAddress().equals(cus.getAddress())&&po.getCustomerName().equals(cus.getCustomerName())&&po.getPhone().equals(cus.getPhone())){
				System.out.println("Add customer failed");
				return ResultMessage.EXIST;
			}
		}
		customerData.add(po);
		System.out.println("Add customer success");
		return ResultMessage.SUCCESS;

	}

	public ResultMessage delete(CustomerPO po) throws RemoteException {
		for(CustomerPO cus:customerData){
			if(cus.getCustomerID().equals(po.getCustomerID())){
				customerData.remove(cus);
				System.out.println("Delete customer success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete customer failed");
		return ResultMessage.FAILED;
			}

	public ArrayList<CustomerPO> findByCustomerID(String customerID) throws RemoteException{
		ArrayList<CustomerPO> findList=new ArrayList<CustomerPO>();
		for(CustomerPO cus:customerData){
			if(cus.getCustomerID().contains(customerID)){
				findList.add(cus);
			}
		}
		System.out.println("Find customer success");
		return findList;
	}

	public ArrayList<CustomerPO> findByKeywords(String keywords) throws RemoteException {
		ArrayList<CustomerPO> findList=new ArrayList<CustomerPO>();
		for(CustomerPO cus:customerData){
			if(cus.getCustomerName().contains(keywords)){
				findList.add(cus);
			}
		}
		System.out.println("Find customer success");
		return findList;

	}

	public ResultMessage update(CustomerPO po) throws RemoteException {
		for(CustomerPO cus:customerData){
			if(cus.getCustomerID().equals(po.getCustomerID())){
				customerData.remove(cus);
				customerData.add(po);
				System.out.println("Update customer success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Update customer failed");
		return ResultMessage.FAILED;
	}

	public ArrayList<CustomerPO> show() throws RemoteException {
		return customerData;
		}
    
	public CustomerPO getCustomerData(String ID) throws RemoteException{
		for(CustomerPO cus:customerData){
			if(cus.getCustomerID().equals(ID)){
				System.out.println("Get customerData success");
				return cus;
			}
		}
		System.out.println("Get customerData failed");
		return null;
	}

	public void init() throws RemoteException {
		customerData.clear();
	    System.out.println("Init customerList success");
	}

}
