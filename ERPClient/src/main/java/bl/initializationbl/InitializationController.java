package bl.initializationbl;

import blservice.initializationblservice.InitInfo;
import blservice.initializationblservice.InitializationBLService;
import po.InitAccountPO;
import util.ResultMessage;
import vo.InitAccountVO;
import vo.InitializationVO;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class InitializationController implements InitializationBLService,InitInfo{
    private Initialization initialization;

    public InitializationController(){
        initialization = new Initialization();
    }
    public ResultMessage init(InitializationVO vo) {
        return initialization.init(vo);
    }

    public InitializationVO show(String date) {
        return initialization.show(date);
    }

    @Override
    public String getStartDate() {
        if (initialization.getRecentInitDate() == null){
            return "2017-01-01";
        }
        else
            return initialization.getRecentInitDate();
    }
}
