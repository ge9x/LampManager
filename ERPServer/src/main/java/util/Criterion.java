package util;

/**
 * 枚举各种查询数据库的方式
 * Created on 2017/11/23
 * @author 巽
 *
 */
public class Criterion {
	/**
	 * 要约束的字段
	 */
	private String field;
	/**
	 * 约束值
	 */
	private Object value;
	/**
	 * 范围查询方式时的上限
	 */
	private Object anotherValue;
	/**
	 * 查询数据库的方式
	 */
	private QueryMode queryMode;
	
	/**
	 * 完全/模糊匹配查询
	 * @param field 字段
	 * @param value 约束值
	 * @param queryMode 查询方式
	 */
	public Criterion(String field, Object value, QueryMode queryMode) {
		super();
		this.field = field;
		this.anotherValue = null;
		this.queryMode = queryMode;
		if(queryMode == QueryMode.FUZZY){
			this.value = '%' + value.toString() + '%';
		}
		else{
			this.value = value;
		}
	}
	
	/**
	 * 范围查询约束
	 * @param field 字段
	 * @param min 下限
	 * @param max 上限
	 */
	public Criterion(String field, Object min, Object max) {
		super();
		this.field = field;
		this.value = min;
		this.anotherValue = max;
		this.queryMode = QueryMode.RANGE;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public Object getAnotherValue() {
		return anotherValue;
	}

	public QueryMode getQueryMode() {
		return queryMode;
	}

}
