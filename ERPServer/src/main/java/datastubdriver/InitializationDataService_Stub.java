package datastubdriver;

import dataservice.initializationdataservice.InitializationDataService;
import po.*;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class InitializationDataService_Stub implements InitializationDataService{

	@Override
	public ResultMessage init(InitializationPO po) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<String> getAllInitDates() {
		ArrayList<String> dates=new ArrayList<>();
		dates.add("2017-12-21");
		return dates;
	}

	@Override
	public InitializationPO getInitializationByDate(String date) {
		InitCustomerPO c1=new InitCustomerPO("00000001", CustomerCategory.PUR_AGENT.getValue(), Level.LEVEL_THREE.getValue(), "进货商1", "13579593893", "马群", "599000", "567@163.com", 300.0, 200.0, 100.0, "Bobule", 30);
		ArrayList<InitCustomerPO> initCustomerPOs=new ArrayList<>();
		initCustomerPOs.add(c1);
		
		InitAccountPO a1=new InitAccountPO(1,"aster", 1000);
		ArrayList<InitAccountPO> initAccountPOs=new ArrayList<>();
		initAccountPOs.add(a1);
		
		InitClassificationPO classification1=new InitClassificationPO(1, "日光灯");
		ArrayList<InitClassificationPO> initClassificationPOs=new ArrayList<>();
		initClassificationPOs.add(classification1);
		 
		InitGoodsPO g1=new InitGoodsPO("0003001", "苹果牌护眼灯", "m", 300.0, 200.0, 230.0, 240.0, 3,"日光灯");
		ArrayList<InitGoodsPO> initGoodsPOs=new ArrayList<>();
		initGoodsPOs.add(g1);
		
		InitializationPO initializationPO=new InitializationPO("2017-12-21", initCustomerPOs, initAccountPOs, initGoodsPOs, initClassificationPOs);
		return initializationPO;
	}
}
