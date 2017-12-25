package blservice.logblservice;

import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * @author 巽
 *
 */
public interface LogInfo {
	/**
	 * 添加日志记录
	 * 
	 * @param userID 操作员ID
	 * @param operationType 操作类型
	 * @param operationObjectType 操作对象的类型
	 * @param details 对象详情
	 * @return 是否成功添加
	 */
	public ResultMessage record(String userID, OperationType operationType, OperationObjectType operationObjectType, String details);
}
