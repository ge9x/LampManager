package vo;

import java.util.Date;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class ProfitVO {
    /**
     * 开始时间
     */
    public String startDate;

    /**
     * 结束时间
     */
    public String endDate;

    /**
     * 销售收入
     */
    public double salesIncome;

    /**
     * 商品报溢收入
     */
    public double overflowIncome;

    /**
     * 成本调价收入
     */
    public double costAdjIncome;

    /**
     * 进货退货差价
     */
    public double buyAndReturnIncome;

    /**
     * 代金券与实际收款差额收入
     */
    public double voucherIncome;

    /**
     * 折让后总收入
     */
    public double totalIncome;

    /**
     * 折让
     */
    public double allowance;

    /**
     * 销售成本
     */
    public double salescost;

    /**
     * 商品报损
     */
    public double lossExpense;

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

    public ProfitVO(String startDate, String endDate, double salesIncome, double overflowIncome, double costAdjIncome, double buyAndReturnIncome, double voucherIncome, double totalIncome, double allowance, double salescost, double lossExpense, double giftExpense, double totalExpense, double profit) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.salesIncome = salesIncome;
        this.overflowIncome = overflowIncome;
        this.costAdjIncome = costAdjIncome;
        this.buyAndReturnIncome = buyAndReturnIncome;
        this.voucherIncome = voucherIncome;
        this.totalIncome = totalIncome;
        this.allowance = allowance;
        this.salescost = salescost;
        this.lossExpense = lossExpense;
        this.giftExpense = giftExpense;
        this.totalExpense = totalExpense;
        this.profit = profit;
    }
}
