package bl.customerbl;

import java.util.ArrayList;

import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.CustomerVO;

public class MockCustomer extends Customer{
	private CustomerVO exampleCustomerVO;
	
	public MockCustomer(){
		this.exampleCustomerVO=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主爸爸","15545786610",
				"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,600);
	}
	
	/**
	 * 得到新的客户编号
	 * @return 新的客户编号
	 */
	public String getNewCustomerID() {
		System.out.println("Get new customerID success");
	    return "00000002";	
	}
	
	/**
	 * 添加客户
	 * @return 添加客户是否成功
	 */
	public ResultMessage addCustomer(CustomerVO vo) {
	   if(!vo.customerID.equals("00000001")){	
		   return ResultMessage.SUCCESS;
	   }else{
		   System.out.println("Customer Exist");
		   return ResultMessage.FAILED;
	   }
	}
	
	/**
	 * 删除客户
	 * @return 是否成功删除客户
	 */
	public ResultMessage deleteCustomer(String customerID){
		if(customerID.equals("00000001")){
			return ResultMessage.SUCCESS;
		}else{
			return ResultMessage.NOT_EXIST;
		}
	}
	
	/**
	 * 通过关键字查找客户
	 * @return 含有关键字的客户
	 */
	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords){
		CustomerVO exampleCustomerVO1=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545788373",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,500);
		CustomerVO exampleCustomerVO2=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
				"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,600);
		CustomerVO exampleCustomerVO3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商2","15244356610",
				"南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);
		ArrayList<CustomerVO> customerList=new ArrayList<CustomerVO>();
		customerList.add(exampleCustomerVO1);
		customerList.add(exampleCustomerVO2);
		customerList.add(exampleCustomerVO3);
		if(keywords.equals("进货商")){
			customerList.add(exampleCustomerVO2);
			customerList.add(exampleCustomerVO3);
			return customerList;
		}else if(keywords.equals("8373")){
			customerList.add(exampleCustomerVO1);
			customerList.add(exampleCustomerVO2);
			return customerList;
		}else{
			return null;
		}
	}
	
	/**
	 * 通过客户编号查找客户
	 * @return 该客户编号的客户
	 */
	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID){
		ArrayList<CustomerVO> customerList=new ArrayList<CustomerVO>();
		if(exampleCustomerVO.customerID.equals(customerID)){
			customerList.add(exampleCustomerVO);
		}
		return customerList;
	}
	
	/**
	 * 修改客户信息
	 * @return 是否成功修改客户信息
	 */
	public ResultMessage updateCustomer(CustomerVO vo){
		if(exampleCustomerVO.customerID.equals("00000001")){
			return ResultMessage.SUCCESS;
		}else{
			return ResultMessage.FAILED;
		}
	}
}
