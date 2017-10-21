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
	String id;
	/**
	 * 商品分类名称
	 */
	String name;
	/**
	 * 该商品分类的父分类
	 */
	ClassificationVO father;
	/**
	 * 该商品分类的所有子分类
	 */
	ArrayList<ClassificationVO> chidren;
	/**
	 * 该商品分类下的所有商品
	 */
	ArrayList<GoodsVO> goods;
	
	public ClassificationVO(String id, String name, ClassificationVO father, ArrayList<ClassificationVO> chidren,
			ArrayList<GoodsVO> goods) {
		super();
		this.id = id;
		this.name = name;
		this.father = father;
		this.chidren = chidren;
		this.goods = goods;
	}
}
