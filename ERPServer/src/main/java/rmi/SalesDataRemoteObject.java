package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.salesdataimpl.SalesDataServiceImpl;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.ResultMessage;

public class SalesDataRemoteObject extends UnicastRemoteObject implements SalesDataService{
	
	private SalesDataService salesDataService;
	protected SalesDataRemoteObject() throws RemoteException{
		salesDataService=SalesDataServiceImpl.getInstance();
	}
	@Override
	public PurchasePO findPurchaseByID(String ID) throws RemoteException {
		return salesDataService.findPurchaseByID(ID);
	}
	@Override
	public ArrayList<PurchasePO> findPurchaseByState(BillState state) throws RemoteException {
		return salesDataService.findPurchaseByState(state);
	}
	@Override
	public SalesPO findSlaesByID(String ID) throws RemoteException {
		return salesDataService.findSlaesByID(ID);
	}
	@Override
	public ArrayList<SalesPO> findSlaesByState(BillState state) throws RemoteException {
		return salesDataService.findSlaesByState(state);
	}
	@Override
	public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException {
		return salesDataService.addGoodsItem(po);
	}
	@Override
	public ResultMessage addPurchase(PurchasePO po) throws RemoteException {
		return salesDataService.addPurchase(po);
	}
	@Override
	public ResultMessage addSales(SalesPO po) throws RemoteException {
		return salesDataService.addSales(po);
	}
	@Override
	public ResultMessage updatePurchase(PurchasePO po) throws RemoteException {
		return salesDataService.updatePurchase(po);
	}
	@Override
	public ResultMessage updateSales(SalesPO po) throws RemoteException {
		return salesDataService.updateSales(po);
	}
	@Override
	public ResultMessage deletePurchase(String ID) throws RemoteException {
		return salesDataService.deletePurchase(ID);
	}
	@Override
	public ResultMessage deleteSales(String ID) throws RemoteException {
		return salesDataService.deletePurchase(ID);
	}
	@Override
	public void init() throws RemoteException {
		salesDataService.init();
	}
	@Override
	public ArrayList<PurchasePO> showPurchase() throws RemoteException {
		return salesDataService.showPurchase();
	}
	@Override
	public ArrayList<PurchasePO> showReturn() throws RemoteException {
		return salesDataService.showReturn();
	}
	@Override
	public ArrayList<SalesPO> showSales() throws RemoteException {
		return salesDataService.showSales();
	}
	@Override
	public ArrayList<SalesPO> showSalesReturn() throws RemoteException {
		return salesDataService.showSalesReturn();
	}
	
	
}
