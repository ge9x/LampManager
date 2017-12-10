package blstubdriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotioncustomer.PromotionCustomerBLService;
import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionCustomerVO;

public class PromotionCustomer_Stub implements PromotionCustomerBLService{

	public ArrayList<PromotionCustomerVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionCustomerVO> promotion = new ArrayList<PromotionCustomerVO>();
		PromotionCustomerVO promotion1 = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		PromotionCustomerVO promotion2 = new PromotionCustomerVO("会员促销策略2","000004", LocalDate.now().toString(), LocalDate.now().toString(), 0, 500, new ArrayList<GoodsItemVO>(), Level.LEVEL_FIVE);
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
	}

	public void addGift(GoodsItemVO vo) {
		// TODO Auto-generated method stub
		ArrayList<GoodsItemVO> gifts = new ArrayList<GoodsItemVO>();
		gifts.add(vo);
		System.out.println("Add gift success!");
	}

	public void addVoucher(double price) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		promotion.voucher = price;
		System.out.println("Set voucher success!");
	}

	public void addAllowance(double price) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		promotion.allowance = price;
		System.out.println("Set allowance success!");
	}

	public void setStartDate(String date) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		promotion.startDate = date;
		System.out.println("Set start time success!");
	}

	public void setEndDate(String date) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		promotion.endDate = date;
		System.out.println("Set end time success!");
	}

	public ResultMessage submit(PromotionCustomerVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	public void setCustomer(Level level) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		promotion.level = level;
		System.out.println("Set customer level success!");
	}

	public PromotionCustomerVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("会员促销策略1","000003", LocalDate.now().toString(), LocalDate.now().toString(), 1000, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		if(promotion.promotionID.equals(promotionID)){
			return promotion;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}

	@Override
	public ResultMessage deletePromotion(String promotionID) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage updatePromotion(PromotionCustomerVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

}
