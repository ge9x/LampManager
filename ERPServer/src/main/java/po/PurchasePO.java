package po;

import java.util.List;

import javax.persistence.CascadeType;
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

import util.BillState;
import util.BillType;

@Entity
@Table(name = "purchase")
public class PurchasePO extends BillPO{
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
	
	public PurchasePO(){};
	
	public PurchasePO(BillType type,BillState state,String supplier
			,int customerID,String inventory,String user,List<GoodsItemPO> goodsItemList,String remarks
			,String date,int turn){
		super(date,type,state,turn);
		super.setType(type);
		super.setState(state);
		super.setDate(date);
		this.supplier=supplier;
		this.customerID=customerID;
		this.inventory=inventory;
		this.user=user;
		this.goodsItemList=goodsItemList;
		this.sum=calSum();
		this.remarks=remarks;
	}
	
	@Deprecated
	public PurchasePO(BillType type,BillState state,int ID,String supplier
			,int customerID,String inventory,String user,List<GoodsItemPO> goodsItemList,String remarks
			,String date){
		super(ID, date, type, state);
		super.setType(type);
		super.setState(state);
		super.setDate(date);
		super.setID(ID);
		this.supplier=supplier;
		this.customerID=customerID;
		this.inventory=inventory;
		this.user=user;
		this.goodsItemList=goodsItemList;
		this.sum=calSum();
		this.remarks=remarks;
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
		return super.getID();
	}

	public void setID(int iD) {
		super.setID(iD);
	}

	@Column(name = "date")
	public String getDate() {
		return super.getDate();
	}

	public void setDate(String date) {
		super.setDate(date);;
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return super.getType();
	}

	public void setType(BillType type) {
		super.setType(type);;
	}

	@Column(name="state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return super.getState();
	}

	public void setState(BillState state) {
		super.setState(state);
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

	@OneToMany(cascade= {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinColumn(name = "purchase")   
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
	
	@Column(name = "turn")
    public int getTurn() {
		return super.getTurn();
	}

	public void setTurn(int turn) {
		super.setTurn(turn);
	}
	
}
