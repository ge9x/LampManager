package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class PromotionTotal extends Promotion{
	
	private PromotionTotalVO vo;
	
	public ArrayList<PromotionTotalVO> show(){
		return null;
	}

	public void addGift(GoodsItemVO vo){
		
	}

	public void addVoucher(double price){
		
	}

	public void setPrice(double price){
		
	}

	public void setStartDate(LocalDate date){
		
	}

	public void setEndDate(LocalDate date){
		
	}

	public ResultMessage submit(PromotionTotalVO vo){
		return null;
	}
	
	public PromotionTotalVO findPromotionByID(String promotionID){
		return null;
	}
}
