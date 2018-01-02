package blservice.promotionblservice.promotionbargain;

import java.util.ArrayList;

import vo.PromotionBargainVO;

/** 
 * Created by Aster on 2017/11/5
 */

public interface PromotionBargainInfo {

	/**
	 * 得到合适的特价包促销策略
	 * 
	 * @return ArrayList
	 */
	public ArrayList<PromotionBargainVO> getFitPromotionBargain();
	
	/**
	 * 通过促销策略名得到特价包促销策略
	 * 
	 * @param promotionName
	 * @return
	 */
	public PromotionBargainVO findPromotionByName(String promotionName);
}
