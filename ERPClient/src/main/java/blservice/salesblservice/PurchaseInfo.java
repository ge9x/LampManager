package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import util.BillType;
import util.ResultMessage;
import vo.PurchaseVO;

public interface PurchaseInfo {
	/**
	 * 得到所有进货单时间
	 */
	public ArrayList<String> getAllPurchaseDate();
	/**
	 * 通过查看时间区间得到进货单VO
	 */
	public ArrayList<PurchaseVO> getPurchaseByDate(String startDate,String endDate);
	/**
	 * 通过查找单据类型得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByType(BillType type);
	/**
	 * 通过查找客户得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByCustomerID(String customerID);
	/**
	 * 通过查找业务员得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDBySalesman(String salesman);
	/**
	 * 通过查找仓库得到进货单ID
	 */
	public ArrayList<String> getPurchaseIDByInventory(String inventory);
	/**
	 * 审批进货单（进货单和进货退货单）
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage examine(PurchaseVO vo);
	/**
	 * 得到所有提交的进货单
	 * @return
	 */
	public ArrayList<PurchaseVO> getAllSubmittedPurchase();
	
	
}
