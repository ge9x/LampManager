package bl.financialbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.SalesDetailsInput;
import com.sun.org.apache.regexp.internal.RE;
import util.BillState;
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
        try {
            return finance.save(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage updateDraft(AccountBillVO vo) {
        try {
            return finance.updateDraft(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage updateDraft(CashBillVO vo) {
        try {
            return finance.updateDraft(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
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
        try {
            return finance.getSubmittedAccountBills();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<AccountBillVO> getPassAccountBills() {
        try {
            return finance.getPassAccountBills();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<AccountBillVO> getFailedAccountBills() {
        try {
            return finance.getFailedAccountBills();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage deleteDraftAccountBill(String ID) {
        try {
            return finance.deleteAccountBill(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage deleteDraftCashBill(String ID) {
        try {
            return finance.deleteCashBill(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public String getAccountNameByID(String accountID) {
        return finance.getAccountNameByID(accountID);
    }

    @Override
    public ArrayList<AccountBillVO> getPaymentsByState(BillState state) {
        try {
            return finance.getPaymentsByState(state);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<AccountBillVO> getReceiptsByState(BillState state) {
        try {
            return finance.getReceiptsByState(state);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    public String getCustomerNameByID(String ID) {
        return finance.getCustomerNameByID(ID);
    }

}
