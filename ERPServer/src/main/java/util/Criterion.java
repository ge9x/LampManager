package util;

/**
 * 枚举各种查询数据库的方式
 * Created on 2017/11/23
 * @author 巽
 *
 */
public class Criterion {
	String field;
	String value;
	/**
	 * 范围查询等方式时需要的另一值
	 */
	String anotherValue;
	QueryMode queryMode;
}
