package bl.formbl;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bl.goodsbl.GoodsBLFactory;
import bl.goodsbl.GoodsController;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import bl.promotionbl.*;
import bl.salesbl.*;
import blservice.goodsblservice.GoodsInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.PromotionInfo;
import blservice.promotionblservice.promotionTotal.PromotionTotalInfo;
import blservice.promotionblservice.promotionbargain.PromotionBargainInfo;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerInfo;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import util.BillType;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Profit {
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
    SalesInfo salesInfo;
    PurchaseInfo purchaseInfo;
    InventoryInfo inventoryInfo;
    GoodsInfo goodsInfo;
    PromotionTotalInfo promotionTotalInfo;
    PromotionCustomerInfo promotionCustomerInfo;
    ArrayList<SalesVO> salesVOS;
    ArrayList<InventoryBillVO> inventoryBillVOS;
    ArrayList<PurchaseVO> purchaseVOS;
    /**
     * 销售收入
     */
    double salesIncome = 0;

    public Profit() {
        salesInfo = SalesBLFactory.getSalesInfo();
        inventoryInfo = InventoryBLFactory.getInfo();
        goodsInfo = GoodsBLFactory.getInfo();
        purchaseInfo = SalesBLFactory.getPurchaseInfo();
        promotionTotalInfo = PromotionBLFactory.getTotalInfo();
        promotionCustomerInfo = PromotionBLFactory.getCustomerInfo();

        salesVOS = new ArrayList<>();
        inventoryBillVOS = new ArrayList<>();
        purchaseVOS = new ArrayList<>();
    }

    public ProfitVO getProfit(String startDate, String endDate) {
        clearData();
        getSalesOrders(startDate, endDate);
        getInventoryBills(startDate, endDate);
        getPurchaseBills(startDate, endDate);
        handleSalesBills();
        handleInventoryBills();
        handlePurchaseBills();
        totalIncome = salesIncome + overflowIncome + buyAndReturnIncome + voucherIncome - allowance;
        totalExpense = salescost + lossExpense + giftExpense;
        profit = totalIncome - totalExpense;
        return new ProfitVO(startDate, endDate, salesIncome, overflowIncome,
                0, buyAndReturnIncome, voucherIncome, totalIncome,
                allowance, salescost, lossExpense, giftExpense, totalExpense, profit);
    }

    public void getSalesOrders(String startDate, String endDate) {
        salesVOS = salesInfo.getAllSalesOrder(startDate, endDate);
    }

    public void getInventoryBills(String startDate, String endDate) {
        inventoryBillVOS = inventoryInfo.getInventoryBillsByDate(startDate, endDate);
    }

    public void getPurchaseBills(String startDate, String endDate) {
        purchaseVOS = purchaseInfo.getPurchaseByDate(startDate, endDate);
    }

    public void handlePurchaseBills() {
        for (PurchaseVO purchaseVO : purchaseVOS) {
            if (purchaseVO.type == BillType.PURCHASE) {
                salescost += purchaseVO.sum;
            } else if (purchaseVO.type == BillType.RETURN) {
                buyAndReturnIncome += purchaseVO.sum;
            }
        }
    }

    //处理销售出货单
    public void handleSalesBills() {
        for (SalesVO salesVO : salesVOS) {
            if (salesVO.type == BillType.SALES) {
                salesIncome += salesVO.beforeSum;
                allowance += salesVO.allowance;
                if (salesVO.voucher > salesVO.beforeSum) {
                    voucherIncome += (salesVO.voucher - salesVO.beforeSum);
                }
                String promotionName = salesVO.promotionName;
                PromotionTotalVO totalVO = promotionTotalInfo.findPromotionByName(promotionName);
                PromotionCustomerVO customerVO = promotionCustomerInfo.findPromotionByName(promotionName);
                ArrayList<GoodsItemVO> items = null;
                if (totalVO != null){
                    items = totalVO.gifts;
                }
                if (customerVO != null){
                   items = customerVO.gifts;
                }
                if (items != null){
                    for (GoodsItemVO itemVO : items){
                        giftExpense += itemVO.sum;
                    }
                }
            }
        }
    }

    public void handleInventoryBills() {
        for (InventoryBillVO inventoryBillVO : inventoryBillVOS) {
            if (inventoryBillVO.type == BillType.OVERFLOW) {
                for (GoodsVO goodsVO : inventoryBillVO.goodsMap.keySet()) {
                    overflowIncome += goodsVO.recentBuyingPrice;
                }
            } else if (inventoryBillVO.type == BillType.LOSS) {
                for (GoodsVO goodsVO : inventoryBillVO.goodsMap.keySet()) {
                    lossExpense += goodsVO.recentBuyingPrice;
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

    private void clearData() {
        salesIncome = 0;
        overflowIncome = 0;
        buyAndReturnIncome = 0;
        voucherIncome = 0;
        totalIncome = 0;
        allowance = 0;
        salescost = 0;
        lossExpense = 0;
        giftExpense = 0;
        totalExpense = 0;
        profit = 0;
    }
}
