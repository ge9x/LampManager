package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataimpl.inventorydataimpl.InventoryDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import dataservice.inventorydataservice.InventoryDataService;
import po.GoodsPO;
import po.InventoryBillPO;
import po.InventoryPO;

public class TestInventory {

	public static void main(String[] args) {
		GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
		InventoryDataService inventoryImpl = InventoryDataServiceImpl.getInstance();
		try {
			// 第一次使用请取消这段注释，成功运行一次后再注释掉
//			HashMap<GoodsPO, Integer> map = new HashMap<>();
//			map.put(goodsImpl.find(1), 4);
//			InventoryPO account = new InventoryPO("栖霞区仓库");
//			inventoryImpl.addInventroy(account);
//			account = inventoryImpl.showInventory().get(0);
//			InventoryBillPO overflowBill = new InventoryBillPO("2017-11-27", BillType.OVERFLOW, BillState.DRAFT, account, "Xun", map);
//			inventoryImpl.addBill(overflowBill);
//
//			map = new HashMap<>();
//			map.put(goodsImpl.find(1), 2);
//			account = new InventoryPO("鼓楼区仓库");
//			inventoryImpl.addInventroy(account);
//			account = inventoryImpl.showInventory().get(1);
//			InventoryBillPO lossBill = new InventoryBillPO("2017-11-27", BillType.LOSS, BillState.DRAFT, account, "Xun", map);
//			inventoryImpl.addBill(lossBill);
//
//			map = new HashMap<>();
//			map.put(goodsImpl.find(1), 1);
//			account = inventoryImpl.showInventory().get(0);
//			InventoryBillPO giftBill = new InventoryBillPO("2017-11-27", BillType.GIFT, BillState.DRAFT, account, "Xun", map);
//			inventoryImpl.addBill(giftBill);
//			
//			ArrayList<InventoryBillPO> allBills = inventoryImpl.show();
//			System.out.println("共有" + allBills.size() + "条库存类单据记录：");
//			for(InventoryBillPO po : allBills){
//				System.out.println(po.getID() + " " + po.getState().toString() + " " + po.getInventory() + " " + po.getGoodsMap());
//			}
//			ArrayList<InventoryBillPO> allAlarmBills = inventoryImpl.showAlarm();
//			System.out.println("共有" + allAlarmBills.size() + "条库存报警单记录：");
//			for(InventoryBillPO po : allAlarmBills){
//				System.out.println(po.getID() + " " + po.getState().toString() + " " + po.getInventory() + " " + po.getGoodsMap());
//			}
//			InventoryBillPO bill3 = inventoryImpl.findBill(3);
//			System.out.println("查询ID为3的库存类单据：");
//			System.out.println(bill3.getID() + " " + bill3.getDate() + " " + bill3.getType() + " " + bill3.getState());
			
//			ArrayList<GoodsPO> allGoods = goodsImpl.show();
//			ArrayList<InventoryPO> allInventory = inventoryImpl.showInventory();
//			for(InventoryPO po : allInventory){
//				po.getNumber().put(allGoods.get((int)(Math.random()*2)), (int)(Math.random()*1024));
//				inventoryImpl.updateInventory(po);
//			}
			
//			InventoryPO inpo = inventoryImpl.showInventory().get(0);
//			System.out.println(inpo.getID() + " " + inpo.getName() + " " + inpo.getNumber());
//			InventoryBillPO bill = inventoryImpl.findBill(2);
//			System.out.println(bill.getID() + " " + bill.getTurn() + " " + bill.getState() + " " + bill.getUser());
//			bill.setState(BillState.PASS);
//			inventoryImpl.updateBill(bill);
			
			ArrayList<GoodsPO> all = goodsImpl.show();
			System.out.println("共有" + all.size() + "条商品记录");
			for(GoodsPO po : all){
				System.out.println(po.getID() + " " + po.getName() + " " + po.countAmount());
				for(InventoryPO ipo : po.getNumber().keySet()){
					System.out.println('\t' + ipo.getName() + " " + po.getNumber().get(ipo));
				}
			}
			ArrayList<InventoryPO> allInventory = inventoryImpl.showInventory();
			System.out.println("共有" + allInventory.size() + "条仓库记录");
			for(InventoryPO po : allInventory){
				System.out.println(po.getID() + " " + po.getName());
				for(GoodsPO ipo : po.getNumber().keySet()){
					System.out.println('\t' + ipo.getName() + " " + po.getNumber().get(ipo));
				}
			}
			ArrayList<InventoryBillPO> allBill = inventoryImpl.show();
			System.out.println("共有" + allBill.size() + "条库存类单据记录：");
			for(InventoryBillPO po : allBill){
				System.out.println(po.buildID() + " " + po.getUser() + " " + po.getInventory().getName());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
