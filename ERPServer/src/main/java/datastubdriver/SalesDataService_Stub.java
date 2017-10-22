package datastubdriver;

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
	String billID;
	Date startDate;
	Date endDate;

	public PurchasePO findPurchase(String ID) {
		if(billID.equals(ID)){
			System.out.println("Find purchase");
			GoodsItemPO goodsItem=new GoodsItemPO("000022","霓虹灯","xxAd",50,30.0,
					1500.0,"包装好");
			PurchasePO pur=new PurchasePO(startDate,BillType.PURCHASE,BillState.DRAFT,ID,"金主爸爸"
					,"默认仓库",UserPosition.SALES_STAFF,goodsItem,"双击666"
					,10000.0,endDate);
			return pur;
		}else{
			System.out.println("Not found");
			return null;
		}
	}

	public SalesPO findSlaes(String ID) {
		if(billID.equals(ID)){
			System.out.println("Find sales");
			GoodsItemPO goodsItem=new GoodsItemPO("000022","霓虹灯","xxAd",50,30.0,
					1500.0,"包装好");
			SalesPO sal=new SalesPO(startDate, BillType.SALES, BillState.PASS, ID, "销售商A", "Lay",
					UserPosition.SALES_STAFF, "默认仓库", goodsItem, 6000.0,
					300.0, 200.0, 5500.0, "come on p,let's go p", endDate);
			return sal;
		}else{
			System.out.println("Not found");
			return null;
		}
	}

	public ResultMessage addPurchase(PurchasePO po) {
		if(po.ID.equals("JHD-yyyyMMdd-000002")){
			System.out.println("Add purchase success");
			return ResultMessage.SUCCESS;
		}else if(po.ID.equals("JHD-yyyyMMdd-000001")){
			System.out.println("purchase exist");
			return ResultMessage.EXIST;
		}else{
			System.out.println("Add purchase failes");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage addSales(SalesPO po) {
		if(po.ID.equals("XSD-yyyyMMdd-000002")){
			System.out.println("Add sales success");
			return ResultMessage.SUCCESS;
		}else if(po.ID.equals("XSD-yyyyMMdd-000001")){
			System.out.println("sales exist");
			return ResultMessage.EXIST;
		}else{
			System.out.println("Add sales failes");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage updatePurchase(PurchasePO po) {
		if(po.ID.equals("JHD-yyyyMMdd-000001")){
			System.out.println("Update purchase success");
			return ResultMessage.SUCCESS;
		}else if(po.ID.equals("JHD-yyyyMMdd-000002")){
			System.out.println("Purchase not exist");
			return ResultMessage.NOT_EXIST;
		}else{
			System.out.println("Update purchase failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage updateSales(SalesPO po) {
		if(po.ID.equals("XSD-yyyyMMdd-000001")){
			System.out.println("Update sales success");
			return ResultMessage.SUCCESS;
		}else if(po.ID.equals("XSD-yyyyMMdd-000002")){
			System.out.println("Sales not exist");
			return ResultMessage.NOT_EXIST;
		}else{
			System.out.println("Update sales failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage deletePurchase(PurchasePO po) {
		if(po.ID.equals("JHD-yyyyMMdd-000001")){
			System.out.println("Delete purchase success");
			return ResultMessage.SUCCESS;
		}else if(po.ID.equals("JHD-yyyyMMdd-000002")){
			System.out.println("Purchase not exist");
			return ResultMessage.NOT_EXIST;
		}else{
			System.out.println("Delete purchase failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage deleteSales(SalesPO po) {
		if(po.ID.equals("XSD-yyyyMMdd-000001")){
			System.out.println("Delete sales success");
			return ResultMessage.SUCCESS;
		}else if(po.ID.equals("XSD-yyyyMMdd-000002")){
			System.out.println("Sales not exist");
			return ResultMessage.NOT_EXIST;
		}else{
			System.out.println("Delte sales failed");
			return ResultMessage.FAILED;
		}
	}

	public void init() {
		System.out.println("Init sales and purchase");
	}
	
	public ArrayList<PurchasePO> showPurchase() {
		ArrayList<PurchasePO> purList=new ArrayList<PurchasePO>();
		GoodsItemPO goodsItem=new GoodsItemPO("000022","霓虹灯","xxAd",50,30.0,
			1500.0,"包装好");
		purList.add(new PurchasePO(startDate,BillType.PURCHASE,BillState.DRAFT,"JHD-yyyyMMdd-000001","金主爸爸"
					,"默认仓库",UserPosition.SALES_STAFF,goodsItem,"双击666"
					,10000.0,endDate));
		return purList;
	}

	public ArrayList<SalesPO> showSales() {
		ArrayList<SalesPO> salList=new ArrayList<SalesPO>();
		GoodsItemPO goodsItem=new GoodsItemPO("000022","霓虹灯","xxAd",50,30.0,
			1500.0,"包装好");
		salList.add(new SalesPO(startDate, BillType.SALES, BillState.PASS, billID, "销售商A", "Lay",
				UserPosition.SALES_STAFF, "默认仓库", goodsItem, 6000.0,
				300.0, 200.0, 5500.0, "come on p,let's go p", endDate));
		return salList;
	}
    
}
