package dataservice.promotiondataservice;

import java.rmi.RemoteException;

import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionPO;
import po.PromotionTotalPO;
import util.PromotionType;
import util.ResultMessage;

/** 
 * Created by Aster on 2017/10/21
 */
public interface PromotionDataService {

	/**
     * 根据促销类型返回促销策略ID
     * 
     * @return promotionID
     */
	public String getID(PromotionType type) throws RemoteException;
	
	/**
     * 按促销策略ID进行查找返回相应的PromotionPO结果
     * 
     * @param promotionID
     * @return promotionPO
     */
	public PromotionPO find(String ID) throws RemoteException;
	/**
	 * 添加会员促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage add(PromotionCustomerPO po) throws RemoteException;
	/**
	 * 添加特价包
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage add(PromotionBargainPO po) throws RemoteException;
	/**
	 * 添加总价促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage add(PromotionTotalPO po) throws RemoteException;
	/**
	 * 删除促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage delete(PromotionPO po) throws RemoteException;
	/**
	 * 修改促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage update(PromotionPO po) throws RemoteException;
	/**
	 * 初始化促销策略
	 * 
	 * @throws RemoteException
	 */
	public void init() throws RemoteException;
}
