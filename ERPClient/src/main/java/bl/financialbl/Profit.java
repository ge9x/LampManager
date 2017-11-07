package bl.financialbl;

import blservice.inventoryblservice.InventoryInfo;
import blservice.salesblservice.SalesInfo;
import vo.ProfitVO;

import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Profit {
    SalesInfo salesInfo;
    InventoryInfo inventoryInfo;
    public Profit(){

    }
    public ProfitVO getProfit(Date startDate, Date endDate){
        return null;
    }
}
