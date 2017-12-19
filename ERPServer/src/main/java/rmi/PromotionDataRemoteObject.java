package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.promotiondataimpl.PromotionDataServiceImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.GoodsItemPO;
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

	@Override
	public ArrayList<PromotionBargainPO> showPB() throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.showPB();
	}

	@Override
	public ArrayList<PromotionCustomerPO> showPC() throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.showPC();
	}

	@Override
	public ArrayList<PromotionTotalPO> showPT() throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.showPT();
	}

	@Override
	public String getNewPromotionBargainID() throws RemoteException {
		return promotionDataService.getNewPromotionBargainID();
	}

	@Override
	public String getNewPromotionCustomerID() throws RemoteException {
		return promotionDataService.getNewPromotionCustomerID();
	}

	@Override
	public String getNewPromotionTotalID() throws RemoteException {
		return promotionDataService.getNewPromotionTotalID();
	}

	@Override
	public ResultMessage addGoodsItem(GoodsItemPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.addGoodsItem(po);
	}

	@Override
	public PromotionCustomerPO findPCByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findPCByName(name);
	}

	@Override
	public PromotionBargainPO findPBByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findPBByName(name);
	}

	@Override
	public PromotionTotalPO findPTByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findPTByName(name);
	}

}
