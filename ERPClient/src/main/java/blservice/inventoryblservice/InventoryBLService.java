package blservice.inventoryblservice;

import java.rmi.Remote;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.InventoryBillVO;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/10/20
 * @author 巽
 * 
 */
public interface InventoryBLService extends Remote {
	/**
	 * 得到所有的仓库名
	 * @return 包含所有仓库名的链表
	 */
	public ArrayList<String> showInventory();
	/**
	 * 得到此仓库此时间段内的出／入库数量／金额，销售／进货数量／金额，以及库存合计
	 * @param startDate 开始时间点
	 * @param endDate 结束时间点
	 * @param inventory 仓库名
	 * @return 此仓库此时间段内的出／入库数量／金额，销售／进货数量／金额，以及库存合计
	 */
	public InventoryViewVO show(String startDate, String endDate, String inventory);
	/**
	 * 得到此日期的库存盘点
	 * @return  当天的库存盘点
	 */
	public InventoryCheckVO check();
	/**
	 * 导出库存盘点到本地
	 * @param filePath 文件导出路径
	 * @param fileName 文件名
	 * @param vo 当天的库存盘点
	 * @return 是否成功导出
	 */
	public ResultMessage exportExcel(String filePath, String fileName, InventoryCheckVO vo);
	/**
	 * 得到所有库存报溢/报损单和赠送单
	 * @return 所有库存报溢/报损单和赠送单的VO
	 */
	public ArrayList<InventoryBillVO> showBills();
	/**
	 * 得到所有库存报警单
	 * @return 所有库存报警单的VO
	 */
	public ArrayList<InventoryBillVO> showAlarmBills();
	/**
	 * 根据单据类型和状态查找单据
	 * @param type 单据类型
	 * @param state 单据状态
	 * @return 符合条件的单据的集合
	 */
	public ArrayList<InventoryBillVO> findBillByStateAndType(BillType type, BillState state);
	/**
	 * 新增仓库名
	 * @param inventory 等待新增的仓库名
	 * @return 是否成功新增
	 */
	public ResultMessage addInventory(String inventory);
	/**
	 * 添加库存报溢单/报溢单/报警单
	 * @param vo
	 * @return 是否成功添加
	 */
	public ResultMessage addBill(InventoryBillVO vo);
	/**
	 * 删除仓库名
	 * @param inventory 等待删除的仓库名
	 * @return 是否成功删除
	 */
	public ResultMessage deleteInventory(String inventory);
	/**
	 * 删除库存单据
	 * @param ID 单据ID
	 * @return 是否成功删除
	 */
	public ResultMessage deleteBill(String ID);
	/**
	 * 修改仓库名
	 * @param before 修改前的仓库名
	 * @param after 修改后的仓库名
	 * @return 是否成功修改
	 */
	public ResultMessage updateInventory(String before, String after);
	/**
	 * 修改库存单据
	 * @param 单据vo
	 * @return 是否成功修改
	 */
	public ResultMessage updateBill(InventoryBillVO vo);
	/**
	 * 查看库存单据详情
	 * @param ID 单据ID
	 * @return 库存单据详情
	 */
	public InventoryBillVO showBillDetails(String ID);
	/**
	 * 将单据草稿提交给总经理审批，改变单据状态为未审核状态
	 * @param vo 单据vo
	 * @return 是否成功提交
	 */
	public ResultMessage submitBill(InventoryBillVO vo);

    /**
     * 根据单据类型查找单据
     * @param type
     * @return
     */
	public ArrayList<InventoryBillVO> findBillByType(BillType type);
	/**
	 * 根据单据类型得到新单据的ID
	 * @param type 单据类型
	 * @return 新单据的完整ID
	 */
	public String getNewBillIDByType(BillType type);

}
