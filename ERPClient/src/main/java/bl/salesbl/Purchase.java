package bl.salesbl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfoenix.controls.JFXPopup.PopupHPosition;

import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.PromotionInfo;
import blservice.userblservice.UserInfo;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import rmi.SalesRemoteHelper;
import util.BillState;
import util.BillType;
import util.Level;
import util.ResultMessage;
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

public class Purchase {
	
	PurchaseLineItem purchaseLineItem;
	PurchaseList purchaseList;
	GoodsItem goodsItem;
	//InventoryInfo inventoryInfo;
	//CustomerInfo customerInfo;
	//PromotionInfo promotionInfo;
	//UserInfo userInfo;
	
	private static SalesDataService salesDataService;
	
	public Purchase(){
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
		List<GoodsItemVO> goodsItemvoList=vo.goodsItemList;
		List<GoodsItemPO> goodsItempoList=new ArrayList<>();
		for(GoodsItemVO goodsItemvo:goodsItemvoList){
			goodsItempoList.add(GoodsItem.voTopo(goodsItemvo));
		}
		po.setGoodsItemList(goodsItempoList);
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
		ArrayList<GoodsItemVO> goodsItemVOs=vo.goodsItemList;
		for(GoodsItemVO goodsItemVO:goodsItemVOs){
			salesDataService.addGoodsItem(GoodsItem.voTopo(goodsItemVO));
		}
		return salesDataService.addPurchase(voTopo(vo));
	}

	public ResultMessage addGoodsItem(GoodsItemVO item) throws RemoteException {
		return goodsItem.addGoodsItem(item);
	}

	public ResultMessage addSales(SalesVO vo) {
		return ResultMessage.NULL;
	}

	public ResultMessage submitPurchase(PurchaseVO pur) throws RemoteException {
		addPurchase(pur);
	    PurchasePO po=salesDataService.findPurchaseByID(pur.ID);
	    po.setState(BillState.SUBMITTED);
		return salesDataService.updatePurchase(po);
	}

	public ResultMessage submitSales(SalesVO sal) {
		return null;
	}

	public ResultMessage saveSales(SalesVO bill) {
		return null;
	}

	public ResultMessage savePurchase(PurchaseVO bill) throws RemoteException {
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
	
	//purchaseInfo
	public ArrayList<String> getAllPurchaseDate() {
        try {
			ArrayList<PurchasePO> purList=salesDataService.showPurchase();
			ArrayList<PurchasePO> reList=salesDataService.showReturn();
			ArrayList<String> dateList=new ArrayList<>();
			purList.addAll(reList);
			for(PurchasePO po:purList){
				dateList.add(po.getDate());
			}
			return dateList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> getPurchaseIDByDate(Date startDate, Date endDate) {
		try {
			ArrayList<PurchasePO> purList=salesDataService.showPurchase();
			ArrayList<PurchasePO> reList=salesDataService.showReturn();
			ArrayList<String> dateList=new ArrayList<>();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getPurchaseIDByType(BillType type) {
		return null;
	}

	public ResultMessage examine(PurchaseVO vo) throws RemoteException {
		return updatePurchase(vo);
	}
	
	public ArrayList<PurchaseVO> getAllSubmittedPurchase() throws RemoteException {
		ArrayList<PurchasePO> purpoList=salesDataService.findPurchaseByState(BillState.SUBMITTED);
		ArrayList<PurchaseVO> purvoList=new ArrayList<>();
		for(PurchasePO po:purpoList){
			purvoList.add(poTovo(po));
		}
		return purvoList;
	}
	
	//purchaseList
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

    private static int getnewPurchaseTurn() throws RemoteException{
    	int turn=0;
    	ArrayList<PurchasePO> purList=salesDataService.showPurchase();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
    	Date date=new Date();  
    	String str=sdf.format(date);  
    	for(PurchasePO po:purList){
    		if(po.getDate().equals(str)){
    			turn++;
    		}
    	}
		return turn+1;
	}
    
    private static int getNewReturnTurn() throws RemoteException{
    	int turn=0;
    	ArrayList<PurchasePO> retList=salesDataService.showReturn();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
    	Date date=new Date(); 
    	String str=sdf.format(date);  
    	for(PurchasePO po:retList){
    		if(po.getDate().equals(str)){
    			turn++;
    		}
    	}
		return turn+1;
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
	
	public static PurchasePO voTopo(PurchaseVO vo) throws NumberFormatException, RemoteException{
		List<GoodsItemVO> goodsItemvoList=vo.goodsItemList;
		List<GoodsItemPO> goodsItempoList=new ArrayList<>();
		for(GoodsItemVO goodsItemvo:goodsItemvoList){
			goodsItempoList.add(GoodsItem.voTopo(goodsItemvo));
		}
		if(vo.type==BillType.PURCHASE){
			return new PurchasePO(vo.type,vo.state,vo.supplier,Integer.parseInt(vo.customerID),vo.inventory,vo.user,goodsItempoList,vo.remarks,vo.date,getnewPurchaseTurn());
		}else{
		return new PurchasePO(vo.type,vo.state,vo.supplier,Integer.parseInt(vo.customerID),vo.inventory,vo.user,goodsItempoList,vo.remarks,vo.date,getNewReturnTurn());
		}
	}
	
	public static PurchaseVO poTovo(PurchasePO po){
		List<GoodsItemPO> goodsItempoList=po.getGoodsItemList();
		ArrayList<GoodsItemVO> goodsItemvoList=new ArrayList<>();
		for(GoodsItemPO goodsItempo:goodsItempoList){
			goodsItemvoList.add(GoodsItem.poTovo(goodsItempo));
		}
		return new PurchaseVO(po.getType(),po.getState(),po.buildID(),po.getSupplier(),Integer.toString(po.getCustomerID()),po.getInventory(),po.getUser(),goodsItemvoList,po.getRemarks(),po.getDate());
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
	
	public ResultMessage deletePurchase(PurchaseVO vo) {
		return ResultMessage.NULL;
	}
	
}
