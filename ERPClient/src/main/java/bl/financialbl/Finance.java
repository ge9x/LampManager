package bl.financialbl;

import bl.accountbl.Account;
import bl.accountbl.AccountController;
import bl.customerbl.Customer;
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
import po.AccountBillPO;
import util.BillState;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Finance{

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
            customerVOS.add(customerInfo.getCustomerByID(id));
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

    public ResultMessage save(AccountBillVO vo) throws RemoteException {
        return accountBill.save(vo);
    }

    public ResultMessage save(CashBillVO vo) throws RemoteException {
        return cashBill.save(vo);
    }

    public ResultMessage updateDraft(AccountBillVO vo) throws RemoteException {
        return accountBill.update(vo);
    }

    public ResultMessage updateDraft(CashBillVO vo) throws RemoteException {
        return cashBill.update(vo);
    }


    public String getCustomerNameByID(String id) {
        CustomerVO vo = customerInfo.getCustomerByID(Integer.parseInt(id));
        return vo.customerName;
    }

    public String getAccountNameByID(String accountID) {
        return accountInfo.getAccountByID(accountID).accountName;
    }

    public ResultMessage deleteAccountBill(String id) throws RemoteException {
        return accountBill.deleteBill(id);

    }

    public ResultMessage deleteCashBill(String id) throws RemoteException {
        return cashBill.deleteBill(id);
    }

    public ArrayList<AccountBillVO> getReceiptsByState(BillState state) throws RemoteException {
        return accountBill.getReceiptsByState(state);
    }

    public ArrayList<AccountBillVO> getPaymentsByState(BillState state) throws RemoteException {
        return accountBill.getPaymentsByState(state);
    }


    public ResultMessage examine(AccountBillVO vo) throws RemoteException {
        return accountBill.examine(vo);
    }
    public ResultMessage examine(CashBillVO vo) throws RemoteException {
        return cashBill.examine(vo);
    }
}
