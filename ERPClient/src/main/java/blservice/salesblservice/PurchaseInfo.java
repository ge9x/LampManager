package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.InventoryBillVO;
import vo.PurchaseVO;

public interface PurchaseInfo {
	public ArrayList<PurchaseVO> getPurchaseByDate(String startDate,String endDate);
	/**
	 * 审批进货单（进货单和进货退货单），如果通过，更改库存数据和客户的应收应付数据
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
	/**
	 * 生成红冲单据（原本数量取负以抵消原单据影响）
	 * 
	 * @param vo 单据VO
	 * @return 是否成功添加红冲单据
	 */
	public ResultMessage redCover(PurchaseVO vo);
	/**
	 * 生成红冲单据和复制单据（原本数量取负以抵消原单据影响，再新生成一张草稿单据）
	 * 
	 * @param vo 单据VO
	 * @return 是否成功添加红冲单据和草稿单据
	 */
	public ResultMessage redCoverAndCopy(PurchaseVO vo);
}
