package dataservice.salesdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsItemPO;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.ResultMessage;

/**
 * created by zlk on 2017/10/21
 */

public interface SalesDataService extends Remote{
	/**
	 * 通过单据编号查找进货单据
	 * 
	 * @param ID
	 * @return 查找到的进货单据
	 * @author zlk
	 */
	public PurchasePO findPurchaseByID(int ID) throws RemoteException;
	/**
	 * 通过单据状态查找进货单据
	 * 
	 * @param ID
	 * @return 查找到的进货单据
	 * @author zlk
	 */
	public PurchasePO findPurchaseByState(BillState state) throws RemoteException;
	/**
	 * 通过单据编号查找销售单据
	 * 
	 * @param ID
	 * @return 查找到的销售单据
	 * @author zlk
	 */
	public SalesPO findSlaesByID(int ID) throws RemoteException;
	/**
	 * 通过单据编号查找销售单据
	 * 
	 * @param ID
	 * @return 查找到的销售单据
	 * @author zlk
	 */
	public SalesPO findSlaesByState(BillState state) throws RemoteException;
	/**
	 * 添加商品清单
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException;
	/**
	 * 添加进货单据
	 * 
	 * @param po
	 * @return 是否成功添加今后单据
	 * @author zlk
	 */
	public ResultMessage addPurchase(PurchasePO po) throws RemoteException;
	/**
	 * 添加销售单据
	 * 
	 * @param po
	 * @return 是否成功添加销售单据
	 * @author zlk
	 */
	public ResultMessage addSales(SalesPO po) throws RemoteException;
	/**
	 * 更新进货单据
	 * 
	 * @param po
	 * @return 是否成功更新进货单据
	 * @author zlk
	 */
	public ResultMessage updatePurchase(PurchasePO po) throws RemoteException;
	/**
	 * 更新销售单据
	 * 
	 * @param po
	 * @return 是否成功更新销售单据
	 * @author zlk
	 */
	public ResultMessage updateSales(SalesPO po) throws RemoteException;
	/**
	 * 删除进货单据
	 * 
	 * @param po
	 * @return 是否成功删除进货单据
	 * @author zlk
	 */
	public ResultMessage deletePurchase(int ID) throws RemoteException;
	/**
	 * 删除销售单据
	 * 
	 * @param po
	 * @return 是否成功删除销售单据
	 * @author zlk
	 */
	public ResultMessage deleteSales(int ID) throws RemoteException;
	/**
	 * 初始化持久化数据库
	 * 
	 * @author zlk
	 */
	public void init() throws RemoteException;
	/**
	 * 展示进货单
	 * 
	 * @return po列表
	 * @author zlk
	 */
	public ArrayList<PurchasePO> showPurchase() throws RemoteException;
	/**
	 * 展示进货退货单
	 * 
	 * @return po列表
	 * @author zlk
	 */
	public ArrayList<PurchasePO> showReturn() throws RemoteException;
	/**
	 * 展示销售出货单
	 * 
	 * @return po列表
	 * @author zlk
	 */
	public ArrayList<SalesPO> showSales() throws RemoteException;
	/**
	 * 展示销售退货单
	 * 
	 * @return po列表
	 * @return zlk
	 */
	public ArrayList<SalesPO> showSalesReturn() throws RemoteException;
}
