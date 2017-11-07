package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;

public class MockSalesLineItem extends SalesLineItem{
	ArrayList<SalesPO> salesBill=new ArrayList<SalesPO>();
	ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO( "挂灯", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		SalesPO s1=new SalesPO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
				"阿强", "默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date());
	    SalesPO s2=new SalesPO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
					"阿奇", "默认仓库",goodsItemList , 100,500, "满足客户需求", new Date());
	    salesBill.add(s1);
	    salesBill.add(s2);
	}
	
	public String getNewSalesID(){
		return "XSD-20171106-00001";
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
