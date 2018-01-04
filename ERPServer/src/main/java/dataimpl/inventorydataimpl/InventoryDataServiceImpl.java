package dataimpl.inventorydataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryBillPO;
import po.InventoryPO;
import util.BillType;
import util.Criterion;
import util.QueryMode;
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
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(
				new Criterion(
						new Criterion("type", BillType.OVERFLOW, QueryMode.FULL),
						new Criterion("type", BillType.LOSS, QueryMode.FULL)
						)
				);
		return inventoryBillDataHelper.multiQuery(criteria);
	}

	@Override
	public ArrayList<InventoryPO> showInventory() throws RemoteException {
		return inventoryDataHelper.fullyQuery(null, null);
	}

	@Override
	public InventoryBillPO findBill(int ID) throws RemoteException {
		return inventoryBillDataHelper.exactlyQuery("id", ID);
	}

	@Override
	public ResultMessage addInventroy(InventoryPO po) throws RemoteException {
		return inventoryDataHelper.save(po);
	}

	@Override
	public ResultMessage addBill(InventoryBillPO po) throws RemoteException {
		return inventoryBillDataHelper.save(po);
	}

	@Override
	public ResultMessage deleteInventory(InventoryPO po) throws RemoteException {
		return inventoryDataHelper.delete(po);
	}

	@Override
	public ResultMessage deleteBill(InventoryBillPO po) throws RemoteException {
		return inventoryBillDataHelper.delete(po);
	}

	@Override
	public ResultMessage updateInventory(InventoryPO po) throws RemoteException {
		return inventoryDataHelper.update(po);
	}

	@Override
	public ResultMessage updateBill(InventoryBillPO po) throws RemoteException {
		return inventoryBillDataHelper.update(po);
	}

	@Override
	public ArrayList<InventoryBillPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException {
		return inventoryBillDataHelper.multiQuery(criteria);
	}

	@Override
	public InventoryPO findInventoryByName(String name) throws RemoteException {
		return inventoryDataHelper.exactlyQuery("name", name);
	}
}
