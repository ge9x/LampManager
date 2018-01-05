package bl.formbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.SalesDetailsInput;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class MockForm extends Form{

    public BillVO findByID(String ID){
        if (ID.equals("SKD-20171022-00001")){
            System.out.println("Submit succeed");
            AccountBillItemVO itemVO1 = new AccountBillItemVO(new AccountVO("001","工商银行账户",2000.00),300,"卖出灯具20个");
            AccountBillItemVO itemVO2 = new AccountBillItemVO(new AccountVO("001","工商银行账户",2000.00), 400, "卖出灯具30个");
            ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<AccountBillItemVO>();
            accountBillItemVOS.add(itemVO1);
            accountBillItemVOS.add(itemVO2);
            BillVO billVO = new AccountBillVO(LocalDate.now().toString(),"SKD-20171022-00001", BillState.DRAFT, BillType.RECEIPT,
                    "客户甲","营业员1",accountBillItemVOS);
            return billVO;
        }else
            return null;
    }

    @Override
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        SalesDetailVO salesDetailVO1 = new SalesDetailVO(LocalDate.now().toString(), "霓虹灯", "大", 20, 35.0);
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<SalesDetailVO>();
        salesDetailVOS.add(salesDetailVO1);
        return salesDetailVOS;
    }

    @Override
    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        ArrayList<BillVO> billVOS = new ArrayList<BillVO>();
        billVOS.add(findByID("SKD-20171022-00001"));
        return billVOS;
    }

    @Override
    public ResultMessage redCover(BillVO billVO) {
        if (billVO.ID.equals("SKD-20171022-00001")) {
            System.out.println("Red Cover succeed");
            return ResultMessage.SUCCESS;
        } else
            return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage redCoverAndCopy(BillVO billVO) {
        if (billVO.ID.equals("SKD-20171022-00001")) {
            System.out.println("Red Cover and Copy succeed");
            return ResultMessage.SUCCESS;
        } else
            return ResultMessage.FAILED;
    }

    @Override
    public ProfitVO getProfit(String startDate, String endDate) {
        ProfitVO profitVO = new ProfitVO("","", 10000, 3000,
                200, 400, 900, 9500, 5000,
                4000, 1000, 500, 5500, 4000);
        return profitVO;
    }
}
