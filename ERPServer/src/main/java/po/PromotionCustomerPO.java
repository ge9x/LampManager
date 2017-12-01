package po;

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

import util.Level;
import util.PromotionType;
import po.GoodsPO;

@Entity
@Table(name = "promotioncustomer")
public class PromotionCustomerPO extends PromotionPO {
	/**编号 */
	private int ID;
	/** 起始时间 */
	private String startDate;
	/** 结束时间 */
	private String endDate;
	/** 促销策略类型 */
	private PromotionType type;
	/** 折让 */
	private double allowance;
	/** 赠品 */
	private List<GoodsPO> gifts;
	/** 客户级别 */
	private Level level;
	/**客户促销策略编号*/
	private String promotionID;

	public PromotionCustomerPO(String startDate, String endDate, PromotionType type, double allowance,
			List<GoodsPO> gifts, Level level) {
		super(startDate, endDate, type);
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.allowance = allowance;
		this.gifts = gifts;
		this.level = level;
		this.promotionID="PC"+Integer.toString(ID);
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

	@Column(name="allowance")
	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "procus")  
	public List<GoodsPO> getGifts() {
		return gifts;
	}

	public void setGifts(List<GoodsPO> gifts) {
		this.gifts = gifts;
	}

	@Column(name="level")
	@Enumerated(EnumType.STRING)
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	@Column(name="promotionID")
	public String getPromotionID(){
		return promotionID;
	}
	
	public void setPromotionID(String promotionID){
		this.promotionID=promotionID;
	}

}
