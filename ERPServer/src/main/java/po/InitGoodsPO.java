package po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zlk on 2017/12/18
 */
@Entity
@Table(name = "initgoods")
public class InitGoodsPO implements Serializable {
	private static final long servialVersionUID=4584937438482L;
	
	/**
	 * 商品ID
	 */
	private int ID;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品型号
	 */
	private String model;
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
	/**
	 * 仓库里存的该商品的总数量
	 */
	private int goodsNumber;
	
	public InitGoodsPO(int iD, String name, String model, double buyingPrice,
			double retailPrice, double recentBuyingPrice, double recentRetailPrice, int goodsNumber) {
		super();
		ID = iD;
		this.name = name;
		this.model = model;
		this.buyingPrice = buyingPrice;
		this.retailPrice = retailPrice;
		this.recentBuyingPrice = recentBuyingPrice;
		this.recentRetailPrice = recentRetailPrice;
		this.goodsNumber = goodsNumber;
	}
	@Id
	@Column(name = "id")
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "model")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Column(name = "buyingPrice")
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	@Column(name = "retailPrice")
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	@Column(name = "recentBuyingPrice")
	public double getRecentBuyingPrice() {
		return recentBuyingPrice;
	}
	public void setRecentBuyingPrice(double recentBuyingPrice) {
		this.recentBuyingPrice = recentBuyingPrice;
	}
	@Column(name = "recentRetailPrice")
	public double getRecentRetailPrice() {
		return recentRetailPrice;
	}
	public void setRecentRetailPrice(double recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}
	@Column(name = "goodsNumber")
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	
}
