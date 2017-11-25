package util;

/**
 * 查询数据库的方式
 * Created on 2017/11/23
 * @author 巽
 *
 */
public enum QueryMode {
	FULL("完全匹配"),
	FUZZY("模糊匹配"),
	RANGE("范围查询");
	
	String value;
	
	QueryMode(String value){
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
