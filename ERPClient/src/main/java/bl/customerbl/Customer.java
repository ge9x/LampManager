package bl.customerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.customerdataservice.CustomerDataService;
import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Customer {
	private CustomerVO vo;
	private CustomerDataService customerDataService;
	
	public Customer(){
		
	}
	
	/**
	 * 创建客户时得到客户编号
	 * 
	 * @return customerID
	 * @author zlk
	 * 
	 */
	public String getNewCustomerID(){
		return null;
		
	}
      //管理客户的步骤
	/**
	 * 管理客户中的添加客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 */
	public ResultMessage addCustomer(CustomerVO vo) {
		return null;
		
	}
	/**
	 * 管理客户中的删除客户
	 * 
	 * @param name
	 * @return 处理信息
	 * @author zlk
	 */
	public ResultMessage deleteCustomer(String customerID){
		return null;
		
	}
	/**
	 * 通过关键字查找客户
	 * 
	 * @param keywords
	 * @return 满足条件的客户
	 * @author zlk
	 */
	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords){
		return null;
		
	}
	/**
	 * 通过客户编号查找客户
	 * 
	 * @param customerID
	 * @return 满足条件的客户
	 * @author zlk
	 */
	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		return null;
		
	}
	/**
	 * 管理客户中的更新客户
	 * 
	 * @param vo
	 * @return 处理信息
	 * @author zlk
	 */
	public ResultMessage updateCustomer(CustomerVO vo){
		return null;
	}
	
	public ArrayList<CustomerVO> show() {
		return null;
	}
}
