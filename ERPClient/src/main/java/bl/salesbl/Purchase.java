package bl.salesbl;

import java.util.ArrayList;

import dataservice.salesdataservice.SalesDataService;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Purchase {
	private PurchaseVO vo;
	private SalesDataService salesDataService;
	
	public Purchase(){
		
	}
	
	/**
     * 创建进货单时得到进货单编号
     * 	
     * @return 进货单编号
     * @author zlk
     */
	  public String getnewPurchaseID(){
		  return null;
	  }
	  /**
	   * 创建进货退货单时得到进货退货单编号
	   * 
	   * @return 进货退货单编号
	   * @author zlk
	   */
	  public String getnewReturnID(){
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
	   * 创建一个进货单
	   * 
	   * @param vo
	   * @return 得到创建后的进货单
	   * @author zlk
	   */
	  public PurchaseVO addPurchase(PurchaseVO vo){
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
	   * 提交进货单
	   * 
	   * @param pur
	   * @return 进货单是否提交成功
	   * @author zlk
	   */
	  public ResultMessage submitPurchase(PurchaseVO pur){
		  return null;
	  }
	  
	  /**
	   * 保存销售单到草稿中
	   * 
	   * @param bill
	   * @return 销售单据是否成功保存到草稿中
	   * @author zlk
	   */
	  public ResultMessage savePurchase(PurchaseVO bill){
		  return null;
	  }
}
