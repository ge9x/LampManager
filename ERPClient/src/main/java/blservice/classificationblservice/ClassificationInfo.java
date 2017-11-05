package blservice.classificationblservice;

import java.util.HashMap;

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
}
