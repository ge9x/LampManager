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
     * 修改单据内容
     * 
     * @param BillVO
     * @return ResultMessage
     */
	public ResultMessage modifyReceipt(BillVO bill);
	
	/**
     * 单据通过审批
     * 
     * @param BillVO
     * @return ResultMessage
     */
	public ResultMessage approveReceipt(BillVO bill);
	
	/**
	 * 单据未通过审批
	 * 
	 * @param BillVO
	 * @return ResultMessage
	 */
	public ResultMessage refuseReceipt(BillVO bill);
}
