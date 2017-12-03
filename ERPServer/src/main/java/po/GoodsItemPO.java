package po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by zlk on 2017/10/20
 */

@Entity
@Table(name = "goodsitem")
public class GoodsItemPO implements Serializable {
	private static final long serialVersionUID = 2723361962211684993L;
	/**商品列表编号*/
	private int ID;
	/**商品编号*/
	private int goodsID;
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
	
	public GoodsItemPO(){};
	
	public GoodsItemPO(int goodsID,String goodsName,String model,int number,double price,
			String remarks){
		this.goodsID=goodsID;
		this.goodsName=goodsName;
		this.model=model;
		this.number=number;
		this.price=price;
		this.sum=number*price;
		this.remarks=remarks;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID(){
		return ID;
	}

	public void setID(int ID){
		this.ID=ID;
	}
	
	@Column(name = "goodsID")
	public int getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	@Column(name = "goodsName")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "model")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "number")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "sum")
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
