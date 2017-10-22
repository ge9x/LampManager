package blstubdriver;

import java.util.ArrayList;
import java.util.Date;

import blservice.salesblservice.SalesBLService;
import util.PromotionType;
import util.ResultMessage;
import util.UserPosition;
import vo.GoodsItemVO;
import vo.GoodsVO;
import util.BillState;
import util.BillType;
import util.Level;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class SalesBLService_Stub implements SalesBLService{
	ArrayList<PurchaseVO> purchaseBill=new ArrayList<PurchaseVO>();
	ArrayList<SalesVO> salesBill=new ArrayList<SalesVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();

	GoodsItemVO gi1=new GoodsItemVO("000001", "霓虹灯", "大", 20, 35.0, 700,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO("000002", "挂灯", "xxdd", 10, 35.0, 350,
			"好看");
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
	}
	{
		PurchaseVO p1=new PurchaseVO(BillType.PURCHASE,BillState.SUBMITTED,"JHD-20171022-00001","供应商1"
				,"默认仓库",UserPosition.SALES_STAFF,gi1,"光照强"
				,700.0,new Date());
		PurchaseVO p2=new PurchaseVO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
				,"默认仓库",UserPosition.SALES_STAFF,gi2,"好看"
				,700.0,new Date());
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	{
		SalesVO s1=new SalesVO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
			UserPosition.SALES_STAFF, "默认仓库",gi1 , 5000, 100,
			500, 4400, "满足客户需求", new Date());
		SalesVO s2=new SalesVO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
				UserPosition.SALES_STAFF, "默认仓库",gi2 , 5000, 100,
				500, 4400, "满足客户需求", new Date());
		salesBill.add(s1);
		salesBill.add(s2);
	}

	public String getnewPurchaseID() {
		int num=0;
		for(PurchaseVO pur:purchaseBill){
			if(pur.type==BillType.PURCHASE){
				num++;
			}
		}
		String ID=Integer.toString(num);
		int len=ID.length();
		for(int i=0;i<5-len;i++){
			ID="0"+ID;
		}
		return "JHD-20171022-"+ID;
	}

	public String getnewReturnID() {
		int num=0;
		for(PurchaseVO pur:purchaseBill){
			if(pur.type==BillType.RETURN){
				num++;
			}
		}
		String ID=Integer.toString(num);
		int len=ID.length();
		for(int i=0;i<5-len;i++){
			ID="0"+ID;
		}
		return "JHTHD-20171022-"+ID;
	}

	public String getnewSalesID() {
		int num=0;
		for(PurchaseVO pur:purchaseBill){
			if(pur.type==BillType.SALES){
				num++;
			}
		}
		String ID=Integer.toString(num);
		int len=ID.length();
		for(int i=0;i<5-len;i++){
			ID="0"+ID;
		}
		return "XSD-20171022-"+ID;
	}

	public String getnewSalesReturnID() {
		int num=0;
		for(PurchaseVO pur:purchaseBill){
			if(pur.type==BillType.SALESRETURN){
				num++;
			}
		}
		String ID=Integer.toString(num);
		int len=ID.length();
		for(int i=0;i<5-len;i++){
			ID="0"+ID;
		}
		return "JHTHD-20171022-"+ID;
	}

	public ArrayList<PromotionBargainVO> showBargains() {
		ArrayList<PromotionBargainVO> getBargains=new ArrayList<PromotionBargainVO>();
		getBargains.add(new PromotionBargainVO("00001", 5000.0, 500.0, new Date(), new Date(), new ArrayList<GoodsVO>()));
		return getBargains;
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer() {
		ArrayList<PromotionCustomerVO> getCustomers=new ArrayList<PromotionCustomerVO>();
		getCustomers.add(new PromotionCustomerVO("00002", new Date(), new Date(), 500.0, 300.0, new ArrayList<GoodsVO>(), util.Level.LEVEL_ONE));
		return getCustomers;
	} 

	public ArrayList<PromotionTotalVO> getFitPromotionTotal() {
		ArrayList<PromotionTotalVO> getTotal=new ArrayList<PromotionTotalVO>();
		getTotal.add(new PromotionTotalVO("00001", new Date(), new Date(), 455.0, new ArrayList<GoodsVO>(), 700.0));
		return getTotal;
	}

	public PurchaseVO addPurchase(PurchaseVO vo) {
		purchaseBill.add(vo);
		System.out.println("add purchaseBill success");
		return vo;
	}

	public void addGoodsItem(GoodsItemVO item) {
		goodsItemList.add(item);
		System.out.println("add goodsItem success");
	}

	public SalesVO addSales(SalesVO vo) {
		salesBill.add(vo);
		System.out.println("add salesBill success");
		return vo;
	}

	public ResultMessage submitPurchase(PurchaseVO pur) {
		for(PurchaseVO vo:purchaseBill){
			if(vo.ID.equals(pur.ID)){
				vo.state=BillState.SUBMITTED;
				System.out.println("Submit purchase success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Submit purchase failes");
		return ResultMessage.FAILED;
	}

	public ResultMessage submitSales(SalesVO sal) {
		for(SalesVO vo:salesBill){
			if(vo.ID.equals(sal.ID)){
				vo.state=BillState.SUBMITTED;
				System.out.println("Submit sales success");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Submit sales failes");
		return ResultMessage.FAILED;
	}

	public ResultMessage saveSales(SalesVO bill) {
		for(SalesVO vo:salesBill){
			if(vo.ID.equals(bill.ID)){
				vo.state=BillState.DRAFT;
				System.out.println("Save sales success");
			}
		}
		System.out.println("Save sales failed");
		return ResultMessage.FAILED;
	}

	public ResultMessage savePurchase(PurchaseVO bill) {
		for(PurchaseVO vo:purchaseBill){
			if(vo.ID.equals(bill.ID)){
				vo.state=BillState.DRAFT;
				System.out.println("Save purchase success");
			}
		}
		System.out.println("Save purchase failed");
		return ResultMessage.FAILED;
	}
	
}
