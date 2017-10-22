package blstubdriver;

import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.CustomerAddVO;
import vo.CustomerVO;

public class CustomerBLService_Stub implements CustomerBLService{
	String ID;

	public CustomerBLService_Stub(String customerID) {
		super();
		this.ID = customerID;
	}

	public String getNewCustomerID() {
		return ID;
	}

	public ResultMessage addCustomer(CustomerAddVO vo) {
		if(vo.customerName.equals("客户1")){
			//System.out.println("Add customer success");
			return ResultMessage.SUCCESS;
		}
		else if(vo.customerName.equals("客户2")){
			//System.out.println("Customer exist");
			return ResultMessage.EXIST;
		}
	    else{
			//System.out.println("Add customer failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage deleteCustomer(String customerID) {
		if(customerID.equals("00000001")){
			System.out.println("Delete customer success");
			return ResultMessage.SUCCESS;
		}else if(customerID.equals("00000002")){
			System.out.println("Customer not exist");
			return ResultMessage.NOT_EXIST;
		}
		else{
			System.out.println("Delete customer failed");
			return ResultMessage.FAILED;
		}
	}

	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) {
		if("客户1".contains(keywords)){
			ArrayList<CustomerVO> findCustomerVO=new ArrayList<CustomerVO>();
			findCustomerVO.add(new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0));
			return findCustomerVO;
		}else{
		return new ArrayList<CustomerVO>();	
		}
	}

	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		if(ID.equals(customerID)){
			ArrayList<CustomerVO> findCustomerVO=new ArrayList<CustomerVO>();
			findCustomerVO.add(new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0));
			return findCustomerVO;
		}else{
		return new ArrayList<CustomerVO>();	
		}
	}

	public ResultMessage updateCustomer(CustomerAddVO vo) {
		if(vo.customerName.equals("客户1")){
			System.out.println("Update customer success");
			return ResultMessage.SUCCESS;
		}else if(vo.customerName.equals("客户2")){
			System.out.println("Customer not exist");
			return ResultMessage.NOT_EXIST;
		}
		else{
			System.out.println("Update customer failed");
			return ResultMessage.FAILED;
		}
	}
	
}
