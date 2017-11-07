package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;

public class MockPurchaseLineItem extends PurchaseLineItem{
	ArrayList<PurchasePO> purchaseBill=new ArrayList<PurchasePO>();
	ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO( "挂灯", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		PurchasePO p1=new PurchasePO(BillType.PURCHASE,BillState.PASS,"JHD-20171022-00001","供应商1"
			,"默认仓库","阿红",goodsItemList,"满足客户需求"
		     ,new Date());
		PurchasePO p2=new PurchasePO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
				,"默认仓库","阿明",goodsItemList,"好看"
				,new Date());
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	public String getNewPurchaseID(){
		return "JHD-20171106-00001";
	}
	
	public ArrayList <PromotionBargainVO> showBargains(){
		ArrayList<PromotionBargainVO> getBargains=new ArrayList<PromotionBargainVO>();
		getBargains.add(new PromotionBargainVO("00001", 5000.0, 500.0, new Date(), new Date(), new ArrayList<GoodsVO>()));
		return getBargains;
	}
		 
    public ArrayList <PromotionCustomerVO> getFitPromotionCustomer(){
    	ArrayList<PromotionCustomerVO> getCustomers=new ArrayList<PromotionCustomerVO>();
		getCustomers.add(new PromotionCustomerVO("00002", new Date(), new Date(), 500.0, 300.0, new ArrayList<GoodsVO>(), util.Level.LEVEL_ONE));
		return getCustomers;
    }
		  
    public ArrayList <PromotionTotalVO> getFitPromotionTotal(){
    	ArrayList<PromotionTotalVO> getTotal=new ArrayList<PromotionTotalVO>();
		getTotal.add(new PromotionTotalVO("00001", new Date(), new Date(), 455.0, new ArrayList<GoodsVO>(), 700.0));
		return getTotal;
	}
}
