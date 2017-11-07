package bl.financialbl;

import blservice.financeblservice.DocumentDetailsInput;
import blservice.financeblservice.FinanceBLService;
import blservice.financeblservice.SalesDetailsInput;
import dataservice.financedataservice.FinanceDataService;
import po.BillPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Finance{

    private FinanceBill financeBill;
    private SalesDetails salesDetails;
    private DocumentDetails documentDetails;
    private Profit profit;

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
