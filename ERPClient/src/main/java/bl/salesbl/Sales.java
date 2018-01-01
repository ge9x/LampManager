package bl.salesbl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

import bl.customerbl.CustomerBLFactory;
import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import bl.userbl.UserBLFactory;
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import rmi.SalesRemoteHelper;
import util.BillState;
import util.BillType;
import util.Level;
import util.ResultMessage;
import util.UserLimits;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Sales {
	private static SalesDataService salesDataService;
	
	SalesLineItem salesLineItem;
	GoodsItem goodsItem;
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	
	public Sales(){
		salesDataService=SalesRemoteHelper.getInstance().getSalesDataService();
		salesLineItem=new SalesLineItem();
		goodsItem=new GoodsItem();
		inventoryInfo=InventoryBLFactory.getInfo();
		customerInfo=CustomerBLFactory.getInfo();
	}
	
	
	public SalesVO findSlaesByID(String ID) {
		return null;
	}
	
	public SalesVO findSalesByState(BillState state) {
		return null;
	}
	
	public ResultMessage addSales(SalesVO vo) throws RemoteException{
		return salesDataService.addSales(voTopo(vo));
	}
	
	public ResultMessage updateSales(SalesVO vo) throws RemoteException{
		SalesPO po=salesDataService.findSlaesByID(vo.ID);
		po.setState(vo.state);
		po.setCustomer(vo.customer);
		po.setCustomerID(Integer.parseInt(vo.customerID));
		po.setSalesman(vo.salesman);
		po.setUser(vo.user);
		po.setInventory(vo.inventory);
		po.getGoodsItemList().clear();
		ArrayList<GoodsItemVO> goodsItemvoList=vo.goodsItemList;
		for(GoodsItemVO goodItemvo:goodsItemvoList){
			GoodsItemPO goodsItemPO=GoodsItem.voTopo(goodItemvo);
			po.getGoodsItemList().add(goodsItemPO);
		}
		po.setAllowance(vo.allowance);
		po.setVoucher(vo.voucher);
		po.setRemarks(vo.remarks);
		po.setPromotionName(vo.promotionName);
		return salesDataService.updateSales(po);
	}
	
	public ResultMessage deleteSales(String ID) {
		return null;
	}
	
	public ArrayList<SalesVO> getAllSalesOrder(String startDate,String endDate) throws ParseException {
		try {
			ArrayList<SalesPO> salpoList=salesDataService.showSales();
			ArrayList<SalesVO> salvoList=new ArrayList<>();
			for(SalesPO po:salpoList){
				Date date=stringToDate(po.getDate());
				if(date.compareTo(stringToDate(startDate))>=0&&date.compareTo(stringToDate(endDate))<=0&&po.getState()==BillState.PASS){
					salvoList.add(poTovo(po));
				}
			}
			return salvoList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SalesVO> getAllSalesReturnOrder(String startDate, String endDate) throws ParseException {
		try {
			ArrayList<SalesPO> salpoList=salesDataService.showSalesReturn();
			ArrayList<SalesVO> salvoList=new ArrayList<>();
			for(SalesPO po:salpoList){
				Date date=stringToDate(po.getDate());
				if(date.compareTo(stringToDate(startDate))>=0&&date.compareTo(stringToDate(endDate))<=0&&po.getState()==BillState.PASS){
					salvoList.add(poTovo(po));
				}
			}
			return salvoList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void init(){
		
	}
	
	public ResultMessage examine(SalesVO vo) throws RemoteException {
		if(vo.state==BillState.PASS){
		if(vo.type==BillType.SALES){
			inventoryInfo.reduceInventory(vo.goodsItemList, vo.inventory);
			customerInfo.raiseCustomerReceive(Integer.parseInt(vo.customerID), vo.afterSum);
		}else{
			inventoryInfo.raiseInventory(vo.goodsItemList, vo.inventory);
			customerInfo.raiseCustomerPay(Integer.parseInt(vo.customerID), vo.afterSum);
		}
		}
		return updateSales(vo);
	}
	
	public ArrayList<SalesVO> getAllSubmittedSales() throws RemoteException {
		ArrayList<SalesPO> poList=salesDataService.findSlaesByState(BillState.SUBMITTED);
		ArrayList<SalesVO> voList=new ArrayList<>();
		for(SalesPO po:poList){
			voList.add(poTovo(po));
		}
		return voList;
	}
	
	public ArrayList<PromotionBargainVO> showBargains() {
		return salesLineItem.showBargains();
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level) {
		return salesLineItem.getFitPromotionCustomer(level);
	}

	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total) {
		return salesLineItem.getFitPromotionTotal(total);
	}
	
	public ResultMessage addGoodsItem(GoodsItemVO item) throws RemoteException {
		return salesDataService.addGoodsItem(GoodsItem.voTopo(item));
	}
	
	public ResultMessage submitSales(SalesVO vo) throws RemoteException{
		CustomerVO customerVO=customerInfo.getCustomerByID(Integer.parseInt(vo.customerID));
		if(vo.type==BillType.SALES&&((customerVO.receive+vo.afterSum)>customerVO.receivableLimit)){
			return ResultMessage.FAILED;
		}
		vo.state=BillState.SUBMITTED;
		return addSales(vo);
	}
	
	public ResultMessage saveSales(SalesVO bill) throws RemoteException {
		addSales(bill);
		SalesPO po=salesDataService.findSlaesByID(bill.ID);
		po.setState(BillState.DRAFT);
		return salesDataService.updateSales(po);
	}
	
	public String getUserName() {
		return salesLineItem.getUserName();
	}
	
	public ArrayList<CustomerVO> getAllSupplier() {
		return salesLineItem.getAllSupplier();
	}
	
	public ArrayList<CustomerVO> getAllCustomer() {
		return salesLineItem.getAllCustomer();
	}
	
	public String getnewSalesID() throws RemoteException {
		return salesDataService.getNewSalesID();
	}
	
	public String getnewSalesReturnID() throws RemoteException {
		return salesDataService.getNewSalesReturnID();
	}
	
	private static Date stringToDate(String date) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date time=dateFormat.parse(date);
		return time;
	}
	
	public ArrayList<SalesVO> getSalesOrderByState(BillState state) throws RemoteException {
		ArrayList<SalesVO> salvoList=new ArrayList<>();
		ArrayList<SalesPO> salrepoList=salesDataService.findSlaesByState(state);
		for(SalesPO po:salrepoList){
			if(po.getType()==BillType.SALES){
				salvoList.add(poTovo(po));
			}
		}
		return salvoList;
	}

	public ArrayList<SalesVO> getSalesreturnOrderByState(BillState state) throws RemoteException {
		ArrayList<SalesVO> salvoList=new ArrayList<>();
		ArrayList<SalesPO> salrepoList=salesDataService.findSlaesByState(state);
		for(SalesPO po:salrepoList){
			if(po.getType()==BillType.SALESRETURN){
				salvoList.add(poTovo(po));
			}
		}
		return salvoList;
	}
	
	public ResultMessage deleteSales(SalesVO vo) throws RemoteException {
		return salesDataService.deletePurchase(vo.ID);
	}
	
	public ArrayList<SalesVO> getSalesByDateAndInventory(String startDate, String endDate, String inventory,
			BillType type) throws ParseException, RemoteException {
		ArrayList<SalesPO> salList=new ArrayList<>();
		ArrayList<SalesVO> getList=new ArrayList<>();
		if(type==BillType.PURCHASE){
			salList=salesDataService.showSales();
		}else{
			salList=salesDataService.showSalesReturn();
		}
		for(SalesPO po:salList){
			Date date=stringToDate(po.getDate());
			if(date.compareTo(stringToDate(startDate))>=0&&date.compareTo(stringToDate(endDate))<=0&&po.getInventory().equals(inventory)&&po.getState()==BillState.PASS){
				getList.add(poTovo(po));
			}
		}
		return getList;

	}
	
	public ArrayList<String> getAllInventory() {
		return salesLineItem.getAllInventory();
	}
	
	public PromotionCustomerVO findPromotionCustomerByName(String name) {
		return salesLineItem.findPromotionCustomerByName(name);
	}
	
	public PromotionBargainVO findPromotionBargainByName(String name) {
		return salesLineItem.findPromotionBargainByName(name);
	}
	
	public PromotionTotalVO findPromotionTotalByName(String name) {
		return salesLineItem.findPromotionTotalByName(name);
	}
	

	public static SalesPO voTopo(SalesVO vo) throws NumberFormatException, RemoteException{
		ArrayList<GoodsItemVO> goodsItemvoList=vo.goodsItemList;
		List<GoodsItemPO> goodsItempoList=new ArrayList<>();
		for(GoodsItemVO goodsItemvo:goodsItemvoList){
			GoodsItemPO goodsItemPO=GoodsItem.voTopo(goodsItemvo);
			goodsItempoList.add(goodsItemPO);
		}
		String str[]=vo.ID.split("-");
		return new SalesPO(vo.type, vo.state, vo.customer, Integer.parseInt(vo.customerID), vo.salesman, vo.user, vo.inventory, goodsItempoList, vo.allowance, vo.voucher, vo.remarks, vo.date, Integer.parseInt(str[2]), vo.promotionName);
	}
	
	public static SalesVO poTovo(SalesPO po){
		List<GoodsItemPO> goodsItempoList=po.getGoodsItemList();
		ArrayList<GoodsItemVO> goodsItemvoList=new ArrayList<>();
		for(GoodsItemPO goodsItempo:goodsItempoList){
			goodsItemvoList.add(GoodsItem.poTovo(goodsItempo));
		}
		return new SalesVO(po.getType(), po.getState(), po.buildID(), po.getCustomer(), String.valueOf(po.getCustomerID()), po.getSalesman(), po.getUser(), po.getInventory(), goodsItemvoList, po.getAllowance(), po.getVoucher(), po.getRemarks(), po.getDate(), po.getPromotionName());
	}
	
	public UserLimits getCurrentUserLimits() {
		return salesLineItem.getCurrentUserLimits();
	}
	
	public ResultMessage redCover(SalesVO vo) throws RemoteException {
		ArrayList<GoodsItemVO> goodsitemList=vo.goodsItemList;
		for(GoodsItemVO goodsItemVO:goodsitemList){
			goodsItemVO.number=-goodsItemVO.number;
		}
		vo.goodsItemList=goodsitemList;
		if(vo.type==BillType.SALES){
			vo.ID=salesDataService.getNewSalesID();
		}else{
			vo.ID=salesDataService.getNewSalesReturnID();
		}
		vo.state=BillState.PASS;
		return addSales(vo);
	}
	
	public ResultMessage redCoverAndCopy(SalesVO vo) throws RemoteException {
		redCover(vo);
		vo.state=BillState.DRAFT;
		if(vo.type==BillType.SALES){
			vo.ID=salesDataService.getNewSalesID();
		}else{
			vo.ID=salesDataService.getNewSalesReturnID();
		}
		return addSales(vo);
	}
	
}
