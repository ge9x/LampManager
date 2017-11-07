package bl.salesbl;

import java.util.ArrayList;
import java.util.Date;

import po.GoodsItemPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

public class MockPurchase extends Purchase{
	ArrayList<PurchasePO> purchaseBill=new ArrayList<PurchasePO>();
	ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO( "霓虹灯", 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO( "挂灯", 10, 35.0,
			"好看");
	
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
		PurchasePO p1=new PurchasePO(BillType.PURCHASE,BillState.PASS,"JHD-20171022-00001","供应商1"
			,"默认仓库","阿红",goodsItemList,"满足客户需求"
		     ,new Date());
		PurchasePO p2=new PurchasePO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
				,"默认仓库","阿明",goodsItemList,"好看"
				,new Date());
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	public PurchasePO findPurchaseByID(String ID) {
		for(PurchasePO pur:purchaseBill){
			if(pur.getID().equals(ID)){
				System.out.println("Find purchase success");
				return pur;
			}
		}
		System.out.println("Find purchase failed");
		return null;
	}
	
	public PurchasePO findPurchaseByState(BillState state) {
		for(PurchasePO pur:purchaseBill){
			if(pur.getState().equals(state)){
				System.out.println("Find purchase success");
				return pur;
			}
		}
		System.out.println("Find purchase failed");
		return null;
	}
	
	public ResultMessage addPurchase(PurchasePO po) {
		for(PurchasePO pur:purchaseBill){
			if(pur.getID().equals(po.getID())){
				System.out.println("Add purchase failed");
				return ResultMessage.FAILED;
			}
		}
		System.out.println("Add purchase success");
		purchaseBill.add(po);
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage updatePurchase(PurchasePO po) {
		for(PurchasePO pur:purchaseBill){
			if(pur.getID().equals(po.getID())){
				System.out.println("Update purchase success");
				purchaseBill.remove(pur);
				purchaseBill.add(po);
				return ResultMessage.SUCCESS;
	
			}
		}
		System.out.println("Update purchase failed");
		return ResultMessage.FAILED;
	}
		
	public ResultMessage deletePurchase(String ID) {
		for(PurchasePO pur:purchaseBill){
			if(pur.getID().equals(ID)){
				purchaseBill.remove(pur);
				System.out.println("Delete purchase success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete Purchase failed");
		return ResultMessage.FAILED;
	}
	
	public void init() {
		purchaseBill.clear();
		System.out.println("Init sales and purchase success");
	}
}
