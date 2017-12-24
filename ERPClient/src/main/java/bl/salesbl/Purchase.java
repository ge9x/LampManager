package bl.salesbl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryController;
import bl.userbl.UserController;
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.userblservice.UserInfo;
import dataservice.salesdataservice.SalesDataService;
import javafx.geometry.VPos;
import po.GoodsItemPO;
import po.PurchasePO;
import rmi.SalesRemoteHelper;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import util.UserLimits;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Purchase {
	
	PurchaseLineItem purchaseLineItem;
	PurchaseList purchaseList;
	GoodsItem goodsItem;
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	UserInfo userInfo;
	
	private static SalesDataService salesDataService;
	
	public Purchase(){
		inventoryInfo=new InventoryController();
		customerInfo=new CustomerController();
		userInfo=new UserController();
		purchaseLineItem=new PurchaseLineItem();
		purchaseList=new PurchaseList();
		goodsItem=new GoodsItem();
		salesDataService=SalesRemoteHelper.getInstance().getSalesDataService();
	}
	
	public PurchaseVO findPurchaseByID(String ID) throws RemoteException {
		return poTovo(salesDataService.findPurchaseByID(ID));
	}
	
	public ArrayList<PurchaseVO> findPurchaseByState(BillState state) throws RemoteException {
		ArrayList<PurchasePO> purpoList=salesDataService.findPurchaseByState(state);
		ArrayList<PurchaseVO> purvoList=new ArrayList<>();
		for(PurchasePO po:purpoList){
			purvoList.add(poTovo(po));
		}
		return purvoList;
	}
	
	public ResultMessage updatePurchase(PurchaseVO vo) throws RemoteException {
		PurchasePO po=salesDataService.findPurchaseByID(vo.ID);
		po.setState(vo.state);
		po.setSupplier(vo.supplier);
		po.setCustomerID(Integer.parseInt(vo.customerID));
		po.setInventory(vo.inventory);
		po.setUser(vo.user);
		po.getGoodsItemList().clear();
		ArrayList<GoodsItemVO> goodsItemVOs=vo.goodsItemList;
		for(GoodsItemVO goodsItemVO:goodsItemVOs){
			GoodsItemPO goodsItemPO=GoodsItem.voTopo(goodsItemVO);
			po.getGoodsItemList().add(goodsItemPO);
		}
		po.setRemarks(vo.remarks);
		po.setSum(vo.sum);
		return salesDataService.updatePurchase(po);
	}
		
	public ResultMessage deletePurchase(String ID) throws RemoteException {
		return salesDataService.deletePurchase(ID);
	}
	
	public void init() throws RemoteException {
		salesDataService.init();
	}
	
	//blService
	public ResultMessage addPurchase(PurchaseVO vo) throws NumberFormatException, RemoteException {
		PurchasePO po = voTopo(vo);
		return salesDataService.addPurchase(po);
	}
	
	public ResultMessage addGoodsItem(GoodsItemVO item) throws RemoteException {
		return salesDataService.addGoodsItem(GoodsItem.voTopo(item));
	}

	public ResultMessage addSales(SalesVO vo) {
		return ResultMessage.NULL;
	}

	public ResultMessage submitPurchase(PurchaseVO pur) throws RemoteException {
		pur.state=BillState.SUBMITTED;
		return addPurchase(pur);
	}

	public ResultMessage submitSales(SalesVO sal) {
		return null;
	}

	public ResultMessage saveSales(SalesVO bill) {
		return null;
	}

	public ResultMessage savePurchase(PurchaseVO bill) throws RemoteException {
		addPurchase(bill);
		PurchasePO po=salesDataService.findPurchaseByID(bill.ID);
		po.setState(BillState.DRAFT);
		return salesDataService.updatePurchase(po);
	}

	public String getUserName() {
		return purchaseLineItem.getUserName();
	}

	public ArrayList<CustomerVO> getAllSupplier() {
		return purchaseLineItem.getAllSupplier();
	}

	public ArrayList<String> getAllInventory() {
		return purchaseLineItem.getAllInventory();
	}

	public ArrayList<CustomerVO> getAllCustomer() {
		return purchaseLineItem.getAllCustomer();
	}
	
	public ArrayList<PurchaseVO> getAllSubmittedPurchase() throws RemoteException {
		ArrayList<PurchasePO> purpoList=salesDataService.findPurchaseByState(BillState.SUBMITTED);
		ArrayList<PurchaseVO> purvoList=new ArrayList<>();
		for(PurchasePO po:purpoList){
			purvoList.add(poTovo(po));
		}
		return purvoList;
	}
	
	public ArrayList<PurchaseVO> showPurchase() throws RemoteException{
		return purchaseList.showPurchase();
	}
	
	public ArrayList<PurchaseVO> showReturn() throws RemoteException{
		return purchaseList.showReturn();
	}

	public String getnewPurchaseID() throws RemoteException {
		return salesDataService.getNewPurchaseID();
	}

	public String getnewReturnID() throws RemoteException {
		return salesDataService.getNewReturnID();
	}
    
    public ArrayList<PurchaseVO> getPurchaseByDate(String startDate, String endDate) throws ParseException {
		try {
			ArrayList<PurchasePO> purpoList=salesDataService.showPurchase();
			ArrayList<PurchasePO> repoList=salesDataService.showReturn();
			purpoList.addAll(repoList);
			ArrayList<PurchaseVO> purvoList=new ArrayList<>();
			for(PurchasePO po:purpoList){
				Date date=stringToDate(po.getDate());
				if(date.compareTo(stringToDate(startDate))>=0&&date.compareTo(stringToDate(endDate))<=0){
					purvoList.add(poTovo(po));
				}
			}
			return purvoList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
  
    
    private static Date stringToDate(String date) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date time=dateFormat.parse(date);
		return time;
	}
	
	public ArrayList<PurchaseVO> getPurchaseOrderByState(BillState state) throws RemoteException {
		ArrayList<PurchaseVO> purvoList=new ArrayList<>();
		ArrayList<PurchasePO> purrepoList=salesDataService.findPurchaseByState(state);
		for(PurchasePO po:purrepoList){
			if(po.getType()==BillType.PURCHASE){
				purvoList.add(poTovo(po));
			}
		}
		return purvoList;
	}

	public ArrayList<PurchaseVO> getReturnOrderByState(BillState state) throws RemoteException {
		ArrayList<PurchaseVO> purvoList=new ArrayList<>();
		ArrayList<PurchasePO> purrepoList=salesDataService.findPurchaseByState(state);
		for(PurchasePO po:purrepoList){
			if(po.getType()==BillType.RETURN){
				purvoList.add(poTovo(po));
			}
		}
		return purvoList;
	}
	
	public ResultMessage deletePurchase(PurchaseVO vo) throws RemoteException{
		return salesDataService.deletePurchase(vo.ID);
	}
	
	public ArrayList<PurchaseVO> getPurchaseByDateAndInventory(String startDate, String endDate, String inventory,
			BillType type) throws RemoteException, ParseException {
		ArrayList<PurchasePO> purList=new ArrayList<>();
		ArrayList<PurchaseVO> getList=new ArrayList<>();
		if(type==BillType.PURCHASE){
			purList=salesDataService.showPurchase();
		}else{
			purList=salesDataService.showReturn();
		}
		for(PurchasePO po:purList){
			Date date=stringToDate(po.getDate());
			if(date.compareTo(stringToDate(startDate))>=0&&date.compareTo(stringToDate(endDate))<=0&&po.getInventory().equals(inventory)&&po.getState()==BillState.PASS){
				getList.add(poTovo(po));
			}
		}
		return getList;
	}
	
	public ResultMessage examine(PurchaseVO vo) throws RemoteException {
	    if(vo.state==BillState.PASS){
		     if(vo.type==BillType.PURCHASE){
			       inventoryInfo.raiseInventory(vo.goodsItemList, vo.inventory);
			       customerInfo.raiseCustomerReceive(Integer.parseInt(vo.customerID), vo.sum);
		     }else{
			       inventoryInfo.reduceInventory(vo.goodsItemList, vo.inventory);
			       customerInfo.raiseCustomerPay(Integer.parseInt(vo.customerID), vo.sum);
		     }
		 }
	    return updatePurchase(vo);
	}
	
	public static PurchasePO voTopo(PurchaseVO vo) throws NumberFormatException, RemoteException{
		ArrayList<GoodsItemVO> goodsItemvoList=vo.goodsItemList;
		List<GoodsItemPO> goodsItempoList=new ArrayList<>();
		for(GoodsItemVO goodsItemvo:goodsItemvoList){
			GoodsItemPO goodsItemPO=GoodsItem.voTopo(goodsItemvo);
			goodsItempoList.add(goodsItemPO);
		}
		String str[]=vo.ID.split("-");
			return new PurchasePO(vo.type,vo.state,vo.supplier,Integer.parseInt(vo.customerID),vo.inventory,vo.user,goodsItempoList,vo.remarks,vo.date,Integer.parseInt(str[2]));
	}
	
	public static PurchaseVO poTovo(PurchasePO po){
		List<GoodsItemPO> goodsItempoList=po.getGoodsItemList();
		ArrayList<GoodsItemVO> goodsItemvoList=new ArrayList<>();
		for(GoodsItemPO goodsItempo:goodsItempoList){
			goodsItemvoList.add(GoodsItem.poTovo(goodsItempo));
		}
		return new PurchaseVO(po.getType(),po.getState(),po.buildID(),po.getSupplier(),Integer.toString(po.getCustomerID()),po.getInventory(),po.getUser(),goodsItemvoList,po.getRemarks(),po.getDate());
	}
	
	public UserLimits getCurrentUserLimits() {
		return userInfo.findUserByID(userInfo.getCurrentUserID()).limit;
	}
	
	public ResultMessage redCover(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
