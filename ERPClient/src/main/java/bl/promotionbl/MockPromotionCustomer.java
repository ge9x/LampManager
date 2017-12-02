package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;

public class MockPromotionCustomer extends PromotionCustomer{
	private PromotionCustomerVO promotionCustomer = new PromotionCustomerVO("会员促销策略","000002", LocalDate.now(), LocalDate.now(), 0, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
	
	@Override
	public ArrayList<PromotionCustomerVO> show(){
		ArrayList<PromotionCustomerVO> promotionCustomerList = new ArrayList<PromotionCustomerVO>();
		promotionCustomerList.add(promotionCustomer);
		return promotionCustomerList;
	}
	
	@Override
	public void addGift(GoodsItemVO vo){
		promotionCustomer.gifts.add(vo);
		System.out.println("Add gift success!");
	}

	@Override
	public void addVoucher(double price){
		promotionCustomer.voucher = price;
		System.out.println("Set voucher success!");
	}

	@Override
	public void addAllowance(double price){
		promotionCustomer.allowance = price;
		System.out.println("Set allowance success!");
	}

	@Override
	public void setCustomer(Level level){
		promotionCustomer.level = level;
		System.out.println("Set customer level success!");
	}

	@Override
	public void setStartDate(LocalDate date){
		promotionCustomer.startDate = date;
		System.out.println("Set start time success!");
	}

	@Override
	public void setEndDate(LocalDate date){
		promotionCustomer.endDate = date;
		System.out.println("Set end time success!");
	}

	@Override
	public ResultMessage submit(PromotionCustomerVO vo){
		if(vo.endDate.isAfter(vo.startDate)){
			System.out.println("Submit success!");
			return ResultMessage.SUCCESS;
		}
		else{
			System.out.println("Set time error!");
			return ResultMessage.FAILED;
		}
	}
	
	@Override
	public PromotionCustomerVO findPromotionByID(String promotionID){
		if(promotionID.equals(promotionCustomer.promotionID)){
			return promotionCustomer;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}
	
}
