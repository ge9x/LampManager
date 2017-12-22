package dataservice.initializationdataservice;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

import po.InitAccountPO;
import po.InitializationPO;
import util.ResultMessage;

/**
 * Created by Kry·L on 2017/10/30.
 */
public interface InitializationDataService extends Remote{

    /**
     * 期初建账
     * @param po
     * @return
     */
    public ResultMessage init(InitializationPO po);
   /**
    * 得到所有期初建账日期
    * @return
    */
   public ArrayList<String> getAllInitDates();
   /**
    * 通过日期得到该套账
    * @param date
    * @return
    */
   public InitializationPO getInitializationByDate(String date);
}
