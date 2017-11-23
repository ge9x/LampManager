package bl.financialbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.SalesDetailsInput;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class FinanceController implements FinanceBLService{

    private Finance finance;

    public FinanceController(){
        finance = new Finance();
    }

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
