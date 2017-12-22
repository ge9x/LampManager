package bl.initializationbl;

import bl.accountbl.Account;
import blservice.accountblservice.AccountInfo;
import po.InitAccountPO;
import vo.AccountVO;
import vo.InitAccountVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountList {
    private ArrayList<AccountVO> accountVOS;
    private AccountInfo info;

    public ArrayList<AccountVO> getAccounts(){
        return accountVOS;
    }

    public ArrayList<AccountVO> posTovos(List<InitAccountPO> initAccountPOS) {
        ArrayList<AccountVO> vos = new ArrayList<>();
        for (InitAccountPO po : initAccountPOS){
            vos.add(new AccountVO(po.getID()+"",po.getName(),po.getMoney()));
        }
        return vos;
    }
}
