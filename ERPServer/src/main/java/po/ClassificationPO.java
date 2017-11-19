package po;

import java.util.ArrayList;

import javax.persistence.*;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
@Entity
@Table(name = "classification")
public class ClassificationPO {
	/**
	 * 商品分类ID
	 */
	private String ID;
	/**
	 * 商品分类名称
	 */
	private String name;
	/**
	 * 该商品分类的父分类
	 */
	private ClassificationPO father;
	/**
	 * 该商品分类的所有子分类
	 */
	private ArrayList<ClassificationPO> chidren;
	/**
	 * 该商品分类下的所有商品
	 */
	private ArrayList<GoodsPO> goods;
	
	public ClassificationPO(String ID, String name, ClassificationPO father, ArrayList<ClassificationPO> chidren, ArrayList<GoodsPO> goods) {
		super();
		this.ID = ID;
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public String getId() {
		return ID;
	}

	public void setId(String ID) {
		this.ID = ID;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "claid")
	public ClassificationPO getFather() {
		return father;
	}

	public void setFather(ClassificationPO father) {
		this.father = father;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "claid")
	public ArrayList<ClassificationPO> getChidren() {
		return chidren;
	}

	public void setChidren(ArrayList<ClassificationPO> chidren) {
		this.chidren = chidren;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "claid")
	public ArrayList<GoodsPO> getGoods() {
		return goods;
	}

	public void setGoods(ArrayList<GoodsPO> goods) {
		this.goods = goods;
	}
	
}
