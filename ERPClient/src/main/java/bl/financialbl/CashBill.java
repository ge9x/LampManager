package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import datastubdriver.FinanceDataService_Stub;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.CashBillItemVO;
import vo.CashBillVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class CashBill {
    CashBillVO cashBillVO;
    private ArrayList<CashBillItem> cashBillItems;

    private UserInfo userInfo;
    private AccountInfo accountInfo;
    private FinanceDataService financeDataService;

    public CashBill(){
        financeDataService = new FinanceDataService_Stub();
    }

    public String getNewCashBillID() throws RemoteException {
        return financeDataService.getNewCashBillID();
    }
    public void addBillItem(CashBillItemVO vo){

    }
    public void addCashBill(String account){

    }
    public CashBillVO submit(){
        return null;
    }
    public void calTotal(){};
    public CashBillVO save(){
        return null;
    }
    public ResultMessage update(CashBillVO vo) {
        return null;
    }
}
