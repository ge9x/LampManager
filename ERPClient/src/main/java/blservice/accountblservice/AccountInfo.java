package blservice.accountblservice;

import bl.accountbl.Account;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public interface AccountInfo {
    /**
     * 得到所有的账户名称
     * @return
     */
    public ArrayList<String> getAllAccountName();

    /**
     * 得到所有账户信息
     * @return
     */
    public ArrayList<AccountVO> getAllAccount();
}
