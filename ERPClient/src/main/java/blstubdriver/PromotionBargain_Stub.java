package blstubdriver;

import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotionbargain.PromotionBargainBLService;
import util.ResultMessage;
import vo.GoodsVO;
import vo.PromotionBargainVO;

public class PromotionBargain_Stub implements PromotionBargainBLService{

	public ArrayList<PromotionBargainVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionBargainVO> promotion = new ArrayList<PromotionBargainVO>();
		PromotionBargainVO promotion1 = new PromotionBargainVO("特价包策略1","000001", 1000, 900, new Date(), new Date(), new ArrayList<GoodsVO>());
		PromotionBargainVO promotion2 = new PromotionBargainVO("特价包策略2","000002", 1500, 1200, new Date(), new Date(), new ArrayList<GoodsVO>());
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
	}

	public void addBargain(GoodsVO vo) {
		// TODO Auto-generated method stub
		ArrayList<GoodsVO> bargain = new ArrayList<GoodsVO>();
		bargain.add(vo);
		System.out.println("Add goods success!");
	}

	public void setPrice(double price) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, new Date(), new Date(), new ArrayList<GoodsVO>());
		promotion.bargainTotal = price;
		System.out.println("Set bargain price success!");
	}

	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, new Date(), new Date(), new ArrayList<GoodsVO>());
		promotion.startDate = date;
		System.out.println("Set start time success!");
	}

	public void setEndDate(Date date) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, new Date(), new Date(), new ArrayList<GoodsVO>());
		promotion.endDate = date;
		System.out.println("Set end time success!");
	}

	public ResultMessage submit(PromotionBargainVO vo) {
		// TODO Auto-generated method stub
		if(!vo.bargains.isEmpty()&&vo.bargainTotal!=0&&vo.bargainTotal<vo.goodsTotal&&vo.endDate.after(vo.startDate)){
			System.out.println("Submit success!");
			return ResultMessage.SUCCESS;
		}
		else{
			if(vo.bargains.isEmpty()){
				System.out.println("Please add goods!");
			}
			else if(vo.bargainTotal==0){
				System.out.println("The price of bargains can't be zero!");
			}
			else if(vo.bargainTotal>=vo.goodsTotal){
				System.out.println("The price of bargains is higher than the original price!");
			}
			else if(!vo.endDate.after(vo.startDate)){
				System.out.println("Set time error!");
			}
			return ResultMessage.FAILED;
		}
	}

	public PromotionBargainVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, new Date(), new Date(), new ArrayList<GoodsVO>());
		if(promotion.promotionID.equals(promotionID)){
			return promotion;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}

}
