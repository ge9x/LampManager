package vo;

public class GoodsItemVO {
     /**商品编号*/
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
	
	
	public GoodsItemVO(String iD, String goodsName, String model, int number, double price, double sum,
			String remarks) {
		super();
		ID = iD;
		this.goodsName = goodsName;
		this.model = model;
		this.number = number;
		this.price = price;
		this.sum = sum;
		this.remarks = remarks;
	}
	
	
}
