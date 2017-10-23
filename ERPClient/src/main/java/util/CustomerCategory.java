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
}
