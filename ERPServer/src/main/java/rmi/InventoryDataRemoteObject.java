package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.inventorydataimpl.InventoryDataServiceImpl;
import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryBillPO;
import po.InventoryPO;
import util.Criterion;
import util.ResultMessage;

/**
 * Created on 2017/12/4
 * @author 巽
 *
 */
public class InventoryDataRemoteObject extends UnicastRemoteObject implements InventoryDataService{
	private static final long serialVersionUID = 222410765716232202L;
	InventoryDataService inventoryDataService;
	
	protected InventoryDataRemoteObject() throws RemoteException {
		super();
		inventoryDataService = InventoryDataServiceImpl.getInstance();
	}

	@Override
	public ArrayList<InventoryBillPO> show() throws RemoteException {
		return inventoryDataService.show();
	}

	@Override
	public ArrayList<InventoryPO> showInventory() throws RemoteException {
		return inventoryDataService.showInventory();
	}

	@Override
	public ArrayList<InventoryBillPO> showAlarm() throws RemoteException {
		return inventoryDataService.showAlarm();
	}

	@Override
	public InventoryBillPO findBill(int ID) throws RemoteException {
		return inventoryDataService.findBill(ID);
	}

	@Override
	public ResultMessage addInventroy(InventoryPO po) throws RemoteException {
		return inventoryDataService.addInventroy(po);
	}

	@Override
	public ResultMessage addBill(InventoryBillPO po) throws RemoteException {
		return inventoryDataService.addBill(po);
	}

	@Override
	public ResultMessage deleteInventory(InventoryPO po) throws RemoteException {
		return inventoryDataService.deleteInventory(po);
	}

	@Override
	public ResultMessage deleteBill(InventoryBillPO po) throws RemoteException {
		return inventoryDataService.deleteBill(po);
	}

	@Override
	public ResultMessage updateInventory(InventoryPO po) throws RemoteException {
		return inventoryDataService.updateInventory(po);
	}

	@Override
	public ResultMessage updateBill(InventoryBillPO po) throws RemoteException {
		return inventoryDataService.updateBill(po);
	}

	@Override
	public ArrayList<InventoryBillPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return inventoryDataService.advancedQuery(criteria);
	}

	@Override
	public InventoryPO findInventoryByName(String name) throws RemoteException {
		return inventoryDataService.findInventoryByName(name);
	}

}
