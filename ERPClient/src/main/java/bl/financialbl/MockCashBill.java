package bl.financialbl;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.CashBillItemVO;
import vo.CashBillVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class MockCashBill extends CashBill {



    @Override
    public ResultMessage submit(CashBillVO vo) {
        CashBillItemVO itemVO3 = new CashBillItemVO("打车",20,"见客户");
        ArrayList<CashBillItemVO> cashBillItemVOS = new ArrayList<CashBillItemVO>();
        cashBillItemVOS.add(itemVO3);
        CashBillVO billVO3 = new CashBillVO(LocalDate.now().toString(),"FKD-20171022-00001", BillState.SUBMITTED, BillType.CASH,
                "营业员1","工商银行账户",cashBillItemVOS,220);
        return ResultMessage.SUCCESS;
    }


    @Override
    public ResultMessage save(CashBillVO vo) {
        CashBillItemVO itemVO3 = new CashBillItemVO("打车",20,"见客户");
        ArrayList<CashBillItemVO> cashBillItemVOS = new ArrayList<CashBillItemVO>();
        cashBillItemVOS.add(itemVO3);
        CashBillVO billVO3 = new CashBillVO(LocalDate.now().toString(),"FKD-20171022-00001", BillState.SUBMITTED, BillType.CASH,
                "营业员1","工商银行账户",cashBillItemVOS,220);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(CashBillVO vo) {
        if (vo.ID.equals("XJFYD-20171022-00001")){
            System.out.println("Update bill succeed");
            return  ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }
}
