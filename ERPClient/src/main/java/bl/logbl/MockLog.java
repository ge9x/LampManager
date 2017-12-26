package bl.logbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

public class MockLog extends Log {
	ArrayList<String> data;
	{
		data = new ArrayList<>();
		data.add("[2017-12-25 21:04] [00002] [增加] [商品分类] [ID:02, 商品分类名称:落地灯]");
	}

	@Override
	public ArrayList<String> show() {
		return data;
	}

	@Override
	public ArrayList<String> getLogByDate(LocalDateTime start, LocalDateTime end) {
		if (start.toLocalDate().toString().equals("2017-12-25")) {
			return data;
		}
		return new ArrayList<>();
	}

	@Override
	public ResultMessage record(OperationType operationType, OperationObjectType operationObjectType, String details)
			throws RemoteException {
		if (details == null) {
			return null;
		}
		String time = LocalTime.now().toString();
		time = time.substring(0, time.charAt('.'));
		data.add('[' + LocalDate.now().toString() + ' ' + time + "] [00000] [" + operationType.getValue() + "] ["
				+ operationObjectType.getValue() + "] [" + details + ']');
		return super.record(operationType, operationObjectType, details);
	}

}
