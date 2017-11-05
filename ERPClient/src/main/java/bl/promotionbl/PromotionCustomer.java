package bl.promotionbl;

import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionCustomerVO;

public class PromotionCustomer extends Promotion{
	
	private PromotionCustomerVO vo;
	
	public ArrayList<PromotionCustomerVO> show(){
		return null;
	}
	
	public void addGift(GoodsVO vo){
		
	}

	public void addVoucher(double price){
		
	}

	public void addAllowance(double price){
		
	}

	public void setCustomer(Level level){
		
	}

	public void setStartDate(Date date){
		
	}

	public void setEndDate(Date date){
		
	}

	public ResultMessage submit(PromotionCustomerVO vo){
		return null;
	}
}
