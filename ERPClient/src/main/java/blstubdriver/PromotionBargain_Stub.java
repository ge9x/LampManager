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
	
	ArrayList<GoodsItemVO> goodsItemList=new ArrayList<GoodsItemVO>();

	GoodsItemVO gi1=new GoodsItemVO("01", "霓虹灯",null ,20, 35.0,
			"耐用");
	GoodsItemVO gi2=new GoodsItemVO("02", "挂灯",null, 10, 35.0,
			"好看");
	{
		goodsItemList.add(gi1);
		goodsItemList.add(gi2);
	}

	public ArrayList<PromotionBargainVO> show() {
		// TODO Auto-generated method stub
		ArrayList<PromotionBargainVO> promotion = new ArrayList<PromotionBargainVO>();
		PromotionBargainVO promotion1 = new PromotionBargainVO("特价包策略1","000001", 1000, 900, LocalDate.now().toString(), LocalDate.now().toString(), goodsItemList);
		PromotionBargainVO promotion2 = new PromotionBargainVO("特价包策略2","000002", 1500, 1200, LocalDate.now().toString(), LocalDate.now().toString(), goodsItemList);
		promotion.add(promotion1);
		promotion.add(promotion2);
		return promotion;
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

	@Override
	public ResultMessage updatePromotion(PromotionBargainVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public String getNewPromotionBargainID() {
		// TODO Auto-generated method stub
		return "PB-0001";
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public PromotionBargainVO findPromotionByName(String promotionName) {
		// TODO Auto-generated method stub
		return null;
	}

}
