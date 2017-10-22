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
	String ID;

	public CustomerDataService_Stub(String iD) {
		super();
		ID = iD;
	}

	public ResultMessage add(CustomerPO po) throws RemoteException {
		if(po.customerName.equals("客户1")){
			System.out.println("Add customer success");
			return ResultMessage.SUCCESS;
		}else if(po.customerName.equals("客户2")){
			System.out.println("Customer exist");
			return ResultMessage.EXIST;
		}else{
			System.out.println("Add customer failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage delete(CustomerPO po) throws RemoteException {
		if(po.customerID.equals("00000001")){
			System.out.println("Delete customer success");
			return ResultMessage.SUCCESS;
		}else if(po.customerID.equals("00000002")){
			System.out.println("Cutomer not exist");
			return ResultMessage.NOT_EXIST;
		}else{
			System.out.println("Delete customer failed");
			return ResultMessage.FAILED;
		}
	}

	public ArrayList<CustomerPO> findByCustomerID(String customerID) throws RemoteException{
		if(ID.equals(customerID)){
			ArrayList<CustomerPO> findCustomerPO=new ArrayList<CustomerPO>();
			findCustomerPO.add(new CustomerPO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0));
			return findCustomerPO;
		}else{
		return new ArrayList<CustomerPO>();	
		}
	}

	public ArrayList<CustomerPO> findByKeywords(String keywords) throws RemoteException {
		if("客户1".equals(keywords)){
			ArrayList<CustomerPO> findCustomerPO=new ArrayList<CustomerPO>();
			findCustomerPO.add(new CustomerPO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0));
			return findCustomerPO;
		}else{
		return new ArrayList<CustomerPO>();	
		}
	}

	public ResultMessage update(CustomerPO po) throws RemoteException {
		if(po.customerID.equals("00000001")){
			System.out.println("Update customer success");
			return ResultMessage.SUCCESS;
		}else if(po.customerID.equals("00000002")){
			System.out.println("Customer not exist");
			return ResultMessage.NOT_EXIST;
		}
		else{
			System.out.println("Update customer failed");
			return ResultMessage.FAILED;
		}
	}

	public ArrayList<CustomerPO> show() throws RemoteException {
		ArrayList<CustomerPO> customerList=new ArrayList<CustomerPO>();
		customerList.add(new CustomerPO("00000012", CustomerCategory.PUR_AGENT, Level.LEVEL_FOUR, "撒哈拉", "13343579908",
			"南半球热带地区", "421099","566@163.com", 1.2, 40000, 30000,
			"业务员1", 222.0));
		return customerList;
	}

	public void init() throws RemoteException {
	      System.out.println("Init customers");
	}

	public CustomerPO getCustomerData() throws RemoteException {
		  CustomerPO newCustomerPO=new CustomerPO("00000012", CustomerCategory.PUR_AGENT, Level.LEVEL_FOUR, "撒哈拉", "13343579908",
					"南半球热带地区", "421099","566@163.com", 1.2, 40000, 30000,
					"业务员1", 222.0);
		  return newCustomerPO;
	}

}
