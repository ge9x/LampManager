package util;

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
	
	public static Level getLevel(String keywords){
		if(keywords.equals("一级普通客户")){
			return Level.LEVEL_ONE;
		}else if(keywords.equals("二级客户")){
			return Level.LEVEL_TWO;
		}else if(keywords.equals("三级客户")){
			return Level.LEVEL_THREE;
		}else if(keywords.equals("四级客户")){
			return Level.LEVEL_FOUR;
		}else if(keywords.equals("VIP五级客户")){
			return Level.LEVEL_FIVE;
		}else{
			return null;
		}
	}
}