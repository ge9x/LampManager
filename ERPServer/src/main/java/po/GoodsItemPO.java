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
	private double total;
	/**备注*/
	private String remarks;
	
	public GoodsItemPO(String goodsID,String goodsName,String model,int number,double price,
			double total,String remarks){
		this.goodsID=goodsID;
		this.goodsName=goodsName;
		this.model=model;
		this.number=number;
		this.price=price;
		this.total=total;
		this.remarks=remarks;
	}
	
	public String getGoodsID(){
		return goodsID;
	}
	
	public String getGoodsName(){
		return goodsName;
	}
	
	public String getModel(){
		return model;
	}
	
	public int getNumber(){
		return number;
	}
	
	public double getPrice(){
		return price;
	}
	
	public double getTotal(){
		return total;
	}
	
	public String getRemarks(){
		return remarks;
	}
}
