package bl.financialbl;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.AccountVO;
import vo.BillVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockAccountBill extends AccountBill {



    @Override
    public ResultMessage submit(AccountBillVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage save(AccountBillVO vo) {
        AccountBillItemVO itemVO1 = new AccountBillItemVO(new AccountVO("001","工商银行账户",2000.00),300,"卖出灯具20个");
        ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<AccountBillItemVO>();
        accountBillItemVOS.add(itemVO1);
        AccountBillVO billVO = new AccountBillVO(LocalDate.now().toString(),"SKD-20171022-00001", BillState.DRAFT, BillType.RECEIPT,
                "客户甲","营业员1",accountBillItemVOS);
        return ResultMessage.SUCCESS;
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
