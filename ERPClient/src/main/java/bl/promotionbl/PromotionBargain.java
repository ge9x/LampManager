package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.loader.custom.EntityFetchReturn;

import bl.financialbl.AccountBillItem;
import bl.salesbl.GoodsItem;
import bl.salesbl.Purchase;
import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import po.AccountBillItemPO;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import rmi.PromotionRemoteHelper;
import util.PromotionType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.UserVO;

public class PromotionBargain extends Promotion{
	
	ArrayList<PromotionBargainPO> promotionBargainPOs;
	UserInfo userInfo;
	
	public PromotionBargain(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
		userInfo = new UserController();
	}
	
	public ArrayList<PromotionBargainVO> show() throws RemoteException{
		ArrayList<PromotionBargainVO> promotionBargainVOs = new ArrayList<>();
		promotionBargainPOs = promotionDataService.showPB();
		for(PromotionBargainPO po:promotionBargainPOs){
			promotionBargainVOs.add(poTOvo(po));
		}
		return promotionBargainVOs;
	}
	
	public String getNewPromotionBargainID() throws RemoteException{
		return promotionDataService.getNewPromotionBargainID();
	}
	
	public PromotionBargainVO findPromotionByName(String promotionName) throws RemoteException{
		PromotionBargainVO promotionBargainVO = poTOvo(promotionDataService.findPBByName(promotionName));
		return promotionBargainVO;
	}
	
	public void addBargain(GoodsItemVO vo){
		
	}
	
	public void setPrice(double price){
		
	}
	
	public void setStartDate(String date){
		
	}
	
	public void setEndDate(String date){
		
	}
	
	public ResultMessage submit(PromotionBargainVO vo) throws RemoteException{
		return promotionDataService.addPB(voTOpo(vo));
	}
	
	public PromotionBargainVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionBargainVO promotionBargainVO = poTOvo(promotionDataService.findPB(promotionID));
		return promotionBargainVO;
	}
	
	public ResultMessage deletePromotion(String promotionID) throws RemoteException{
		promotionBargainPOs.clear();
		promotionBargainPOs = promotionDataService.showPB();
		for(PromotionBargainPO po:promotionBargainPOs){
			if(po.getPromotionID().equals(promotionID)){
				return promotionDataService.deletePB(po);
			}
		}
		return ResultMessage.FAILED;
	}
	
	public ResultMessage updatePromotion(PromotionBargainVO promotionBargainVO) throws RemoteException{
		promotionBargainPOs = promotionDataService.showPB();
		for(PromotionBargainPO po:promotionBargainPOs){
			if(po.getPromotionID().equals(promotionBargainVO.promotionID)){
				po.setPromotionName(promotionBargainVO.promotionName);
				po.setStartDate(promotionBargainVO.startDate);
				po.setEndDate(promotionBargainVO.endDate);
				po.setBargainTotal(promotionBargainVO.bargainTotal);
				po.setGoodsTotal(promotionBargainVO.goodsTotal);
				po.getBargains().clear();
				ArrayList<GoodsItemVO> itemVOs = promotionBargainVO.bargains;
				for (GoodsItemVO itemVO : itemVOs) {
                    GoodsItemPO itemPO = GoodsItem.voTopo(itemVO);
                    po.getBargains().add(itemPO);
                }
				return promotionDataService.updatePB(po);
			}
		}
		
		return ResultMessage.FAILED;
	}
	
	public PromotionBargainPO voTOpo(PromotionBargainVO promotionBargainVO){
		List<GoodsItemPO> bargains = new ArrayList<>();
		for(GoodsItemVO vo:promotionBargainVO.bargains){
			bargains.add(GoodsItem.voTopo(vo));
		}
		return new PromotionBargainPO(promotionBargainVO.promotionName, promotionBargainVO.startDate, promotionBargainVO.endDate, PromotionType.BARGAIN_STRATEGY, 
				promotionBargainVO.goodsTotal, promotionBargainVO.bargainTotal, bargains, promotionBargainVO.promotionID);
	}
	
	public PromotionBargainVO poTOvo (PromotionBargainPO promotionBargainPO){
		ArrayList<GoodsItemVO> bargains = new ArrayList<>();
		for(GoodsItemPO po:promotionBargainPO.getBargains()){
			bargains.add(GoodsItem.poTovo(po));
		}
		return new PromotionBargainVO(promotionBargainPO.getPromotionName(), promotionBargainPO.getPromotionID(), promotionBargainPO.getGoodsTotal(),
				promotionBargainPO.getBargainTotal(), promotionBargainPO.getStartDate(), promotionBargainPO.getEndDate(), bargains);
	}
	
	public String getCurrentUserName(){
		return userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID());
	}
}
