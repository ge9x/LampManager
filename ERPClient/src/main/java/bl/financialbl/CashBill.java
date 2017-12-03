package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import datastubdriver.FinanceDataService_Stub;
import po.AccountBillItemPO;
import po.CashBillItemPO;
import po.CashBillPO;
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

    private FinanceDataService financeDataService;

    public CashBill(){
        financeDataService = new FinanceDataService_Stub();
    }

    public String getNewCashBillID() throws RemoteException {
        return financeDataService.getNewCashBillID();
    }
    public ResultMessage submit(CashBillVO vo) throws RemoteException {
        return financeDataService.addBill(voTopo(vo));
    }
    public CashBillVO save(){
        return null;
    }

    public ResultMessage update(CashBillVO vo) {
        return null;
    }

    public CashBillPO voTopo(CashBillVO vo){
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        ArrayList<CashBillItemPO> itemPOS = new ArrayList<>();
        for(CashBillItemVO itemVO : vo.cashBillItems){
            itemPOS.add(CashBillItem.voTopo(itemVO));
        }
        CashBillPO po = new CashBillPO(vo.date, vo.type, vo.state, vo.userName, Integer.parseInt(vo.accountName), itemPOS, vo.sum, turn);
        return po;
    }
}
