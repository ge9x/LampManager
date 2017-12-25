package blstubdriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.poi.hssf.record.chart.AreaRecord;

import blservice.salesblservice.SalesBLService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.PromotionType;
import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.GoodsVO;
import util.BillState;
import util.BillType;
import util.CustomerCategory;
import util.Level;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;
import vo.UserVO;

public class SalesBLService_Stub implements SalesBLService{
	ArrayList<PurchaseVO> purchaseBill=new ArrayList<PurchaseVO>();
	ArrayList<SalesVO> salesBill=new ArrayList<SalesVO>();
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();

	GoodsItemVO gi1=new GoodsItemVO("01", "霓虹灯",null ,20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO("02", "挂灯",null, 10, 35.0,
			"好看");
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
	}
	{
		PurchaseVO p1=new PurchaseVO(BillType.PURCHASE,BillState.PASS,"JHD-20171022-00001","供应商1"
				,"00000001","默认仓库","阿红",goodsItemList,"满足客户需求"
			     ,LocalDate.now().toString());
		PurchaseVO p2=new PurchaseVO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
					,"00000002","默认仓库","阿明",goodsItemList,"好看"
					,LocalDate.now().toString());
		purchaseBill.add(p1);
		purchaseBill.add(p2);
	}
	
	{
		SalesVO s1=new SalesVO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
				"阿强","00000003", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
	    SalesVO s2=new SalesVO(BillType.SALES, BillState.FAILED, "XSTHD-20171022-00002", "销售商2", "业务员2",
					"阿奇", "000000004","默认仓库",goodsItemList , 100,500, "满足客户需求", LocalDate.now().toString(),"PT-2");
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
		getBargains.add(new PromotionBargainVO("特价包策略","00001", 5000.0, 500.0, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>()));
		return getBargains;
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level) {
		ArrayList<PromotionCustomerVO> getCustomers=new ArrayList<PromotionCustomerVO>();
		getCustomers.add(new PromotionCustomerVO("会员促销策略","00002", LocalDate.now().toString(), LocalDate.now().toString(), 500.0, 300.0, new ArrayList<GoodsItemVO>(), util.Level.LEVEL_ONE));
		return getCustomers;
	} 

	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total) {
		ArrayList<PromotionTotalVO> getTotal=new ArrayList<PromotionTotalVO>();
		getTotal.add(new PromotionTotalVO("总价促销策略","00001", LocalDate.now().toString(), LocalDate.now().toString(), 455.0, new ArrayList<GoodsItemVO>(), 700.0));
		return getTotal;
	}

	public ResultMessage addPurchase(PurchaseVO vo) {
		purchaseBill.add(vo);
		System.out.println("add purchaseBill success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage addGoodsItem(GoodsItemVO item) {
		goodsItemList.add(item);
		System.out.println("add goodsItem success");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage addSales(SalesVO vo) {
		salesBill.add(vo);
		System.out.println("add salesBill success");
		return ResultMessage.SUCCESS;
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

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		UserVO user = new UserVO("0033", "1234", "SalesStaff", UserPosition.SALES_STAFF, UserLimits.STAFF);
		return user.userID;
	}

	@Override
	public ArrayList<CustomerVO> getAllSupplier() {
		// TODO Auto-generated method stub
		ArrayList<CustomerVO> supplier = new ArrayList<>();
		CustomerVO c1=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
				"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,500);
		CustomerVO c2=new CustomerVO("00000005",CustomerCategory.SELLER,Level.LEVEL_FOUR,"金主2","15546674310",
			"南京仙林大学城","421000","ddk@163.com",1.0,15000.0,0.0,"业务员1",150.0,500);
		supplier.add(c1);
		supplier.add(c2);
		return supplier;
	}

	@Override
	public ArrayList<String> getAllInventory() {
		// TODO Auto-generated method stub
		ArrayList<String> inventory = new ArrayList<>();
		inventory.add("仓库1");
		inventory.add("仓库2");
		inventory.add("仓库3");
		return inventory;
	}

	@Override
	public ArrayList<CustomerVO> getAllCustomer() {
		// TODO Auto-generated method stub
		ArrayList<CustomerVO> customerData=new ArrayList<CustomerVO>();
		CustomerVO c1=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,500);
		CustomerVO c2=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_ONE,"进货商1","15247678373",
					"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,600);
		CustomerVO c3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_THREE,"进货商2","15244358373",
				"南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);
		CustomerVO c4=new CustomerVO("00000004",CustomerCategory.PUR_AGENT,Level.LEVEL_TWO,"进货商3","15244358397",
				"南京新街口","421001","12s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);
		CustomerVO c5=new CustomerVO("00000005",CustomerCategory.SELLER,Level.LEVEL_FOUR,"金主2","15546674310",
				"南京仙林大学城","421000","ddk@163.com",1.0,15000.0,0.0,"业务员1",150.0,500);
		ArrayList<Integer> cus=new ArrayList<>();
		customerData.add(c1);
		customerData.add(c2);
		customerData.add(c3);
		customerData.add(c4);
		customerData.add(c5);
		return customerData;
	}

	@Override
	public ArrayList<PurchaseVO> getPurchaseOrderByState(BillState state) {
		ArrayList<PurchaseVO> purList=new ArrayList<>();
		for(PurchaseVO pur:purchaseBill){
			if(pur.state==state&&pur.type==BillType.PURCHASE){
				purList.add(pur);
			}
		}
		return purList;
	}

	@Override
	public ArrayList<PurchaseVO> getReturnOrderByState(BillState state) {
		ArrayList<PurchaseVO> purList=new ArrayList<>();
		for(PurchaseVO pur:purchaseBill){
			if(pur.state==state&&pur.type==BillType.RETURN){
				purList.add(pur);
			}
		}
		return purList;
	}

	@Override
	public ArrayList<SalesVO> getSalesOrderByState(BillState state) {
		ArrayList<SalesVO> salList=new ArrayList<>();
		for(SalesVO sal:salesBill){
			if(sal.state==state&&sal.type==BillType.SALES){
				salList.add(sal);
			}
		}
		return salList;
	}

	@Override
	public ArrayList<SalesVO> getSalesreturnOrderByState(BillState state) {
		ArrayList<SalesVO> salList=new ArrayList<>();
		for(SalesVO sal:salesBill){
			if(sal.state==state&&sal.type==BillType.SALES){
				salList.add(sal);
			}
		}
		return salList;
	}

	@Override
	public ResultMessage deletePurchase(PurchaseVO vo) {
		purchaseBill.remove(vo);
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteSales(SalesVO vo) {
		salesBill.remove(vo);
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage updatePurchase(PurchaseVO vo) {
		return null;
	}

	@Override
	public ResultMessage updateSales(SalesVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public PromotionCustomerVO findPromotionCustomerByName(String name) {
		PromotionCustomerVO promotion1 = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		return promotion1;
	}

	@Override
	public PromotionBargainVO findPromotionBargainByName(String name) {
		PromotionBargainVO promotion1 = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), goodsItemList);
		return promotion1;
	}

	@Override
	public PromotionTotalVO findPromotionTotalByName(String name) {
		PromotionTotalVO promotion1 = new PromotionTotalVO("总价促销策略1","000005", LocalDate.now().toString(), LocalDate.now().toString(), 500, new ArrayList<GoodsItemVO>(), 10000);
		return promotion1;
	}

	@Override
	public UserLimits getCurrentUserLimits() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
