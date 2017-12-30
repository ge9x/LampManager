package bl.initializationbl;

import bl.logbl.LogBLFactory;
import blservice.logblservice.LogInfo;
import dataimpl.initializationdataimpl.InitializationDataServiceImpl;
import dataservice.initializationdataservice.InitializationDataService;
import po.InitializationPO;
import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Initialization{
    private LogInfo logInfo;
    private GoodsList goodsList;
    private CustomerList customerList;
    private AccountList accountList;
    private ClassificationList classificationList;

    private InitializationDataService initializationDataService;

    public Initialization(){
        initializationDataService = InitializationDataServiceImpl.getInstance();
        logInfo = LogBLFactory.getInfo();
        goodsList = new GoodsList();
        customerList = new CustomerList();
        accountList = new AccountList();
        classificationList = new ClassificationList();
    }
    public ResultMessage init() throws RemoteException {
        InitializationPO po = new InitializationPO();
        po.setDate(LocalDate.now().toString());
        po.setInitClassificationPOS(classificationList.getClassifications());
        po.setInitAccountPOS(accountList.getAccounts());
        po.setInitGoodsPOS(goodsList.getGoods());
        po.setInitCustomerPOS(customerList.getCustomers());
        ResultMessage re = initializationDataService.init(po);
        if (re == ResultMessage.SUCCESS){
            logInfo.record(OperationType.ADD, OperationObjectType.INITIALIZATION,po.toString());
        }
        return re;
    }

    public ArrayList<String> getAllInitDate() throws RemoteException {
        return initializationDataService.getAllInitDates();
    }
    public String getRecentInitDate() throws RemoteException {
        ArrayList<String> dates = getAllInitDate();
        if (dates != null && !dates.isEmpty())
            return dates.get(dates.size()-1);
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
