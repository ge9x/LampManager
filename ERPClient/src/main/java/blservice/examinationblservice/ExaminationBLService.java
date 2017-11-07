package blservice.examinationblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.BillVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface ExaminationBLService {
	/**
     * 显示待审批单据
     * 
     * @return ArrayList<BillVO>
     */
	public ArrayList<BillVO> show();
	
	/**
     * 显示历史单据
     * 
     * @return ArrayList<BillVO>
     */
	public ArrayList<BillVO> showHistory();
	
	/**
     * 查看单据内容
     * 
     * @param receipt
     * @return ResultMessage
     */
	public BillVO checkReceipt(String billID);
	
	/**
     * 修改单据内容
     * 
     * @param receipt
     * @return ResultMessage
     */
	public ResultMessage modifyReceipt(BillVO bill);
	
	/**
     * 修改单据状态
     * 
     * @param receipt
     * @return ResultMessage
     */
	public ResultMessage approveReceipt(BillVO bill);
}
