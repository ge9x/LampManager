package vo;

import util.BillState;
import util.BillType;

import java.util.Date;

/**
 * Created by Kry·L on 2017/10/21.
 */
public abstract class BillVO {
    /**
     * 单据创建时间
     */
    public Date date;

    /**
     * 收付款单据编号
     */
    public String ID;

    /**
     * 收付款单据状态
     */
    public BillState state;

    /**
     * 收付款单据类型
     */
    public BillType type;
}
