package bl.initializationbl;

import bl.classificationbl.Classification;
import dataservice.initializationdataservice.InitializationDataService;
import util.ResultMessage;
import vo.InitAccountVO;

import java.time.LocalDate;
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
        goodsList = new GoodsList();
        customerList = new CustomerList();
        accountList = new AccountList();
        classificationList = new ClassificationList();

    }
    public InitAccountVO init() {
        return null;
    }

    public InitAccountVO show() {
        return new InitAccountVO(LocalDate.now().toString(),accountList.getAccounts(),customerList.getCustomers(),goodsList.getGoods(),classificationList.getClassifications());
    }
}
