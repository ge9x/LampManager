package dataimpl.promotiondataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.promotiondataservice.PromotionDataService;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionTotalPO;
import rmi.GoodsDataRemoteObject;
import util.Criterion;
import util.PromotionType;
import util.ResultMessage;

public class PromotionDataServiceImpl implements PromotionDataService{
	private static PromotionDataServiceImpl promotionDataServiceImpl;
	private DataHelper<PromotionCustomerPO> promotionCustomerDataHelper;
	private DataHelper<PromotionBargainPO> promotionBargainDataHelper;
	private DataHelper<PromotionTotalPO> promotionTotalDataHelper;
	private DataHelper<GoodsItemPO> goodsItemDataHelper;
	
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
		this.goodsItemDataHelper=new HibernateDataHelper<GoodsItemPO>(GoodsItemPO.class);
	}

	@Override
	public PromotionCustomerPO findPC(String ID) throws RemoteException {
		return promotionCustomerDataHelper.exactlyQuery("promotionID", ID);
	}

	@Override
	public PromotionBargainPO findPB(String ID) throws RemoteException {
		return promotionBargainDataHelper.exactlyQuery("promotionID", ID);
	}

	@Override
	public PromotionTotalPO findPT(String ID) throws RemoteException {
		return promotionTotalDataHelper.exactlyQuery("promotionID", ID);
	}

	public ResultMessage addPC(PromotionCustomerPO po) throws RemoteException {
		List<GoodsItemPO> goodsItemPOs=po.getGifts();
		for(GoodsItemPO goodsItemPO:goodsItemPOs){
			addGoodsItem(goodsItemPO);
		}
		return promotionCustomerDataHelper.save(po);
	}
	
	public ResultMessage addPB(PromotionBargainPO po) throws RemoteException{
		List<GoodsItemPO> goodsItemPOs=po.getBargains();
		for(GoodsItemPO goodsItemPO:goodsItemPOs){
			addGoodsItem(goodsItemPO);
		}
		return promotionBargainDataHelper.save(po);
	}
	
	public ResultMessage addPT(PromotionTotalPO po) throws RemoteException{
		List<GoodsItemPO> goodsItemPOs=po.getGifts();
		for(GoodsItemPO goodsItemPO:goodsItemPOs){
			addGoodsItem(goodsItemPO);
		}
		return promotionTotalDataHelper.save(po);
	}

	@Override
	public ResultMessage deletePC(PromotionCustomerPO po) throws RemoteException {
		return promotionCustomerDataHelper.delete(po);
	}

	@Override
	public ResultMessage deletePB(PromotionBargainPO po) throws RemoteException {
		return promotionBargainDataHelper.delete(po);
	}

	@Override
	public ResultMessage deletePT(PromotionTotalPO po) throws RemoteException {
		return promotionTotalDataHelper.delete(po);
	}

	@Override
	public ResultMessage updatePC(PromotionCustomerPO po) throws RemoteException {
		return promotionCustomerDataHelper.update(po);
	}

	@Override
	public ResultMessage updatePB(PromotionBargainPO po) throws RemoteException {
		/**
		ArrayList<GoodsItemPO> goodsItemPOs=goodsItemDataHelper.fullyQuery("probar", String.valueOf(po.getID()));
		for(GoodsItemPO goodsItemPO:goodsItemPOs){
			goodsItemDataHelper.delete(goodsItemPO);
		}
		*/
		List<GoodsItemPO> newGoodsItemPOs=po.getBargains();
		for(GoodsItemPO goodsItemPO:newGoodsItemPOs){
			goodsItemDataHelper.save(goodsItemPO);
		}
		return promotionBargainDataHelper.update(po);
	}

	@Override
	public ResultMessage updatePT(PromotionTotalPO po) throws RemoteException {
		return promotionTotalDataHelper.update(po);
	}

	@Override
	public ArrayList<PromotionBargainPO> showPB() throws RemoteException {
		return promotionBargainDataHelper.multiQuery(new ArrayList<>());
	}

	@Override
	public ArrayList<PromotionCustomerPO> showPC() throws RemoteException {
		return promotionCustomerDataHelper.multiQuery(new ArrayList<>());
	}

	@Override
	public ArrayList<PromotionTotalPO> showPT() throws RemoteException {
		return promotionTotalDataHelper.multiQuery(new ArrayList<>());
	}

	@Override
	public String getNewPromotionBargainID() throws RemoteException {
		ArrayList<PromotionBargainPO> pBList=showPB();
		return "PB-"+String.valueOf(pBList.get(pBList.size()-1).getID()+1);
	}

	@Override
	public String getNewPromotionCustomerID() throws RemoteException {
		ArrayList<PromotionCustomerPO> pCList=showPC();
		return "PC-"+String.valueOf(pCList.get(pCList.size()-1).getID()+1);
	}

	@Override
	public String getNewPromotionTotalID() throws RemoteException {
		ArrayList<PromotionTotalPO> pTList=showPT();
		return "PT-"+String.valueOf(pTList.get(pTList.size()-1).getID()+1);
	}

	@Override
	public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException {
		return goodsItemDataHelper.save(po);
	}

}
