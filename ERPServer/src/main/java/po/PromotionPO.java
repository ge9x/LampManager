package po;

import util.PromotionType;


public class PromotionPO {
	/**促销策略编号*/
    private String promotionID;
	/**起始时间*/
	private String startDate;
	/**结束时间*/
	private String endDate;
	/**促销策略类型*/
	private PromotionType type;

	public PromotionPO( String startDate, String endDate, PromotionType type) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public PromotionType getType() {
		return type;
	}

	public void setType(PromotionType type) {
		this.type = type;
	}

	
	
}
