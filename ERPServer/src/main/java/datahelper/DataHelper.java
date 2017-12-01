package datahelper;

import java.util.ArrayList;

import javafx.util.Pair;
import util.Criterion;
import util.ResultMessage;

/**
 * 直接操作MySQL的接口<br>
 * Created on 2017/11/23
 * @author 巽
 *
 * @param <T> 要操作的数据的映射类
 */
public interface DataHelper<T> {
	/**
	 * 添加持久化对象到数据库
	 * @param t 要添加的持久化对象
	 * @return 是否添加成功
	 */
	public ResultMessage save(T po);
	/**
	 * 删除数据库中的持久化对象
	 * @param t 要删除的持久化对象
	 * @return 是否删除成功
	 */
	public ResultMessage delete(T po);
	/**
	 * 修改数据库中的持久化对象
	 * @param t 要修改的持久化对象
	 * @return 是否修改成功
	 */
	public ResultMessage update(T po);
	/**
	 * 精确查询，多用于通过ID查询，默认结果数<=1
	 * @param field 要查询的字段
	 * @param value 要匹配的值
	 * @return 查询到的对象
	 */
	public T exactlyQuery(String field, Object value);
	/**
	 * 完全匹配，多用于查询字段某一值的所有对象
	 * @param field 要查询的字段
	 * @param value 要匹配的值
	 * @return 查询到的所有对象的集合
	 */
	public ArrayList<T> fullyQuery(String field, Object value);
	/**
	 * 模糊查询
	 * @param field 要查询的字段
	 * @param value 要匹配的关键字
	 * @return 查询到的对象的集合
	 */
	public ArrayList<T> fuzzyQuery(String field, String value);
	/**
	 * 多重条件查询
	 * @param criteria 所有条件的集合
	 * @return 查询到的对象的集合
	 */
	public ArrayList<T> multiQuery(ArrayList<Criterion> criteria);
	/**
	 * 统计持久化对象数量
	 * @return 持久化对象的个数
	 */
	public Long count();
	
	public Long count(String propertyName, String value, String anotherProperty, String anotherValue);
}
