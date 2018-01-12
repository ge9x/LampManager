package bl.financialbl;

import bl.accountbl.AccountBLFactory;
import bl.logbl.LogBLFactory;
import bl.messagebl.MessageBLFactory;
import blservice.accountblservice.AccountInfo;
import blservice.logblservice.LogInfo;
import blservice.messageblservice.MessageInfo;
import dataimpl.financedataimpl.FinanceDataServiceImpl;
import dataservice.financedataservice.FinanceDataService;
import po.CashBillItemPO;
import po.CashBillPO;
import util.*;
import vo.CashBillItemVO;
import vo.CashBillVO;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class CashBill {
    private FinanceDataService financeDataService;
    private LogInfo logInfo;
    private MessageInfo messageInfo;
    private AccountInfo accountInfo;

    ArrayList<CashBillPO> cashBillPOS;

    public CashBill(){
        financeDataService = FinanceDataServiceImpl.getInstance();
        logInfo = LogBLFactory.getInfo();
        messageInfo = MessageBLFactory.getInfo();
        accountInfo = AccountBLFactory.getInfo();
        cashBillPOS = new ArrayList<>();
    }

    public String getNewCashBillID() throws RemoteException {
        return financeDataService.getNewCashBillID();
    }
    public ResultMessage submit(CashBillVO vo) throws RemoteException {
        CashBillPO po = voTopo(vo);
        ResultMessage re = financeDataService.addBill(po);

        /**如果提交成功，就记录日志，并向总经理发送审批消息**/
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.ADD, OperationObjectType.BILL, po.toString());
            messageInfo.addMessage(vo.state,vo.ID, LocalDateTime.now().toString(),UserPosition.FINANCIAL_STAFF);
        }
        return re;
    }
    public ResultMessage save(CashBillVO vo) throws RemoteException {
        CashBillPO po = voTopo(vo);
        ResultMessage re = financeDataService.addBill(po);
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.ADD,OperationObjectType.BILL,po.toString());
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
                    logInfo.record(OperationType.UPDATE,OperationObjectType.BILL,po.toString());
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
                    logInfo.record(OperationType.DELETE, OperationObjectType.BILL, po.toString());
                }
                return re;
            }
        }
        return ResultMessage.FAILED;
    }

    public ResultMessage examine(CashBillVO vo) throws RemoteException {
        logInfo.close();
        ResultMessage re = update(vo);
        logInfo.open();

        /**如果审批成功，就记录日志，并且向单据制定人员发送消息**/
        if (vo.state != BillState.SUBMITTED && re == ResultMessage.SUCCESS) {
            accountInfo.changeMoney(vo.accountID, -vo.sum);
            logInfo.record(OperationType.EXAMINE, OperationObjectType.BILL, voTopo(vo).toString());
            messageInfo.addMessage(vo.state,vo.ID,LocalDateTime.now().toString(),UserPosition.FINANCIAL_STAFF);
        }
        return re;
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
        logInfo.close();
        String ID = getNewCashBillID();
        billVO.ID = ID;
        for (CashBillItemVO itemVO : billVO.cashBillItems) {
            itemVO.money = -itemVO.money;
        }
        billVO.sum = billVO.calSum();
        submit(billVO);
        ResultMessage re = examine(billVO);
        logInfo.open();
        if (re == ResultMessage.SUCCESS) {
            logInfo.record(OperationType.REDCOVER, OperationObjectType.BILL, voTopo(billVO).toString());
        }
        return re;
    }

    public ResultMessage redCoverAndCopy(CashBillVO billVO) throws RemoteException {
        logInfo.close();
        redCover(billVO);
        String ID = getNewCashBillID();
        billVO.ID = ID;
        billVO.state = BillState.DRAFT;
        ResultMessage re = save(billVO);
        logInfo.open();
        if (re == ResultMessage.SUCCESS) {
            logInfo.record(OperationType.REDCOVERANDCOPY, OperationObjectType.BILL, voTopo(billVO).toString());
        }
        return re;
    }
}
