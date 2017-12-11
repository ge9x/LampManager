package dataservice.inventorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InventoryBillPO;
import po.InventoryPO;
import util.Criterion;
import util.ResultMessage;
/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface InventoryDataService extends Remote{
	/**
	 * 得到所有库存报溢单、报损单和赠送单
	 * @return 所有库存报溢单、报损单和赠送单的PO
	 * @throws RemoteException
	 */
	public ArrayList<InventoryBillPO> show() throws RemoteException;
	/**
	 * 得到所有仓库名
	 * @return 包含所有仓库名的PO的链表
	 * @throws RemoteException
	 */
	public ArrayList<InventoryPO> showInventory() throws RemoteException;
	/**
	 * 得到所有库存报警单
	 * @return 所有库存报警单的PO
	 * @throws RemoteException
	 */
	public ArrayList<InventoryBillPO> showAlarm() throws RemoteException;
	/**
	 * 查找库存单据
	 * @param ID 单据ID
	 * @return 找到的库存单据PO
	 * @throws RemoteException
	 */
	public InventoryBillPO findBill(int ID) throws RemoteException;
	/**
	 * 新增仓库名
	 * @param po 等待新增的仓库名PO
	 * @return 是否新增成功
	 * @throws RemoteException
	 */
	public ResultMessage addInventroy(InventoryPO po) throws RemoteException;
	/**
	 * 添加库存单据
	 * @param po 待添加的单据PO
	 * @return 是否添加成功
	 * @throws RemoteException
	 */
	public ResultMessage addBill(InventoryBillPO po) throws RemoteException;
	/**
	 * 删除仓库名
	 * @param po 等待删除的仓库名PO
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public ResultMessage deleteInventory(InventoryPO po) throws RemoteException;
	/**
	 * 删除库存单据
	 * @param po 待删除的单据PO
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public ResultMessage deleteBill(InventoryBillPO po) throws RemoteException;
	/**
	 * 修改仓库名
	 * @param po 已修改的仓库名PO
	 * @return 是否修改成功
	 * @throws RemoteException
	 */
	public ResultMessage updateInventory(InventoryPO po) throws RemoteException;
	/**
	 * 修改库存单据
	 * @param po 已修改的单据PO
	 * @return 是否修改成功
	 * @throws RemoteException
	 */
	public ResultMessage updateBill(InventoryBillPO po) throws RemoteException;
	/**
	 * 自定义库存类单据高级查询（详见Criterion）
	 * @param criteria 条件列表
	 * @return 符合条件的库存类单据PO的列表
	 * @throws RemoteException
	 * @see Criterion
	 */
	public ArrayList<InventoryBillPO> advancedQuery(ArrayList<Criterion> criteria) throws RemoteException;
}
