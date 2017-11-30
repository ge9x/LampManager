package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;

public class TestGoods {

	public static void main(String[] args) {
		GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
		
		// 第一次使用请取消这段注释，成功运行一次后再注释掉
	//	try {
	//		ClassificationDataService claImpl = ClassificationDataServiceImpl.getInstance();
	//		ClassificationPO father = claImpl.find(1);
	//		GoodsPO goods = new GoodsPO("圣洁牌奢华七彩霓虹灯", "XXL", father, 250, 25, 250, 2500, 250, 2500);
	//		goodsImpl.add(goods);
	//	} catch (RemoteException e) {
	//		e.printStackTrace();
	//	}
	//	end(session);
	//	
	//	
	//	session = setUp();
		try {
			ArrayList<GoodsPO> allGoods = goodsImpl.show();
			System.out.println("共有" + allGoods.size() + "条商品记录：");
			for(GoodsPO po : allGoods){
				System.out.println(po.getID() + " " + po.getName());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
