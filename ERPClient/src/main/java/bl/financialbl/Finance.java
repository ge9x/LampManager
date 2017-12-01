package bl.financialbl;

import bl.formbl.DocumentDetails;
import bl.formbl.Profit;
import bl.formbl.SalesDetails;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.SalesDetailsInput;
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

    AccountBill accountBill;
    CashBill cashBill;

    private FinanceDataService financeDataService;

    public Finance(){
        accountBill = new AccountBill();
        cashBill = new CashBill();
    }

    public String getNewReceiptID() throws RemoteException {
        return accountBill.getNewReceiptID();
    }

    public String getNewPaymentID() throws RemoteException {
        return accountBill.getNewPaymentID();
    }

    public String getNewCashBillID() {
        return cashBill.getNewCashBillID();
    }

    public String getUserID() {
        return null;
    }

    public ArrayList<CustomerVO> getAllCustomer() {
        return null;
    }

    public ArrayList<AccountVO> getAllAccount() {
        return null;
    }

    public ResultMessage submit(AccountBillVO vo) throws RemoteException{
        return accountBill.submit(vo);
    }

    public ResultMessage submit(CashBillVO vo) {
        return null;
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
