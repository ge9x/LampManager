package blservice.promotionblservice.promotioncustomer;

import java.util.ArrayList;

import util.Level;
import vo.PromotionCustomerVO;

/** 
 * Created by Aster on 2017/11/5
 */
public interface PromotionCustomerInfo {

	/**
	 * 通过客户等级筛选合适的会员促销策略
	 * 
	 * @param level
	 * @return ArrayList
	 */
	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level);
	
	/**
	 * 通过促销策略名查找会员促销策略
	 * 
	 * @param promotionName
	 * @return PromotionCustomerVO
	 */
	public PromotionCustomerVO findPromotionByName(String promotionName);
}
