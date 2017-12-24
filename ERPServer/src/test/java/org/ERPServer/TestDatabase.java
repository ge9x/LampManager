package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataimpl.inventorydataimpl.InventoryDataServiceImpl;
import dataservice.classificationdataservice.ClassificationDataService;
import dataservice.goodsdataservice.GoodsDataService;
import dataservice.inventorydataservice.InventoryDataService;
import po.ClassificationPO;
import po.GoodsPO;
import po.InventoryBillPO;

public class TestDatabase {
	SessionFactory sf;
	
	public TestDatabase(){
		sf = new Configuration().configure().buildSessionFactory();
	}
	
//	private Session setUp(){
//		Session s = sf.openSession();
//		s.beginTransaction();
//		return s;
//	}
//	
//	private void end(Session s){
//		s.getTransaction().commit();
//		s.close();
//	}
	
	public void testClassification(){
//		Session s = setUp();
		ClassificationDataService claImpl = ClassificationDataServiceImpl.getInstance();
		
		
		try {
//			第一次使用请取消这段注释，成功运行一次后再注释掉
//			ClassificationPO clapo = new ClassificationPO();
//			clapo.setName("灯");
//			GoodsPO gpo = new GoodsPO("圣洁牌经典黑白款落地灯", "L", null, 500, 50, 233.3, 250, 233.3, 250);
//			ArrayList<GoodsPO> goods = new ArrayList<GoodsPO>();
//			goods.add(gpo);
//			clapo.setGoods(goods);
//			s.save(clapo);
//			s.save(gpo);
//			ClassificationPO father = claImpl.find(1);
//			claImpl.add(new ClassificationPO("落地灯", father, null, null));
//			ClassificationPO father = claImpl.find(2);
//			claImpl.add(new ClassificationPO("可折叠落地灯", father, null, null));
			
			ArrayList<ClassificationPO> claPOs = claImpl.show();
			System.out.println("共有" + claPOs.size() + "条商品分类记录：");
			for(ClassificationPO po : claPOs){
				System.out.println(po.getID() + " " + po.getName() + " " + po.getGoods());
			}
			System.out.println("查询结束");
		} catch (RemoteException e) {
			System.out.println("Exception！");
			e.printStackTrace();
		}
//		end(s);
	}
	
	public void testGoods(){
		GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
		
		// 第一次使用请取消这段注释，成功运行一次后再注释掉
//		try {
//			ClassificationDataService claImpl = ClassificationDataServiceImpl.getInstance();
//			ClassificationPO father = claImpl.find(1);
//			GoodsPO goods = new GoodsPO("圣洁牌奢华七彩霓虹灯", "XXL", father, 250, 25, 250, 2500, 250, 2500);
//			goodsImpl.add(goods);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		end(session);
//		
//		
//		session = setUp();
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
	
	public void testInventory(){
		InventoryDataService inventoryImpl = InventoryDataServiceImpl.getInstance();
//		GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
		try {
			// 第一次使用请取消这段注释，成功运行一次后再注释掉
//			HashMap<GoodsPO, Integer> map = new HashMap<>();
//			map.put(goodsImpl.find(1), 4);
//			InventoryPO inventory = new InventoryPO("栖霞区仓库");
//			inventoryImpl.addInventroy(inventory);
//			inventory = inventoryImpl.showInventory().get(0);
//			InventoryBillPO overflowBill = new InventoryBillPO("2017-11-27", BillType.OVERFLOW, BillState.DRAFT, inventory, "Xun", map);
//			inventoryImpl.addBill(overflowBill);
//
//			map = new HashMap<>();
//			map.put(goodsImpl.find(1), 2);
//			inventory = new InventoryPO("鼓楼区仓库");
//			inventoryImpl.addInventroy(inventory);
//			inventory = inventoryImpl.showInventory().get(1);
//			InventoryBillPO lossBill = new InventoryBillPO("2017-11-27", BillType.LOSS, BillState.DRAFT, inventory, "Xun", map);
//			inventoryImpl.addBill(lossBill);
//
//			map = new HashMap<>();
//			map.put(goodsImpl.find(1), 1);
//			inventory = inventoryImpl.showInventory().get(0);
//			InventoryBillPO giftBill = new InventoryBillPO("2017-11-27", BillType.GIFT, BillState.DRAFT, inventory, "Xun", map);
//			inventoryImpl.addBill(giftBill);
			
			ArrayList<InventoryBillPO> allBills = inventoryImpl.show();
			System.out.println("共有" + allBills.size() + "条库存类单据记录：");
			for(InventoryBillPO po : allBills){
				System.out.println(po.getID() + " " + po.getState().toString() + " " + po.getInventory() + " " + po.getGoodsMap());
			}
			InventoryBillPO bill3 = inventoryImpl.findBill(3);
			System.out.println("查询ID为3的库存类单据：");
			System.out.println(bill3.getID() + " " + bill3.getDate() + " " + bill3.getType() + " " + bill3.getState());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestDatabase test = new TestDatabase();
		
		test.testClassification();
		test.testGoods();
		test.testInventory();
		
		test.sf.close();
	}

}
