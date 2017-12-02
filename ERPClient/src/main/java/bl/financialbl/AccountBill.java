package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import blservice.customerblservice.CustomerInfo;
import blservice.userblservice.UserInfo;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.AccountPO;
import rmi.FinanceRemoteHelper;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;

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
        financeDataService = FinanceRemoteHelper.getInstance().getFinanceDataService();
    }

    public String getNewReceiptID() throws RemoteException {
        return financeDataService.getNewReceiptID();
    }

    public String getNewPaymentID() throws RemoteException {
        return financeDataService.getNewPaymentID();
    }

    public ResultMessage submit(AccountBillVO vo) throws RemoteException{
        return financeDataService.addBill(voTopo(vo));
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

    public AccountBillPO voTopo(AccountBillVO vo){
        ArrayList<AccountBillItemPO> accountBillItemPOS = new ArrayList<>();
        for (AccountBillItemVO accountBillItemVO : vo.accountBillItems){
            accountBillItemPOS.add(AccountBillItem.voTopo(accountBillItemVO));
        }
        int turn = Integer.parseInt(vo.ID.split("-")[2]);
        AccountBillPO accountBillPO = new AccountBillPO(vo.date,vo.type,vo.state,Integer.parseInt(vo.customerID),vo.userName,accountBillItemPOS,turn);
        return accountBillPO;
    }
}
