package blstubdriver;

import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.CustomerAddVO;
import vo.CustomerVO;

public class CustomerBLService_Stub implements CustomerBLService{
	ArrayList<CustomerVO> customerData=new ArrayList<CustomerVO>();
		{
		CustomerVO c1=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0);
		CustomerVO c2=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
					"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0);
		CustomerVO c3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商2","15244358373",
				"南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0);
		customerData.add(c1);
		customerData.add(c2);
		customerData.add(c3);
	}
	
	public String getNewCustomerID() {
		int len=customerData.size();
		String ID=Integer.toString(len+1);
		int size=ID.length();
		for(int i=0;i<8-size;i++){
			ID="0"+ID;
		}
		return ID;
	}

	public ResultMessage addCustomer(CustomerVO vo) {
		for(CustomerVO cus:customerData){
			if(vo.address.equals(cus.address)&&vo.customerName.equals(cus.customerName)&&vo.phone.equals(cus.phone)){
				System.out.println("Add customer failed");
				return ResultMessage.EXIST;
			}
		}
		customerData.add(vo);
		System.out.println("Add customer success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deleteCustomer(String customerID) {
		for(CustomerVO cus:customerData){
			if(cus.customerID.equals(customerID)){
				customerData.remove(cus);
				System.out.println("Delete customer success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete customer failed");
		return ResultMessage.FAILED;
	}

	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) {
		ArrayList<CustomerVO> findList=new ArrayList<CustomerVO>();
		for(CustomerVO cus:customerData){
			if(cus.customerName.contains(keywords)){
				findList.add(cus);
			}
		}
		System.out.println("Find customer success");
		return findList;
	}

	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		ArrayList<CustomerVO> findList=new ArrayList<CustomerVO>();
		for(CustomerVO cus:customerData){
			if(cus.customerID.contains(customerID)){
				findList.add(cus);
			}
		}
		System.out.println("Find customer success");
		return findList;
	}

	public ResultMessage updateCustomer(CustomerVO vo) {
		for(CustomerVO cus:customerData){
			if(cus.customerID.equals(vo.customerID)){
				customerData.remove(cus);
				customerData.add(vo);
				System.out.println("Update customer success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Update customer failed");
		return ResultMessage.FAILED;
	}
	
}
