package bl.financialbl;

import bl.accountbl.AccountController;
import blservice.accountblservice.AccountInfo;
import blservice.userblservice.UserInfo;
import com.sun.org.apache.regexp.internal.RE;
import dataimpl.financedataimpl.FinanceDataServiceImpl;
import dataservice.financedataservice.FinanceDataService;
import datastubdriver.FinanceDataService_Stub;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.CashBillItemPO;
import po.CashBillPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.CashBillItemVO;
import vo.CashBillVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class CashBill {

    private FinanceDataService financeDataService;
    ArrayList<CashBillPO> cashBillPOS;
    AccountInfo accountInfo;

    public CashBill(){
        financeDataService = FinanceDataServiceImpl.getInstance();
        accountInfo = new AccountController();
        cashBillPOS = new ArrayList<>();
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



    public ResultMessage deleteBill(String id) throws RemoteException {
        ArrayList<CashBillPO> cashBillPOS = financeDataService.getAllCashBills();
        int turn = Integer.parseInt(id.split("-")[2]);
        for (CashBillPO po : cashBillPOS){
            if (po.getTurn() == turn)
                return financeDataService.deleteBill(po);
        }
        return ResultMessage.FAILED;
    }

    public ResultMessage examine(CashBillVO vo) throws RemoteException {
        accountInfo.changeMoney(vo.accountID,-vo.sum);
        return update(vo);
    }

    public ArrayList<CashBillVO> getCashBillByState(BillState state) throws RemoteException {
        cashBillPOS.clear();
        ArrayList<CashBillVO> vos = new ArrayList<>();
        ArrayList<CashBillPO> pos = financeDataService.getAllCashBills();
        for(CashBillPO po : pos){
            if (po.getState() == state){
                cashBillPOS.add(po);
                vos.add(poTovo(po));
            }
        }
        return vos;
    }

    public CashBillPO voTopo(CashBillVO vo){
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        ArrayList<CashBillItemPO> itemPOS = new ArrayList<>();
        for(CashBillItemVO itemVO : vo.cashBillItems){
            itemPOS.add(CashBillItem.voTopo(itemVO));
        }
        int accountID = 0;
        if (vo.accountID != ""){
            accountID = Integer.parseInt(vo.accountID);
        }
        CashBillPO po = new CashBillPO(vo.date, vo.type, vo.state, vo.userName, accountID, itemPOS, vo.sum, turn);
        return po;
    }

    public CashBillVO poTovo(CashBillPO po){
        ArrayList<CashBillItemVO> itemVOS = new ArrayList<>();
        for (CashBillItemPO itemPO : po.getCashBillItemPOS()){
            itemVOS.add(CashBillItem.poTovo(itemPO));
        }
        CashBillVO vo = new CashBillVO(po.getDate(),po.buildID(),po.getState(),po.getType(),po.getUserName(),String.valueOf(po.getAccountID()),itemVOS,po.getSum());
        return vo;
    }
}
