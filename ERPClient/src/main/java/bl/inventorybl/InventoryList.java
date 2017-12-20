package bl.inventorybl;

import java.util.ArrayList;
import java.util.HashMap;

import bl.salesbl.PurchaseController;
import bl.salesbl.SalesController;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import util.BillType;
import util.InventoryListItemType;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.InventoryViewItemVO;
import vo.InventoryViewVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * 库存变动情况的清单
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryList {
	private ArrayList<InventoryLineItem> inventoryLineItem;
	private SalesInfo salesInfo;
	private PurchaseInfo purchaseInfo;
	
	public InventoryList(){
		inventoryLineItem = new ArrayList<InventoryLineItem>();
	}
	
	public InventoryViewVO show(String startDate, String endDate, String inventory){
		// 初始化
		if(salesInfo == null){
			salesInfo = new SalesController();
		}
		if(purchaseInfo == null){
			purchaseInfo = new PurchaseController();
		}
		inventoryLineItem.clear();
		// 获取数据
		ArrayList<SalesVO> salesVOs = salesInfo.getSalesByDateAndInventory(startDate, endDate, inventory, BillType.SALES);
		ArrayList<SalesVO> salesReturnVOs = salesInfo.getSalesByDateAndInventory(startDate, endDate, inventory, BillType.SALESRETURN);
		ArrayList<PurchaseVO> purchaseVOs = purchaseInfo.getPurchaseByDateAndInventory(startDate, endDate, inventory, BillType.PURCHASE);
		ArrayList<PurchaseVO> purchaseReturnVOs = purchaseInfo.getPurchaseByDateAndInventory(startDate, endDate, inventory, BillType.RETURN);
		// 处理
		for(SalesVO vo : salesVOs){
			this.addAll(vo.goodsItemList, InventoryListItemType.SALES);
		}
		for(SalesVO vo : salesReturnVOs){
			this.addAll(vo.goodsItemList, InventoryListItemType.SALES);
		}
		for(PurchaseVO vo : purchaseVOs){
			this.addAll(vo.goodsItemList, InventoryListItemType.SALES);
		}
		for(PurchaseVO vo : purchaseReturnVOs){
			this.addAll(vo.goodsItemList, InventoryListItemType.SALES);
		}
		ArrayList<InventoryViewItemVO> item = new ArrayList<>();
		HashMap<GoodsVO, Double> total = new HashMap<>();
		InventoryViewVO ret = new InventoryViewVO(startDate, endDate, inventory, item, total);
		return ret;
	}
	
	private void addAll(ArrayList<GoodsItemVO> goodsItemList, InventoryListItemType inventoryListItemType){
		
	}
}
