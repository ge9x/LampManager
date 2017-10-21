package dataservice.classificationdataservice;

import java.rmi.RemoteException;

import po.ClassificationPO;
import util.ResultMessage;
/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface ClassificationDataService {
	/**
	 * 查找商品分类PO
	 * @param id 商品分类ID
	 * @return 找到的商品分类PO
	 * @throws RemoteException
	 */
	public ClassificationPO find(String id) throws RemoteException;
	/**
	 * 添加商品分类
	 * @param po 待添加的商品分类PO
	 * @return 是否添加成功
	 * @throws RemoteException
	 */
	public ResultMessage add(ClassificationPO po) throws RemoteException;
	/**
	 * 删除商品分类
	 * @param po 待删除的商品分类PO
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public ResultMessage delete(ClassificationPO po) throws RemoteException;
	/**
	 * 修改商品分类
	 * @param po 已修改的商品分类PO
	 * @return 是否修改成功
	 * @throws RemoteException
	 */
	public ResultMessage update(ClassificationPO po) throws RemoteException;
}
