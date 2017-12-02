package vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.PromotionType;

/**
 * Created by Aster on 2017/10/21.
 */
public class PromotionBargainVO extends PromotionVO{
	
	public double goodsTotal;
	
	public double bargainTotal;
	
	public ArrayList<GoodsItemVO> bargains;
	
	public PromotionBargainVO(String promotionName, String promotionID, double goodsTotal, double bargainTotal, LocalDate startDate, LocalDate endDate, ArrayList<GoodsItemVO> bargains){
		this.promotionName = promotionName;
		this.promotionID = promotionID;
		this.goodsTotal = goodsTotal;
		this.bargainTotal = bargainTotal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bargains = bargains;	
		this.type = PromotionType.BARGAIN_STRATEGY;
	}
}
