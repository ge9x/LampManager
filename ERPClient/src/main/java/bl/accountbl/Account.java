package bl.accountbl;

import dataservice.accountdataservice.AccountDataService;
import po.AccountBillPO;
import po.AccountPO;
import rmi.AccountRemoteHelper;
import util.ResultMessage;
import vo.AccountVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Account{
    //TODO delete

    private AccountDataService accountDataService;
    ArrayList<AccountPO> accountPOS;

    public Account() {
        accountDataService = AccountRemoteHelper.getInstance().getAccountDataService();

    }

    public ResultMessage addAccount(AccountVO accountVO) throws RemoteException{
        AccountPO accountPO = voTopo(accountVO);
        ResultMessage re = accountDataService.addAccount(accountPO);
        return re;
    }

    public ResultMessage deleteAccount(String name) {
        return null;
    }

    public ArrayList<AccountVO> findAccountByName(String keyword) throws RemoteException{
        accountPOS = accountDataService.findByName(keyword);
        ArrayList<AccountVO> accountVOS = new ArrayList<>();
        for (AccountPO po:accountPOS){
            accountVOS.add(poTOvo(po));
        }
        return accountVOS;
    }

    public ResultMessage updateAccount(AccountVO accountVO) throws RemoteException {
        for (AccountPO accountPO:accountPOS){
            if (accountPO.getID() == Integer.parseInt(accountVO.accountID)) {
                accountPO.setName(accountVO.accountName);
                return accountDataService.updateAccount(accountPO);
            }
        }
        return ResultMessage.FAILED;
    }

    public ArrayList<AccountVO> show() throws RemoteException{
        accountPOS = accountDataService.show();
        ArrayList<AccountVO> accountVOS = new ArrayList<>();
        for (AccountPO po:accountPOS){
            accountVOS.add(poTOvo(po));
        }
        return accountVOS;
    }

    public ArrayList<String> getAllAccountName() throws RemoteException{
        ArrayList<String> accountNames = new ArrayList<>();
        for (AccountPO po:accountPOS){
            accountNames.add(po.getName());
        }
        return accountNames;
    }

    public AccountVO getAccountByID(String id) throws RemoteException {
        ArrayList<AccountPO> accountPOS = accountDataService.show();
        for (AccountPO po : accountPOS){
            if (po.getID() == Integer.parseInt(id)){
                return poTOvo(po);
            }
        }
        return null;
    }
    public AccountPO voTopo(AccountVO accountVO){
        return new AccountPO(accountVO.accountName,accountVO.money);
    }
    public AccountVO poTOvo(AccountPO accountPO){
        return new AccountVO(String.valueOf(accountPO.getID()),accountPO.getName(),accountPO.getMoney());
    }


}
