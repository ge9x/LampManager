package blservice.goodsblservice;

import java.util.ArrayList;
import java.util.HashMap;

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
	 * 根据商品ID得到商品的进价和销售价
	 * @param ID 商品ID
	 * @return 包含商品进价和销售价的哈希表：HashMap<进价，销售价>
	 */
	public HashMap<Double, Double> getBuyingPriceAndRetailPriceByID(String ID);
}
