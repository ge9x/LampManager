package bl.salesbl;

import java.util.ArrayList;

import dataservice.salesdataservice.SalesDataService;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Sales {
	private SalesVO vo;
	private SalesDataService salesDataService;
	
	public Sales(){
		
	}
	
	
	  /**
	   * 创建销售单时得到销售单编号
	   * 
	   * @return 得到销售单编号
	   * @author zlk
	   */
	  public String getnewSalesID(){
		  return null;
	  }
	  /**
	   * 创建销售退货单时得到销售退货单编号
	   * 
	   * @return 得到销售退货单编号
	   * @author zlk
	   */
	  public String getnewSalesReturnID(){
		  return null;
	  }
	  /**
	   * 展示促销策略
	   * 
	   * @return 得到促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionBargainVO> showBargains(){
		  return null;
	  }
	  /**
	   * 得到合适的会员促销策略
	   * 
	   * @return 会员促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionCustomerVO> getFitPromotionCustomer(){
		  return null;
	  }
	  /**
	   * 得到合适的总价促销策略
	   * 
	   * @return 总价促销策略
	   * @author zlk
	   */
	  public ArrayList <PromotionTotalVO> getFitPromotionTotal(){
		  return null;
	  }
	 
	  /**
	   * 添加商品清单信息
	   * 
	   * @param item
	   * @author zlk
	   */
	  public void addGoodsItem(GoodsItemVO item){
		  
	  }
	  /**
	   * 创建一个销售单
	   * 
	   * @param vo
	   * @return 得到创建后的销售单
	   * @author zlk
	   */
	  public SalesVO addSales(SalesVO vo){
		  return null;
	  }
	  
	  /**
	   * 提交销售单
	   * 
	   * @param sal
	   * @return 销售单是否提交成功
	   * @author zlk
	   */
	  public ResultMessage submitSales(SalesVO sal){
		  return null;
	  }
	  /**
	   * 保存进货单到草稿中
	   * 
	   * @param bill
	   * @return 进货单据是否成功保存到草稿中
	   * @author zlk
	   */
	  public ResultMessage saveSales(SalesVO bill){
		  return null;
	  }
	 
}
