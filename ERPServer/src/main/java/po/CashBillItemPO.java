package po;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class CashBillItemPO {
    /**
     * 条目名
     */
    private String itemName;

    /**
     * 金额
     */
    private double money;

    /**
     * 备注
     */
    private String remark;

    public CashBillItemPO(String itemName, double money, String remark) {
        this.itemName = itemName;
        this.money = money;
        this.remark = remark;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
