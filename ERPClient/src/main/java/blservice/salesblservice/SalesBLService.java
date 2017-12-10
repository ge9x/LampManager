package blservice.salesblservice;

import java.util.ArrayList;

import util.BillState;
import util.Level;
import util.ResultMessage;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * created by zlk on 2017/10/21
 */

public interface SalesBLService {
	  /**
	   * 得到新的进货单编号
	   * @return
	   */
	  public String getnewPurchaseID();
      /**
       * 得到新的进货退货单编号
       * @return
       */
	  public String getnewReturnID();
	  /**
	   * 得到新的销售进货单编号
	   * @return
	   */
	  public String getnewSalesID();
	  /**
	   * 得到新的销售退货单编号
	   * @return
	   */
	  public String getnewSalesReturnID();
	  /**
	   * 得到合适的特价包促销策略
	   * @return
	   */
	  public ArrayList <PromotionBargainVO> showBargains();
	  /**
	   * 得到合适的会员促销策略
	   * 
	   * @return 会员促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionCustomerVO> getFitPromotionCustomer(Level level);
	  /**
	   * 得到合适的总价促销策略
	   * 
	   * @return 总价促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionTotalVO> getFitPromotionTotal(double total);
	  /**
	   * 创建一个进货单
	   * 
	   * @param vo
	   * @return 得到创建后的进货单
	   * @author zlk
	   */
	  public ResultMessage addPurchase(PurchaseVO vo);
	  /**
	   * 删除进货单据
	   * @param vo
	   * @return
	   */
	  public ResultMessage deletePurchase(PurchaseVO vo);
	  /**
	   * 添加商品清单信息
	   * 
	   * @param item
	   * @author zlk
	   */
	  public ResultMessage addGoodsItem(GoodsItemVO item);
	  /**
	   * 创建一个销售单
	   * 
	   * @param vo
	   * @return 得到创建后的销售单
	   * @author zlk
	   */
	  public ResultMessage addSales(SalesVO vo);
	  /**
	   * 删除销售单据
	   * @param vo
	   * @return
	   */
	  public ResultMessage deleteSales(SalesVO vo);
	  /**
	   * 提交进货单
	   * 
	   * @param pur
	   * @return 进货单是否提交成功
	   * @author zlk
	   */
	  public ResultMessage submitPurchase(PurchaseVO pur);
	  /**
	   * 提交销售单
	   * 
	   * @param sal
	   * @return 销售单是否提交成功
	   * @author zlk
	   */
	  public ResultMessage submitSales(SalesVO sal);
	  /**
	   * 保存进货单到草稿中
	   * 
	   * @param bill
	   * @return 进货单据是否成功保存到草稿中
	   * @author zlk
	   */
	  public ResultMessage saveSales(SalesVO bill);
	  /**
	   * 保存销售单到草稿中
	   * 
	   * @param bill
	   * @return 销售单据是否成功保存到草稿中
	   * @author zlk
	   */
	  public ResultMessage savePurchase(PurchaseVO bill);
	  /**
	   * 获得当前用户ID
	   * @return 当前用户ID
	   */
	  public String getUserName();
	  /**
	   * 获得所有供应商列表
	   * @return 供应商VO的列表
	   */
	  public ArrayList<CustomerVO> getAllSupplier();
	  /**
	   * 获得所有仓库列表
	   * @return 仓库VO的列表
	   */
	  public ArrayList<String> getAllInventory();
	  /**
	   * 获得所有客户列表
	   * @return 客户VO的列表
	   */
	  public ArrayList<CustomerVO> getAllCustomer();
	  /**
	   * 通过状态得到进货单据
	   * @param state
	   * @return
	   */
	  public ArrayList<PurchaseVO> getPurchaseOrderByState(BillState state);
	  /**
	   * 通过状态得到进货退货单
	   * @param state
	   * @return
	   */
	  public ArrayList<PurchaseVO> getReturnOrderByState(BillState state);
	  /**
	   * 通过状态得到销售出货单
	   * @param state
	   * @return
	   */
	  public ArrayList<SalesVO> getSalesOrderByState(BillState state);
	  /**
	   * 通过单据得到销售退货单
	   * @param state
	   * @return
	   */
	  public ArrayList<SalesVO> getSalesreturnOrderByState(BillState state);
}
