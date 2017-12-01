package bl.financialbl;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.AccountVO;
import vo.BillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockAccountBill extends AccountBill {

    @Override
    public HashMap<String, String> getAllCustomers() {
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("00000001","老李");
        return map;
    }

    @Override
    public ArrayList<String> getAllAccount() {
        ArrayList<String> account = new ArrayList<String>();
        account.add("工商银行账户1");
        return account;
    }

    @Override
    public void addBillItem(AccountBillItemVO vo) {

    }

    @Override
    public void addAccountBill(String customer, String account) {

    }

    @Override
    public void calTotal() {

    }

    @Override
    public ResultMessage submit(AccountBillVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public AccountBillVO save() {
        AccountBillItemVO itemVO1 = new AccountBillItemVO(new AccountVO("001","工商银行账户",2000.00),300,"卖出灯具20个");
        ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<AccountBillItemVO>();
        accountBillItemVOS.add(itemVO1);
        AccountBillVO billVO = new AccountBillVO(new Date(),"SKD-20171022-00001", BillState.DRAFT, BillType.RECEIPT,
                "客户甲","营业员1",accountBillItemVOS);
        return billVO;
    }

    @Override
    public ResultMessage update(AccountBillVO vo) {
        if (vo.ID.equals("SKD-20171022-00001")){
            System.out.println("Update bill succeed");
            return  ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }
}
