package blservice;

import java.util.ArrayList;
import java.util.Date;

import util.ResultMessage;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/10/20
 * @author 巽
 * 
 */
public interface InventoryBLService {
	/**
	 * 得到此仓库此时间段内的出／入库数量／金额，销售／进货数量／金额，以及库存合计
	 * @param startDate 开始时间点
	 * @param endDate 结束时间点
	 * @param inventory 仓库名
	 * @return 此仓库此时间段内的出／入库数量／金额，销售／进货数量／金额，以及库存合计
	 */
	public InventoryViewVO show(Date startDate, Date endDate, String inventory);
	/**
	 * 得到此日期的库存盘点
	 * @param today 日期
	 * @return  当天的库存盘点
	 */
	public InventoryCheckVO check(Date today);
	/**
	 * 导出库存盘点到本地
	 * @param vo 当天的库存盘点
	 * @return 是否成功导出
	 */
	public ResultMessage exportExcel(InventoryCheckVO vo);
	/**
	 * 得到所有库存单据
	 * @return 所有库存单据
	 */
	public ArrayList<InventoryBillVO> showBills();
	/**
	 * 搜索符合条件的库存单据
	 * @param startDate 开始时间点
	 * @param endDate 结束时间点
	 * @param inventory 仓库名
	 * @param id 单据ID
	 * @param keyword 关键字
	 * @return 符合条件的库存单据
	 */
	public ArrayList<InventoryBillVO> searchBill(Date startDate, Date endDate, String inventory, String id, String keyword);
	/**
	 * 添加库存报溢单
	 * @param vo
	 * @return 是否成功添加
	 */
	public ResultMessage addOverflowBill(InventoryBillVO vo);
	/**
	 * 添加库存报损单
	 * @param vo
	 * @return 是否成功添加
	 */
	public ResultMessage addLossBill(InventoryBillVO vo);
	/**
	 * 删除库存单据
	 * @param id 单据ID
	 * @return 是否成功删除
	 */
	public ResultMessage deleteBill(String id);
	/**
	 * 查看库存单据详情
	 * @param id 单据ID
	 * @return 库存单据详情
	 */
	public InventoryBillVO showBillDetails(String id);
	/**
	 * 将单据草稿提交给总经理审批，改变单据状态为未审核状态
	 * @param id 单据ID
	 * @return 是否成功提交
	 */
	public ResultMessage submitBill(String id);
}
