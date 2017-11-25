package po;

import java.util.List;

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
	private List<ClassificationPO> chidren;
	/**
	 * 该商品分类下的所有商品
	 */
	private List<GoodsPO> goods;
	
	public ClassificationPO(){ }
	
	public ClassificationPO(String ID, String name, ClassificationPO father, List<ClassificationPO> chidren, List<GoodsPO> goods) {
		super();
		this.ID = ID;
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}

	@Id
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
	public List<ClassificationPO> getChidren() {
		return chidren;
	}

	public void setChidren(List<ClassificationPO> chidren) {
		this.chidren = chidren;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "claid")
	public List<GoodsPO> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsPO> goods) {
		this.goods = goods;
	}
	
}
