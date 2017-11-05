package blstubdriver;

import java.util.ArrayList;
import java.util.Date;

import blservice.promotionTotal.PromotionTotalBLService;
import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionTotalVO;

public class PromotionTotal_Stub implements PromotionTotalBLService{

	public ArrayList<PromotionTotalVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionTotalVO> promotion = new ArrayList<PromotionTotalVO>();
		PromotionTotalVO promotion1 = new PromotionTotalVO("000005", new Date(), new Date(), 500, new ArrayList<GoodsVO>(), 10000);
		PromotionTotalVO promotion2 = new PromotionTotalVO("000006", new Date(), new Date(), 0, new ArrayList<GoodsVO>(), 30000);
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
	}

	public void addGift(GoodsVO vo) {
		// TODO Auto-generated method stub
		PromotionTotalVO promotion = new PromotionTotalVO("000005", new Date(), new Date(), 500, new ArrayList<GoodsVO>(), 10000);
		promotion.gifts.add(vo);
		System.out.println("Add gift success!");
	}

	public void addVoucher(double price) {
		// TODO Auto-generated method stub
		PromotionTotalVO promotion = new PromotionTotalVO("000005", new Date(), new Date(), 500, new ArrayList<GoodsVO>(), 10000);
		promotion.voucher = price;
		System.out.println("Set voucher success!");
	}

	public void setPrice(double price) {
		// TODO Auto-generated method stub
		PromotionTotalVO promotion = new PromotionTotalVO("000005", new Date(), new Date(), 500, new ArrayList<GoodsVO>(), 10000);
		promotion.totalPrice = price;
		System.out.println("Set target price success!");
	}

	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		PromotionTotalVO promotion = new PromotionTotalVO("000005", new Date(), new Date(), 500, new ArrayList<GoodsVO>(), 10000);
		promotion.startDate = date;
		System.out.println("Set start time success!");
	}

	public void setEndDate(Date date) {
		// TODO Auto-generated method stub
		PromotionTotalVO promotion = new PromotionTotalVO("000005", new Date(), new Date(), 500, new ArrayList<GoodsVO>(), 10000);
		promotion.endDate = date;
		System.out.println("Set end time success!");
	}

	public ResultMessage submit(PromotionTotalVO vo) {
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

}
