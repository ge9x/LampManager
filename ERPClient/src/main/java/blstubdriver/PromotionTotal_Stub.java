package blstubdriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import blservice.promotionblservice.promotionTotal.PromotionTotalBLService;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionTotalVO;

public class PromotionTotal_Stub implements PromotionTotalBLService{

	public ArrayList<PromotionTotalVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionTotalVO> promotion = new ArrayList<PromotionTotalVO>();
		PromotionTotalVO promotion1 = new PromotionTotalVO("总价促销策略1","000005", LocalDate.now().toString(), LocalDate.now().toString(), 500, new ArrayList<GoodsItemVO>(), 10000);
		PromotionTotalVO promotion2 = new PromotionTotalVO("总价促销策略2","000006", LocalDate.now().toString(), LocalDate.now().toString(), 0, new ArrayList<GoodsItemVO>(), 30000);
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
	}

	public ResultMessage submit(PromotionTotalVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	public PromotionTotalVO findPromotionByID(String promotionID) {
		// TODO Auto-generated method stub
		PromotionTotalVO promotion = new PromotionTotalVO("总价促销策略1","000005", LocalDate.now().toString(), LocalDate.now().toString(), 500, new ArrayList<GoodsItemVO>(), 10000);
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
	public ResultMessage updatePromotion(PromotionTotalVO promotionTotalVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public String getNewPromotionTotalID() {
		// TODO Auto-generated method stub
		return "PT-0001";
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public PromotionTotalVO findPromotionByName(String promotionName) {
		// TODO Auto-generated method stub
		return null;
	}

}
