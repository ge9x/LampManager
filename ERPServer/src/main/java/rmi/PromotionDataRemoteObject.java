package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataimpl.promotiondataimpl.PromotionDataServiceImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import po.PromotionTotalPO;
import util.ResultMessage;

public class PromotionDataRemoteObject extends UnicastRemoteObject implements PromotionDataService{

	private PromotionDataService promotionDataService;
	
	protected PromotionDataRemoteObject() throws RemoteException{
		promotionDataService = PromotionDataServiceImpl.getInstance();
	}
	
	@Override
	public PromotionCustomerPO findPC(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findPC(ID);
	}

	@Override
	public PromotionBargainPO findPB(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findPB(ID);
	}

	@Override
	public PromotionTotalPO findPT(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findPT(ID);
	}

	@Override
	public ResultMessage addPC(PromotionCustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.addPC(po);
	}

	@Override
	public ResultMessage addPB(PromotionBargainPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.addPB(po);
	}

	@Override
	public ResultMessage addPT(PromotionTotalPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.addPT(po);
	}

	@Override
	public ResultMessage deletePC(PromotionCustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.deletePC(po);
	}

	@Override
	public ResultMessage deletePB(PromotionBargainPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.deletePB(po);
	}

	@Override
	public ResultMessage deletePT(PromotionTotalPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.deletePT(po);
	}

	@Override
	public ResultMessage updatePC(PromotionCustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.updatePC(po);
	}

	@Override
	public ResultMessage updatePB(PromotionBargainPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.updatePB(po);
	}

	@Override
	public ResultMessage updatePT(PromotionTotalPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.updatePT(po);
	}

}
