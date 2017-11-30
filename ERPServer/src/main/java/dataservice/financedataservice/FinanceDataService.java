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

}
