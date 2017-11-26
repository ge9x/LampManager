package dataimpl.inventorydataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryBillPO;
import po.InventoryPO;
import util.ResultMessage;

/**
 * Created on 2017/11/27
 * @author 巽
 *
 */
public class InventoryDataServiceImpl implements InventoryDataService{
	private static InventoryDataServiceImpl inventoryDataServiceImpl;
	private DataHelper<InventoryPO> inventoryDataHelper;
	private DataHelper<InventoryBillPO> inventoryBillDataHelper;
	
	public static InventoryDataServiceImpl getInstance(){
		if(inventoryDataServiceImpl == null){
			inventoryDataServiceImpl = new InventoryDataServiceImpl();
		}
		return inventoryDataServiceImpl;
	}
	
	private InventoryDataServiceImpl(){
		inventoryDataHelper = new HibernateDataHelper<InventoryPO>(InventoryPO.class);
		inventoryBillDataHelper = new HibernateDataHelper<InventoryBillPO>(InventoryBillPO.class);
	}

	@Override
	public ArrayList<InventoryBillPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showInventory() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InventoryBillPO> showAlarm() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryBillPO findBill(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addInventroy(String inventory) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addBill(InventoryBillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteInventory(String inventory) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteBill(InventoryBillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateInventory(String before, String after) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateBill(InventoryBillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
