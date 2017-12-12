package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeListener;

import org.jboss.logging.Logger.Level;

import dataservice.promotiondataservice.PromotionDataService;
import po.GoodsItemPO;
import po.GoodsPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionPO;
import po.PromotionTotalPO;
import util.PromotionType;
import util.ResultMessage;

public class PromotionDataService_Stub implements PromotionDataService{
	ArrayList<PromotionPO> promotion = new ArrayList<PromotionPO>();
	ArrayList<PromotionCustomerPO> PCList=new ArrayList<PromotionCustomerPO>();
	ArrayList<PromotionBargainPO> PBList=new ArrayList<PromotionBargainPO>();
	ArrayList<PromotionTotalPO> PTList=new ArrayList<PromotionTotalPO>();
	long count = 1;
	String newID = String.valueOf(count);
	
	ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
	
	GoodsItemPO gi1=new GoodsItemPO("1", "霓虹灯",null, 20, 35.0,
			"耐用");
	GoodsItemPO gi2=new GoodsItemPO("2", "挂灯",null, 10, 35.0,
			"好看");
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
	}
	
	//PCList.add(new PromotionCustomerPO("会员促销策略","2017-11-30","2017-11-30",PromotionType.MEMBER_PROMOTION_STRATEGY,500,goodsItemList,Level.LEVEL_FOUR));
	//PBList.add(new PromotionBargainPO("特价包","2017-12-1","2017-12-1",PromotionType.BARGAIN_STRATEGY,900.0,500.0,goodsItemList));
	//PTList.add(new PromotionTotalPO("总价促销策略","2017-12-1","2017-12-1",PromotionType.TOTAL_PROMOTION_STRATEGY,200.0,goodsItemList,300.0));
	
	public PromotionCustomerPO findPC(String ID) throws RemoteException {
		for(PromotionCustomerPO po:PCList){
			if(po.getPromotionID().equals(ID)){
				return po;
			}
		}
		return null;
	}

	public PromotionBargainPO findPB(String ID) throws RemoteException {
		for(PromotionBargainPO po:PBList){
			if(po.getPromotionID().equals(ID)){
				return po;
			}
		}
		return null;
	}

	public PromotionTotalPO findPT(String ID) throws RemoteException {
		for(PromotionTotalPO po:PTList){
			if(po.getPromotionID().equals(ID)){
				return po;
			}
		}
		return null;
	}

	public ResultMessage addPC(PromotionCustomerPO po) throws RemoteException {
		PCList.add(po);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage addPB(PromotionBargainPO po) throws RemoteException {
		PBList.add(po);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage addPT(PromotionTotalPO po) throws RemoteException {
		PTList.add(po);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage deletePC(PromotionCustomerPO po) throws RemoteException {
		for(PromotionCustomerPO pc:PCList){
			if(pc.getID()==po.getID()){
				PCList.remove(pc);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage deletePB(PromotionBargainPO po) throws RemoteException {
		for(PromotionBargainPO pb:PBList){
			if(pb.getID()==po.getID()){
				PBList.remove(pb);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage deletePT(PromotionTotalPO po) throws RemoteException {
		for(PromotionTotalPO pt:PTList){
			if(pt.getID()==po.getID()){
				PTList.remove(pt);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage updatePC(PromotionCustomerPO po) throws RemoteException {
		for(PromotionCustomerPO pc:PCList){
			if(pc.getID()==po.getID()){
				PCList.remove(pc);
				PCList.add(po);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage updatePB(PromotionBargainPO po) throws RemoteException {
		for(PromotionBargainPO pb:PBList){
			if(pb.getID()==po.getID()){
				PBList.remove(pb);
				PBList.add(po);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage updatePT(PromotionTotalPO po) throws RemoteException {
		for(PromotionTotalPO pt:PTList){
			if(pt.getID()==po.getID()){
				PTList.remove(pt);
				PTList.add(po);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILED;
	}

	@Override
	public ArrayList<PromotionBargainPO> showPB() throws RemoteException {
		return PBList;
	}

	@Override
	public ArrayList<PromotionCustomerPO> showPC() throws RemoteException {
		return PCList;
	}

	@Override
	public ArrayList<PromotionTotalPO> showPT() throws RemoteException {
		return PTList;
	}

	@Override
	public String getNewPromotionBargainID() throws RemoteException {
		return "PB-1";
	}

	@Override
	public String getNewPromotionCustomerID() throws RemoteException {
		return "PC-1";
	}

	@Override
	public String getNewPromotionTotalID() throws RemoteException {
		return "PT-1";
	}

}
