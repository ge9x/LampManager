package blservice.goodsblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.GoodsVO;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface GoodsBLService {
	/**
	 * 展示所有商品
	 * @return 所有商品
	 */
	public ArrayList<GoodsVO> show();
	/**
	 * 查找商品
	 * @param keyword 关键字
	 * @return 所有符合条件的商品
	 */
	public ArrayList<GoodsVO> find(String keyword);
	/**
	 * 查看商品详细信息
	 * @param id 商品ID
	 * @return 商品
	 */
	public GoodsVO showDetails(String id);
	/**
	 * 添加商品
	 * @param vo 待添加的商品
	 * @return 是否添加成功
	 */
	public ResultMessage add(GoodsVO vo);
	/**
	 * 删除商品
	 * @param id 待删除的商品的ID
	 * @return 是否删除成功
	 */
	public ResultMessage delete(String id);
	/**
	 * 修改商品
	 * @param vo 已修改的商品
	 * @return 是否修改成功
	 */
	public ResultMessage update(GoodsVO vo);
	/**
	 * 得到自动生成的新商品的ID
	 * @return 自动生成的ID
	 */
	public String getNewID();
}
