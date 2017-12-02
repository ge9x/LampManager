package bl.financialbl;

import bl.accountbl.AccountController;
import bl.customerbl.CustomerController;
import bl.formbl.DocumentDetails;
import bl.formbl.Profit;
import bl.formbl.SalesDetails;
import bl.userbl.MockUser;
import bl.userbl.UserController;
import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.SalesDetailsInput;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Finance{
    //TODO getAllCustomer接口未定

    AccountBill accountBill;
    CashBill cashBill;

    private FinanceDataService financeDataService;
    UserInfo userInfo;
    CustomerInfo customerInfo;
    AccountInfo accountInfo;


    public Finance(){
        accountBill = new AccountBill();
        cashBill = new CashBill();
        userInfo = new UserController();
        customerInfo = new CustomerController();
        accountInfo = new AccountController();
    }

    public String getNewReceiptID() throws RemoteException {
        return accountBill.getNewReceiptID();
    }

    public String getNewPaymentID() throws RemoteException {
        return accountBill.getNewPaymentID();
    }

    public String getNewCashBillID() throws RemoteException {
        return cashBill.getNewCashBillID();
    }

    public String getUserID() {
        String ID = userInfo.getCurrentUserID();
        return userInfo.getCurrentUserNameByID(ID);
    }

    public ArrayList<CustomerVO> getAllCustomer() {
        ArrayList<CustomerVO> customerVOS = new ArrayList<>();
        ArrayList<Integer> IDs = customerInfo.getAllCustomerID();
        for (int id:IDs){
            customerVOS.add(customerInfo.getCustomerByID());
        }
        return customerVOS;
    }

    public ArrayList<AccountVO> getAllAccount() {
        return accountInfo.show();
    }

    public ResultMessage submit(AccountBillVO vo) throws RemoteException{
        return accountBill.submit(vo);
    }

    public ResultMessage submit(CashBillVO vo) throws RemoteException {
        return cashBill.submit(vo);
    }

    public ResultMessage save(AccountBillVO vo) {
        return null;
    }

    public ResultMessage save(CashBillVO vo) {
        return null;
    }

    public ResultMessage updateDraft(AccountBillVO vo) {
        return null;
    }

    public ResultMessage updateDraft(CashBillVO vo) {
        return null;
    }


}
