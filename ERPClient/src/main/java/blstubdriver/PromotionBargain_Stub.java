package blstubdriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotionbargain.PromotionBargainBLService;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;

public class PromotionBargain_Stub implements PromotionBargainBLService{

	public ArrayList<PromotionBargainVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionBargainVO> promotion = new ArrayList<PromotionBargainVO>();
		PromotionBargainVO promotion1 = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>());
		PromotionBargainVO promotion2 = new PromotionBargainVO("特价包策略2","000002", 1500, 1200, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>());
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
	}

	public void addBargain(GoodsItemVO vo) {
		// TODO Auto-generated method stub
		ArrayList<GoodsItemVO> bargain = new ArrayList<GoodsItemVO>();
		bargain.add(vo);
		System.out.println("Add goods success!");
	}

	public void setPrice(double price) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>());
		promotion.bargainTotal = price;
		System.out.println("Set bargain price success!");
	}

	public void setStartDate(String date) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>());
		promotion.startDate = date;
		System.out.println("Set start time success!");
	}

	public void setEndDate(String date) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>());
		promotion.endDate = date;
		System.out.println("Set end time success!");
	}

	public ResultMessage submit(PromotionBargainVO vo) {
		// TODO Auto-generated method stub
		if(!vo.bargains.isEmpty()&&vo.bargainTotal!=0&&vo.bargainTotal<vo.goodsTotal){
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
			return ResultMessage.FAILED;
		}
	}

	public PromotionBargainVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		PromotionBargainVO promotion = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), new ArrayList<GoodsItemVO>());
		if(promotion.promotionID.equals(promotionID)){
			return promotion;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}

}
