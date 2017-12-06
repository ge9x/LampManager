package bl.salesbl;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

import dataservice.salesdataservice.SalesDataService;
import javassist.expr.NewArray;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;
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
	private PurchaseVO vo;
	private PurchaseList purchaseList;
	
	private static SalesDataService salesDataService;
	
	public Purchase(){
		
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
		po.setSupplier(vo.supplier);
		po.setCustomerID(Integer.parseInt(vo.customerID));
		po.setInventory(vo.inventory);
		po.setUser(vo.user);
		po.setGoodsItemList((List)vo.goodsItemList);
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
	public String getnewPurchaseID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getnewReturnID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getnewSalesID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getnewSalesReturnID() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PromotionBargainVO> showBargains() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PromotionTotalVO> getFitPromotionTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addPurchase(PurchaseVO vo) throws NumberFormatException, RemoteException {
		return salesDataService.addPurchase(voTopo(vo));
	}

	public ResultMessage addGoodsItem(GoodsItemVO item) throws RemoteException {
		return salesDataService.addGoodsItem(voTopo(item));
	}

	public ResultMessage addSales(SalesVO vo) {
		return ResultMessage.NULL;
	}

	public ResultMessage submitPurchase(PurchaseVO pur) throws RemoteException {
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

	public String getUserID() {
		return null;
	}

	public ArrayList<CustomerVO> getAllSupplier() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAllInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Integer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getPurchaseIDByType(BillType type) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getPurchaseIDByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getPurchaseIDBySalesman(String salesman) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getPurchaseIDByInventory(String inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage examine(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
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
    
    public static GoodsItemPO voTopo(GoodsItemVO vo){
    	return new GoodsItemPO(vo.ID,vo.goodsName,vo.model,vo.number,vo.price,vo.remarks);
    }
    
    public static GoodsItemVO poTovo(GoodsItemPO po){
    	return new GoodsItemVO(po.getGoodsID(), po.getGoodsName(), po.getModel(), po.getNumber(), po.getPrice(), po.getRemarks());
    }
	
	public static PurchasePO voTopo(PurchaseVO vo) throws NumberFormatException, RemoteException{
		if(vo.type==BillType.PURCHASE){
			return new PurchasePO(vo.type,vo.state,vo.supplier,Integer.parseInt(vo.customerID),vo.inventory,vo.user,(List)vo.goodsItemList,vo.remarks,vo.date,getnewPurchaseTurn());
		}else{
		return new PurchasePO(vo.type,vo.state,vo.supplier,Integer.parseInt(vo.customerID),vo.inventory,vo.user,(List)vo.goodsItemList,vo.remarks,vo.date,getNewReturnTurn());
		}
	}
	
	public static PurchaseVO poTovo(PurchasePO po){
		return new PurchaseVO(po.getType(),po.getState(),po.buildID(),po.getSupplier(),Integer.toString(po.getCustomerID()),po.getInventory(),po.getUser(),(ArrayList)po.getGoodsItemList(),po.getRemarks(),po.getDate());
	}
}
