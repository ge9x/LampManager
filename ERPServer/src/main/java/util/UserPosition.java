package util;

/** 
 * Created by Aster on 2017/10/21
 */
public enum UserPosition {
	FINANCIAL_STAFF("财务人员"),
	INVENTORY_STAFF("库存管理人员"),
	SALES_STAFF("进货销售人员"),
	GENERAL_MANAGER("总经理"),
	ADMIN("管理员");
	
	String value;

    UserPosition(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
