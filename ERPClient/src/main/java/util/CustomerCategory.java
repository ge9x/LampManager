package util;

/**
 * created by zlk on 2017/10/21
 */

public enum CustomerCategory {
	PUR_AGENT("进货商"),
	SELLER("销售商");
	
	String value;
	
	CustomerCategory(String value){
		this.value=value;
	}
	
	public String getValue(){
		return value;
	}
	public static CustomerCategory categoryToString(String value){
		switch (value) {
		case "进货商":
			return CustomerCategory.PUR_AGENT;
		case "销售商":
			return CustomerCategory.SELLER;
		default:
			return null;
		}
	}
}
