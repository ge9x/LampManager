package po;

import java.io.Serializable;
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
@Table(name = "promotiontotal")
public class PromotionTotalPO extends PromotionPO implements Serializable {
	private static final long serialVersionUID = -3043573457465832L;
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
	/**代金券*/
    private double voucher;
	/**赠品*/
	private List<GoodsItemPO> gifts;
	/**总价*/
	private double totalPrice;
	/**总价促销策略编号*/
	private String promotionID;
	
	public PromotionTotalPO(){};
	
	public PromotionTotalPO(String promotionName,String startDate, String endDate, PromotionType type, double voucher, List<GoodsItemPO> gifts, double totalPrice,String promotionID) {
		super(startDate, endDate, type);
		this.promotionName=promotionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.voucher = voucher;
		this.gifts = gifts;
		this.totalPrice = totalPrice;
		this.promotionID=promotionID;
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

	@Column(name = "voucher")
	public double getVoucher() {
		return voucher;
	}

	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "prototal")  
	public List<GoodsItemPO> getGifts() {
		return gifts;
	}

	public void setGifts(List<GoodsItemPO> gifts) {
		this.gifts = gifts;
	}

	@Column(name = "totalprice")
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Column(name="promotionID")
	public String getPromotionID(){
		return promotionID;
	}
	
	public void setPromotionID(String promotionID){
		this.promotionID=promotionID;
	}
}
