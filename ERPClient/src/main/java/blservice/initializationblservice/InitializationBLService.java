package blservice.initializationblservice;

import util.ResultMessage;
import vo.InitAccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/30.
 */
public interface InitializationBLService {
    /**
     * 期初建账
     * @param vo
     * @return
     */
    public ResultMessage init(InitAccountVO vo);

    /**
     * 展示所有期初建账信息
     * @return
     */
    public InitAccountVO show();
}
