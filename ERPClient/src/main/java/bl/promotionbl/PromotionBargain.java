package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bl.salesbl.Purchase;
import po.GoodsItemPO;
import po.GoodsPO;
import po.PromotionBargainPO;
import rmi.PromotionRemoteHelper;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;

public class PromotionBargain extends Promotion{
	
	ArrayList<PromotionBargainPO> promotionBargainPOs;
	
	public PromotionBargain(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
	}
	
	public ArrayList<PromotionBargainVO> show(){
		ArrayList<PromotionBargainVO> promotionBargainVOs = new ArrayList<>();
		
		return null;
	}
	
	public void addBargain(GoodsItemVO vo){
		
	}
	
	public void setPrice(double price){
		
	}
	
	public void setStartDate(LocalDate date){
		
	}
	
	public void setEndDate(LocalDate date){
		
	}
	
	public ResultMessage submit(PromotionBargainVO vo) throws RemoteException{
		return promotionDataService.addPB(voTOpo(vo));
	}
	
	public PromotionBargainVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionBargainVO promotionBargainVO = poTOvo(promotionDataService.findPB(promotionID));
		return promotionBargainVO;
	}
	
	public PromotionBargainPO voTOpo(PromotionBargainVO promotionBargainVO){
		List<GoodsItemPO> bargains = new ArrayList<>();
		for(GoodsItemVO vo:promotionBargainVO.bargains){
			bargains.add(Purchase.voTopo(vo));
		}
		return new PromotionBargainPO(promotionBargainVO.promotionName, promotionBargainVO.startDate, promotionBargainVO.endDate, PromotionType.BARGAIN_STRATEGY, 
				promotionBargainVO.goodsTotal, promotionBargainVO.bargainTotal, bargains);
	}
	
	public PromotionBargainVO poTOvo (PromotionBargainPO promotionBargainPO){
		return null;
	}
}
