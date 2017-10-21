package vo;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/21.
 */
public class AccountBillVO {

    /**
     * 收付款单据编号
     */
    public String ID;

    /**
     * 收付款单据状态
     */
    public BillState state;
    /**
     * 客户名称
     */
    public String customerName;

    /**
     * 操作员（当前登录用户）名称
     */
    public String userName;

    /**
     * 转账列表
     */
    public ArrayList<AccountBillItemVO> accountBillItems;

    /**
     * 总额汇总
     */
    public double sum;

    public AccountBillVO(String ID, String customerName, String userName, ArrayList<AccountBillItemVO> items){
        this.ID = ID;
        this.customerName = customerName;
        this.userName = userName;
        this.accountBillItems = items;
        this.sum = calSum();
    }

    /**
     * 根据转账列表计算总额汇总
     * @return 总额汇总
     */
    private double calSum(){
        double sum = 0;
        for (int i = 0; i < this.accountBillItems.size(); i++){
            sum += accountBillItems.get(i).transferMoney;
        }
        return sum;
    }
}
