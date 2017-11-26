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
	private String inventory;
	/**
	 * 操作员
	 */
	private String user;
	/**
	 * 该单据内的商品对应的溢出/缺损数量
	 */
	private Map<GoodsPO, Integer> goodsMap;
	
	public InventoryBillPO(int ID, String date, BillType type, BillState state, String inventory, String user, HashMap<GoodsPO, Integer> goodsMap) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	@MapKey(name = "changenumber")
	public Map<GoodsPO, Integer> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map<GoodsPO, Integer> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
}
