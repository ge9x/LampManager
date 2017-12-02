package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.AccountPO;
import rmi.FinanceRemoteHelper;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBill {

    private AccountBillVO accountBill;
    private ArrayList<AccountBillItem> accountBillItems;
    private AccountBillItem accountBillItem;
    FinanceDataService financeDataService;

    private AccountInfo accountInfo;

    public AccountBill(){
        financeDataService = FinanceRemoteHelper.getInstance().getFinanceDataService();
    }
    public HashMap<String,String> getAllCustomers(){
        return null;
    }

    public ArrayList<String> getAllAccount(){
        return null;
    }

    public String getNewReceiptID() throws RemoteException {
        return financeDataService.getNewReceiptID();
    }

    public String getNewPaymentID() throws RemoteException {
        return financeDataService.getNewPaymentID();
    }

    public void addBillItem(AccountBillItemVO vo){

    }
    public void addAccountBill(String customer,String account){

    }
    public void calTotal(){

    }
    public ResultMessage submit(AccountBillVO vo) throws RemoteException{
        return financeDataService.addBill(voTopo(vo));
    }
    public AccountBillVO save(){
        return null;
    }
    public ResultMessage update(AccountBillVO vo){
        return null;
    }

    public AccountBillPO voTopo(AccountBillVO vo){
        ArrayList<AccountBillItemPO> accountBillItemPOS = new ArrayList<>();
        for (AccountBillItemVO accountBillItemVO : vo.accountBillItems){
            AccountBillItemPO accountBillItemPO = new AccountBillItemPO(accountBillItemVO.account)
        }
        AccountBillPO accountBillPO = new AccountBillPO(vo.date,vo.type,vo.state,vo.customerID,vo.userName,)
    }
}
