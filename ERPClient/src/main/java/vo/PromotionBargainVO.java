package vo;

import java.util.ArrayList;
import java.util.Date;

import util.PromotionType;

/**
 * Created by Aster on 2017/10/21.
 */
public class PromotionBargainVO {
	public String promotionID;
	
	public double goodsTotal;
	
	public double bargainTotal;
	
	public Date startDate;
	
	public Date endDate;
	
	public ArrayList<GoodsVO> bargains;
	
	public PromotionType type;
	
	public PromotionBargainVO(String promotionID, double goodsTotal, double bargainTotal, Date startDate, Date endDate, ArrayList<GoodsVO> bargains){
		this.promotionID = promotionID;
		this.goodsTotal = goodsTotal;
		this.bargainTotal = bargainTotal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bargains = bargains;	
		this.type = PromotionType.BARGAIN_STRATEGY;
	}
}
