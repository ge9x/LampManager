package dataservice.inventorydataservice;

import java.rmi.RemoteException;

import po.InventoryBillPO;
import util.ResultMessage;
/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface InventoryDataService {
	/**
	 * 查找库存单据
	 * @param id 单据ID
	 * @return 找到的库存单据PO
	 * @throws RemoteException
	 */
	public InventoryBillPO findBill(String id) throws RemoteException;
	/**
	 * 添加库存单据
	 * @param po 待添加的单据PO
	 * @return 是否添加成功
	 * @throws RemoteException
	 */
	public ResultMessage addBill(InventoryBillPO po) throws RemoteException;
	/**
	 * 删除库存单据
	 * @param po 待删除的单据PO
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public ResultMessage deleteBill(InventoryBillPO po) throws RemoteException;
	/**
	 * 修改库存单据
	 * @param po 已修改的单据PO
	 * @return 是否修改成功
	 * @throws RemoteException
	 */
	public ResultMessage updateBill(InventoryBillPO po) throws RemoteException;
}
