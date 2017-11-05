package bl.financialbl;

import dataservice.financedataservice.FinanceDataService;
import po.BillPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

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

    public String getNewReceiptID() throws RemoteException {
        return null;
    }

    public String getNewPaymentID() throws RemoteException {
        return null;
    }

    public String getNewCashBillID() throws RemoteException {
        return null;
    }

    public ResultMessage addBill(BillPO po) throws RemoteException {
        return null;
    }

    public ResultMessage deleteBill(BillPO po) throws RemoteException {
        return null;
    }

    public ResultMessage updateBill(BillPO po) throws RemoteException {
        return null;
    }

    public ArrayList<BillPO> findBillByType(BillType typpe) throws RemoteException {
        return null;
    }

    public BillPO findBillByID(String ID) throws RemoteException {
        return null;
    }

    public ArrayList<BillPO> findBillByState(BillState state) throws RemoteException {
        return null;
    }

    public ArrayList<BillPO> show() throws RemoteException {
        return null;
    }

    public ArrayList<String> getAllIDByType(BillType type) throws RemoteException {
        return null;
    }

    public Date getDate(String ID) throws RemoteException {
        return null;
    }

    public String getCustomer(String ID) throws RemoteException {
        return null;
    }

    public String getSalesman(String ID) throws RemoteException {
        return null;
    }

    public String getInventory(String ID) throws RemoteException {
        return null;
    }
}
