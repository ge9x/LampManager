package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import util.BillType;
import vo.GoodsItemVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public interface SalesInfo {
	/**
	 * 得到所有进货单时间
	 */
	public ArrayList<Date> getAllPurchaseDate();
	/**
	 * 得到所有销售单时间
	 */
	public ArrayList<Date> getAllSalesDate();
	/**
	 * 得到所有商品名
	 */
	public ArrayList<String> getAllGoodsName();
	/**
	 * 得到所有业务员
	 */
	public ArrayList<String> getSalesman();
	/**
	 * 得到所有仓库
	 */
	public ArrayList<String> getAllInventory();
	/**
	 * 得到所有客户ID
	 */
	public ArrayList<String> getAllCustomerID();
	//查看销售明细表
	/**
	 * 通过查看时间区间得到销售出货单ID
	 */
	public ArrayList<String> getSalesOrderIDByDate(Date startDate,Date endDate);
	/**
	 * 通过查找商品名得到销售出货单ID
	 */
	public ArrayList<String> getSalesOrderIDByGoodsName(String goodsName);
	/**
	 * 通过查找客户得到销售出货单ID
	 */
	public ArrayList<String> getSalesOrderIDByCustomerID(String customerID);
	/**
	 * 通过查找业务员得到销售出货单ID
	 */
	public ArrayList<String> getSalesOrderIDBySalesman(String salesman);
	/**
	 * 通过查找仓库得到销售出货单ID
	 */
	public ArrayList<String> getSalesOrderIDByInventory(String inventory);
	//查看经营历程表
	/**
	 * 通过查看时间区间得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByDate(Date startDate,Date endDate);
	/**
	 * 通过查看时间区间得到销售单ID
	 */
	public ArrayList<String> getSalesIDByDate(Date startDate,Date endDate);
	/**
	 * 通过查找单据类型得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByType(BillType type);
	/**
	 * 通过查找单据类型得到销售单ID
	 */
	public ArrayList<String> getSalesIDByType(BillType type);
	/**
	 * 通过查找客户得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByCustomerID(String customerID);
	/**
	 * 通过查找客户得到销售单ID
	 */
	public ArrayList<String> getSalesIDByCustomerID(String customerID);
	/**
	 * 通过查找业务员得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDBySalesman(String salesman);
	/**
	 * 通过查找业务员得到销售单ID
	 */
	public ArrayList<String> getSalesIDBySalesman(String salesman);
	/**
	 * 通过查找仓库得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByInventory(String inventory);
	/**
	 * 通过查找仓库得到销售单ID
	 */
	public ArrayList<String> getSalesIDByInventory(String invenory);
	//通过ID得到进货销售具体信息
	/**
	 * 通过ID得到时间
	 */
	public Date getDateByID(String ID);
	/**
	 * 通过ID得到商品名、型号、数量、单价
	 */
	public ArrayList<GoodsItemVO> getGoodsItemByID(String ID);
	/**
	 * 通过ID得到折让
	 */
	public double getAllowance(String ID);
	/**
	 * 通过ID得到总额（折让后总额）
	 */
	public double getSumByID(String ID);
}
