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
	 * 关闭日志系统（默认开启），关闭期间所有的record调用将无法生效并返回FAILED<br>
	 * 每调用一次本方法都将给日志系统加上一层锁，在所有的锁解开前系统都处于关闭状态
	 * 
	 * @return SUCCEED：关闭成功
	 */
	public ResultMessage close();

	/**
	 * 开启日志系统（默认开启），开启后可正常调用record方法<br>
	 * 每调用一次本方法都将给日志系统解开一层锁，在所有的锁解开前系统都处于关闭状态
	 * 
	 * @return SUCCEED：成功解开所有锁，系统已处于开启状态<br>FAILED：解开了一层锁但系统仍处于关闭状态
	 */
	public ResultMessage open();
}
