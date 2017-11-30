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
     * 单据最后修改时间
     */
    private String date;

    /**
     * 单据编号
     */
    private int ID;

    /**
     * 单据状态
     */
    private BillState state;

    /**
     * 单据类型
     */
    private BillType type;
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
	
	public InventoryBillPO(String date, BillType type, BillState state, InventoryPO inventory, String user, HashMap<GoodsPO, Integer> goodsMap) {
		super(date, type, state);
		this.date = date;
		this.state = state;
		this.type = type;
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
		this.date = date;
		this.ID = ID;
		this.state = state;
		this.type = type;
		this.inventory = inventory;
		this.user = user;
		this.goodsMap = goodsMap;
	}

	@Column(name = "date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
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
	
}
