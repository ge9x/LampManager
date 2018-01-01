package bl.financialbl;

import bl.accountbl.AccountBLFactory;
import bl.accountbl.AccountController;
import bl.logbl.LogBLFactory;
import blservice.accountblservice.AccountInfo;
import blservice.logblservice.LogInfo;
import blservice.userblservice.UserInfo;
import com.sun.org.apache.regexp.internal.RE;
import dataimpl.financedataimpl.FinanceDataServiceImpl;
import dataservice.financedataservice.FinanceDataService;
import datastubdriver.FinanceDataService_Stub;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.CashBillItemPO;
import po.CashBillPO;
import util.*;
import vo.AccountBillVO;
import vo.CashBillItemVO;
import vo.CashBillVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class CashBill {

    private FinanceDataService financeDataService;
    private LogInfo logInfo;
    ArrayList<CashBillPO> cashBillPOS;
    AccountInfo accountInfo;

    public CashBill(){
        financeDataService = FinanceDataServiceImpl.getInstance();
        logInfo = LogBLFactory.getInfo();
        accountInfo = AccountBLFactory.getInfo();
        cashBillPOS = new ArrayList<>();
    }

    public String getNewCashBillID() throws RemoteException {
        return financeDataService.getNewCashBillID();
    }
    public ResultMessage submit(CashBillVO vo) throws RemoteException {
        CashBillPO po = voTopo(vo);
        ResultMessage re = financeDataService.addBill(po);

        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.REDCOVERANDCOPY,OperationObjectType.BILL,po.toString());
        }
        return re;
    }
    public ResultMessage save(CashBillVO vo) throws RemoteException {
        CashBillPO po = voTopo(vo);
        ResultMessage re = financeDataService.addBill(po);
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.REDCOVERANDCOPY,OperationObjectType.BILL,po.toString());
        }
        return re;
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
                ResultMessage re = financeDataService.updateBill(po);
                if (re == ResultMessage.SUCCESS){
                    logInfo.record(OperationType.REDCOVERANDCOPY,OperationObjectType.BILL,po.toString());
                }
                return re;
            }
        }
        return ResultMessage.FAILED;
    }



    public ResultMessage deleteBill(String id) throws RemoteException {
        ArrayList<CashBillPO> cashBillPOS = financeDataService.getAllCashBills();
        int turn = Integer.parseInt(id.split("-")[2]);
        for (CashBillPO po : cashBillPOS){
            if (po.getTurn() == turn) {
                ResultMessage re = financeDataService.deleteBill(po);
                if (re == ResultMessage.SUCCESS) {
                    logInfo.record(OperationType.REDCOVERANDCOPY, OperationObjectType.BILL, po.toString());
                }
                return re;
            }
        }
        return ResultMessage.FAILED;
    }

    public ResultMessage examine(CashBillVO vo) throws RemoteException {
        if(vo.state == BillState.PASS){
            accountInfo.changeMoney(vo.accountID, -vo.sum);
            logInfo.record(OperationType.REDCOVERANDCOPY,OperationObjectType.BILL,voTopo(vo).toString());
        }
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

    public ArrayList<CashBillVO> getBillsByDate(String startDate, String endDate) throws RemoteException {
        ArrayList<CashBillVO> cashBillVOS = new ArrayList<>();
        if (cashBillPOS == null || cashBillPOS.isEmpty()){
            cashBillPOS = financeDataService.getAllCashBills();
        }
        for (CashBillPO po : cashBillPOS){
            LocalDate billDate = LocalDate.parse(po.getDate());
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            if (((billDate.isBefore(end) && billDate.isAfter(start) )|| billDate.isEqual(start) || billDate.isEqual(end)) && po.getState() == BillState.PASS ) {
                cashBillVOS.add(poTovo(po));
            }
        }
        return cashBillVOS;
    }

    public ResultMessage redCover(CashBillVO billVO) throws RemoteException {
        String ID = getNewCashBillID();
        billVO.ID = ID;
        for (CashBillItemVO itemVO:billVO.cashBillItems){
            itemVO.money = -itemVO.money;
        }
        billVO.sum = billVO.calSum();
        submit(billVO);
        ResultMessage re = examine(billVO);
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.REDCOVERANDCOPY,OperationObjectType.BILL,voTopo(billVO).toString());
        }
        return re;
    }

    public ResultMessage redCoverAndCopy(CashBillVO billVO) throws RemoteException {
        redCover(billVO);
        String ID = getNewCashBillID();
        billVO.ID = ID;
        billVO.state = BillState.DRAFT;
        ResultMessage re = save(billVO);
        if (re == ResultMessage.SUCCESS) {
            logInfo.record(OperationType.REDCOVERANDCOPY, OperationObjectType.BILL, voTopo(billVO).toString());
        }
        return re;
    }
}
