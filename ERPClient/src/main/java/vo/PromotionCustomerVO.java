package vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.PromotionType;

/**
 * Created by Aster on 2017/10/21.
 */
public class PromotionCustomerVO extends PromotionVO{
	
	public double voucher;
	
	public double allowance;
	
	public ArrayList<GoodsItemVO> gifts;
	
	public Level level;
	
	public PromotionCustomerVO(String promotionName, String promotionID, String startDate, String endDate, double voucher, double allowance, ArrayList<GoodsItemVO> gifts, Level level){
		this.promotionName = promotionName;
		this.promotionID = promotionID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.voucher = voucher;
		this.allowance = allowance;
		this.gifts = gifts;
		this.level = level;
		this.type = PromotionType.MEMBER_PROMOTION_STRATEGY;
	}
}
