package vo;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class GoodsVO {
	/**
	 * 商品ID
	 */
	String id;
	/**
	 * 商品名称
	 */
	String name;
	/**
	 * 商品型号
	 */
	String model;
	/**
	 * 商品所属分类
	 */
	String classification;
	/**
	 * 商品所在仓库
	 */
	String inventory;
	/**
	 * 商品数量
	 */
	int amount;
	/**
	 * 商品警戒数量
	 */
	int alarmAmount;
	/**
	 * 商品进价
	 */
	double buyingPrice;
	/**
	 * 商品零售价
	 */
	double retailPrice;
	/**
	 * 商品最近进价
	 */
	double recentBuyingPrice;
	/**
	 * 商品最近零售价
	 */
	double recentRetailPrice;
	
	public GoodsVO(String id, String name, String model, String classification, String inventory, int amount,
			int alarmAmount, double buyingPrice, double retailPrice, double recentBuyingPrice,
			double recentRetailPrice) {
		super();
		this.id = id;
		this.name = name;
		this.model = model;
		this.classification = classification;
		this.inventory = inventory;
		this.amount = amount;
		this.alarmAmount = alarmAmount;
		this.buyingPrice = buyingPrice;
		this.retailPrice = retailPrice;
		this.recentBuyingPrice = recentBuyingPrice;
		this.recentRetailPrice = recentRetailPrice;
	}
	
}
