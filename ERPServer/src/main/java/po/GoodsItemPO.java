package po;

/**
 * created by zlk on 2017/10/20
 */

public class GoodsItemPO {
	/**商品编号*/
	private String goodsID;
	/**商品名称*/
	private String goodsName;
	/**型号*/
	private String model;
	/**数量*/
	private int number;
	/**单价*/
	private double price;
	/**金额*/
	private double sum;
	/**备注*/
	private String remarks;
	
	public GoodsItemPO(String goodsID,String goodsName,String model,int number,double price,
			String remarks){
		this.goodsID=goodsID;
		this.goodsName=goodsName;
		this.model=model;
		this.number=number;
		this.price=price;
		this.sum=number*price;
		this.remarks=remarks;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
