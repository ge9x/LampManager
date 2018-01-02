package blservice.goodsblservice;

import java.rmi.Remote;
import java.util.ArrayList;

import util.ResultMessage;
import vo.GoodsVO;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public interface GoodsBLService extends Remote {
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
	 * @param ID 商品ID
	 * @return 商品
	 */
	public GoodsVO showDetails(String ID);
	/**
	 * 添加商品
	 * @param vo 待添加的商品
	 * @return 是否添加成功
	 */
	public ResultMessage add(GoodsVO vo);
	/**
	 * 删除商品
	 * @param ID 待删除的商品的ID
	 * @return 	SUCCESS：删除成功<br>
	 * 			FAILED：删除失败（网络连接错误）<br>
	 * 			EXIST：商品数量不为零或与一年内的已通过单据有关联<br>
	 * 			NOT_EXIST：要删除的商品不存在
	 */
	public ResultMessage delete(String ID);
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
	public String getNewID(String calssificationID);
}
