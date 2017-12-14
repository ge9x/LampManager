package bl.inventorybl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bl.goodsbl.Goods;
import bl.goodsbl.GoodsController;
import bl.salesbl.SalesController;
import blservice.goodsblservice.GoodsInfo;
import blservice.salesblservice.SalesInfo;
import dataservice.inventorydataservice.InventoryDataService;
import po.GoodsItemPO;
import po.GoodsPO;
import po.InventoryBillPO;
import po.InventoryPO;
import rmi.InventoryRemoteHelper;
import util.BillState;
import util.BillType;
import util.Criterion;
import util.QueryMode;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class Inventory {
	private InventoryDataService inventoryDataService;
	private InventoryList inventoryList;
	private InventoryBill inventoryBill;
	private SalesInfo salesInfo;
	private GoodsInfo goodsInfo;

	public Inventory() {
		inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
		// salesInfo = new SalesController(); // TODO
		goodsInfo = new GoodsController();
		inventoryBill = new InventoryBill();
	}

	public ArrayList<String> showInventory() throws RemoteException {
		ArrayList<InventoryPO> pos = inventoryDataService.showInventory();
		ArrayList<String> ret = new ArrayList<>();
		for (InventoryPO po : pos) {
			ret.add(po.getName());
		}
		return ret;
	}

	public InventoryViewVO show(String startDate, String endDate, String inventory) { // TODO
		return null;
	}

	public InventoryCheckVO check() { // TODO
		return null;
	}

	public ResultMessage exportExcel(InventoryCheckVO vo) { // TODO
		return null;
	}

	public ArrayList<InventoryBillVO> showBills() throws RemoteException {
		return inventoryBill.show();
	}

	public ArrayList<InventoryBillVO> showAlarmBills() throws RemoteException {
		return inventoryBill.showAlarm();
	}

	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state) throws RemoteException {
		return inventoryBill.findByStateAndType(type, state);
	}

	public ResultMessage addInventory(String inventory) throws RemoteException {
		ArrayList<InventoryPO> pos = inventoryDataService.showInventory();
		for (InventoryPO po : pos) {
			if (po.getName().equals(inventory)) {
				return ResultMessage.EXIST;
			}
		}
		InventoryPO toAdd = new InventoryPO(inventory);
		return inventoryDataService.addInventroy(toAdd);
	}

	public ResultMessage addBill(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.add(vo);
	}

	public ResultMessage deleteInventory(String inventory) throws RemoteException {
		InventoryPO found = this.getInventoryByName(inventory);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			return inventoryDataService.deleteInventory(found);
		}
	}

	public ResultMessage deleteBill(String ID) throws RemoteException {
		return inventoryBill.delete(ID);
	}

	public ResultMessage updateInventory(String before, String after) throws RemoteException {
		InventoryPO found = this.getInventoryByName(before);
		if (found == null) {
			return ResultMessage.NOT_EXIST;
		}
		else {
			return inventoryDataService.updateInventory(found);
		}
	}

	public ResultMessage updateBill(InventoryBillVO vo) throws NumberFormatException, RemoteException {
		return inventoryBill.update(vo);
	}

	public InventoryBillVO showBillDetails(String ID) throws NumberFormatException, RemoteException {
		return inventoryBill.showDetails(ID);
	}

	/**
	 * 约定：和addBill方法逻辑相同（仅在使用情境和语义上有所区别）
	 */
	public ResultMessage submitBill(InventoryBillVO vo) throws RemoteException {
		return inventoryBill.submit(vo);
	}

	public ArrayList<InventoryBillVO> findBillByType(BillType type) throws RemoteException {
		return inventoryBill.findByType(type);
	}
	
    public String getNewBillIDByType(BillType type) throws RemoteException {
		return inventoryBill.getNewIDByType(type);
    }

    public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate) throws RemoteException {
        return inventoryBill.getBillsByDate(startDate, endDate);
    }

	protected InventoryPO getInventoryByName(String name) throws RemoteException {
		return inventoryDataService.findInventoryByName(name);
	}

	public ResultMessage raiseInventory(ArrayList<GoodsItemPO> goodsItems, String inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage reduceInventory(ArrayList<GoodsItemPO> goodsItems, String inventory) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
