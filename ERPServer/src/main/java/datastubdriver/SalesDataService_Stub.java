package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import util.UserPosition;


public class SalesDataService_Stub implements SalesDataService{
	ArrayList<PurchasePO> purchaseBill=new ArrayList<PurchasePO>();
	ArrayList<SalesPO> salesBill=new ArrayList<SalesPO>();
	ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO(1, "霓虹灯",null, 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO(2, "挂灯",null, 10, 35.0,
			"好看");
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
	}
	{
		PurchasePO p1=new PurchasePO(BillType.PURCHASE,BillState.PASS,1,"供应商1"
				,1,"默认仓库","阿红",goodsItemList,"满足客户需求"
			     ,"2017-11-30");
		PurchasePO p2=new PurchasePO(BillType.RETURN,BillState.SUBMITTED,2,"供应商2"
					,2,"默认仓库","阿明",goodsItemList,"好看"
					,"2017-11-30");
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	{
		SalesPO s1=new SalesPO(BillType.SALES, BillState.DRAFT, 1, "销售商1", "业务员1",
				"阿强", "000003","默认仓库",goodsItemList , 100,500,  "满足客户需求", "2017-11-30");
	    SalesPO s2=new SalesPO(BillType.SALES, BillState.FAILED, 2, "销售商2", "业务员2",
					"阿奇","000004", "默认仓库",goodsItemList , 100,500, "满足客户需求", "2017-11-30");
	    salesBill.add(s1);
	    salesBill.add(s2);
	}


	public PurchasePO findPurchaseByID(String ID) throws RemoteException {
		for(PurchasePO pur:purchaseBill){
			if(pur.getID().equals(ID)){
				System.out.println("Find purchase success");
				return pur;
			}
		}
		System.out.println("Find purchase failed");
		return null;
	}

	public PurchasePO findPurchaseByState(BillState state) throws RemoteException {
		for(PurchasePO pur:purchaseBill){
			if(pur.getState().equals(state)){
				System.out.println("Find purchase success");
				return pur;
			}
		}
		System.out.println("Find purchase failed");
		return null;
	}

	public SalesPO findSlaesByID(String ID) throws RemoteException {
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(ID)){
				System.out.println("Find sales success");
				return sal;
			}
		}
		System.out.println("Find sales failed");
		return null;
	}

	public SalesPO findSlaesByState(BillState state) throws RemoteException {
		for(SalesPO sal:salesBill){
			if(sal.getState().equals(state)){
				System.out.println("Find sales success");
				return sal;
			}
		}
		System.out.println("Find sales failed");
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

	public ResultMessage addSales(SalesPO po) {
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(po.getID())){
				System.out.println("Add sales failed");
				return ResultMessage.FAILED;
			}
		}
		System.out.println("Add sales success");
		salesBill.add(po);
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

	public ResultMessage updateSales(SalesPO po) {
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(po.getID())){
				System.out.println("Update sales success");
				salesBill.remove(sal);
				salesBill.add(po);
				return ResultMessage.SUCCESS;
	
			}
		}
		System.out.println("Update sales failed");
		return ResultMessage.FAILED;

	}

	public ResultMessage deletePurchase(String ID) throws RemoteException {
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

	public ResultMessage deleteSales(String ID) throws RemoteException {
		for(SalesPO sal:salesBill){
			if(sal.getID().equals(ID)){
				salesBill.remove(sal);
				System.out.println("Delete sales success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Delete sales failed");
		return ResultMessage.FAILED;
	}
    
	public void init() {
		purchaseBill.clear();
		salesBill.clear();
		System.out.println("Init sales and purchase success");
		}
	
	public ArrayList<PurchasePO> showPurchase() {
		return purchaseBill;
	}

	public ArrayList<SalesPO> showSales() {
		return salesBill;
	}

}
