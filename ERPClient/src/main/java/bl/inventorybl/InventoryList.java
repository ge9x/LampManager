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
import vo.InventoryBillVO;
import vo.InventoryViewItemVO;
import vo.InventoryViewVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * 库存变动情况的清单 Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class InventoryList {
	private ArrayList<InventoryLineItem> inventoryLineItems;
	private SalesInfo salesInfo;
	private PurchaseInfo purchaseInfo;

	public InventoryList() {
		inventoryLineItems = new ArrayList<InventoryLineItem>();
	}

	public InventoryViewVO show(String startDate, String endDate, String inventory,
			ArrayList<InventoryBillVO> inventoryBillVOs) {
		// 初始化
		if (salesInfo == null) {
			salesInfo = new SalesController();
		}
		if (purchaseInfo == null) {
			purchaseInfo = new PurchaseController();
		}
		inventoryLineItems.clear();
		// 获取数据
		ArrayList<SalesVO> salesVOs = salesInfo.getSalesByDateAndInventory(startDate, endDate, inventory,
				BillType.SALES);
		ArrayList<SalesVO> salesReturnVOs = salesInfo.getSalesByDateAndInventory(startDate, endDate, inventory,
				BillType.SALESRETURN);
		ArrayList<PurchaseVO> purchaseVOs = purchaseInfo.getPurchaseByDateAndInventory(startDate, endDate, inventory,
				BillType.PURCHASE);
		ArrayList<PurchaseVO> purchaseReturnVOs = purchaseInfo.getPurchaseByDateAndInventory(startDate, endDate,
				inventory, BillType.RETURN);
		// 处理
		for (SalesVO vo : salesVOs) {
			this.addAll(vo.date, vo.goodsItemList, InventoryListItemType.SALES);
		}
		for (SalesVO vo : salesReturnVOs) {
			this.addAll(vo.date, vo.goodsItemList, InventoryListItemType.IN);
		}
		for (PurchaseVO vo : purchaseVOs) {
			this.addAll(vo.date, vo.goodsItemList, InventoryListItemType.PURCHASE);
		}
		for (PurchaseVO vo : purchaseReturnVOs) {
			this.addAll(vo.date, vo.goodsItemList, InventoryListItemType.OUT);
		}
		for (InventoryBillVO vo : inventoryBillVOs) {
			InventoryListItemType type = null;
			switch (vo.type) {
			case OVERFLOW:
				type = InventoryListItemType.IN;
				break;
			case LOSS:
				type = InventoryListItemType.OUT;
				break;
			default:
				System.out.println("InventoryList.show:不应出现的单据类型：" + vo.type.getValue());
				break;
			}
			this.addAll(vo.date, vo.goodsMap, type);
		}
		HashMap<GoodsVO, Double> total = new HashMap<>();
		ArrayList<InventoryViewItemVO> viewItemVOs = new ArrayList<>();
		for (InventoryLineItem lineItem : inventoryLineItems) {
			GoodsVO goodsVO = new GoodsVO(lineItem.goodsID);
			goodsVO.name = lineItem.goodsName;
			goodsVO.model = goodsVO.model;
			InventoryViewItemVO viewItemVO = new InventoryViewItemVO(lineItem.date, goodsVO,
					lineItem.inventoryListItemType, lineItem.numberDifference, lineItem.totalPrice);
			viewItemVOs.add(viewItemVO);
			if (total.containsKey(goodsVO)) {
				total.put(goodsVO, total.get(goodsVO) + lineItem.totalPrice);
			}
			else {
				total.put(goodsVO, lineItem.totalPrice);
			}
		}
		InventoryViewVO ret = new InventoryViewVO(startDate, endDate, inventory, viewItemVOs, total);
		return ret;
	}

	private void addAll(String date, ArrayList<GoodsItemVO> goodsItemList,
			InventoryListItemType inventoryListItemType) {
		for (GoodsItemVO itemVO : goodsItemList) {
			InventoryLineItem item = new InventoryLineItem(date, itemVO.ID, itemVO.goodsName, itemVO.model,
					inventoryListItemType, itemVO.number, itemVO.sum);
			inventoryLineItems.add(item);
		}
	}

	private void addAll(String date, HashMap<GoodsVO, Integer> goodsMap, InventoryListItemType inventoryListItemType) {
		for (GoodsVO goodsVO : goodsMap.keySet()) {
			InventoryLineItem item = new InventoryLineItem(date, goodsVO.ID, goodsVO.name, goodsVO.model,
					inventoryListItemType, goodsMap.get(goodsVO), goodsVO.buyingPrice * goodsMap.get(goodsVO));
			inventoryLineItems.add(item);
		}
	}
}
