package blservice.goodsblservice;

import java.util.ArrayList;

import po.GoodsPO;
import util.ResultMessage;
import vo.GoodsIdentityVO;
import vo.GoodsVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public interface GoodsInfo {
	/**
	 * 得到所有商品的信息
	 * @return 包含所有商品的VO的链表
	 */
	public ArrayList<GoodsVO> getAllGoods();
	/**
	 * 得到所有商品的ID、名称和型号
	 * @return 包含所有商品的ID、名称和型号的VO的链表
	 */
	public ArrayList<GoodsIdentityVO> getAllGoodsIdentity();
	/**
	 * 根据商品ID得到商品的进价
	 * @param ID 商品ID
	 * @return 商品进价
	 */
	public Double getBuyingPriceByID(String ID);
	/**
	 * 根据商品ID得到商品的销售价
	 * @param ID 商品ID
	 * @return 商品销售价
	 */
	public Double getRetailPriceByID(String ID);
	/**
	 * 根据商品ID得到商品
	 * @param ID 商品的ID
	 * @return 商品
	 */
	public GoodsPO getGoodsByID(String ID);
	/**
	 * 根据商品ID更新商品最近进价
	 * @param goodsID 商品ID
	 * @param recentBuyingPrice 新的最近进价
	 * @return 	SUCCESS：更新成功<br>
	 * 			FAILED：更新失败（网络错误）<br>
	 * 			NOT_EXIST：商品不存在
	 */
	public ResultMessage updateRecentBuyingPrice(String goodsID, double recentBuyingPrice);
	/**
	 * 根据商品ID更新商品最近零售价
	 * @param goodsID 商品ID
	 * @param recentRetailPrice 新的最近零售价
	 * @return 	SUCCESS：更新成功<br>
	 * 			FAILED：更新失败（网络错误）<br>
	 * 			NOT_EXIST：商品不存在
	 */
	public ResultMessage updateRecentRetailPrice(String goodsID, double recentRetailPrice);
}
