package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;

public class PromotionBargain extends Promotion{
	
	private PromotionBargainVO vo;
	
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
}
