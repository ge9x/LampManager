package bl.promotionbl;

import blservice.promotionblservice.promotionTotal.PromotionTotalBLService;
import blservice.promotionblservice.promotionTotal.PromotionTotalInfo;
import blservice.promotionblservice.promotionbargain.PromotionBargainBLService;
import blservice.promotionblservice.promotionbargain.PromotionBargainInfo;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerBLService;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class PromotionBLFactory {
	private static PromotionBargainController promotionBargainController;
	private static PromotionCustomerController promotionCustomerController;
	private static PromotionTotalController promotionTotalController;

	public synchronized static PromotionBargainBLService getBargainBLService() {
		if (promotionBargainController == null) {
			promotionBargainController = new PromotionBargainController();
		}
		return promotionBargainController;
	}

	public synchronized static PromotionBargainInfo getBargainInfo() {
		if (promotionBargainController == null) {
			promotionBargainController = new PromotionBargainController();
		}
		return promotionBargainController;
	}

	public synchronized static PromotionCustomerBLService getCustormerBLService() {
		if (promotionCustomerController == null) {
			promotionCustomerController = new PromotionCustomerController();
		}
		return promotionCustomerController;
	}

	public synchronized static PromotionCustomerInfo getCustomerInfo() {
		if (promotionCustomerController == null) {
			promotionCustomerController = new PromotionCustomerController();
		}
		return promotionCustomerController;
	}

	public synchronized static PromotionTotalBLService getTotalBLService() {
		if (promotionTotalController == null) {
			promotionTotalController = new PromotionTotalController();
		}
		return promotionTotalController;
	}

	public synchronized static PromotionTotalInfo getTotalInfo() {
		if (promotionTotalController == null) {
			promotionTotalController = new PromotionTotalController();
		}
		return promotionTotalController;
	}
}
