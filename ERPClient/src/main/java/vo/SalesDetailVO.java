package vo;

import ExcelUtil.impl.ExcelColumnName;

import java.util.Date;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class SalesDetailVO {
    /**
     * 时间（精确到天）
     */
    @ExcelColumnName(value = "时间")
    public String date;

    /**
     * 商品名称
     */
    @ExcelColumnName(value = "商品名")
    public String goodName;

    /**
     * 型号
     */
    @ExcelColumnName(value = "型号")
    public String type;

    /**
     * 数量
     */
    @ExcelColumnName(value = "数量")
    public int amount;

    /**
     * 单价
     */
    @ExcelColumnName(value = "单价")
    public double price;

    /**
     * 总额
     */
    @ExcelColumnName(value = "总额" )
    public double sum;

    public SalesDetailVO(String date, String goodName, String type, int amount, double price) {
        this.date = date;
        this.goodName = goodName;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.sum = price * amount;
    }
}
