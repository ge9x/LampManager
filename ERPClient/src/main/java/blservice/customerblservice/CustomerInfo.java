package blservice.customerblservice;

import java.util.ArrayList;

import vo.CustomerVO;

/**
 * Created by zlk on 2017/11/5
 */

public interface CustomerInfo {
	/**
	 * 得到所有客户名称
	 * @return 所有客户名称
	 */
	public ArrayList<String> getAllCustomerName();
	/**
	 * 通过客户名字得到客户所有信息
	 * @return 该客户所有信息
	 */
	public CustomerVO getCustomerByName();
	/**
	 * 通过客户编号得到客户所有信息
	 * @return 该客户所有信息
	 */
	public CustomerVO getCustomerByID();
}
