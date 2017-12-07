package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import bl.salesbl.Purchase;
import po.GoodsItemPO;
import po.PromotionCustomerPO;
import rmi.PromotionRemoteHelper;
import util.Level;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionCustomerVO;

public class PromotionCustomer extends Promotion{
	
	ArrayList<PromotionCustomerPO> promotionCustomerPOs;
	
	public PromotionCustomer(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
	}
	
	public ArrayList<PromotionCustomerVO> show() throws RemoteException{
		ArrayList<PromotionCustomerVO> promotionCustomerVOs = new ArrayList<>();
		promotionCustomerPOs = promotionDataService.showPC();
		for(PromotionCustomerPO po:promotionCustomerPOs){
			promotionCustomerVOs.add(poTOvo(po));
		}
		return promotionCustomerVOs;
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
	
	public PromotionCustomerVO poTOvo(PromotionCustomerPO promotionCustomerPO){
		ArrayList<GoodsItemVO> gifts = new ArrayList<>();
		for(GoodsItemPO po:promotionCustomerPO.getGifts()){
			gifts.add(Purchase.poTovo(po));
		}
		return new PromotionCustomerVO(promotionCustomerPO.getPromotionName(), promotionCustomerPO.getPromotionID(), promotionCustomerPO.getStartDate(),
				promotionCustomerPO.getEndDate(), promotionCustomerPO.getVoucher(), promotionCustomerPO.getAllowance(), gifts, promotionCustomerPO.getLevel());
	}
	
	public PromotionCustomerPO voTOpo(PromotionCustomerVO promotionCustomerVO){
		ArrayList<GoodsItemPO> gifts = new ArrayList<>();
		for(GoodsItemVO vo:promotionCustomerVO.gifts){
			gifts.add(Purchase.voTopo(vo));
		}
		return new PromotionCustomerPO(promotionCustomerVO.promotionName, promotionCustomerVO.startDate, promotionCustomerVO.endDate, 
				PromotionType.MEMBER_PROMOTION_STRATEGY, promotionCustomerVO.voucher, promotionCustomerVO.allowance, gifts, 
				promotionCustomerVO.level);
	}
}
