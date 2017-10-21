package util;

/**
 * Created by Kry·L on 2017/10/21.
 */
public enum BillType {
    OVERFLOW("库存报溢单"),
    LOSS("库存报损单"),
    ALARM("库存报警单"),
    PURCHASE("进货单"),
    RETURN("进货退货单"),
    SALES("销售单"),
    SALESRETURN("销售退货单"),
    RECEIPT("收款单"),
    PAYMENT("付款单"),
    CASH("现金费用单");

    String value;
    BillType(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

}
