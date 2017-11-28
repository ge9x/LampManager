package dataservice.financedataservice;

import po.BillPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/10/22.
 */
public interface FinanceDataService {

    /**
     * 获得新的收款单ID
     * @return
     * @throws RemoteException
     */
    public int getNewReceiptID() throws RemoteException;

    /**
     * 获得新的付款单ID
     * @return
     * @throws RemoteException
     */
    public int getNewPaymentID() throws RemoteException;

    /**
     * 获得新的现金费用单
     * @return
     * @throws RemoteException
     */
    public int getNewCashBillID() throws RemoteException;

    /**
     * 添加新的单据
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage addBill(BillPO po) throws RemoteException;

    /**
     * 删除单据
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteBill(BillPO po) throws RemoteException;

    /**
     * 更新单据
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateBill(BillPO po) throws RemoteException;

    /**
     * 根据单据类型查找
     * @param typpe
     * @return
     * @throws RemoteException
     */
    public ArrayList<BillPO> findBillByType(BillType typpe) throws RemoteException;

    /**
     * 根据单据ID查找
     * @param ID
     * @return
     * @throws RemoteException
     */
    public BillPO findBillByID(int ID) throws RemoteException;

    /**
     * 根据单据状态查找
     * @param state
     * @return
     * @throws RemoteException
     */
    public ArrayList<BillPO> findBillByState(BillState state) throws RemoteException;

    /**
     * 显示所有单据
     * @return
     * @throws RemoteException
     */
    public ArrayList<BillPO> show() throws RemoteException;

    /**
     * 通过单据类型得到所有该类型单据ID
     * @param type
     * @return
     * @throws RemoteException
     */
    public ArrayList<String> getAllIDByType(BillType type) throws RemoteException;

    /**
     * 获得该单据的最后修改时间
     * @param ID
     * @return
     * @throws RemoteException
     */
    public Date getDate(int ID) throws RemoteException;

    /**
     * 获得该单据的客户
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String getCustomer(int ID) throws RemoteException;

    /**
     * 获得该单据的业务员
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String getSalesman(int ID) throws RemoteException;

    /**
     * 获得该单据的仓库
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String getInventory(int ID) throws RemoteException;

}
