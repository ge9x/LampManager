package bl.initializationbl;

import blservice.initializationblservice.InitInfo;
import blservice.initializationblservice.InitializationBLService;
import po.InitAccountPO;
import util.ResultMessage;
import vo.InitAccountVO;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class InitializationController implements InitializationBLService,InitInfo{
    private Initialization initialization;

    public InitializationController(){
        initialization = new Initialization();
    }
    public InitAccountVO init() {
        return null;
    }

    public InitAccountVO show() {
        return initialization.show();
    }

    @Override
    public String getStartDate() {
        return null;
    }
}
