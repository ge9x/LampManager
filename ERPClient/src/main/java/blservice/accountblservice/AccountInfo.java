package blservice.accountblservice;

import bl.accountbl.Account;
import util.ResultMessage;
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
    public ArrayList<AccountVO> show();

    /**
     * 根据ID查找账户
     * @param id
     * @return
     */
    public AccountVO getAccountByID(String id);

    /**
     * 单据审批通过，修改余额
     * @param ID
     * @param money
     * @return
     */
    public ResultMessage changeMoney(String ID,double money);
}
