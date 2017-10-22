package dataservice.salesdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PurchasePO;
import po.SalesPO;
import util.ResultMessage;

/**
 * created by zlk on 2017/10/21
 */

public interface SalesDataService {
	/**
	 * 通过单据编号查找进货单据
	 * 
	 * @param ID
	 * @return 查找到的进货单据
	 * @author zlk
	 */
	public PurchasePO findPurchase(String ID) throws RemoteException;
	/**
	 * 通过单据编号查找销售单据
	 * 
	 * @param ID
	 * @return 查找到的销售单据
	 * @author zlk
	 */
	public SalesPO findSlaes(String ID) throws RemoteException;
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
	public ResultMessage deletePurchase(PurchasePO po) throws RemoteException;
	/**
	 * 删除销售单据
	 * 
	 * @param po
	 * @return 是否成功删除销售单据
	 * @author zlk
	 */
	public ResultMessage deleteSales(SalesPO po) throws RemoteException;
	/**
	 * 初始化持久化数据库
	 * 
	 * @author zlk
	 */
	public void init() throws RemoteException;
	/**
	 * 展示进货单据
	 * 
	 * @return po列表
	 * @author zlk
	 */
	public ArrayList<PurchasePO> showPurchase() throws RemoteException;
	/**
	 * 展示销售单据
	 * 
	 * @return po列表
	 * @author zlk
	 */
	public ArrayList<SalesPO> showSales() throws RemoteException;
}
