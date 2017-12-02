package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionCustomerVO;

public class PromotionCustomer extends Promotion{
	
	private PromotionCustomerVO vo;
	
	public ArrayList<PromotionCustomerVO> show(){
		return null;
	}
	
	public void addGift(GoodsItemVO vo){
		
	}

	public void addVoucher(double price){
		
	}

	public void addAllowance(double price){
		
	}

	public void setCustomer(Level level){
		
	}

	public void setStartDate(LocalDate date){
		
	}

	public void setEndDate(LocalDate date){
		
	}

	public ResultMessage submit(PromotionCustomerVO vo){
		return null;
	}
	
	public PromotionCustomerVO findPromotionByID(String promotionID){
		return null;
	}
}
