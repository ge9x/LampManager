package blstubdriver;

import java.time.LocalDateTime;
import java.util.ArrayList;

import blservice.logblservice.LogBLService;

/**
 * Created on 2017/12/25
 * @author 巽
 *
 */
public class LogBLService_Stub implements LogBLService{
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
		if(start.toLocalDate().toString().equals("2017-12-25")){
			return data;
		}
		return new ArrayList<>();
	}

}
