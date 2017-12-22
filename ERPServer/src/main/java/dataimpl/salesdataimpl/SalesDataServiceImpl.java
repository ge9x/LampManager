package dataimpl.salesdataimpl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	public PurchasePO findPurchaseByID(String ID) throws RemoteException {
		String[] arr=ID.split("-");
		ArrayList<Criterion> criteria=new ArrayList<>(); 
		if(arr[0].equals("JHD")){
			criteria.add(
					new Criterion("type",BillType.PURCHASE,QueryMode.FULL)
					);
			criteria.add(
					new Criterion("date", addHyphen(arr[1]),QueryMode.FULL)
					);
			criteria.add(
					new Criterion("turn", Integer.parseInt(arr[2]),QueryMode.FULL)
					);
			return purchaseDataHelper.multiQuery(criteria).get(0);
		}else{
			criteria.add(
					new Criterion("type",BillType.RETURN,QueryMode.FULL)
					);
			criteria.add(
					new Criterion("date", addHyphen(arr[1]),QueryMode.FULL)
					);
			criteria.add(
					new Criterion("turn", Integer.parseInt(arr[2]),QueryMode.FULL)
					);
			return purchaseDataHelper.multiQuery(criteria).get(0);
		}
	}

	public ArrayList<PurchasePO> findPurchaseByState(BillState state) throws RemoteException {
		ArrayList<Criterion> criteria=new ArrayList<>();
		criteria.add(
				new Criterion("state", state,QueryMode.FULL)
				);
		return purchaseDataHelper.multiQuery(criteria);
	}

	public SalesPO findSlaesByID(String ID) throws RemoteException {
		String[] arr=ID.split("-");
		ArrayList<Criterion> criteria=new ArrayList<>(); 
		if(arr[0].equals("XSD")){
			criteria.add(
					new Criterion("type",BillType.SALES,QueryMode.FULL)
					);
			criteria.add(
					new Criterion("date", addHyphen(arr[1]),QueryMode.FULL)
					);
			criteria.add(
					new Criterion("turn", Integer.parseInt(arr[2]),QueryMode.FULL)
					);
			return salesDataHelper.multiQuery(criteria).get(0);
		}else{
			criteria.add(
					new Criterion("type",BillType.SALESRETURN,QueryMode.FULL)
					);
			criteria.add(
					new Criterion("date", addHyphen(arr[1]),QueryMode.FULL)
					);
			criteria.add(
					new Criterion("turn", Integer.parseInt(arr[2]),QueryMode.FULL)
					);
			return salesDataHelper.multiQuery(criteria).get(0);
		}
	}

	public ArrayList<SalesPO> findSlaesByState(BillState state) throws RemoteException {
		ArrayList<Criterion> criteria=new ArrayList<>();
		criteria.add(
				new Criterion("state", state,QueryMode.FULL)
				);
		return salesDataHelper.multiQuery(criteria);
	}
	
	public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException {
		return goodsItemDataHelper.save(po);
	}

	public ResultMessage addPurchase(PurchasePO po) throws RemoteException {
	    List<GoodsItemPO> goodsItemPOs=po.getGoodsItemList();
	    for(GoodsItemPO goodsItemPO:goodsItemPOs){
	    	addGoodsItem(goodsItemPO);
	    }
		return purchaseDataHelper.save(po);
	}

	public ResultMessage addSales(SalesPO po) throws RemoteException {
		List<GoodsItemPO> goodsItemPOs=po.getGoodsItemList();
		for(GoodsItemPO goodsItemPO:goodsItemPOs){
			addGoodsItem(goodsItemPO);
		}
		return salesDataHelper.save(po);
	}

	public ResultMessage updatePurchase(PurchasePO po) throws RemoteException {
		List<GoodsItemPO> goodsItemPOs=po.getGoodsItemList();
		for(GoodsItemPO goodsItemPO:goodsItemPOs){
			addGoodsItem(goodsItemPO);
		}
		return purchaseDataHelper.update(po);
	}

	public ResultMessage updateSales(SalesPO po) throws RemoteException {
		return salesDataHelper.update(po);
	}

	public ResultMessage deletePurchase(String ID) throws RemoteException {
		return purchaseDataHelper.delete(salesDataServiceImpl.findPurchaseByID(ID));
	}

	public ResultMessage deleteSales(String ID) throws RemoteException {
		return salesDataHelper.delete(salesDataServiceImpl.findSlaesByID(ID));
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
	
	/**
	 * 给时间增加横杠
	 * @param date
	 * @return
	 */
	private String addHyphen(String date){
		return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
	}

	@Override
	public String getNewPurchaseID() throws RemoteException{
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.PURCHASE, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalDate.now().toString(), QueryMode.FULL));
		int num = salesDataHelper.multiQuery(criteria).size() + 1;
		return BillType.PURCHASE.getAcronym() + "-" + LocalDate.now().toString().replace("-", "") + "-" + String.format("%05d", num);
	}

	@Override
	public String getNewReturnID() throws RemoteException{
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.RETURN, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalDate.now().toString(), QueryMode.FULL));
		int num = salesDataHelper.multiQuery(criteria).size() + 1;
		return BillType.RETURN.getAcronym() + "-" + LocalDate.now().toString().replace("-", "") + "-" + String.format("%05d", num);
	}

	@Override
	public String getNewSalesID() throws RemoteException{
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.SALES, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalDate.now().toString(), QueryMode.FULL));
		int num = salesDataHelper.multiQuery(criteria).size() + 1;
		return BillType.SALES.getAcronym() + "-" + LocalDate.now().toString().replace("-", "") + "-" + String.format("%05d", num);
	}

	@Override
	public String getNewSalesReturnID() throws RemoteException{
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.SALESRETURN, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalDate.now().toString(), QueryMode.FULL));
		int num = salesDataHelper.multiQuery(criteria).size() + 1;
		return BillType.SALESRETURN.getAcronym() + "-" + LocalDate.now().toString().replace("-", "") + "-" + String.format("%05d", num);
	}
	
}
