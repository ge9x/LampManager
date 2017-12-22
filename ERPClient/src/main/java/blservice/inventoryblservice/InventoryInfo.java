package blservice.inventoryblservice;

import vo.GoodsItemVO;
import vo.InventoryBillVO;

import java.util.ArrayList;

import po.InventoryPO;
import util.ResultMessage;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public interface InventoryInfo {

	/**
	 * 根据日期获得已经审批通过的库存报溢单、库存报损单、库存赠送单
	 * 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return 该段日期内已经审批通过的三种库存类单据
	 */
	public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate);

	/**
	 * 获得所有仓库的名字
	 * 
	 * @return 所有仓库的名字
	 */
	public ArrayList<String> getAllInventoryName();

	/**
	 * 通过仓库名字得到仓库的持久化对象
	 * 
	 * @param name 仓库的名字
	 * @return 仓库的PO
	 */
	public InventoryPO getInventoryByName(String name);

	/**
	 * 增加库存
	 * 
	 * @param goodsItems 要增加的商品条目
	 * @param inventory 仓库名字
	 * @return 是否成功增加
	 */
	public ResultMessage raiseInventory(ArrayList<GoodsItemVO> goodsItems, String inventory);

	/**
	 * 减少库存
	 * 
	 * @param goodsItems 要减少的商品条目
	 * @param inventory 仓库名字
	 * @return 是否成功减少
	 */
	public ResultMessage reduceInventory(ArrayList<GoodsItemVO> goodsItems, String inventory);

	/**
	 * 审批通过的单据要对库存产生影响
	 * 
	 * @param vo 审批通过的库存报溢单或库存报损单
	 * @return 是否成功对库存产生影响
	 */
	public ResultMessage examine(InventoryBillVO vo);

	/**
	 * 得到所有待审批的库存报溢单和库存报损单
	 * 
	 * @return 所有库存报溢单和库存报损单的VO的集合
	 */
	public ArrayList<InventoryBillVO> getAllSubmittedInventoryBill();
}
