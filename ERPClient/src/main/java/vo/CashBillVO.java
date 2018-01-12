package vo;

import bl.financialbl.CashBillItem;
import util.BillState;
import util.BillType;
import util.Money;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class CashBillVO extends BillVO{
    private static final String seperator = System.lineSeparator();

    /**
     * 操作员（当前登录用户）名称
     */
    public String userName;

    /**
     * 银行账户名称
     */
    public String accountID;

    /**
     * 条目清单
     */
    public ArrayList<CashBillItemVO> cashBillItems;

    /**
     * 总额
     */
    public double sum;

    public CashBillVO(String date, String ID, BillState state, BillType type, String userName, String accountID, ArrayList<CashBillItemVO> cashBillItems, double sum) {
        this.date = date;
        this.ID = ID;
        this.state = state;
        this.type = type;
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItems = cashBillItems;
        this.sum = sum;
    }
    public double calSum(){
        double sum = 0;
        for (CashBillItemVO itemVO: cashBillItems){
            sum += itemVO.money;
        }
        return sum;
    }

    @Override
    public String toString(){
        return super.toString()+
                "操作员: " + userName+ seperator +
                "银行账户: " + accountID + seperator +
                "总额: " + Money.getMoneyString(sum) + seperator +
                itemsToString();
    }

    public String itemsToString(){
        String str = "条目清单：" + seperator;
        for (CashBillItemVO itemVO : cashBillItems){
            str += (itemVO.toString() + seperator);
        }
        return str;
    }
}
