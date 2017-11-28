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
	 * 并集查询方式时的约束1
	 */
	private Criterion criterion1;
	/**
	 * 并集查询方式时的约束2
	 */
	private Criterion criterion2;
	
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
	 * @param min 下限（若无则为null）
	 * @param max 上限（若无则为null）
	 */
	public Criterion(String field, Object min, Object max) {
		super();
		this.field = field;
		this.value = min;
		this.anotherValue = max;
		this.queryMode = QueryMode.RANGE;
	}
	
	/**
	 * 并集查询约束
	 * @param criterion1 约束1
	 * @param criterion2 约束2
	 */
	public Criterion(Criterion criterion1, Criterion criterion2){
		this.criterion1 = criterion1;
		this.criterion2 = criterion2;
		this.queryMode = QueryMode.OR;
	}

	public Criterion getCriterion1() {
		return criterion1;
	}

	public Criterion getCriterion2() {
		return criterion2;
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
