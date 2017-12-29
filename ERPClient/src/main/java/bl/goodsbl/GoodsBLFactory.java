package bl.goodsbl;

import blservice.goodsblservice.GoodsBLService;
import blservice.goodsblservice.GoodsInfo;

/**
 * Created on 2017/12/29
 * @author 巽
 *
 */
public class GoodsBLFactory {
	private static GoodsController goodsController;

	public synchronized static GoodsBLService getBLService() {
		if (goodsController == null) {
			goodsController = new GoodsController();
		}
		return goodsController;
	}

	public synchronized static GoodsInfo getInfo() {
		if (goodsController == null) {
			goodsController = new GoodsController();
		}
		return goodsController;
	}
}
