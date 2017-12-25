package bl.initializationbl;

import dataimpl.initializationdataimpl.InitializationDataServiceImpl;
import dataservice.initializationdataservice.InitializationDataService;
import po.InitializationPO;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Initialization{

    private GoodsList goodsList;
    private CustomerList customerList;
    private AccountList accountList;
    private ClassificationList classificationList;

    private InitializationDataService initializationDataService;

    public Initialization(){
        initializationDataService = InitializationDataServiceImpl.getInstance();
        goodsList = new GoodsList();
        customerList = new CustomerList();
        accountList = new AccountList();
        classificationList = new ClassificationList();
    }
    public ResultMessage init() throws RemoteException {
        InitializationPO po = new InitializationPO();
        po.setInitClassificationPOS(classificationList.getClassifications());
        po.setInitAccountPOS(accountList.getAccounts());
        po.setInitGoodsPOS(goodsList.getGoods());
        po.setInitCustomerPOS(customerList.getCustomers());
        return initializationDataService.init(po);
    }

    public ArrayList<String> getAllInitDate() throws RemoteException {
        return initializationDataService.getAllInitDates();
    }
    public String getRecentInitDate() throws RemoteException {
        ArrayList<String> dates = getAllInitDate();
        if (dates != null && !dates.isEmpty())
            return dates.get(dates.size());
        else
            return null;
    }
    public InitializationVO show(String date) throws RemoteException {
        InitializationPO po = initializationDataService.getInitializationByDate(date);
        ArrayList<AccountVO> accountVOS = accountList.posTovos(po.getInitAccountPOS());
        ArrayList<ClassificationVO> classificationVOS = classificationList.posTovos(po.getInitClassificationPOS());
        ArrayList<GoodsVO> goodsVOS = goodsList.posTovos(po.getInitGoodsPOS());
        ArrayList<CustomerVO> customerVOS = customerList.posTovos(po.getInitCustomerPOS());

        InitializationVO vo = new InitializationVO(accountVOS,classificationVOS,goodsVOS,customerVOS);
        return vo;

    }
}
