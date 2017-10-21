package blservice.salesblservice;

import java.util.ArrayList;

public interface SalesBLService {
	//销售界面得到商品信息
	//销售界面得到促销信息
    //销售的步骤
	  public String getnewPurchaseID();
	  public String getnewReturnID();
	  public String getnewSalesID();
	  public String getnewSalesReturnID();
	  public ArrayList <PromotionBargainVO> showBargains();
	  public ArrayList <PromotionCustomerVO> findFitPromotionCustomer();
	  public ArrayList <PromotionTotalVO> findFitPromotionTotal();
	  public purchaseVO addPurchase(PurchaseVO vo);
	  public void addGoods(GoodsVO item);
	  public SalesVO addSalesItem(SalesPO);
	  public SalesVO submitPurchase(PurchaseVO pur);
	  public SalesVO submitSales(SalesVO sal);
	  public SalesVO save(SalesVO bill);
}
