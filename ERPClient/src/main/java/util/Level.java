package util;

/**
 * created by zlk on 2017/10/21
 */

public enum Level {
	LEVEL_ONE("一级普通客户"),
	LEVEL_TWO("二级客户"),
	LEVEL_THREE("三级客户"),
	LEVEL_FOUR("四级客户"),
	LEVEL_FIVE("VIP五级客户");
	
	String value;
	Level(String value){
		this.value=value;
	}
	
	public String getValue(){
		return value;
	}
	public static Level levelToString(String value){
		switch (value) {
		case "一级普通客户":
			return LEVEL_ONE;
		case "二级客户":
			return LEVEL_TWO;
		case "三级客户":
			return LEVEL_THREE;
		case "四级客户":
			return LEVEL_FOUR;
		case "VIP五级客户":
			return LEVEL_FIVE;
 		default:
			return null;
		}
	}
}