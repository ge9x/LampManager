package bl.inventorybl;

import java.util.ArrayList;
import java.util.Date;

import blservice.inventoryblservice.InventoryBLService;
import blservice.inventoryblservice.InventoryInfo;
import util.ResultMessage;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryController implements InventoryBLService, InventoryInfo{

	private Inventory inventory;

	public InventoryController(){
		inventory = new Inventory();
	}
	
	public ArrayList<String> showInventory() {
		return null;
	}

	public InventoryViewVO show(Date startDate, Date endDate, String inventory) {
		return null;
	}

	public InventoryCheckVO check() {
		return null;
	}

	public ResultMessage exportExcel(InventoryCheckVO vo) {
		return null;
	}

	public ArrayList<InventoryBillVO> showBills() {
		return null;
	}

	public ArrayList<InventoryBillVO> showAlarmBills() {
		return null;
	}

	public ArrayList<InventoryBillVO> findBill(Date startDate, Date endDate, String inventory, String id,
			String keyword) {
		return null;
	}

	public ResultMessage addInventory(String inventory) {
		return null;
	}

	public ResultMessage addBill(InventoryBillVO vo) {
		return null;
	}

	public ResultMessage deleteInventory(String inventory) {
		return null;
	}

	public ResultMessage deleteBill(String ID) {
		return null;
	}

	public ResultMessage updateInventory(String before, String after) {
		return null;
	}

	public ResultMessage updateBill(InventoryBillVO vo) {
		return null;
	}

	public InventoryBillVO showBillDetails(String ID) {
		return null;
	}

	public ResultMessage submitBill(String ID) {
		return null;
	}
}
