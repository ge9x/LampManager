package po;

import java.util.ArrayList;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class GoodsPO extends PO{
	/**
	 * 商品ID
	 */
	private String ID;
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
	private ArrayList<Double> recentBuyingPrice;
	/**
	 * 商品最近零售价
	 */
	private ArrayList<Double> recentRetailPrice;
	
	public GoodsPO(String ID, String name, String model, String classification, String inventory, int amount,
			int alarmAmount, double buyingPrice, double retailPrice, ArrayList<Double> recentBuyingPrice,
			ArrayList<Double> recentRetailPrice) {
		super();
		this.ID = ID;
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
		return ID;
	}
	
	public void setId(String ID) {
		this.ID = ID;
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
	
	public ArrayList<Double> getRecentBuyingPrice() {
		return recentBuyingPrice;
	}
	
	public void setRecentBuyingPrice(ArrayList<Double> recentBuyingPrice) {
		this.recentBuyingPrice = recentBuyingPrice;
	}
	
	public ArrayList<Double> getRecentRetailPrice() {
		return recentRetailPrice;
	}
	
	public void setRecentRetailPrice(ArrayList<Double> recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}
	
}
