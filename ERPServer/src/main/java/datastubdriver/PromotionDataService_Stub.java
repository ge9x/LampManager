package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import util.ResultMessage;

public class PromotionDataService_Stub implements PromotionDataService{
	ArrayList<PromotionPO> promotion = new ArrayList<PromotionPO>();
	long count = 1;
	String newID = String.valueOf(count);
	
	public String getID() throws RemoteException {
		// TODO Auto-generated method stub		
		return newID;
	}

	public PromotionPO find(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		for(PromotionPO po : promotion){
			if(po.getPromotionID().equals(ID)){
				System.out.println("Get promotion success!");
				return po;
			}
		}
		System.out.println("Can't find promotion!");
		return null;
	}

	public ResultMessage add(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		promotion.add(po);
		count++;
		newID = String.valueOf(count);
		System.out.println("Add promotion success!");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(PromotionPO i : promotion){
			if(i.getPromotionID().equals(po.getPromotionID())){
				promotion.remove(i);
				System.out.println("Delete promotion success!");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Promotion doesn't exist!");
		return ResultMessage.NOT_EXIST;
	}

	public ResultMessage update(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(PromotionPO i : promotion){
			if(i.getPromotionID().equals(po.getPromotionID())){
				promotion.remove(i);
				promotion.add(po);
				System.out.println("Update promotion success!");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("Promotion doesn't exist!");
		return ResultMessage.NOT_EXIST;
	}

	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		promotion.clear();
		count = 1;
		System.out.println("Initial success!");
	}

}
