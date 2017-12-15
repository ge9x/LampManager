package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import bl.salesbl.GoodsItem;
import bl.salesbl.Purchase;
import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import po.PromotionCustomerPO;
import rmi.PromotionRemoteHelper;
import util.Level;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionCustomerVO;

public class PromotionCustomer extends Promotion{
	
	ArrayList<PromotionCustomerPO> promotionCustomerPOs;
	UserInfo userInfo;
	
	public PromotionCustomer(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
		userInfo = new UserController();
	}
	
	public ArrayList<PromotionCustomerVO> show() throws RemoteException{
		ArrayList<PromotionCustomerVO> promotionCustomerVOs = new ArrayList<>();
		promotionCustomerPOs = promotionDataService.showPC();
		for(PromotionCustomerPO po:promotionCustomerPOs){
			promotionCustomerVOs.add(poTOvo(po));
		}
		return promotionCustomerVOs;
	}
	
	public String getNewPromotionCustomerID() throws RemoteException{
		return promotionDataService.getNewPromotionCustomerID();
	}
	
	public void addGift(GoodsItemVO vo){
		
	}

	public void addVoucher(double price){
		
	}

	public void addAllowance(double price){
		
	}

	public void setCustomer(Level level){
		
	}

	public void setStartDate(String date){
		
	}

	public void setEndDate(String date){
		
	}

	public ResultMessage submit(PromotionCustomerVO vo) throws RemoteException{
		return promotionDataService.addPC(voTOpo(vo));
	}
	
	public PromotionCustomerVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionCustomerVO vo = poTOvo(promotionDataService.findPC(promotionID));
		return vo;
	}
	
	public ResultMessage deletePromotion(String promotionID) throws RemoteException{
		promotionCustomerPOs.clear();
		promotionCustomerPOs = promotionDataService.showPC();
		for(PromotionCustomerPO po:promotionCustomerPOs){
			if(po.getPromotionID().equals(promotionID)){
				return promotionDataService.deletePC(po);
			}
		}
		return ResultMessage.FAILED;
	}
	
	public ResultMessage updatePromotion(PromotionCustomerVO promotionCustomerVO) throws RemoteException{
		promotionCustomerPOs = promotionDataService.showPC();
		for(PromotionCustomerPO po:promotionCustomerPOs){
			if(po.getPromotionID().equals(promotionCustomerVO.promotionID)){
				po.setPromotionName(promotionCustomerVO.promotionName);
				po.setStartDate(promotionCustomerVO.startDate);
				po.setEndDate(promotionCustomerVO.endDate);
				po.setLevel(promotionCustomerVO.level);
				po.setAllowance(promotionCustomerVO.allowance);
				po.setVoucher(promotionCustomerVO.voucher);
				po.getGifts().clear();
				ArrayList<GoodsItemVO> itemVOs = promotionCustomerVO.gifts;
				for (GoodsItemVO itemVO : itemVOs) {
                    GoodsItemPO itemPO = GoodsItem.voTopo(itemVO);
                    po.getGifts().add(itemPO);
                }
				return promotionDataService.updatePC(po);
			}
		}
		
		return ResultMessage.FAILED;
	}
	
	public PromotionCustomerVO poTOvo(PromotionCustomerPO promotionCustomerPO){
		ArrayList<GoodsItemVO> gifts = new ArrayList<>();
		for(GoodsItemPO po:promotionCustomerPO.getGifts()){
			gifts.add(GoodsItem.poTovo(po));
		}
		return new PromotionCustomerVO(promotionCustomerPO.getPromotionName(), promotionCustomerPO.getPromotionID(), promotionCustomerPO.getStartDate(),
				promotionCustomerPO.getEndDate(), promotionCustomerPO.getVoucher(), promotionCustomerPO.getAllowance(), gifts, promotionCustomerPO.getLevel());
	}
	
	public PromotionCustomerPO voTOpo(PromotionCustomerVO promotionCustomerVO){
		ArrayList<GoodsItemPO> gifts = new ArrayList<>();
		for(GoodsItemVO vo:promotionCustomerVO.gifts){
			gifts.add(GoodsItem.voTopo(vo));
		}
		return new PromotionCustomerPO(promotionCustomerVO.promotionName, promotionCustomerVO.startDate, promotionCustomerVO.endDate, 
				PromotionType.MEMBER_PROMOTION_STRATEGY, promotionCustomerVO.voucher, promotionCustomerVO.allowance, gifts, 
				promotionCustomerVO.level, promotionCustomerVO.promotionID);
	}
	
	public String getCurrentUserName(){
		return userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID());
	}
}
