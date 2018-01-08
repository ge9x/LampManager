package vo;

public class GoodsItemVO {
    private static final String seperator = System.lineSeparator();

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
	
	
	public GoodsItemVO(String ID,String goodsName,String model,int number, double price,
			String remarks) {
		this.ID=ID;
		this.goodsName = goodsName;
		this.model=model;
		this.number = number;
		this.price = price;
		this.sum = number*price;
		this.remarks = remarks;
	}

	@Override
    public String toString(){
	    return goodsName + "\t" + model + "\t" + number + "\t" + price + "\t" + sum + "\t" + remarks;
    }
	
}
