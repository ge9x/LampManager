package vo;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class CashBillVO extends BillVO{

    /**
     * 操作员（当前登录用户）名称
     */
    public String userName;

    /**
     * 银行账户名称
     */
    public String accountName;

    /**
     * 条目清单
     */
    public ArrayList<CashBillItemVO> cashBillItems;

    /**
     * 总额
     */
    public double sum;
}
