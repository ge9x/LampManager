package blservice.accountblservice;
import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/20.
 */
public interface AccountBLService {

    /**
     * 添加银行账户
     * @param accountVO
     * @return 是否成功
     */
    public ResultMessage addAccount(AccountVO accountVO);

    /**
     * 删除银行账户
     * @param name
     * @return 是否成功
     */
    public ResultMessage deleteAccount(String name);

    /**
     * 通过银行账户名称查找
     * @param keyword
     * @return 银行账户的VO列表
     */
    public ArrayList<AccountVO> findAccountByName(String keyword);

    /**
     * 更新银行账户
     * @param accountVO
     * @return 是否成功
     */
    public ResultMessage updateAccount(AccountVO accountVO);

    /**
     * 获得所有的银行账户
     * @return 银行账户的VO列表
     */
    public ArrayList<AccountVO> show();



}
