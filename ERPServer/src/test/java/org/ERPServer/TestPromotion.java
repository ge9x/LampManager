package org.ERPServer;

import java.util.ArrayList;
import java.util.List;

import dataimpl.promotiondataimpl.PromotionDataServiceImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.GoodsPO;
import util.Level;
import util.PromotionType;

public class TestPromotion {
	public static void main(String[] args){
		PromotionDataService proImpl=PromotionDataServiceImpl.getInstance();
		
//			第一次请取消这段注释，成功运行一次后再注释掉
			
			List<GoodsPO> goodsList=new ArrayList<GoodsPO>();
			GoodsPO g1 = new GoodsPO( "圣洁牌纯黑落地灯", "L", null, 700, 250, 233.3, 250, 233.3, 250);
			GoodsPO g2 = new GoodsPO( "圣洁牌纯白落地灯", "M", null, 700, 250, 233.3, 250, 233.3, 250);
			goodsList.add(g1);
			goodsList.add(g2);
			
			//proImpl.add(new PromotionCustomerPO("2017-11-30","2017-11-30",PromotionType.MEMBER_PROMOTION_STRATEGY,100,))
	
	}
}
