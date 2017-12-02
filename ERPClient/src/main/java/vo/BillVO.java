package vo;

import util.BillState;
import util.BillType;

import java.util.Date;

/**
 * Created by Kry·L on 2017/10/21.
 */
public abstract class BillVO {
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
}
