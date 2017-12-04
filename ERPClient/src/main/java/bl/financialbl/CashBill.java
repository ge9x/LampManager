package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.userblservice.UserInfo;
import com.sun.org.apache.regexp.internal.RE;
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
    ArrayList<CashBillPO> cashBillPOS;

    public CashBill(){
        financeDataService = new FinanceDataService_Stub();
    }

    public String getNewCashBillID() throws RemoteException {
        return financeDataService.getNewCashBillID();
    }
    public ResultMessage submit(CashBillVO vo) throws RemoteException {
        return financeDataService.addBill(voTopo(vo));
    }
    public ResultMessage save(CashBillVO vo) throws RemoteException {
        return financeDataService.addBill(voTopo(vo));
    }

    public ResultMessage update(CashBillVO vo) throws RemoteException {
        cashBillPOS = financeDataService.getAllCashBills();
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        for (CashBillPO po : cashBillPOS){
            if (po.getTurn() == turn){
                po.setState(vo.state);
                if (vo.accountID != "")
                    po.setAccountID(Integer.parseInt(vo.accountID));
                else
                    po.setAccountID(0);
                po.setSum(vo.sum);
                po.setDate(vo.date);
                po.getCashBillItemPOS().clear();
                ArrayList<CashBillItemVO> itemVOS = vo.cashBillItems;
                for (CashBillItemVO itemVO:itemVOS){
                    CashBillItemPO itemPO = CashBillItem.voTopo(itemVO);
                    po.getCashBillItemPOS().add(itemPO);
                }
                return financeDataService.updateBill(po);
            }
        }
        return ResultMessage.FAILED;
    }

    public CashBillPO voTopo(CashBillVO vo){
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        ArrayList<CashBillItemPO> itemPOS = new ArrayList<>();
        for(CashBillItemVO itemVO : vo.cashBillItems){
            itemPOS.add(CashBillItem.voTopo(itemVO));
        }
        CashBillPO po = new CashBillPO(vo.date, vo.type, vo.state, vo.userName, Integer.parseInt(vo.accountID), itemPOS, vo.sum, turn);
        return po;
    }

    public ResultMessage deleteBill(String id) throws RemoteException {
        ArrayList<CashBillPO> cashBillPOS = financeDataService.getAllCashBills();
        int turn = Integer.parseInt(id.split("-")[2]);
        for (CashBillPO po : cashBillPOS){
            if (po.getTurn() == turn)
                return financeDataService.deleteBill(po);
        }
        return ResultMessage.FAILED;
    }
}
