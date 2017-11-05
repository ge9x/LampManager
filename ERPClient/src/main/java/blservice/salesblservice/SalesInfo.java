package blservice.salesblservice;

import java.sql.Date;
import java.util.ArrayList;

import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public interface SalesInfo {
	/**
	 * 得到所有进货单据
	 * @return 所有进货单据
	 */
	public ArrayList<SalesVO> getAllSales();
	/**
	 * 得到所有销售单据
	 * @return 所有销售单据
	 */
	public ArrayList<PurchaseVO> getAllPurchase();
	/**
	 * 得到所有进货单时间
	 * @return 所有进货单时间
	 */
	public ArrayList<Date> getPurchaseDate();
	/**
	 * 得到所有销售单时间
	 * @return 所有销售单时间
	 */
	public ArrayList<Date> getSalesDate();
}
