package po;

import java.util.ArrayList;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class ClassificationPO extends PO {
	/**
	 * 商品分类ID
	 */
	private String id;
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
	
	public ClassificationPO(String id, String name, ClassificationPO father, ArrayList<ClassificationPO> chidren, ArrayList<GoodsPO> goods) {
		super();
		this.id = id;
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassificationPO getFather() {
		return father;
	}

	public void setFather(ClassificationPO father) {
		this.father = father;
	}

	public ArrayList<ClassificationPO> getChidren() {
		return chidren;
	}

	public void setChidren(ArrayList<ClassificationPO> chidren) {
		this.chidren = chidren;
	}

	public ArrayList<GoodsPO> getGoods() {
		return goods;
	}

	public void setGoods(ArrayList<GoodsPO> goods) {
		this.goods = goods;
	}
	
}
