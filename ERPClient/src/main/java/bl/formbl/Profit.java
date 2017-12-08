package bl.formbl;

import bl.inventorybl.InventoryController;
import bl.salesbl.SalesController;
import blservice.inventoryblservice.InventoryInfo;
import blservice.salesblservice.SalesInfo;
import vo.ProfitVO;
import vo.SalesVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Profit {
    SalesInfo salesInfo;
    InventoryInfo inventoryInfo;

    ArrayList<SalesVO> salesVOS;

    /**
     * 销售收入
     */
    double salesIncome;

    /**
     * 折让
     */
    public double allowance;

    public Profit(){
        salesInfo = new SalesController();
        inventoryInfo = new InventoryController();
    }

    public ProfitVO getProfit(String startDate, String endDate) {
        getSalesOrders(startDate,endDate);
        handleSalesBills();
//        return new ProfitVO(startDate, endDate, )
        return null;

    }

    public void getSalesOrders(String startDate, String endDate){
        salesVOS = salesInfo.getAllSalesOrder(startDate,endDate);
    }

    public void getInventoryBills(String startDate,String endDate){

    }
    public void handleSalesBills(){
        for (SalesVO salesVO:salesVOS){
            salesIncome += salesVO.afterSum;
            allowance += salesVO.allowance;
        }
    }

}
