package vo;

public class GoodsItemVO {
	/**编号*/
	public String ID;
	/**名称*/
	public String goodsName;
	/**型号*/
	public String model;
	/**数量*/
	public int number;
	/**单价*/
	public double price;
	/**金额*/
	public double sum;
	/**备注*/
	public String remarks;
	
	
	public GoodsItemVO( String goodsName, int number, double price,
			String remarks) {
		this.goodsName = goodsName;
		this.number = number;
		this.price = price;
		this.sum = number*price;
		this.remarks = remarks;
	}
	
}
