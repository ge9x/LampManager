package bl.financialbl;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class CashBillItem {
    /**
     * 条目名
     */
    public String itemName;

    /**
     * 金额
     */
    public double money;

    /**
     * 备注
     */
    public String remark;

    public CashBillItem(String itemName, double money, String remark) {
        this.itemName = itemName;
        this.money = money;
        this.remark = remark;
    }
}
