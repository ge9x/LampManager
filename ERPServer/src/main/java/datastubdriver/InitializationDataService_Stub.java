package datastubdriver;

import dataservice.initializationdataservice.InitializationDataService;
import po.*;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class InitializationDataService_Stub implements InitializationDataService{

    public ResultMessage init(InitAccountPO po) {
        return ResultMessage.SUCCESS;
    }

    public InitAccountPO show() {
        AccountPO account = new AccountPO("001","工商银行账户", 2000.00);
        ArrayList<AccountPO> accountPOS = new ArrayList<AccountPO>();
        accountPOS.add(account);

        CustomerPO customer=new CustomerPO( CustomerCategory.PUR_AGENT, Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,100);
        ArrayList<CustomerPO> customerPOS = new ArrayList<CustomerPO>();
        customerPOS.add(customer);

        ClassificationPO classification = new ClassificationPO(5, "霓虹灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
        ArrayList<ClassificationPO> classificationPOS = new ArrayList<ClassificationPO>();
        classificationPOS.add(classification);

        GoodsPO goods = new GoodsPO(5, "后现代主义七彩霓虹灯", "LLL", null, 7, 3, 23333.3, 250000, 2000.0,2000.0);
        ArrayList<GoodsPO> goodsVOS = new ArrayList<GoodsPO>();
        goodsVOS.add(goods);

        InitAccountPO po = new InitAccountPO(new Date(),customerPOS,accountPOS,goodsVOS,classificationPOS);
        return po;
    }
}
