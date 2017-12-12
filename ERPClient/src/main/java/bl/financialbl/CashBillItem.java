package bl.financialbl;

import po.CashBillItemPO;
import vo.CashBillItemVO;

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
    public static CashBillItemPO voTopo(CashBillItemVO vo){
        CashBillItemPO po = new CashBillItemPO(vo.itemName,vo.money,vo.remark);
        return po;
    }
    public static CashBillItemVO poTovo(CashBillItemPO po){
        return new CashBillItemVO(po.getItemName(),po.getMoney(),po.getRemark());
    }
}
