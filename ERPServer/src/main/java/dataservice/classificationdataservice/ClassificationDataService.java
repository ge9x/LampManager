package dataservice.classificationdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ClassificationPO;
import util.ResultMessage;
/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface ClassificationDataService {
	/**
	 * 得到所有商品分类
	 * @return 所有商品分类的PO
	 * @throws RemoteException
	 */
	public ArrayList<ClassificationPO> show() throws RemoteException;
	/**
	 * 查找商品分类PO
	 * @param ID 商品分类ID
	 * @return 找到的商品分类PO
	 * @throws RemoteException
	 */
	public ClassificationPO findByID(int ID) throws RemoteException;
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
	/**
	 * 获取新建商品分类时自动生成的ID
	 * @return 自动生成的ID
	 * @throws RemoteException
	 */
	public String getNewID() throws RemoteException;
	/**
	 * 以商品分类的名称的关键字进行模糊查找
	 * @param keyword 名称关键字
	 * @return 查找到的商品分类的PO的集合
	 * @throws RemoteException
	 */
	public ArrayList<ClassificationPO> findFuzzilyByName(String keyword) throws RemoteException;
	/**
	 * 以商品分类的名称进行精确查找
	 * @param name 完整名称
	 * @return 查找到的商品分类的PO的集合
	 * @throws RemoteException
	 */
	public ArrayList<ClassificationPO> findFullyByName(String name) throws RemoteException;
}
