package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.AccountPO;
import rmi.FinanceRemoteHelper;
import util.BillState;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBill {
    //TODO 草稿更新单据

    private AccountBillVO accountBill;
    private ArrayList<AccountBillItem> accountBillItems;
    private AccountBillItem accountBillItem;
    FinanceDataService financeDataService;

    ArrayList<AccountBillPO> accountBillPOS;

    public AccountBill(){
        accountBillPOS = new ArrayList<>();
        accountBillItem = new AccountBillItem();
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
        return financeDataService.addBill(po);
    }
    public ResultMessage save(AccountBillVO vo) throws RemoteException {
        return financeDataService.addBill(voTopo(vo));
    }

    public ResultMessage update(AccountBillVO vo){
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        for (AccountBillPO po : accountBillPOS) {
            if (po.getID() == turn) {
                ArrayList<AccountBillItemVO> itemVOS = vo.accountBillItems;
            }
        }
        return ResultMessage.FAILED;
    }

    public ArrayList<AccountBillVO> getDraftAccountBills() throws RemoteException {
        accountBillPOS.clear();
        ArrayList<AccountBillVO> vos = new ArrayList<>();
        ArrayList<AccountBillPO> pos = financeDataService.getAllAccountBills();
        for(AccountBillPO po : pos){
            if (po.getState() == BillState.DRAFT){
                accountBillPOS.add(po);
                vos.add(poTovo(po));
            }
        }
        return vos;
    }

    public AccountBillPO voTopo(AccountBillVO vo){
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

}
