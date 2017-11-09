package blstubdriver;

import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotioncustomer.PromotionCustomerBLService;
import util.Level;
import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionCustomerVO;

public class PromotionCustomer_Stub implements PromotionCustomerBLService{

	public ArrayList<PromotionCustomerVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionCustomerVO> promotion = new ArrayList<PromotionCustomerVO>();
		PromotionCustomerVO promotion1 = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		PromotionCustomerVO promotion2 = new PromotionCustomerVO("000004", new Date(), new Date(), 0, 500, new ArrayList<GoodsVO>(), Level.LEVEL_FIVE);
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
	}

	public void addGift(GoodsVO vo) {
		// TODO Auto-generated method stub
		ArrayList<GoodsVO> gifts = new ArrayList<GoodsVO>();
		gifts.add(vo);
		System.out.println("Add gift success!");
	}

	public void addVoucher(double price) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		promotion.voucher = price;
		System.out.println("Set voucher success!");
	}

	public void addAllowance(double price) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		promotion.allowance = price;
		System.out.println("Set allowance success!");
	}

	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		promotion.startDate = date;
		System.out.println("Set start time success!");
	}

	public void setEndDate(Date date) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		promotion.endDate = date;
		System.out.println("Set end time success!");
	}

	public ResultMessage submit(PromotionCustomerVO vo) {
		// TODO Auto-generated method stub
		if(vo.endDate.after(vo.startDate)){
			System.out.println("Submit success!");
			return ResultMessage.SUCCESS;
		}
		else{
			System.out.println("Set time error!");
			return ResultMessage.FAILED;
		}
	}

	public void setCustomer(Level level) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		promotion.level = level;
		System.out.println("Set customer level success!");
	}

	public PromotionCustomerVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		PromotionCustomerVO promotion = new PromotionCustomerVO("000003", new Date(), new Date(), 1000, 0, new ArrayList<GoodsVO>(), Level.LEVEL_THREE);
		if(promotion.promotionID.equals(promotionID)){
			return promotion;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}

}
