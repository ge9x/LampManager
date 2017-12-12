package bl.formbl;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bl.goodsbl.Goods;
import bl.goodsbl.GoodsController;
import bl.inventorybl.InventoryController;
import bl.salesbl.PurchaseController;
import bl.salesbl.SalesController;
import blservice.goodsblservice.GoodsInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import util.BillType;
import util.ResultMessage;
import vo.*;

import javax.print.DocFlavor;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Profit {
    SalesInfo salesInfo;
    PurchaseInfo purchaseInfo;
    InventoryInfo inventoryInfo;
    GoodsInfo goodsInfo;

    ArrayList<SalesVO> salesVOS;
    ArrayList<InventoryBillVO> inventoryBillVOS;
    ArrayList<PurchaseVO> purchaseVOS;
    /**
     * 销售收入
     */
    double salesIncome = 0;

    /**
     * 商品报溢收入
     */
    public double overflowIncome = 0;

    /**
     * 进货退货差价
     */
    public double buyAndReturnIncome = 0;

    /**
     * 代金券与实际收款差额收入
     */
    public double voucherIncome = 0;

    /**
     * 折让后总收入
     */
    public double totalIncome = 0;


    /**
     * 折让
     */
    public double allowance = 0;

    /**
     * 销售成本
     */
    public double salescost = 0;

    /**
     * 商品报损
     */
    public double lossExpense = 0;

    /**
     * 商品赠出
     */
    public double giftExpense;

    /**
     * 总支出
     */
    public double totalExpense;

    /**
     * 利润
     */
    public double profit;

    public Profit(){
        salesInfo = new SalesController();
        inventoryInfo = new InventoryController();
        goodsInfo = new GoodsController();
        purchaseInfo = new PurchaseController();
    }

    public ProfitVO getProfit(String startDate, String endDate) {
        getSalesOrders(startDate,endDate);
        getInventoryBills(startDate, endDate);
        getPurchaseBills(startDate, endDate);
        handleSalesBills();
        handleInventoryBills();
        handlePurchaseBills();
        totalIncome = salesIncome + overflowIncome + buyAndReturnIncome + voucherIncome - allowance;
        totalExpense = salescost + lossExpense + giftExpense;
        profit = totalIncome = totalExpense;
        return new ProfitVO(startDate, endDate, salesIncome, overflowIncome,
                0,buyAndReturnIncome,voucherIncome,totalIncome,
                allowance,salescost,lossExpense,giftExpense,totalExpense,profit);
    }

    public void getSalesOrders(String startDate, String endDate){
        salesVOS = salesInfo.getAllSalesOrder(startDate,endDate);
    }

    public void getInventoryBills(String startDate,String endDate){
        inventoryBillVOS = inventoryInfo.getInventoryBillsByDate(startDate,endDate);
    }

    public void getPurchaseBills(String startDate, String endDate){
        purchaseVOS = purchaseInfo.getPurchaseByDate(startDate,endDate);
    }
    public void handlePurchaseBills(){
        for (PurchaseVO purchaseVO:purchaseVOS){
            if (purchaseVO.type == BillType.PURCHASE){
                salescost += purchaseVO.sum;
            }else if (purchaseVO.type == BillType.RETURN){
                buyAndReturnIncome += purchaseVO.sum;
            }
        }
    }
    //处理销售出货单
    public void handleSalesBills(){
        for (SalesVO salesVO:salesVOS){
            if (salesVO.type == BillType.SALES){
                salesIncome += salesVO.beforeSum;
                allowance += salesVO.allowance;
                if (salesVO.voucher > salesVO.beforeSum){
                    voucherIncome += (salesVO.voucher - salesVO.beforeSum);
                }
            }
        }
    }

    public void handleInventoryBills(){
        for (InventoryBillVO inventoryBillVO : inventoryBillVOS){
            if (inventoryBillVO.type == BillType.OVERFLOW){
                for (GoodsVO goodsVO : inventoryBillVO.goodsMap.keySet()){
                    overflowIncome += goodsVO.recentBuyingPrice;
                }
            }else if (inventoryBillVO.type == BillType.LOSS){
                for (GoodsVO goodsVO : inventoryBillVO.goodsMap.keySet()){
                    lossExpense += goodsVO.recentBuyingPrice;
                }
            }else if (inventoryBillVO.type == BillType.GIFT){
                for (GoodsVO goodsVO : inventoryBillVO.goodsMap.keySet()){
                    giftExpense += goodsVO.recentRetailPrice;
                }
            }
        }
    }

    public ResultMessage export(String filePath, String filename, ArrayList<ProfitVO> profitVOS) {
        filename = filename.split("\\.")[0];
        ExportToExcel exporter = new ExportToExcel.Builder(filePath, filename, ExcelType.XLSX)
                .withModel(Model.of(ProfitVO.class, profitVOS)).build();
        if (exporter.export())
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }
}
