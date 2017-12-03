package bl.financialbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.SalesDetailsInput;
import com.sun.org.apache.regexp.internal.RE;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
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
        try {
            return finance.getNewReceiptID();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNewPaymentID() {
        try {
            return finance.getNewPaymentID();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNewCashBillID() {
        try {
            return finance.getNewCashBillID();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserID() {
        return finance.getUserID();
    }

    public ArrayList<CustomerVO> getAllCustomer() {
        return finance.getAllCustomer();
    }

    public ArrayList<AccountVO> getAllAccount() {
        return finance.getAllAccount();
    }

    public ResultMessage submit(AccountBillVO vo) {
        try {
            return finance.submit(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    public ResultMessage submit(CashBillVO vo) {
        try {
            return finance.submit(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage save(AccountBillVO vo) {
        try {
            return finance.save(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage save(CashBillVO vo) {
        return finance.save(vo);
    }

    public ResultMessage updateDraft(AccountBillVO vo) {
        return null;
    }

    public ResultMessage updateDraft(CashBillVO vo) {
        return null;
    }

    @Override
    public ArrayList<AccountBillVO> getDraftAccountBills() {
        try {
            return finance.getDraftAccountBills();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<AccountBillVO> getSubmittedAccountBills() {
        return null;
    }

    @Override
    public ArrayList<AccountBillVO> getPassAccountBills() {
        return null;
    }

    @Override
    public ArrayList<AccountBillVO> getFailedAccountBills() {
        return null;
    }

    @Override
    public String getCustomerNameByID(String ID) {
        return null;
    }
}
