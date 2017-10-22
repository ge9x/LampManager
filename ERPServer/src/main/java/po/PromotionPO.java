package po;

import java.util.ArrayList;
import java.util.Date;

import util.Level;
import util.PromotionType;

public class PromotionPO {
	private String promotionID;
	
	private double goodsTotal;
	
	private double bargainTotal;
	
	private Date startDate;
	
	private Date endDate;
	
	private ArrayList<GoodsPO> bargains;
	
	private double voucher;
	
	private double allowance;
	
	private ArrayList<GoodsPO> gifts;
	
	private Level level;
	
	private double totalPrice;
	
	private PromotionType type;

	public PromotionPO(String promotionID, double goodsTotal, double bargainTotal, Date startDate, Date endDate,
			ArrayList<GoodsPO> bargains, double voucher, double allowance, ArrayList<GoodsPO> gifts, Level level,
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

	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public double getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(double goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public double getBargainTotal() {
		return bargainTotal;
	}

	public void setBargainTotal(double bargainTotal) {
		this.bargainTotal = bargainTotal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<GoodsPO> getBargains() {
		return bargains;
	}

	public void setBargains(ArrayList<GoodsPO> bargains) {
		this.bargains = bargains;
	}

	public double getVoucher() {
		return voucher;
	}

	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public ArrayList<GoodsPO> getGifts() {
		return gifts;
	}

	public void setGifts(ArrayList<GoodsPO> gifts) {
		this.gifts = gifts;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PromotionType getType() {
		return type;
	}

	public void setType(PromotionType type) {
		this.type = type;
	}
	
}
