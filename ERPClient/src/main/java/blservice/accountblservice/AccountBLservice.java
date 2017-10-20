package blservice.accountblservice;
import util.ResultMessage;
import vo.AccountVO;

/**
 * Created by Kry·L on 2017/10/20.
 */
public interface AccountBLservice {
    /**
     *
     * @param vo
     * @return 是否成功
     */
    public ResultMessage addAccount(AccountVO vo);

}
