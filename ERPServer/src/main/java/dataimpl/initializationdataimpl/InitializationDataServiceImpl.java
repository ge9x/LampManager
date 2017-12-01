package dataimpl.initializationdataimpl;

import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.initializationdataservice.InitializationDataService;
import po.AccountPO;
import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsPO;
import po.InitAccountPO;
import util.Criterion;
import util.ResultMessage;

public class InitializationDataServiceImpl implements InitializationDataService{
	private static InitializationDataServiceImpl initializationDataServiceImpl;
	private DataHelper<InitAccountPO> initAccountDataHelper;
	private DataHelper<CustomerPO> customerDataHelper;
	private DataHelper<AccountPO> accountDataHelper;
	private DataHelper<GoodsPO> goodsDataHelper;
	private DataHelper<ClassificationPO> classificationDataHelper;
	
	public static InitializationDataServiceImpl getInstance(){
		if(initializationDataServiceImpl == null){
			initializationDataServiceImpl = new InitializationDataServiceImpl();
		}
		return initializationDataServiceImpl;
	}
	
	private InitializationDataServiceImpl(){
		this.initAccountDataHelper = new HibernateDataHelper<InitAccountPO>(InitAccountPO.class);
	}
	
	public ResultMessage init(InitAccountPO po) {
		/**
		ArrayList<CustomerPO> cus=(ArrayList<CustomerPO>) po.getCustomerPOS();
		for(CustomerPO cuspo:cus){
			customerDataHelper.save(cuspo);
		}
		
		ArrayList<AccountPO> acc=(ArrayList<AccountPO>) po.getAccountPOS();
		for(AccountPO accpo:acc){
			accountDataHelper.save(accpo);
		}
		
		ArrayList<GoodsPO> goods=(ArrayList<GoodsPO>) po.getGoodsPOS();
		for(GoodsPO goodspo:goods){
			goodsDataHelper.save(goodspo);
		}
		
		ArrayList<ClassificationPO> cla=(ArrayList<ClassificationPO>) po.getClassificationPOS();
		for(ClassificationPO clapo:cla){
			classificationDataHelper.save(clapo);
		}
		*/
		return initAccountDataHelper.save(po);
	}

	public InitAccountPO show() {
		return initAccountDataHelper.multiQuery(new ArrayList<Criterion>()).get(0);
	}


}
