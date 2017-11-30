package po;

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
import java.util.ArrayList;

import util.BillState;
import util.BillType;

@Entity
@Table(name = "purchase")
public class PurchasePO extends BillPO{
	/**单据编号*/
	private int ID;
	/**单据最后修改时间*/
	private String date;
	/**单据类型*/
	private BillType type;
	/**单据状态*/
	private BillState state;
	/**供应商*/
	private String supplier;
	/**供应商ID*/
	private int customerID;
	/**仓库*/
	private String inventory;
	/**操作员*/
	private String user;
	/**商品列表*/
	private List<GoodsItemPO> goodsItemList;
	/**备注*/
	private String remarks;
	/**总额合计*/
	private double sum;
	
	@Deprecated
	public PurchasePO(BillType type,BillState state,int ID,String supplier
			,int customerID,String inventory,String user,List<GoodsItemPO> goodsItemList,String remarks
			,String endDate){
		super(ID, endDate, type, state);
		this.type=type;
		this.state=state;
		this.ID=ID;
		this.supplier=supplier;
		this.customerID=customerID;
		this.inventory=inventory;
		this.user=user;
		this.goodsItemList=goodsItemList;
		this.sum=calSum();
		this.remarks=remarks;
		this.date=endDate;
	}
	
	private double calSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).getSum();
		}
		return sum;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Column(name = "date")
	public String getEndDate() {
		return date;
	}

	public void setEndDate(String endDate) {
		this.date = endDate;
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
	}

	@Column(name="state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	@Column(name = "supplier")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Column(name = "customerID")
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Column(name = "inventory")
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	@Column(name = "user")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
	public List<GoodsItemPO> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<GoodsItemPO> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "sum")
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	
	
	
}
