package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataimpl.promotiondataimpl.PromotionDataServiceImpl;
import dataimpl.salesdataimpl.SalesDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.GoodsPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionTotalPO;
import util.Level;
import util.PromotionType;

public class TestPromotion {
	public static void main(String[] args){
		PromotionDataService proImpl=PromotionDataServiceImpl.getInstance();
		SalesDataService salesImpl=SalesDataServiceImpl.getInstance();
		
		try{
			
			ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
			
			GoodsItemPO gi1=new GoodsItemPO("1", "霓虹灯",null, 20, 35.0,
					"耐用");
			GoodsItemPO gi2=new GoodsItemPO("2", "挂灯",null, 10, 35.0,
					"好看");
			{
				goodsItemList.add(gi1);
				goodsItemList.add(gi2);
			}
			
			salesImpl.addGoodsItem(gi1);
			salesImpl.addGoodsItem(gi2);
			
//			第一次请取消这段注释，成功运行一次后再注释掉
		
			
				proImpl.addPC(new PromotionCustomerPO("会员促销策略","2017-11-30","2017-11-30",PromotionType.MEMBER_PROMOTION_STRATEGY,300,500,goodsItemList,Level.LEVEL_FOUR,"PB-1"));
				proImpl.addPB(new PromotionBargainPO("特价包","2017-12-1","2017-12-1",PromotionType.BARGAIN_STRATEGY,900.0,500.0,goodsItemList,"PC-1"));
				proImpl.addPT(new PromotionTotalPO("总价促销策略","2017-12-1","2017-12-1",PromotionType.TOTAL_PROMOTION_STRATEGY,200.0,goodsItemList,300.0,"PT-1"));
			
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
			List<PromotionBargainPO> pbList=proImpl.showPB();
			for(int i=0;i<pbList.size();i++){
				System.out.println(pbList.get(i).getPromotionID()+" "+pbList.get(i).getBargainTotal());
			}
			System.out.println("查询结束！");
			} catch (RemoteException e) {
				System.out.println("Exception!");
				e.printStackTrace();
			}
	
	}
}
