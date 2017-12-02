package dataimpl.salesdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.BillType;
import util.Criterion;
import util.QueryMode;
import util.ResultMessage;

public class SalesDataServiceImpl implements SalesDataService{
    private static SalesDataServiceImpl salesDataServiceImpl;
    private DataHelper<SalesPO> salesDataHelper;
    private DataHelper<PurchasePO> purchaseDataHelper;
    private DataHelper<GoodsItemPO> goodsItemDataHelper;
    
    public static SalesDataServiceImpl getInstance(){
    	if(salesDataServiceImpl == null){
			salesDataServiceImpl = new SalesDataServiceImpl();
		}
		return salesDataServiceImpl;
    }
    
    private SalesDataServiceImpl(){
		this.salesDataHelper = new HibernateDataHelper<SalesPO>(SalesPO.class);
		this.purchaseDataHelper=new HibernateDataHelper<PurchasePO>(PurchasePO.class); 
		this.goodsItemDataHelper=new HibernateDataHelper<GoodsItemPO>(GoodsItemPO.class); 
	}
	
	public PurchasePO findPurchaseByID(int ID) throws RemoteException {
		return purchaseDataHelper.exactlyQuery("id", ID);
	}

	public PurchasePO findPurchaseByState(BillState state) throws RemoteException {
		return purchaseDataHelper.exactlyQuery("type", state);
	}

	public SalesPO findSlaesByID(int ID) throws RemoteException {
		return salesDataHelper.exactlyQuery("id", ID);
	}

	public SalesPO findSlaesByState(BillState state) throws RemoteException {
		return salesDataHelper.exactlyQuery("type", state);
	}
	
	public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException {
		return goodsItemDataHelper.save(po);
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

	public ResultMessage deletePurchase(int ID) throws RemoteException {
		return purchaseDataHelper.delete(purchaseDataHelper.exactlyQuery("id", ID));
	}

	public ResultMessage deleteSales(int ID) throws RemoteException {
		return salesDataHelper.delete(salesDataHelper.exactlyQuery("id", ID));
	}

	public void init() throws RemoteException {
		ArrayList<PurchasePO> purchaseList=salesDataServiceImpl.showPurchase();
		ArrayList<SalesPO> salesList=salesDataServiceImpl.showSales();
		for(int i=0;i<purchaseList.size();i++){
			purchaseDataHelper.delete(purchaseList.get(i));
		}
		for(int i=0;i<salesList.size();i++){
			salesDataHelper.delete(salesList.get(i));
		}
	}

	public ArrayList<PurchasePO> showPurchase() throws RemoteException {
		ArrayList<Criterion> criterion=new ArrayList<Criterion>();
		criterion.add(
				new Criterion("type",BillType.PURCHASE,QueryMode.FULL)
				);
		return purchaseDataHelper.multiQuery(criterion);
	}
	
	public ArrayList<PurchasePO> showReturn() throws RemoteException {
		ArrayList<Criterion> criterion=new ArrayList<Criterion>();
		criterion.add(
				new Criterion("type",BillType.RETURN,QueryMode.FULL)
				);
		return purchaseDataHelper.multiQuery(criterion);
	}

	public ArrayList<SalesPO> showSales() throws RemoteException {
		ArrayList<Criterion> criterion=new ArrayList<Criterion>();
		criterion.add(
				new Criterion("type",BillType.SALES,QueryMode.FULL)
				);
		return salesDataHelper.multiQuery(criterion);
	}

	public ArrayList<SalesPO> showSalesReturn() throws RemoteException {
		ArrayList<Criterion> criterion=new ArrayList<Criterion>();
		criterion.add(
				new Criterion("type",BillType.SALESRETURN,QueryMode.FULL)
				);
		return salesDataHelper.multiQuery(criterion);
	}
	
}
