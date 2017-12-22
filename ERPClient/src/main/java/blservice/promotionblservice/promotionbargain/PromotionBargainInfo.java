package blservice.promotionblservice.promotionbargain;

import java.util.ArrayList;

import vo.PromotionBargainVO;

public interface PromotionBargainInfo {

	public ArrayList<PromotionBargainVO> getFitPromotionBargain();
	
	public PromotionBargainVO findPromotionByName(String promotionName);
}
