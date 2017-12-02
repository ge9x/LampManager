package po;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import util.BillState;
import util.BillType;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
@Entity
@Table(name = "inventorybill")
public class InventoryBillPO extends BillPO{
	/**
	 * 该单据涉及的仓库
	 */
	private InventoryPO inventory;
	/**
	 * 操作员
	 */
	private String user;
	/**
	 * 该单据内的商品对应的溢出/缺损数量
	 */
	private Map<GoodsPO, Integer> goodsMap;
	
	public InventoryBillPO(){ }

	public InventoryBillPO(String date, BillType type, BillState state, InventoryPO inventory, String user, HashMap<GoodsPO, Integer> goodsMap, int turn) {
		super(date, type, state, turn);
		this.inventory = inventory;
		this.user = user;
		this.goodsMap = goodsMap;
	}

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
	public InventoryBillPO(int ID, String date, BillType type, BillState state, InventoryPO inventory, String user, HashMap<GoodsPO, Integer> goodsMap) {
		super(ID, date, type, state);
		this.inventory = inventory;
		this.user = user;
		this.goodsMap = goodsMap;
	}

	@Column(name = "date")
	public String getDate() {
		return super.getDate();
	}

	public void setDate(String date) {
		super.setDate(date);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return super.getID();
	}

	public void setID(int iD) {
		super.setID(iD);;
	}

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return super.getState();
	}

	public void setState(BillState state) {
		super.setState(state);
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return super.getType();
	}

	public void setType(BillType type) {
		super.setType(type);
	}

	@ManyToOne
	@JoinColumn(name = "inventoryid")
	public InventoryPO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryPO inventory) {
		this.inventory = inventory;
	}

	@Column(name = "user")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "inventorybill_change_info")
	public Map<GoodsPO, Integer> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map<GoodsPO, Integer> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
	@Column(name = "turn")
    public int getTurn() {
		return super.getTurn();
	}

	public void setTurn(int turn) {
		super.setTurn(turn);
	}
	
}
