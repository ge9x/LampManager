package bl.financialbl;

import bl.formbl.DocumentDetails;
import bl.formbl.Profit;
import bl.formbl.SalesDetails;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.SalesDetailsInput;
import dataservice.financedataservice.FinanceDataService;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Finance{

    AccountBill accountBill;
    CashBill cashBill;

    private FinanceDataService financeDataService;

    public String getNewReceiptID() {
        return null;
    }

    public String getNewPaymentID() {
        return null;
    }

    public String getNewCashBillID() {
        return null;
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

    public ResultMessage submit(AccountBillVO vo) {
        return null;
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
