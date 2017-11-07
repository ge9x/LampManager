package bl.financialbl;

import blservice.financeblservice.DocumentDetailsInput;
import blservice.financeblservice.FinanceBLService;
import blservice.financeblservice.SalesDetailsInput;
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

    public BillVO findByID(String ID) {
        return null;
    }

    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        return null;
    }

    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        return null;
    }

    public ResultMessage redCover(BillVO billVO) {
        return null;
    }

    public ResultMessage redCoverAndCopy(BillVO billVO) {
        return null;
    }

    public ProfitVO getProfit(Date startDate, Date endDate) {
        return null;
    }
}
