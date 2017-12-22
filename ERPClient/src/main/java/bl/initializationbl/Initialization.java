package bl.initializationbl;

import bl.classificationbl.Classification;
import bl.customerbl.Customer;
import dataservice.initializationdataservice.InitializationDataService;
import po.InitializationPO;
import util.ResultMessage;
import vo.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResultMessage init(InitializationVO vo) {

        return initializationDataService.init(null);
    }

    public ArrayList<String> getAllInitDate() {
        return initializationDataService.getAllInitDates();
    }
    public String getRecentInitDate(){
        ArrayList<String> dates = getAllInitDate();
        if (dates != null && !dates.isEmpty())
            return dates.get(dates.size());
        else
            return null;
    }
    public InitializationVO show(String date){
        InitializationPO po = initializationDataService.getInitializationByDate(date);
        ArrayList<AccountVO> accountVOS = accountList.posTovos(po.getInitAccountPOS());
        ArrayList<ClassificationVO> classificationVOS = classificationList.posTovos(po.getInitClassificationPOS());
        ArrayList<GoodsVO> goodsVOS = goodsList.posTovos(po.getInitGoodsPOS());
        ArrayList<CustomerVO> customerVOS = customerList.posTovos(po.getInitCustomerPOS());

        InitializationVO vo = new InitializationVO(accountVOS,classificationVOS,goodsVOS,customerVOS);
        return vo;

    }
}
