package dataservice.accountdataservice;

import po.AccountPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/22.
 */
public interface AccountDataService {
    /**
     * 添加一个银行账户
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage addAccount(AccountPO po) throws RemoteException;

    /**
     * 删除一个银行账户
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteAccount(AccountPO po) throws RemoteException;

    /**
     * 更新一个银行账户
     * @param po
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateAccount(AccountPO po) throws RemoteException;

    /**
     * 通过名称查找银行账户
     * @param keyword
     * @return 名称包含关键字的所有银行账户列表
     * @throws RemoteException
     */
    public ArrayList<AccountPO> findByName(String keyword) throws RemoteException;

    /**
     * 获得所有银行账户
     * @return 所有银行账户列表
     * @throws RemoteException
     */
    public ArrayList<AccountPO> show() throws RemoteException;
}

