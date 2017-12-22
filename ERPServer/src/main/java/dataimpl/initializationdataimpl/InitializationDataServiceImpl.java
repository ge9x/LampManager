package dataimpl.initializationdataimpl;

import java.util.ArrayList;
import java.util.List;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.initializationdataservice.InitializationDataService;
import po.AccountPO;
import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsPO;
import po.InitAccountPO;
import po.InitClassificationPO;
import po.InitCustomerPO;
import po.InitGoodsPO;
import po.InitializationPO;
import util.Criterion;
import util.ResultMessage;

public class InitializationDataServiceImpl implements InitializationDataService{
	private static InitializationDataServiceImpl initializationDataServiceImpl;
	private DataHelper<InitAccountPO> initAccountDataHelper;
	private DataHelper<InitCustomerPO> initCustomerDataHelper;
	private DataHelper<InitClassificationPO> initClassificationDataHelper;
	private DataHelper<InitGoodsPO> initGoodsDataHelper;
	private DataHelper<InitializationPO> initializationDataHelper;
	
	public static InitializationDataServiceImpl getInstance(){
		if(initializationDataServiceImpl == null){
			initializationDataServiceImpl = new InitializationDataServiceImpl();
		}
		return initializationDataServiceImpl;
	}
	
	private InitializationDataServiceImpl(){
		this.initAccountDataHelper = new HibernateDataHelper<InitAccountPO>(InitAccountPO.class);
		this.initCustomerDataHelper=new HibernateDataHelper<InitCustomerPO>(InitCustomerPO.class);
		this.initClassificationDataHelper=new HibernateDataHelper<InitClassificationPO>(InitClassificationPO.class);
		this.initGoodsDataHelper=new HibernateDataHelper<InitGoodsPO>(InitGoodsPO.class);
		this.initializationDataHelper=new HibernateDataHelper<InitializationPO>(InitializationPO.class);
	}
	
	public ResultMessage init(InitializationPO po) {
		List<InitCustomerPO> customerPOs=po.getInitCustomerPOS();
		for(InitCustomerPO initCustomerPO:customerPOs){
			initCustomerDataHelper.save(initCustomerPO);
		}
		List<InitClassificationPO> classificationPOs=po.getInitClassificationPOS();
		for(InitClassificationPO classificationPO:classificationPOs){
			initClassificationDataHelper.save(classificationPO);
		}
		List<InitGoodsPO> initGoodsPOs=po.getInitGoodsPOS();
		for(InitGoodsPO initGoodsPO:initGoodsPOs){
			initGoodsDataHelper.save(initGoodsPO);
		}
		List<InitAccountPO> initAccountPOs=po.getInitAccountPOS();
		for(InitAccountPO initAccountPO:initAccountPOs){
			initAccountDataHelper.save(initAccountPO);
		}
		return initializationDataHelper.save(po);
	}
	
	@Override
	public ArrayList<String> getAllInitDates() {
	    ArrayList<InitializationPO> initializationPOs=initializationDataHelper.multiQuery(new ArrayList<Criterion>());
	    ArrayList<String> initDates=new ArrayList<>();
	    for(InitializationPO initializationPO:initializationPOs){
	    	initDates.add(initializationPO.getDate());
	    }
		return initDates;
	}

	@Override
	public InitializationPO getInitializationByDate(String date) {
		ArrayList<InitializationPO> initializationPOs=initializationDataHelper.multiQuery(new ArrayList<Criterion>());
		System.out.println(initializationPOs.get(0).getDate());
		for(InitializationPO initializationPO:initializationPOs){
			if(initializationPO.getDate().equals(date)){
				return initializationPO;
			}
		}
		return null;
	}


}
