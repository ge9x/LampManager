package bl.inventorybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.inventoryblservice.InventoryBLService;
import blservice.inventoryblservice.InventoryInfo;
import po.InventoryPO;
import util.BillState;
import util.BillType;
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
		try {
			return inventory.showInventory();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public InventoryViewVO show(String startDate, String endDate, String inventory) {
		return this.inventory.show(startDate, endDate, inventory);
	}

	public InventoryCheckVO check() {
		return inventory.check();
	}

	public ResultMessage exportExcel(InventoryCheckVO vo) {
		return inventory.exportExcel(vo);
	}

	public ArrayList<InventoryBillVO> showBills() {
		try {
			return inventory.showBills();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<InventoryBillVO> showAlarmBills() {
		try {
			return inventory.showAlarmBills();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state) {
		try {
			return inventory.findBillByStateAndType(type, state);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage addInventory(String inventory) {
		try {
			return this.inventory.addInventory(inventory);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage addBill(InventoryBillVO vo) {
		try {
			return inventory.addBill(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage deleteInventory(String inventory) {
		try {
			return this.inventory.deleteInventory(inventory);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage deleteBill(String ID) {
		return inventory.deleteBill(ID);
	}

	public ResultMessage updateInventory(String before, String after) {
		try {
			return inventory.updateInventory(before, after);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage updateBill(InventoryBillVO vo) {
		try {
			return inventory.updateBill(vo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public InventoryBillVO showBillDetails(String ID) {
		try {
			return inventory.showBillDetails(ID);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage submitBill(InventoryBillVO vo) {
		try {
			return inventory.submitBill(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

    @Override
    public ArrayList<InventoryBillVO> findBillByType(BillType type) {
        try {
			return inventory.findBillByType(type);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
    }

    @Override
    public String getNewBillIDByType(BillType type) {
        return null;	// TODO
    }

    @Override
    public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate) {
        return null; // TODO
    }

	@Override
	public ArrayList<String> getAllInventoryName() {
		return this.showInventory();
	}

	@Override
	public InventoryPO getInventoryByName(String name) {
		try {
			return inventory.getInventoryByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
