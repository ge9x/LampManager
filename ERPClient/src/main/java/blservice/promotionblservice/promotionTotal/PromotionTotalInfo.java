package blservice.promotionblservice.promotionTotal;

import java.util.ArrayList;

import vo.PromotionTotalVO;

/** 
 * Created by Aster on 2017/11/5
 */
public interface PromotionTotalInfo {
	
	/**
	 * 通过商品总价筛选合适的总价促销策略
	 * 
	 * @param total
	 * @return ArrayList
	 */
	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total);
	
	/**
	 * 通过促销策略名查找总价促销策略
	 * 
	 * @param promotionName
	 * @return PromotionTotalVO
	 */
	public PromotionTotalVO findPromotionByName(String promotionName);
}
