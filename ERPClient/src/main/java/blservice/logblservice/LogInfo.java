package blservice.logblservice;

import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * 
 * @author 巽
 *
 */
public interface LogInfo {
	/**
	 * 添加日志记录。系统关闭状态下无法添加，并且会返回FAILED
	 * 
	 * @param operationType 操作类型
	 * @param operationObjectType 操作对象的类型
	 * @param details 对象详情
	 * @return SUCCEED：添加成功<br>FAILED：添加失败
	 */
	public ResultMessage record(OperationType operationType, OperationObjectType operationObjectType, String details);

	/**
	 * 关闭日志系统（默认开启），关闭期间所有的record调用将无法生效并返回FAILED
	 * 
	 * @return SUCCEED：关闭成功<br>FAILED：已为关闭状态
	 */
	public ResultMessage close();

	/**
	 * 开启日志系统（默认开启），开启后可正常调用record方法
	 * 
	 * @return SUCCEED：开启成功<br>FAILED：已为开启状态
	 */
	public ResultMessage open();
}
