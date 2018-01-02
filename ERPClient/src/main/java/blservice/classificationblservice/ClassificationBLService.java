package blservice.classificationblservice;

import java.rmi.Remote;
import java.util.ArrayList;

import util.ResultMessage;
import vo.ClassificationVO;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface ClassificationBLService extends Remote{
	/**
	 * 得到所有商品分类
	 * @return 所有商品分类
	 */
	public ArrayList<ClassificationVO> show();
	/**
	 * 查找商品分类
	 * @param keyword 关键字
	 * @return 符合条件的商品分类
	 */
	public ArrayList<ClassificationVO> find(String keyword);
	/**
	 * 查看商品分类详情
	 * @param ID 商品分类ID
	 * @return 该商品分类
	 */
	public ClassificationVO showDetails(String ID);
	/**
	 * 添加商品分类
	 * @param vo 待添加的商品分类
	 * @return 是否添加成功
	 */
	public ResultMessage add(ClassificationVO vo);
	/**
	 * 删除商品分类
	 * @param ID 待删除的商品分类的ID
	 * @return 	SUCCESS：删除成功<br>
	 * 			FAILED：删除失败（网络错误）<br>
	 * 			EXIST：已有子分类或已包含商品<br>
	 * 			NOT_EXIST：要删除的商品分类不存在
	 */
	public ResultMessage delete(String ID);
	/**
	 * 修改商品分类
	 * @param vo 已修改的商品分类
	 * @return 是否修改成功
	 */
	public ResultMessage update(ClassificationVO vo);
	/**
	 * 新建商品分类时获取自动生成的ID
	 * @return 生成的新ID
	 */
	public String getNewID();
}
