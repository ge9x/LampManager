package po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

/**
 * Created on 2017/11/23
 * @author 巽
 *
 */
@Entity
@Embeddable
@Table(name = "inventory")
public class InventoryPO implements Serializable {
	private static final long serialVersionUID = 7157821248286002274L;
	/**
	 * 仓库名的ID
	 */
	private int ID;
	/**
	 * 仓库名字
	 */
	private String name;
	/**
	 * 仓库里每件商品的数量
	 */
	private Map<GoodsPO, Integer> number = new HashMap<GoodsPO, Integer>();
	

	public InventoryPO(){ }
	
	public InventoryPO(String name) {
		this.name = name;
	}

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
	public InventoryPO(int ID, String name){
		this.ID = ID;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="inventory_goods_number")
	@MapKeyJoinColumn(name = "GoodsPO_id")
	@Column(name = "number")
	public Map<GoodsPO, Integer> getNumber() {
		return number;
	}

	public void setNumber(Map<GoodsPO, Integer> number) {
		this.number = number;
	}
}
