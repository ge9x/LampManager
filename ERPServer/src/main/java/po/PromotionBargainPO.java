package po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import util.PromotionType;


@Entity
@Table(name = "promotionbargain")
public class PromotionBargainPO extends PromotionPO{
	/** 编号 */
	private int ID;
	/**促销策略名字*/
	private String promotionName;
	/** 起始时间 */
	private String startDate;
	/** 结束时间 */
	private String endDate;
	/** 促销策略类型 */
	private PromotionType type;
	/**原有组合所有商品总价*/
    private double goodsTotal;
	/**特价包总价*/
	private double bargainTotal;
	/**组合商品*/
	private List<GoodsItemPO> bargains;
	/**特价包促销策略编号*/
	private String promotionID;
	
	public PromotionBargainPO(){};
	
	public PromotionBargainPO(String promotionName,String startDate, String endDate, PromotionType type, double goodsTotal, double bargainTotal, List<GoodsItemPO> bargains) {
		super(startDate, endDate, type);
		this.promotionName=promotionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.goodsTotal = goodsTotal;
		this.bargainTotal = bargainTotal;
		this.bargains = bargains;
		this.promotionID="PB-"+Integer.toString(getID());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	@Column(name="promotionName")
	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	@Column(name = "startdate")
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "enddate")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	public PromotionType getType() {
		return type;
	}
	public void setType(PromotionType type) {
		this.type = type;
	}
	
	@Column(name="goodstotal")
	public double getGoodsTotal() {
		return goodsTotal;
	}
	public void setGoodsTotal(double goodsTotal) {
		this.goodsTotal = goodsTotal;
	}
	
	@Column(name="bargaintotal")
	public double getBargainTotal() {
		return bargainTotal;
	}
	public void setBargainTotal(double bargainTotal) {
		this.bargainTotal = bargainTotal;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "probar")  
	public List<GoodsItemPO> getBargains() {
		return bargains;
	}
	public void setBargains(List<GoodsItemPO> bargains) {
		this.bargains = bargains;
	}
	
	@Column(name="promotionID")
	public String getPromotionID(){
		return calPromotionID();
	}
	
	public void setPromotionID(String promotionID){
		this.promotionID=calPromotionID();
	}
	
	private String calPromotionID(){
		return "PB-"+Integer.toString(ID);
	}
}
