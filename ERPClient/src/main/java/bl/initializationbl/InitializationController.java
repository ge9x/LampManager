package bl.initializationbl;

import blservice.initializationblservice.InitInfo;
import blservice.initializationblservice.InitializationBLService;
import po.InitAccountPO;
import util.ResultMessage;
import vo.InitAccountVO;
import vo.InitializationVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class InitializationController implements InitializationBLService,InitInfo{
    private Initialization initialization;

    protected InitializationController(){
        initialization = new Initialization();
    }
    public ResultMessage init() {
        try {
            return initialization.init();
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public InitializationVO show(String date) {
        try {
            return initialization.show(date);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<String> getAllInitDate() {
        try {
            return initialization.getAllInitDate();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 本方法用于期初建账
     * @return
     */
    @Override
    public String getRecentInitdate() {
        try {
            return initialization.getRecentInitDate();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 本方法用于报表等Info接口，提供本次期初建账的开始日期
     * 如果尚未期初建账，返回2017-01-01
     * @return
     */
    @Override
    public String getStartDate() {
        try {
            if (initialization.getRecentInitDate() == null){
                return "2017-01-01";
            }
            else
                return initialization.getRecentInitDate();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
