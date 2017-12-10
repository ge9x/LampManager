package vo;

import java.util.ArrayList;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class ClassificationVO {
	/**
	 * 商品分类ID
	 */
	public String ID;
	/**
	 * 商品分类名称
	 */
	public String name;
	/**
	 * 该商品分类的父分类
	 */
	public ClassificationVO father;
	/**
	 * 该商品分类的所有子分类
	 */
	public ArrayList<ClassificationVO> chidren;
	/**
	 * 该商品分类下的所有商品
	 */
	public ArrayList<GoodsVO> goods;
	
	public ClassificationVO(){ }
	
	public ClassificationVO(String ID, String name, ClassificationVO father, ArrayList<ClassificationVO> chidren,
			ArrayList<GoodsVO> goods) {
		super();
		this.ID = ID;
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}
}
