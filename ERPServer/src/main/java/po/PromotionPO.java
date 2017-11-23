package po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Level;
import util.PromotionType;

@Entity
@Table(name = "promotion")
public class PromotionPO {
	private String promotionID;
	
	private double goodsTotal;
	
	private double bargainTotal;
	
	private Date startDate;
	
	private Date endDate;
	
	private List<GoodsPO> bargains;
	
	private double voucher;
	
	private double allowance;
	
	private List<GoodsPO> gifts;
	
	private Level level;
	
	private double totalPrice;
	
	private PromotionType type;

	public PromotionPO(String promotionID, double goodsTotal, double bargainTotal, Date startDate, Date endDate,
			List<GoodsPO> bargains, double voucher, double allowance, List<GoodsPO> gifts, Level level,
			double totalPrice, PromotionType type) {
		this.promotionID = promotionID;
		this.goodsTotal = goodsTotal;
		this.bargainTotal = bargainTotal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bargains = bargains;
		this.voucher = voucher;
		this.allowance = allowance;
		this.gifts = gifts;
		this.level = level;
		this.totalPrice = totalPrice;
		this.type = type;
	}

	@Id
	@Column(name = "promotionID")
	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	@Column(name = "goodsTotal")
	public double getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(double goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	@Column(name = "bargainTotal")
	public double getBargainTotal() {
		return bargainTotal;
	}

	public void setBargainTotal(double bargainTotal) {
		this.bargainTotal = bargainTotal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
	public List<GoodsPO> getBargains() {
		return bargains;
	}

	public void setBargains(List<GoodsPO> bargains) {
		this.bargains = bargains;
	}

	@Column(name = "voucher")
	public double getVoucher() {
		return voucher;
	}

	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}

	@Column(name = "allowance")
	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
	public List<GoodsPO> getGifts() {
		return gifts;
	}

	public void setGifts(List<GoodsPO> gifts) {
		this.gifts = gifts;
	}

	@Column
	@Enumerated(EnumType.STRING)
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Column(name = "totalPrice")
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column
	@Enumerated(EnumType.STRING)
	public PromotionType getType() {
		return type;
	}

	public void setType(PromotionType type) {
		this.type = type;
	}
	
}
