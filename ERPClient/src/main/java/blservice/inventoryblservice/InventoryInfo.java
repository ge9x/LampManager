package blservice.inventoryblservice;

import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBill;
import vo.InventoryBillVO;

import java.util.ArrayList;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public interface InventoryInfo {

    /**
     * 根据日期获得库存报溢单、库存报损单、库存赠送单
     * @param startDate
     * @param endDate
     * @return
     */
    public ArrayList<InventoryBillVO> getInventoryBillsByDate(String startDate, String endDate);


}
