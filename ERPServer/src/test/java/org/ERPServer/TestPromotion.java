package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataimpl.promotiondataimpl.PromotionDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import dataservice.promotiondataservice.PromotionDataService;
import po.GoodsPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionTotalPO;
import util.Level;
import util.PromotionType;

public class TestPromotion {
	public static void main(String[] args){
		PromotionDataService proImpl=PromotionDataServiceImpl.getInstance();
		GoodsDataService goodsImpl=GoodsDataServiceImpl.getInstance();
		
		try{
			
			List<GoodsPO> goodsList=new ArrayList<GoodsPO>();
			GoodsPO g1 = new GoodsPO( "圣洁牌纯黑落地灯", "L", null, 700, 250, 233.3, 250, 233.3);
			GoodsPO g2 = new GoodsPO( "圣洁牌纯白落地灯", "M", null, 700, 250, 233.3, 250, 233.3);
			goodsList.add(g1);
			goodsList.add(g2);
			
//			第一次请取消这段注释，成功运行一次后再注释掉
			/**
			goodsImpl.add(g1);
			goodsImpl.add(g2);
			
				proImpl.addPC(new PromotionCustomerPO("2017-11-30","2017-11-30",PromotionType.MEMBER_PROMOTION_STRATEGY,500,goodsList,Level.LEVEL_FOUR));
				proImpl.addPB(new PromotionBargainPO("2017-12-1","2017-12-1",PromotionType.BARGAIN_STRATEGY,900.0,500.0,goodsList));
				proImpl.addPT(new PromotionTotalPO("2017-12-1","2017-12-1",PromotionType.TOTAL_PROMOTION_STRATEGY,200.0,goodsList,300.0));
				*/
			PromotionCustomerPO PC=proImpl.findPC("PC-1");	
			System.out.println(PC.getStartDate()+" "+PC.getAllowance()+" "+PC.getLevel());
			System.out.println("查询结束！");
			/**
			proImpl.deletePC(PC);
			System.out.println("查询结束");
			
			PromotionBargainPO PB=proImpl.findPB("PB-1");
			PB.setEndDate("2017-12-3");
			proImpl.updatePB(PB);
			System.out.println("查询结束！");
			*/
			} catch (RemoteException e) {
				System.out.println("Exception!");
				e.printStackTrace();
			}
	
	}
}
