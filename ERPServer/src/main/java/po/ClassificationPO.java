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
	 * 该商品分类的父分类的名称
	 */
	private String father;
	/**
	 * 该商品分类的所有子分类的名称
	 */
	private ArrayList<String> chidren;
	/**
	 * 该商品分类下的所有商品的ID
	 */
	private ArrayList<String> goods;
	
	public ClassificationPO(String id, String name, String father, ArrayList<String> chidren, ArrayList<String> goods) {
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

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public ArrayList<String> getChidren() {
		return chidren;
	}

	public void setChidren(ArrayList<String> chidren) {
		this.chidren = chidren;
	}

	public ArrayList<String> getGoods() {
		return goods;
	}

	public void setGoods(ArrayList<String> goods) {
		this.goods = goods;
	}
	
}
