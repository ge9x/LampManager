package dataservice.promotiondataservice;

import java.rmi.RemoteException;

/** 
 * Created by Aster on 2017/10/21
 */
public interface PromotionDataService {

	/**
     * 返回促销策略ID
     * 
     * @return promotionID
     */
	public String getID() throws RemoteException;
	
	/**
     * 按促销策略ID进行查找返回相应的PromotionPO结果
     * 
     * @param promotionID
     * @return promotionPO
     */
	public PromotionPO find(String ID) throws RemoteException;
	
	public ResultMessage add(PromotionPO po) throws RemoteException;
	
	public ResultMessage delete(PromotionPO po) throws RemoteException;
	
	public ResultMessage update(PromotionPO po) throws RemoteException;
	
	public void init() throws RemoteException;
}
