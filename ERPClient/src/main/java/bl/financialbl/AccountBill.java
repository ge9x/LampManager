package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import rmi.FinanceRemoteHelper;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;

import java.rmi.RemoteException;
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

    private UserInfo userInfo;
    private CustomerInfo info;
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
        return financeDataService.addBill(new AccountBillPO());
    }
    public AccountBillVO save(){
        return null;
    }
    public ResultMessage update(AccountBillVO vo){
        return null;
    }

    public void voTopo(AccountBillVO vo){
    }
}
