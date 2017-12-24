package vo;

import util.BillState;
import util.BillType;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/21.
 */
public class AccountBillVO extends BillVO{


    /**
     * 客户ID
     */
    public String customerID;

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

    public AccountBillVO(String date, String ID, BillState state, BillType type, String customerID, String userName, ArrayList<AccountBillItemVO> items) {
        this.date = date;
        this.ID = ID;
        this.state = state;
        this.type = type;
        this.customerID = customerID;
        this.userName = userName;
        this.accountBillItems = items;
        this.sum = calSum();
    }

    /**
     * 根据转账列表计算总额汇总
     * @return 总额汇总
     */
    public double calSum(){
        double sum = 0;
        for (int i = 0; i < this.accountBillItems.size(); i++){
            sum += accountBillItems.get(i).transferMoney;
        }
        return sum;
    }
}
