package blservice.promotionblservice.promotionTotal;

import java.util.ArrayList;

import vo.PromotionTotalVO;

public interface PromotionTotalInfo {
	
	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total);
	
	public PromotionTotalVO findPromotionByName(String promotionName);
}
