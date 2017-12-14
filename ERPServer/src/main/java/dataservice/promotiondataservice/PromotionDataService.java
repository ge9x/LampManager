package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsItemPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionTotalPO;
import util.ResultMessage;

/** 
 * Created by Aster on 2017/10/21
 */
public interface PromotionDataService extends Remote{
	/**
     * 按促销策略ID进行查找返回相应的PromotionCustomerPO结果
     * 
     * @param promotionID
     * @return promotionCustomerPO
     */
	public PromotionCustomerPO findPC(String ID) throws RemoteException;
	/**
	 * 按促销策略ID进行查找返回相应的PromotionBargainPO结果
	 * 
	 * @param ID
	 * @return
	 * @throws RemoteException
	 */
	public PromotionBargainPO findPB(String ID) throws RemoteException;
	/**
	 * 按促销策略ID进行查找返回相应的PromotionTotalPO结果
	 * 
	 * @param ID
	 * @return
	 * @throws RemoteException
	 */
	public PromotionTotalPO findPT(String ID) throws RemoteException;
	/**
	 * 添加会员促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addPC(PromotionCustomerPO po) throws RemoteException;
	/**
	 * 添加特价包
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addPB(PromotionBargainPO po) throws RemoteException;
	/**
	 * 添加总价促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addPT(PromotionTotalPO po) throws RemoteException;
	/**
	 * 删除会员促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage deletePC(PromotionCustomerPO po) throws RemoteException;
	/**
	 * 删除特价包
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage deletePB(PromotionBargainPO po) throws RemoteException;
	/**
	 * 删除总价促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage deletePT(PromotionTotalPO po) throws RemoteException;
	/**
	 * 修改会员促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updatePC(PromotionCustomerPO po) throws RemoteException;
	/**
	 * 修改特价包
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updatePB(PromotionBargainPO po) throws RemoteException;
	/**
	 * 修改总价促销策略
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updatePT(PromotionTotalPO po) throws RemoteException;
	/**
	 * 展示所有的特价包
	 * 
	 * @return
	 * @throws RemoteException
	 */
    public ArrayList<PromotionBargainPO> showPB() throws RemoteException;
    /**
     * 展示所有的会员促销策略
     * 
     * @return
     * @throws RemoteException
     */
    public ArrayList<PromotionCustomerPO> showPC() throws RemoteException;
    /**
     * 展示所有的总价促销策略
     * 
     * @return
     * @throws RemoteException
     */
    public ArrayList<PromotionTotalPO> showPT() throws RemoteException;
    /**
     * 得到新的特价包编号
     * 
     * @return
     * @throws RemoteException
     */
    public String getNewPromotionBargainID() throws RemoteException;
    /**
     * 得到新的会员促销编号
     * 
     * @return
     * @throws RemoteException
     */
    public String getNewPromotionCustomerID() throws RemoteException;
    /**
     * 得到新的总价促销编号
     * 
     * @return
     * @throws RemoteException
     */
    public String getNewPromotionTotalID() throws RemoteException;
    /**
     * 增加商品清单
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException;
}
