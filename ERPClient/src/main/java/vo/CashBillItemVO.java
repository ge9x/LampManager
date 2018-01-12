package vo;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class CashBillItemVO {
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

    public CashBillItemVO(String itemName, double money, String remark) {
        this.itemName = itemName;
        this.money = money;
        this.remark = remark;
    }
    @Override
    public String toString(){
        return itemName + "\t" + money + "\t" + remark;
    }
}
