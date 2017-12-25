package vo;

import bl.classificationbl.Classification;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/10/30.
 */
public class InitAccountVO {
    String date;
    ArrayList<AccountVO> accountVOS;
    ArrayList<CustomerVO> customerVOS;
    ArrayList<GoodsVO> goodsVOS;
    ArrayList<ClassificationVO> classificationVOS;

    public InitAccountVO(String date, ArrayList<AccountVO> accountVOS, ArrayList<CustomerVO> customerVOS, ArrayList<GoodsVO> goodsVOS, ArrayList<ClassificationVO> classificationVOS) {
        this.date = date;
        this.accountVOS = accountVOS;
        this.customerVOS = customerVOS;
        this.goodsVOS = goodsVOS;
        this.classificationVOS = classificationVOS;
    }
}
