package bl.salesbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.salesdataservice.SalesDataService;
import po.PurchasePO;
import vo.PurchaseVO;

public class PurchaseList {
	private SalesDataService salesDataService;
	Purchase purchase;
	
	public PurchaseList(){
		
	}
	
	public ArrayList<PurchaseVO> showPurchase() throws RemoteException{
		ArrayList<PurchasePO> poList=salesDataService.showPurchase();
		ArrayList<PurchaseVO> voList=new ArrayList<>();
		for(PurchasePO po:poList){
			voList.add(Purchase.poTovo(po));
		}
		return voList;
	}
	
	public ArrayList<PurchaseVO> showReturn() throws RemoteException{
		ArrayList<PurchasePO> poList=salesDataService.showReturn();
		ArrayList<PurchaseVO> voList=new ArrayList<>();
		for(PurchasePO po:poList){
			voList.add(Purchase.poTovo(po));
		}
		return voList;
	}
	
}
