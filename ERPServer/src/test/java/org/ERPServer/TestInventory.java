package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataimpl.inventorydataimpl.InventoryDataServiceImpl;
import dataservice.goodsdataservice.GoodsDataService;
import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryBillPO;

public class TestInventory {

	public static void main(String[] args) {
		InventoryDataService inventoryImpl = InventoryDataServiceImpl.getInstance();
		GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
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
			ArrayList<InventoryBillPO> allAlarmBills = inventoryImpl.showAlarm();
			System.out.println("共有" + allAlarmBills.size() + "条库存报警单记录：");
			for(InventoryBillPO po : allAlarmBills){
				System.out.println(po.getID() + " " + po.getState().toString() + " " + po.getInventory() + " " + po.getGoodsMap());
			}
			InventoryBillPO bill3 = inventoryImpl.findBill(3);
			System.out.println("查询ID为3的库存类单据：");
			System.out.println(bill3.getID() + " " + bill3.getDate() + " " + bill3.getType() + " " + bill3.getState());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
