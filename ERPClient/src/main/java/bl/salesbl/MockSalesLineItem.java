package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.SalesVO;

public class MockSalesLineItem extends SalesLineItem{
	ArrayList<SalesVO> salesBill=new ArrayList<SalesVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();
	
	GoodsItemVO gi1=new GoodsItemVO("01", "霓虹灯",null ,20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO("02", "挂灯",null, 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		SalesVO s1=new SalesVO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
				"阿强","00000003", "默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date());
	    SalesVO s2=new SalesVO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
					"阿奇","00000004" ,"默认仓库",goodsItemList , 100,500, "满足客户需求", new Date());
	    salesBill.add(s1);
	    salesBill.add(s2);
	}
	
	public String getNewSalesID(){
		return "XSD-20171106-00001";
	}
	
	public ArrayList <PromotionBargainVO> showBargains(){
		ArrayList<PromotionBargainVO> getBargains=new ArrayList<PromotionBargainVO>();
		getBargains.add(new PromotionBargainVO("特价包策略","00001", 5000.0, 500.0, new Date(), new Date(), new ArrayList<GoodsVO>()));
		return getBargains;
	}
		 
    public ArrayList <PromotionCustomerVO> getFitPromotionCustomer(){
    	ArrayList<PromotionCustomerVO> getCustomers=new ArrayList<PromotionCustomerVO>();
		getCustomers.add(new PromotionCustomerVO("会员促销策略","00002", new Date(), new Date(), 500.0, 300.0, new ArrayList<GoodsVO>(), util.Level.LEVEL_ONE));
		return getCustomers;
    }
		  
    public ArrayList <PromotionTotalVO> getFitPromotionTotal(){
    	ArrayList<PromotionTotalVO> getTotal=new ArrayList<PromotionTotalVO>();
		getTotal.add(new PromotionTotalVO("总价促销策略","00001", new Date(), new Date(), 455.0, new ArrayList<GoodsVO>(), 700.0));
		return getTotal;
	}
    
    public ResultMessage alterInventory(SalesVO vo){
    	for(SalesVO sal:salesBill){
			if(sal.ID.equals(vo.ID)){
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("not find bill");
		return ResultMessage.NOT_EXIST;
	}
	
	public ResultMessage alterCustomer(SalesVO vo){
		for(SalesVO sal:salesBill){
			if(sal.ID.equals(vo.ID)){
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("not find bill");
		return ResultMessage.NOT_EXIST;
	}
}
