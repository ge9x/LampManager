package bl.promotionbl;

import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class MockPromotionTotal extends PromotionTotal{
	
	private PromotionTotalVO promotionTotal = new PromotionTotalVO("000003", new Date(), new Date(), 0, new ArrayList<GoodsVO>(), 0);
	
	@Override
	public ArrayList<PromotionTotalVO> show(){
		ArrayList<PromotionTotalVO> promotionTotalList = new ArrayList<PromotionTotalVO>();
		promotionTotalList.add(promotionTotal);
		return promotionTotalList;
	}

	@Override
	public void addGift(GoodsVO vo){
		promotionTotal.gifts.add(vo);
		System.out.println("Add gift success!");
	}

	@Override
	public void addVoucher(double price){
		promotionTotal.voucher = price;
		System.out.println("Set voucher success!");
	}

	@Override
	public void setPrice(double price){
		promotionTotal.totalPrice = price;
		System.out.println("Set target price success!");
	}

	@Override
	public void setStartDate(Date date){
		promotionTotal.startDate = date;
		System.out.println("Set start time success!");
	}

	@Override
	public void setEndDate(Date date){
		promotionTotal.endDate = date;
		System.out.println("Set end time success!");
	}

	@Override
	public ResultMessage submit(PromotionTotalVO vo){
		if(vo.endDate.after(vo.startDate)){
			System.out.println("Submit success!");
			return ResultMessage.SUCCESS;
		}
		else{
			System.out.println("Set time error!");
			return ResultMessage.FAILED;
		}
	}
	
	@Override
	public PromotionTotalVO findPromotionByID(String promotionID){
		if(promotionID.equals(promotionTotal.promotionID)){
			return promotionTotal;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}
	
}
