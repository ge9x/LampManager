package vo;

import util.BillState;
import util.BillType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/10/21.
 */
public class AccountBillVO {
    /**
     * 单据创建时间
     */
    public Date date;

    /**
     * 收付款单据编号
     */
    public String ID;

    /**
     * 收付款单据状态
     */
    public BillState state;

    /**
     * 收付款单据类型
     */
    public BillType type;

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

    public AccountBillVO(Date date, String ID, BillState state, BillType type, String customerName, String userName, ArrayList<AccountBillItemVO> items) {
        this.date = date;
        this.ID = ID;
        this.state = state;
        this.type = type;
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
