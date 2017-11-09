package bl.inventorybl;

import blservice.goodsblservice.GoodsInfo;
import util.ResultMessage;
import vo.InventoryBillVO;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryBill {
	private InventoryBillVO vo;
	private GoodsInfo goodsInfo;
	
	public InventoryBill(){
		
	}

	/**
	 * 提交单据
	 * @return 是否提交成功
	 */
    public ResultMessage submit(){
        return null;
    }
    
    /**
     * 保存单据（成为草稿状态）
     * @return 是否保存成功
     */
    public ResultMessage save(){
        return null;
    }
    
    /**
     * 修改单据
     * @param vo 要修改的单据的VO
     * @return 是否修改成功
     */
    public ResultMessage update(InventoryBillVO vo){
        return null;
    }
}
