package po;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class GoodsPO extends PO{
	/**
	 * 商品ID
	 */
	private String id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品型号
	 */
	private String model;
	/**
	 * 商品所属分类
	 */
	private String classification;
	/**
	 * 商品所在仓库
	 */
	private String inventory;
	/**
	 * 商品数量
	 */
	private int amount;
	/**
	 * 商品警戒数量
	 */
	private int alarmAmount;
	/**
	 * 商品进价
	 */
	private double buyingPrice;
	/**
	 * 商品零售价
	 */
	private double retailPrice;
	/**
	 * 商品最近进价
	 */
	private double recentBuyingPrice;
	/**
	 * 商品最近零售价
	 */
	private double recentRetailPrice;
	
	public GoodsPO(String id, String name, String model, String classification, String inventory, int amount,
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public String getInventory() {
		return inventory;
	}
	
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAlarmAmount() {
		return alarmAmount;
	}
	
	public void setAlarmAmount(int alarmAmount) {
		this.alarmAmount = alarmAmount;
	}
	
	public double getBuyingPrice() {
		return buyingPrice;
	}
	
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
	public double getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public double getRecentBuyingPrice() {
		return recentBuyingPrice;
	}
	
	public void setRecentBuyingPrice(double recentBuyingPrice) {
		this.recentBuyingPrice = recentBuyingPrice;
	}
	
	public double getRecentRetailPrice() {
		return recentRetailPrice;
	}
	
	public void setRecentRetailPrice(double recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}
	
}
