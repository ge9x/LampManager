package bl.initializationbl;

import dataservice.initializationdataservice.InitializationDataService;
import util.ResultMessage;
import vo.InitAccountVO;

import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Initialization{
    private Date date;
    private GoodsList goodsList;
    private CustomerList customerList;
    private AccountList accountList;
    private ClassificationList classificationList;

    private InitializationDataService initializationDataService;

    public Initialization(){

    }
    public InitAccountVO init() {
        return null;
    }

    public InitAccountVO show() {
        return null;
    }
}
