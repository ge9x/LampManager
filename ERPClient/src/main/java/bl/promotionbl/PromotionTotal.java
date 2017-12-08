package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bl.salesbl.Purchase;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import po.PromotionTotalPO;
import rmi.PromotionRemoteHelper;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class PromotionTotal extends Promotion{
	
	private ArrayList<PromotionTotalPO> promotionTotalPOs;
	
	public PromotionTotal(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
	}
	
	public ArrayList<PromotionTotalVO> show() throws RemoteException{
		ArrayList<PromotionTotalVO> promotionTotalVOs = new ArrayList<>();
		promotionTotalPOs = promotionDataService.showPT();
		for(PromotionTotalPO po:promotionTotalPOs){
			promotionTotalVOs.add(poTOvo(po));
		}
		return promotionTotalVOs;
	}

	public void addGift(GoodsItemVO vo){
		
	}

	public void addVoucher(double price){
		
	}

	public void setPrice(double price){
		
	}

	public void setStartDate(String date){
		
	}

	public void setEndDate(String date){
		
	}

	public ResultMessage submit(PromotionTotalVO vo) throws RemoteException{
		return promotionDataService.addPT(voTOpo(vo));
	}
	
	public PromotionTotalVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionTotalVO vo = poTOvo(promotionDataService.findPT(promotionID));
		return vo;
	}
	
	public ResultMessage deletePromotion(String promotionID) throws RemoteException{
		promotionTotalPOs.clear();
		promotionTotalPOs = promotionDataService.showPT();
		for(PromotionTotalPO po:promotionTotalPOs){
			if(po.getPromotionID().equals(promotionID)){
				return promotionDataService.addPT(po);
			}
		}
		return ResultMessage.FAILED;
	}
	
	public ResultMessage updatePromotion(PromotionTotalVO promotionTotalVO) throws RemoteException{
		promotionTotalPOs.clear();
		promotionTotalPOs = promotionDataService.showPT();
		for(PromotionTotalPO po:promotionTotalPOs){
			if(po.getPromotionID().equals(promotionTotalVO.promotionID)){
				po.setPromotionName(promotionTotalVO.promotionName);
				po.setStartDate(promotionTotalVO.startDate);
				po.setEndDate(promotionTotalVO.endDate);
				po.setTotalPrice(promotionTotalVO.totalPrice);
				po.setVoucher(promotionTotalVO.voucher);
				po.getGifts().clear();
				ArrayList<GoodsItemVO> itemVOs = promotionTotalVO.gifts;
				for (GoodsItemVO itemVO : itemVOs) {
                    GoodsItemPO itemPO = Purchase.voTopo(itemVO);
                    po.getGifts().add(itemPO);
                }
				return promotionDataService.updatePT(po);
			}
		}
		return ResultMessage.FAILED;
	}
	
	public PromotionTotalVO poTOvo(PromotionTotalPO promotionTotalPO){
		ArrayList<GoodsItemVO> gifts = new ArrayList<>();
		for(GoodsItemPO po:promotionTotalPO.getGifts()){
			gifts.add(Purchase.poTovo(po));
		}
		return new PromotionTotalVO(promotionTotalPO.getPromotionName(), promotionTotalPO.getPromotionID(), promotionTotalPO.getStartDate(),
				promotionTotalPO.getEndDate(), promotionTotalPO.getVoucher(), gifts, promotionTotalPO.getTotalPrice());
	}
	
	public PromotionTotalPO voTOpo(PromotionTotalVO promotionTotalVO){
		ArrayList<GoodsItemPO> gifts = new ArrayList<>();
		for(GoodsItemVO vo:promotionTotalVO.gifts){
			gifts.add(Purchase.voTopo(vo));
		}
		return new PromotionTotalPO(promotionTotalVO.promotionName, promotionTotalVO.startDate, promotionTotalVO.endDate, 
				PromotionType.TOTAL_PROMOTION_STRATEGY, promotionTotalVO.voucher, gifts, promotionTotalVO.totalPrice);
	}
}
