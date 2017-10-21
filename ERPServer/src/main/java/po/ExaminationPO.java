package po;

import java.util.ArrayList;

public class ExaminationPO {
	private ArrayList<InventoryBillPO> inventoryBillPOs;

	private ArrayList<SalesPO> salesPOs;
	
	private ArrayList<PurchasePO> purchasePOs;
	
	private ArrayList<AccountBillPO> accountBillPOs;
	
	private ArrayList<CashBillPO> cashBillPOs;
	
	public ExaminationPO(ArrayList<InventoryBillPO> inventoryBillPOs, ArrayList<SalesPO> salesPOs, ArrayList<PurchasePO> purchasePOs, ArrayList<AccountBillPO> accountBillPOs, public ArrayList<CashBillPO> cashBillPOs){
		this.inventoryBillPOs = inventoryBillPOs;
		this.salesPOs = salesPOs;
		this.purchasePOs = purchasePOs;
		this.accountBillPOs = accountBillPOs;
		this.cashBillPOs = cashBillPOs;
	}

	public ArrayList<InventoryBillPO> getInventoryBillPOs() {
		return inventoryBillPOs;
	}

	public void setInventoryBillPOs(ArrayList<InventoryBillPO> inventoryBillPOs) {
		this.inventoryBillPOs = inventoryBillPOs;
	}

	public ArrayList<SalesPO> getSalesPOs() {
		return salesPOs;
	}

	public void setSalesPOs(ArrayList<SalesPO> salesPOs) {
		this.salesPOs = salesPOs;
	}

	public ArrayList<PurchasePO> getPurchasePOs() {
		return purchasePOs;
	}

	public void setPurchasePOs(ArrayList<PurchasePO> purchasePOs) {
		this.purchasePOs = purchasePOs;
	}

	public ArrayList<AccountBillPO> getAccountBillPOs() {
		return accountBillPOs;
	}

	public void setAccountBillPOs(ArrayList<AccountBillPO> accountBillPOs) {
		this.accountBillPOs = accountBillPOs;
	}

	public ArrayList<CashBillPO> getCashBillPOs() {
		return cashBillPOs;
	}

	public void setCashBillPOs(ArrayList<CashBillPO> cashBillPOs) {
		this.cashBillPOs = cashBillPOs;
	}
	
	
}
