package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class MockPromotionTotal extends PromotionTotal{
	
	private PromotionTotalVO promotionTotal = new PromotionTotalVO("总价促销策略","000003", LocalDate.now().toString(), LocalDate.now().toString(), 0, new ArrayList<GoodsItemVO>(), 0);
	
	@Override
	public ArrayList<PromotionTotalVO> show(){
		ArrayList<PromotionTotalVO> promotionTotalList = new ArrayList<PromotionTotalVO>();
		promotionTotalList.add(promotionTotal);
		return promotionTotalList;
	}

	@Override
	public void addGift(GoodsItemVO vo){
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
	public void setStartDate(String date){
		promotionTotal.startDate = date;
		System.out.println("Set start time success!");
	}

	@Override
	public void setEndDate(String date){
		promotionTotal.endDate = date;
		System.out.println("Set end time success!");
	}

	@Override
	public ResultMessage submit(PromotionTotalVO vo){
		return ResultMessage.SUCCESS;
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
