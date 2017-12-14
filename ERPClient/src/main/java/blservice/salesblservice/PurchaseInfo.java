package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PurchaseVO;

public interface PurchaseInfo {
	public ArrayList<PurchaseVO> getPurchaseByDate(String startDate,String endDate);
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
	/**
	 * 通过时间区间、仓库得到通过的进货单据的商品信息
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ArrayList<PurchaseVO> getPurchaseByDateAndInventory(String startDate,String endDate,String inventory,BillType type);
	
}
