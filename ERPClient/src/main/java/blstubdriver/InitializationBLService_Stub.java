package blstubdriver;

import blservice.initializationblservice.InitializationBLService;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class InitializationBLService_Stub implements InitializationBLService {

    @Override
    public ResultMessage init() {
        return ResultMessage.SUCCESS;
    }

    public InitializationVO show(String date)  {
        AccountVO account = new AccountVO("001","工商银行账户", 2000.00);
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

        InitializationVO vo = new InitializationVO(accountVOS,classificationVOS,goodsVOS,customerVOS);
        return vo;
    }

    @Override
    public ArrayList<String> getAllInitDate() {
        ArrayList<String> dates = new ArrayList<>();
        dates.add("2018-01-01");
        return dates;
    }

    @Override
    public String getRecentInitdate() {
        return "2018-01-01";
    }

}
