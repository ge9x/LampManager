package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.TableStringConverter;

import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public interface SalesInfo {
	/**
	 * 得到所有销售出货单
	 * @return
	 */
	public ArrayList<SalesVO> getAllSalesOrder(String startDate,String endDate);
	/**
	 * 审批销售单（销售出货单和销售退货单）
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage examine(SalesVO vo);
	/**
	 * 得到所有提交的销售单
	 * @return
	 */
	public ArrayList<SalesVO> getAllSubmittedSales();
}
