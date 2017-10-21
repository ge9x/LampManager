package vo;

import java.util.ArrayList;
import java.util.Date;

import util.PromotionType;

/**
 * Created by Aster on 2017/10/21.
 */
public class PromotionTotalVO {
	public String promotionID;
	
	public Date startDate;
	
	public Date endDate;
	
	public double voucher;
	
	public ArrayList<GoodsVO> gifts;
	
	public double totalPrice;
	
	public PromotionType type;
	
	public PromotionTotalVO(String promotionID, Date startDate, Date endDate, double voucher, ArrayList<GoodsVO> gifts, double totalPrice){
		this.promotionID = promotionID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.voucher = voucher;
		this.gifts = gifts;
		this.totalPrice = totalPrice;
		this.type = PromotionType.TOTAL_PROMOTION_STRATEGY;
	}
}
