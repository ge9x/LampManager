package po;

import javax.persistence.*;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
@Entity
@Embeddable
@Table(name = "goods")
public class GoodsPO {
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
	 * 商品所属分类
	 */
	private ClassificationPO classification;
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
	
	public GoodsPO(){ }

	public GoodsPO(String name, String model, ClassificationPO classification, int amount,
			int alarmAmount, double buyingPrice, double retailPrice, double recentBuyingPrice,
			double recentRetailPrice) {
		super();
		this.name = name;
		this.model = model;
		this.classification = classification;
		this.amount = amount;
		this.alarmAmount = alarmAmount;
		this.buyingPrice = buyingPrice;
		this.retailPrice = retailPrice;
		this.recentBuyingPrice = recentBuyingPrice;
		this.recentRetailPrice = recentRetailPrice;
	}

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
	public GoodsPO(int ID, String name, String model, ClassificationPO classification, int amount,
			int alarmAmount, double buyingPrice, double retailPrice, double recentBuyingPrice,
			double recentRetailPrice) {
		super();
		this.ID = ID;
		this.name = name;
		this.model = model;
		this.classification = classification;
		this.amount = amount;
		this.alarmAmount = alarmAmount;
		this.buyingPrice = buyingPrice;
		this.retailPrice = retailPrice;
		this.recentBuyingPrice = recentBuyingPrice;
		this.recentRetailPrice = recentRetailPrice;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return ID;
	}
	
	public void setId(int ID) {
		this.ID = ID;
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
	
	@ManyToOne
	@JoinColumn(name = "claid")
	public ClassificationPO getClassification() {
		return classification;
	}
	
	public void setClassification(ClassificationPO classification) {
		this.classification = classification;
	}
	
	@Column(name = "amount")
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "alarmamount")
	public int getAlarmAmount() {
		return alarmAmount;
	}
	
	public void setAlarmAmount(int alarmAmount) {
		this.alarmAmount = alarmAmount;
	}

	@Column(name = "buyingprice")
	public double getBuyingPrice() {
		return buyingPrice;
	}
	
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	@Column(name = "retailprice")
	public double getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Column(name = "recentbuyingprice")
	public double getRecentBuyingPrice() {
		return recentBuyingPrice;
	}
	
	public void setRecentBuyingPrice(double recentBuyingPrice) {
		this.recentBuyingPrice = recentBuyingPrice;
	}

	@Column(name = "recentretailprice")
	public double getRecentRetailPrice() {
		return recentRetailPrice;
	}
	
	public void setRecentRetailPrice(double recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}
	
}
