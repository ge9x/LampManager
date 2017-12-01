package dataimpl.promotiondataimpl;

import java.rmi.RemoteException;
import java.util.List;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataimpl.customerdataimpl.CustomerDataServiceImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.CustomerPO;
import po.GoodsPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionPO;
import po.PromotionTotalPO;
import util.PromotionType;
import util.ResultMessage;

public class PromotionDataServiceImpl implements PromotionDataService{
	private static PromotionDataServiceImpl promotionDataServiceImpl;
	private DataHelper<PromotionPO> promotionDataHelper;
	private DataHelper<PromotionCustomerPO> promotionCustomerDataHelper;
	private DataHelper<PromotionBargainPO> promotionBargainDataHelper;
	private DataHelper<PromotionTotalPO> promotionTotalDataHelper;
	private DataHelper<GoodsPO> goodsDataHelper;
	
	public static PromotionDataServiceImpl getInstance(){
		if(promotionDataServiceImpl == null){
			promotionDataServiceImpl = new PromotionDataServiceImpl();
		}
		return promotionDataServiceImpl;
	}
	
	private PromotionDataServiceImpl(){
		this.promotionCustomerDataHelper = new HibernateDataHelper<PromotionCustomerPO>(PromotionCustomerPO.class);
		this.promotionBargainDataHelper=new HibernateDataHelper<PromotionBargainPO>(PromotionBargainPO.class);
		this.promotionTotalDataHelper=new HibernateDataHelper<PromotionTotalPO>(PromotionTotalPO.class);
	}

	public String getID(PromotionType type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public PromotionPO find(String ID) throws RemoteException {
		return promotionDataHelper.exactlyQuery("promotionID", ID);
	}

	public ResultMessage add(PromotionCustomerPO po) throws RemoteException {
		List<GoodsPO> goodsList=po.getGifts();
		for(GoodsPO goodsPO:goodsList){
			goodsDataHelper.save(goodsPO);
		}
		return promotionCustomerDataHelper.save(po);
	}
	
	public ResultMessage add(PromotionBargainPO po) throws RemoteException{
		List<GoodsPO> goodsList=po.getBargains();
		for(GoodsPO goodsPO:goodsList){
			goodsDataHelper.save(goodsPO);
		}
		return promotionBargainDataHelper.save(po);
	}
	
	public ResultMessage add(PromotionTotalPO po) throws RemoteException{
		List<GoodsPO> goodsList=po.getGifts();
		for(GoodsPO goodsPO:goodsList){
			goodsDataHelper.save(goodsPO);
		}
		return promotionTotalDataHelper.save(po);
	}

	public ResultMessage delete(PromotionPO po) throws RemoteException {
		return promotionDataHelper.delete(po);
	}

	public ResultMessage update(PromotionPO po) throws RemoteException {
		return promotionDataHelper.update(po);
	}

	public void init() throws RemoteException {
		
	}

	

}
