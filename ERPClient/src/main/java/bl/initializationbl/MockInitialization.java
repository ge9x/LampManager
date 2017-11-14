package bl.initializationbl;

import bl.accountbl.Account;
import bl.classificationbl.Classification;
import bl.goodsbl.Goods;
import util.CustomerCategory;
import util.Level;
import vo.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockInitialization extends Initialization {

    @Override
    public InitAccountVO init() {
        AccountVO account = new AccountVO("工商银行账户", 2000.00);
        ArrayList<AccountVO> accountVOS = new ArrayList<AccountVO>();
        accountVOS.add(account);

        CustomerVO customer=new CustomerVO("00000003", CustomerCategory.PUR_AGENT, Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,500);
        ArrayList<CustomerVO> customerVOS = new ArrayList<CustomerVO>();
        customerVOS.add(customer);

        ClassificationVO classification = new ClassificationVO("05", "霓虹灯", null, new ArrayList<ClassificationVO>(), new ArrayList<GoodsVO>());
        ArrayList<ClassificationVO> classificationVOS = new ArrayList<ClassificationVO>();
        classificationVOS.add(classification);

        GoodsVO goods = new GoodsVO("05000005", "后现代主义七彩霓虹灯", "LLL", "霓虹灯", "栖霞区仓库", 7, 3, 23333.3, 250000, 25000, 255555);
        ArrayList<GoodsVO> goodsVOS = new ArrayList<GoodsVO>();
        goodsVOS.add(goods);

        InitAccountVO vo = new InitAccountVO(new Date(),accountVOS,customerVOS,goodsVOS,classificationVOS);
        return vo;
    }


    @Override
    public InitAccountVO show() {
        return init();
    }
}
