package dataimpl.initializationdataimpl;

import java.util.ArrayList;

import datahelper.DataHelper;
import dataservice.initializationdataservice.InitializationDataService;
import po.ClassificationPO;
import po.InitAccountPO;
import util.Criterion;
import util.ResultMessage;

public class InitializationDataServiceImpl implements InitializationDataService{
	private static InitializationDataService initializationDataServiceImpl;
	private DataHelper<InitAccountPO> initAccountDataHelper;
	
	public ResultMessage init(InitAccountPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public InitAccountPO show() {
		return initAccountDataHelper.multiQuery(new ArrayList<Criterion>()).get(0);
	}

}
