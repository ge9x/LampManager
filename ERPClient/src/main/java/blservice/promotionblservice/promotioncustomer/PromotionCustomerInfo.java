package blservice.promotionblservice.promotioncustomer;

import java.util.ArrayList;

import util.Level;
import vo.PromotionCustomerVO;

public interface PromotionCustomerInfo {

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level);
	
	public PromotionCustomerVO findPromotionByName(String promotionName);
}
