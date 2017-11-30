package dataimpl.salesdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import dataservice.salesdataservice.SalesDataService;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.Criterion;
import util.ResultMessage;

public class SalesDataServiceImpl implements SalesDataService{
    private static SalesDataServiceImpl salesDataServiceImpl;
    private DataHelper<SalesPO> salesDataHelper;
    private DataHelper<PurchasePO> purchaseDataHelper;
	
	public PurchasePO findPurchaseByID(String ID) throws RemoteException {
		return purchaseDataHelper.exactlyQuery("id", Integer.parseInt(ID));
	}

	public PurchasePO findPurchaseByState(BillState state) throws RemoteException {
		return purchaseDataHelper.exactlyQuery("type", state);
	}

	public SalesPO findSlaesByID(String ID) throws RemoteException {
		return salesDataHelper.exactlyQuery("id", Integer.parseInt(ID));
	}

	public SalesPO findSlaesByState(BillState state) throws RemoteException {
		return salesDataHelper.exactlyQuery("type", state);
	}

	public ResultMessage addPurchase(PurchasePO po) throws RemoteException {
		return purchaseDataHelper.save(po);
	}

	public ResultMessage addSales(SalesPO po) throws RemoteException {
		return salesDataHelper.save(po);
	}

	public ResultMessage updatePurchase(PurchasePO po) throws RemoteException {
		return purchaseDataHelper.update(po);
	}

	public ResultMessage updateSales(SalesPO po) throws RemoteException {
		return salesDataHelper.update(po);
	}

	public ResultMessage deletePurchase(String ID) throws RemoteException {
		return purchaseDataHelper.delete(purchaseDataHelper.exactlyQuery("id", Integer.parseInt(ID)));
	}

	public ResultMessage deleteSales(String ID) throws RemoteException {
		return salesDataHelper.delete(salesDataHelper.exactlyQuery("id", Integer.parseInt(ID)));
	}

	public void init() throws RemoteException {
		
	}

	public ArrayList<PurchasePO> showPurchase() throws RemoteException {
		return purchaseDataHelper.multiQuery(new ArrayList<Criterion>());
	}

	public ArrayList<SalesPO> showSales() throws RemoteException {
		return salesDataHelper.multiQuery(new ArrayList<Criterion>());
	}
	
}
