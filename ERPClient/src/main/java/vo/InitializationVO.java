package vo;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/22.
 */
public class InitializationVO {
    ArrayList<AccountVO> accounts;
    ArrayList<ClassificationVO> classifications;
    ArrayList<GoodsVO> goods;
    ArrayList<CustomerVO> customers;

    public InitializationVO(ArrayList<AccountVO> accounts, ArrayList<ClassificationVO> classifications, ArrayList<GoodsVO> goods, ArrayList<CustomerVO> customers) {
        this.accounts = accounts;
        this.classifications = classifications;
        this.goods = goods;
        this.customers = customers;
    }
}
