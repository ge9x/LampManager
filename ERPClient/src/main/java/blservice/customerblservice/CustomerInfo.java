package blservice.customerblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zlk on 2017/11/5
 */

public interface CustomerInfo {
	/**
	 * 得到所有客户编号
	 * @return
	 */
	public ArrayList<Integer> getAllCustomerID();
	/**
	 * 得到所有客户名称
	 * @return 所有客户名称
	 */
	public ArrayList<String> getAllCustomerName();
	/**
	 * 通过客户编号得到客户所有信息
	 * @return 该客户所有信息
	 */
	public CustomerVO getCustomerByID(int ID);
	/**
	 * 得到所有的供应商ID
	 * @return
	 */
	public ArrayList<CustomerVO> getAllSupplier();
	/**
	 * 得到所有的销售商
	 * @return
	 */
	public ArrayList<CustomerVO> getAllSeller();
	/**
	 * 增加客户应收
	 * @param customerID
	 * @param amount
	 * @return
	 */
	public ResultMessage raiseCustomerReceive(int customerID,double amount);
	/**
	 * 减少客户应收
	 * @param customerID
	 * @param amount
	 * @return
	 */
	public ResultMessage reduceCustomerReceive(int customerID,double amount);
	/**
	 * 增加客户应付
	 * @param customerID
	 * @param amount
	 * @return
	 */
	public ResultMessage raiseCustomerPay(int customerID,double amount);
	/**
	 * 减少客户应付
	 * @param customerID
	 * @param amount
	 * @return
	 */
	public ResultMessage reduceCustomerPay(int customerID,double amount);

}
