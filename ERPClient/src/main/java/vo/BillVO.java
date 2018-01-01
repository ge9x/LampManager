package vo;

import util.BillState;
import util.BillType;

/**
 * Created by Kry·L on 2017/10/21.
 */
public abstract class BillVO {
    private static final String seperator = System.lineSeparator();
    /**
     * 单据最后修改时间
     */
    public String date;

    /**
     * 单据编号
     */
    public String ID;

    /**
     * 单据状态
     */
    public BillState state;

    /**
     * 单据类型
     */
    public BillType type;

    @Override
    public String toString(){
        return  "单据编号: " + ID + seperator +
                "单据类型: " + type + seperator +
                "单据状态: " + state + seperator +
                "生成时间：" + date + seperator;
    }
}
