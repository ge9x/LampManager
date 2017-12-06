package bl.salesbl;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataservice.salesdataservice.SalesDataService;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Purchase {
	private PurchaseVO vo;
	private PurchaseList purchaseList;
	
	private static SalesDataService salesDataService;
	
	public Purchase(){
		
	}
	
	public PurchaseVO findPurchaseByID(String ID) {
		return null;
	}
	
	public PurchaseVO findPurchaseByState(BillState state) throws RemoteException {
		return poTovo(salesDataService.findPurchaseByState(state));
	}
	
	public ResultMessage addPurchase(PurchaseVO vo) throws NumberFormatException, RemoteException {
		return salesDataService.addPurchase(voTopo(vo));
	}
	
	public ResultMessage updatePurchase(PurchaseVO vo) {
		return null;
	}
		
	public ResultMessage deletePurchase(String ID) {
		return null;
	}
	
	public void init() {
		
	}
	
	public ResultMessage submitPurchase(PurchaseVO vo){
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
