package bl.initializationbl;

import bl.accountbl.Account;
import bl.accountbl.AccountController;
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

    public AccountList(){
        info = new AccountController();
    }
    public ArrayList<InitAccountPO> getAccounts(){
        ArrayList<InitAccountPO> pos = new ArrayList<>();
        ArrayList<AccountVO> accountVOS = info.show();
        for (AccountVO accountVO : accountVOS){
            pos.add(new InitAccountPO(Integer.parseInt(accountVO.accountID),accountVO.accountName,accountVO.money));
        }
        return pos;
    }

    public ArrayList<AccountVO> posTovos(List<InitAccountPO> initAccountPOS) {
        ArrayList<AccountVO> vos = new ArrayList<>();
        for (InitAccountPO po : initAccountPOS){
            vos.add(new AccountVO(po.getID()+"",po.getName(),po.getMoney()));
        }
        return vos;
    }
}
