package blservice.inventoryblservice;

import vo.InventoryBillVO;

import java.util.ArrayList;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public interface InventoryInfo {

    /**
     * 根据日期获得已经审批通过的库存报溢单、库存报损单、库存赠送单
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 该段日期内已经审批通过的三种库存类单据
     */
    public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate);


}
