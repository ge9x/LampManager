package dataservice.formdataservice;

import po.BillPO;
import util.BillState;
import util.BillType;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/26.
 */
public interface FormDataService {

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
    public BillPO findBillByID(String ID) throws RemoteException;

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
    public Date getDate(String ID) throws RemoteException;

    /**
     * 获得该单据的客户
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String getCustomer(String ID) throws RemoteException;

    /**
     * 获得该单据的业务员
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String getSalesman(String ID) throws RemoteException;

    /**
     * 获得该单据的仓库
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String getInventory(String ID) throws RemoteException;

}
