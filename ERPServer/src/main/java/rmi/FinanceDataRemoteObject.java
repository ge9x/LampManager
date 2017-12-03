package rmi;

import dataimpl.financedataimpl.FinanceDataServiceImpl;
import dataservice.financedataservice.FinanceDataService;
import datastubdriver.FinanceDataService_Stub;
import po.AccountBillPO;
import po.BillPO;
import po.CashBillPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/1.
 */
public class FinanceDataRemoteObject extends UnicastRemoteObject implements FinanceDataService{

    private FinanceDataService financeDataService;

    protected FinanceDataRemoteObject() throws RemoteException{
        financeDataService = FinanceDataServiceImpl.getInstance();
    }

    @Override
    public String getNewReceiptID() throws RemoteException {
        return financeDataService.getNewReceiptID();
    }

    @Override
    public String getNewPaymentID() throws RemoteException {
        return financeDataService.getNewPaymentID();
    }

    @Override
    public String getNewCashBillID() throws RemoteException {
        return financeDataService.getNewCashBillID();
    }

    @Override
    public ResultMessage addBill(BillPO po) throws RemoteException {
        return financeDataService.addBill(po);
    }

    @Override
    public ResultMessage deleteBill(BillPO po) throws RemoteException {
        return  financeDataService.deleteBill(po);
    }

    @Override
    public ResultMessage updateBill(BillPO po) throws RemoteException {
        return financeDataService.updateBill(po);
    }

    @Override
    public ArrayList<AccountBillPO> getAllAccountBills() throws RemoteException {
        return financeDataService.getAllAccountBills();
    }

    @Override
    public ArrayList<CashBillPO> getAllCashBills() throws RemoteException {
        return financeDataService.getAllCashBills();
    }
}
