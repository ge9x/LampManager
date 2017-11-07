package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.userblservice.UserInfo;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBill {

    private AccountBillVO accountBill;
    private ArrayList<AccountBillItem> accountBillItems;

    private UserInfo userInfo;
    private CustomerInfo info;
    private AccountInfo accountInfo;

    public AccountBill(){

    }
    public HashMap<String,String> getAllCustomers(){
        return null;
    }

    public ArrayList<String> getAllAccount(){
        return null;
    }
    public void addBillItem(AccountBillItemVO vo){

    }
    public void addAccountBill(String customer,String account){

    }
    public void calTotal(){

    }
    public AccountBillVO submit(){
        return null;
    }
    public AccountBillVO save(){
        return null;
    }
    public ResultMessage update(AccountBillVO vo){
        return null;
    }
}
