package po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Created by zlk on 2017/12/18
 */
@Entity
@Table(name = "initclassification")
public class InitClassificationPO implements Serializable{
	private static final long servialVersionUID=574935438782732L;
    
	/**
	 * 商品分类ID
	 */
	private int ID;
	/**
	 * 商品分类名称
	 */
	private String name;
	
	public InitClassificationPO(){};


	public InitClassificationPO(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}
	
	@Id
	@Column(name = "id")
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
