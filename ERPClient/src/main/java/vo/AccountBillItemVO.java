package vo;

/**
 * Created by Kry·L on 2017/10/21.
 */
public class AccountBillItemVO {
    /**
     * 银行账户
     */
    public AccountVO account;

    /**
     * 转账金额
     */
    public double transferMoney;

    /**
     * 备注
     */
    public String remark;

    public AccountBillItemVO(AccountVO accountVO,double transferMoney, String remark){
        this.account = accountVO;
        this.transferMoney = transferMoney;
        this.remark = remark;
    }

}
