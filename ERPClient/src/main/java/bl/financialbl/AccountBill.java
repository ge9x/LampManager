package bl.financialbl;

import bl.accountbl.Account;
import bl.accountbl.AccountBLFactory;
import bl.accountbl.AccountController;
import bl.customerbl.CustomerController;
import bl.logbl.LogBLFactory;
import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.logblservice.LogInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.AccountPO;
import rmi.FinanceRemoteHelper;
import util.*;
import vo.AccountBillItemVO;
import vo.AccountBillVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.image.AreaAveragingScaleFilter;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBill {

    private AccountBillVO accountBill;
    private ArrayList<AccountBillItem> accountBillItems;
    private AccountBillItem accountBillItem;

    FinanceDataService financeDataService;
    AccountInfo accountInfo;
    LogInfo logInfo;

    ArrayList<AccountBillPO> accountBillPOS;

    public AccountBill(){
        accountBillPOS = new ArrayList<>();
        accountBillItem = new AccountBillItem();
        accountInfo = AccountBLFactory.getInfo();
        logInfo = LogBLFactory.getInfo();
        financeDataService = FinanceRemoteHelper.getInstance().getFinanceDataService();
    }

    public String getNewReceiptID() throws RemoteException {
        return financeDataService.getNewReceiptID();
    }

    public String getNewPaymentID() throws RemoteException {
        return financeDataService.getNewPaymentID();
    }

    public ResultMessage submit(AccountBillVO vo) throws RemoteException {
        AccountBillPO po = voTopo(vo);
        ResultMessage re = financeDataService.addBill(po);
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.ADD,OperationObjectType.BILL,po.toString());
        }
        return re;
    }
    public ResultMessage save(AccountBillVO vo) throws RemoteException {
        AccountBillPO po = voTopo(vo);
        ResultMessage re = financeDataService.addBill(po);
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.ADD,OperationObjectType.BILL,po.toString());
        }
        return re;
    }

    public ResultMessage update(AccountBillVO vo) throws RemoteException {
        accountBillPOS = financeDataService.getAllAccountBills();
        for (AccountBillPO po : accountBillPOS) {
            if (po.buildID().equals(vo.ID)) {
                po.setState(vo.state);
                if (vo.customerID != ""){
                    po.setCustomerID(Integer.parseInt(vo.customerID));
                }else{
                    po.setCustomerID(0);
                }
                po.setSum(vo.sum);
                po.getAccountBillItemPOS().clear();
                ArrayList<AccountBillItemVO> itemVOS = vo.accountBillItems;
                for (AccountBillItemVO itemVO : itemVOS) {
                    AccountBillItemPO itemPO = AccountBillItem.voTopo(itemVO);
                    po.getAccountBillItemPOS().add(itemPO);
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
        ArrayList<AccountBillPO> accountBillPOS = financeDataService.getAllAccountBills();
        for (AccountBillPO po : accountBillPOS){
            if (po.buildID().equals(id)){
                ResultMessage re = financeDataService.deleteBill(po);
                if (re == ResultMessage.SUCCESS){
                    logInfo.record(OperationType.DELETE,OperationObjectType.BILL,po.toString());
                }
                return re;
            }
        }
        return ResultMessage.FAILED;
    }

    public ArrayList<AccountBillVO> getReceiptsByState(BillState state) throws RemoteException {
        accountBillPOS.clear();
        ArrayList<AccountBillVO> vos = new ArrayList<>();
        ArrayList<AccountBillPO> pos = financeDataService.getAllAccountBills();
        for(AccountBillPO po : pos){
            if (po.getState() == state && po.getType() == BillType.RECEIPT){
                accountBillPOS.add(po);
                vos.add(poTovo(po));
            }
        }
        return vos;
    }

    public ArrayList<AccountBillVO> getPaymentsByState(BillState state) throws RemoteException {
        accountBillPOS.clear();
        ArrayList<AccountBillVO> vos = new ArrayList<>();
        ArrayList<AccountBillPO> pos = financeDataService.getAllAccountBills();
        for(AccountBillPO po : pos){
            if (po.getState() == state && po.getType() == BillType.PAYMENT){
                accountBillPOS.add(po);
                vos.add(poTovo(po));
            }
        }
        return vos;
    }


    public ResultMessage examine(AccountBillVO vo) throws RemoteException {
        logInfo.close();
        ResultMessage re = update(vo);
        logInfo.open();
        if (vo.state == BillState.PASS) {
            if (vo.type == BillType.RECEIPT) {
                for (AccountBillItemVO itemVO : vo.accountBillItems) {
                    accountInfo.changeMoney(itemVO.account.accountID, itemVO.transferMoney);
                }
            } else {
                for (AccountBillItemVO itemVO : vo.accountBillItems) {
                    accountInfo.changeMoney(itemVO.account.accountID, -itemVO.transferMoney);
                }
            }
            logInfo.record(OperationType.EXAMINE, OperationObjectType.BILL, voTopo(vo).toString());
        }
        return re;
    }

    public static AccountBillPO voTopo(AccountBillVO vo){
        ArrayList<AccountBillItemPO> accountBillItemPOS = new ArrayList<>();
        for (AccountBillItemVO accountBillItemVO : vo.accountBillItems){
            accountBillItemPOS.add(AccountBillItem.voTopo(accountBillItemVO));
        }
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        int customerID = 0;
        if (!vo.customerID.equals(""))
            customerID = Integer.parseInt(vo.customerID);
        AccountBillPO accountBillPO = new AccountBillPO(vo.date,vo.type,vo.state,customerID,vo.userName,accountBillItemPOS,turn);
        return accountBillPO;
    }
    public AccountBillVO poTovo(AccountBillPO po){
        ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<>();
        for (AccountBillItemPO accountBillItemPO : po.getAccountBillItemPOS()) {
            accountBillItemVOS.add(accountBillItem.poTovo(accountBillItemPO));
        }
        AccountBillVO accountBillVO = new AccountBillVO(po.getDate(), po.buildID(), po.getState(), po.getType(), String.valueOf(po.getCustomerID()), po.getUserName(), accountBillItemVOS);
        return accountBillVO;
    }

    public ArrayList<AccountBillVO> getBillsByDate(String startDate, String endDate) throws RemoteException {
        ArrayList<AccountBillVO> accountBillVOS = new ArrayList<>();
        if (accountBillPOS == null || accountBillPOS.isEmpty()) {
            accountBillPOS = financeDataService.getAllAccountBills();
        }
        for (AccountBillPO po : accountBillPOS) {
            LocalDate billDate = LocalDate.parse(po.getDate());
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            if (((billDate.isBefore(end) && billDate.isAfter(start) )|| billDate.isEqual(start) || billDate.isEqual(end)) && po.getState() == BillState.PASS ) {
                accountBillVOS.add(poTovo(po));
            }
        }
        return accountBillVOS;
    }

    public ResultMessage redCover(AccountBillVO billVO) throws RemoteException {
        logInfo.close();
        String ID = "";

        if (billVO.type == BillType.RECEIPT)
            ID = getNewReceiptID();
        else
            ID = getNewPaymentID();
        billVO.ID = ID;
        for (AccountBillItemVO itemVO : billVO.accountBillItems) {
            itemVO.transferMoney = -itemVO.transferMoney;
        }

        billVO.sum = billVO.calSum();
        submit(billVO);

        ResultMessage re = examine(billVO);
        logInfo.open();
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.REDCOVER,OperationObjectType.BILL,voTopo(billVO).toString());
        }
        return re;
    }

    public ResultMessage redCoverAndCopy(AccountBillVO billVO) throws RemoteException {
        logInfo.close();
        redCover(billVO);
        String ID = "";
        if (billVO.type == BillType.RECEIPT)
            ID = getNewReceiptID();
        else
            ID = getNewPaymentID();
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
