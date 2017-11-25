package po;

import javax.persistence.*;

/**
 * Created on 2017/11/23
 * @author 巽
 *
 */
@Entity
@Table(name = "inventory")
public class InventoryPO {
	/**
	 * 仓库名的ID
	 */
	private int ID;
	/**
	 * 仓库名字
	 */
	private String name;
	
	public InventoryPO(){ }
	
	public InventoryPO(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return ID;
	}
	
	public void setId(int ID) {
		this.ID = ID;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
