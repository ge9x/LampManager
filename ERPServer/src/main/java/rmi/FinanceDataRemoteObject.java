package rmi;

import dataservice.financedataservice.FinanceDataService;
import datastubdriver.FinanceDataService_Stub;
import po.BillPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kry·L on 2017/12/1.
 */
public class FinanceDataRemoteObject extends UnicastRemoteObject implements FinanceDataService{

    private FinanceDataService financeDataService;

    protected FinanceDataRemoteObject() throws RemoteException{
        financeDataService = new FinanceDataService_Stub();
    }

    @Override
    public String getNewReceiptID() throws RemoteException {
        return null;
    }

    @Override
    public String getNewPaymentID() throws RemoteException {
        return null;
    }

    @Override
    public String getNewCashBillID() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage addBill(BillPO po) throws RemoteException {
        return financeDataService.addBill(po);
    }

    @Override
    public ResultMessage deleteBill(BillPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage updateBill(BillPO po) throws RemoteException {
        return null;
    }
}
