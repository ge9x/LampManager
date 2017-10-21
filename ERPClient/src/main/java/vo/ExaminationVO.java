package vo;

import java.util.ArrayList;

/** 
 * Created by Aster on 2017/10/20
 */

public class ExaminationVO {
	public ArrayList<InventoryBillVO> inventoryBillVOs;

	public ArrayList<SalesVO> salesVOs;
	
	public ArrayList<PurchaseVO> purchaseVOs;
	
	public ArrayList<AccountBillVO> accountBillVOs;
	
	public ArrayList<CashBillVO> cashBillVOs;
	
	public ExaminationVO(ArrayList<InventoryBillVO> inventoryBillVOs, ArrayList<SalesVO> salesVOs, ArrayList<PurchaseVO> purchaseVOs, ArrayList<AccountBillVO> accountBillVOs, public ArrayList<CashBillVO> cashBillVOs){
		this.inventoryBillVOs = inventoryBillVOs;
		this.salesVOs = salesVOs;
		this.purchaseVOs = purchaseVOs;
		this.accountBillVOs = accountBillVOs;
		this.cashBillVOs = cashBillVOs;
	}
}
