package bl.promotionbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;

public class MockPromotionBargain extends PromotionBargain{
	private PromotionBargainVO promotionBargain = new PromotionBargainVO("特价包策略","000001", 0, 0, LocalDate.now(), LocalDate.now(), new ArrayList<GoodsItemVO>());
	
	@Override
	public ArrayList<PromotionBargainVO> show(){
		ArrayList<PromotionBargainVO> promotionBargainList = new ArrayList<PromotionBargainVO>();
		promotionBargainList.add(promotionBargain);
		return promotionBargainList;
	}
	
	@Override
	public void addBargain(GoodsItemVO vo){
		promotionBargain.bargains.add(vo);
		System.out.println("Add goods success!");
	}
	
	@Override
	public void setPrice(double price){
		promotionBargain.bargainTotal = price;
		System.out.println("Set bargain price success!");
		
	}
	
	@Override
	public void setStartDate(LocalDate date){
		promotionBargain.startDate = date;
		System.out.println("Set promotion start date success!");
	}
	
	@Override
	public void setEndDate(LocalDate date){
		promotionBargain.endDate = date;
		System.out.println("Set promotion end date success!");
	}
	
	@Override
	public ResultMessage submit(PromotionBargainVO vo){
		if(!vo.bargains.isEmpty()&&vo.bargainTotal!=0&&vo.bargainTotal<vo.goodsTotal&&vo.endDate.isAfter(vo.startDate)){
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
			else if(!vo.endDate.isAfter(vo.startDate)){
				System.out.println("Set time error!");
			}
			return ResultMessage.FAILED;
		}
	}
	
	@Override
	public PromotionBargainVO findPromotionByID(String promotionID){
		if(promotionID.equals(promotionBargain.promotionID)){
			return promotionBargain;
		}
		else{
			System.out.println("Can't find promotion!");
			return null;
		}
	}
	
}
