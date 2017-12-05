package blservice.classificationblservice;

import java.util.HashMap;

import po.ClassificationPO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public interface ClassificationInfo {
	/**
	 * 得到所有商品分类的ID和名称
	 * @return 包含所有商品分类的ID和名称的哈希表<ID，名称>
	 */
	public HashMap<String, String> getAllClassification();
	/**
	 * 通过商品分类的名称得到商品分类
	 * @param name 商品分类的名称
	 * @return 叫这个名字的商品分类
	 */
	public ClassificationPO getClassificationByName(String name);
}
