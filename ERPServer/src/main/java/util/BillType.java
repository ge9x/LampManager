package util;

/**
 * Created by Kry·L on 2017/10/21.
 */
public enum BillType {
    OVERFLOW("库存报溢单", "BYD"),
    LOSS("库存报损单", "BSD"),
    ALARM("库存报警单", "BJD"),
    GIFT("库存赠送单", "ZSD"),
    PURCHASE("进货单", "JHD"),
    RETURN("进货退货单", "JHTHD"),
    SALES("销售单", "XSD"),
    SALESRETURN("销售退货单", "XSTHD"),
    RECEIPT("收款单", "SKD"),
    PAYMENT("付款单", "FKD"),
    CASH("现金费用单", "XJFYD");

    String value;
    String acronym;
    
    BillType(String value, String acronym){
        this.value = value;
        this.acronym = acronym;
    }
    
    public String getValue(){
        return value;
    }

    public String getAcronym(){
    	return acronym;
    }
}
