package bl.financialbl;

import blservice.financeblservice.DocumentDetailsInput;
import blservice.financeblservice.SalesDetailsInput;
import util.*;
import vo.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockFinance extends Finance {
    @Override
    public String getNewReceiptID() {
        return "SKD-20171022-00001";
    }

    @Override
    public String getNewPaymentID() {
        return "FKD-20171022-00001";
    }

    @Override
    public String getNewCashBillID() {
        return "XJFYD-20171022-00001";
    }

    @Override
    public String getUserID() {
        return "test1";
    }

    @Override
    public ArrayList<CustomerVO> getAllCustomer() {
        CustomerVO c3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);
        ArrayList<CustomerVO> customerVOS = new ArrayList<CustomerVO>();
        customerVOS.add(c3);
        return customerVOS;
    }

    @Override
    public ArrayList<AccountVO> getAllAccount() {
        AccountVO account = new AccountVO("001","工商银行账户", 2000.00);
        ArrayList<AccountVO> accountVOS = new ArrayList<AccountVO>();
        accountVOS.add(account);
        return accountVOS;
    }

    @Override
    public ResultMessage submit(AccountBillVO vo) {
        if (vo.ID.equals("SKD-20171022-00001")){
            System.out.println("Submit succeed");
            return ResultMessage.SUCCESS;
        }else
            return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage submit(CashBillVO vo) {
        if (vo.ID.equals("XJFYD-20171022-00001")){
            System.out.println("Submit succeed");
            return ResultMessage.SUCCESS;
        }else
            return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage save(AccountBillVO vo) {
        if (vo.ID.equals("SKD-20171022-00001")){
            System.out.println("Save succeed");
            return ResultMessage.SUCCESS;
        }else
            return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage save(CashBillVO vo) {
        if (vo.ID.equals("XJFYD-20171022-00001")){
            System.out.println("Save succeed");
            return ResultMessage.SUCCESS;
        }else
            return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage updateDraft(AccountBillVO vo) {
        if (vo.ID.equals("SKD-20171022-00001")) {
            System.out.println("Submit succeed");
            return ResultMessage.SUCCESS;
        } else
            return ResultMessage.FAILED;
    }
    @Override
    public ResultMessage updateDraft(CashBillVO vo) {
        if (vo.ID.equals("XJFYD-20171022-00001")){
            System.out.println("Submit succeed");
            return ResultMessage.SUCCESS;
        }else
            return ResultMessage.FAILED;
    }

    @Override
    public BillVO findByID(String ID){
        if (ID.equals("SKD-20171022-00001")){
            System.out.println("Submit succeed");
            AccountBillItemVO itemVO1 = new AccountBillItemVO(new AccountVO("001","工商银行账户",2000.00),300,"卖出灯具20个");
            AccountBillItemVO itemVO2 = new AccountBillItemVO(new AccountVO("001","工商银行账户",2000.00), 400, "卖出灯具30个");
            ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<AccountBillItemVO>();
            accountBillItemVOS.add(itemVO1);
            accountBillItemVOS.add(itemVO2);
            BillVO billVO = new AccountBillVO(new Date(),"SKD-20171022-00001", BillState.DRAFT,BillType.RECEIPT,
                    "客户甲","营业员1",accountBillItemVOS);
            return billVO;
        }else
            return null;
    }

    @Override
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        SalesDetailVO salesDetailVO1 = new SalesDetailVO(new Date(), "霓虹灯", "大", 20, 35.0);
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
    public ProfitVO getProfit(Date startDate, Date endDate) {
        ProfitVO profitVO = new ProfitVO(new Date(), new Date(), 10000, 3000,
                200, 400, 900, 9500, 5000,
                4000, 1000, 500, 5500, 4000);
        return profitVO;
    }
}
