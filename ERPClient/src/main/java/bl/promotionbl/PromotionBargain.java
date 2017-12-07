package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public ResultMessage submit(PromotionBargainVO vo){
		return null;
	}
	
	public PromotionBargainVO findPromotionByID(String promotionID){
		return null;
	}
	
	public PromotionBargainPO voTOpo(PromotionBargainVO promotionBargainVO){
		return new PromotionBargainPO(promotionBargainVO.startDate, promotionBargainVO.endDate, PromotionType.BARGAIN_STRATEGY, 
				promotionBargainVO.goodsTotal, promotionBargainVO.bargainTotal, List<GoodsItemPO> bargains);
	}
}
