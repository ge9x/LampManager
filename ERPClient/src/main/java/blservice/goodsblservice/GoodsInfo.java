package blservice.goodsblservice;

import java.util.ArrayList;

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
}
