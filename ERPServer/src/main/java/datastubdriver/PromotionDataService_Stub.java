package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger.Level;

import dataservice.promotiondataservice.PromotionDataService;
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
	
	List<GoodsPO> goodsList=new ArrayList<GoodsPO>();
	GoodsPO g1 = new GoodsPO( "圣洁牌纯黑落地灯", "L", null, 700, 250, 233.3, 250, 233.3,1);
	GoodsPO g2 = new GoodsPO( "圣洁牌纯白落地灯", "M", null, 700, 250, 233.3, 250, 233.3,2);
	{
	goodsList.add(g1);
	goodsList.add(g2);
	
		PCList.add(new PromotionCustomerPO("2017-11-30","2017-11-30",PromotionType.MEMBER_PROMOTION_STRATEGY,500,goodsList,null));
		PBList.add(new PromotionBargainPO("2017-12-1","2017-12-1",PromotionType.BARGAIN_STRATEGY,900.0,500.0,goodsList));
		PTList.add(new PromotionTotalPO("2017-12-1","2017-12-1",PromotionType.TOTAL_PROMOTION_STRATEGY,200.0,goodsList,300.0));
	
	}
	
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

}
