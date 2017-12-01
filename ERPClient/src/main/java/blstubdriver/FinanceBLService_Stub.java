package blstubdriver;

import blservice.formblservice.DocumentDetailsInput;
import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.SalesDetailsInput;
import util.*;
import vo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class FinanceBLService_Stub implements FinanceBLService {

    ArrayList<CustomerVO> customerVOS;
    ArrayList<AccountVO> accountVOS;
    AccountBillVO billVO;


    ArrayList<BillVO> billVOS;

    public FinanceBLService_Stub(){
        CustomerVO c1=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
                "南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,500);
        CustomerVO c2=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
                "南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,600);
        CustomerVO c3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,400);

        AccountVO vo1 = new AccountVO("001","工商银行账户1",2000);
        AccountVO vo2 = new AccountVO("001","工商银行账户2",1000);
        AccountVO vo3 = new AccountVO("001","工商银行账户3",4000);

        AccountBillItemVO itemVO1 = new AccountBillItemVO(vo1,300,"卖出灯具20个");
        AccountBillItemVO itemVO2 = new AccountBillItemVO(vo2,400,"卖出灯具30个");
        CashBillItemVO itemVO3 = new CashBillItemVO("打车",20,"见客户");
        CashBillItemVO itemVO4 = new CashBillItemVO("吃饭",200,"请客户吃中饭");



        ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<AccountBillItemVO>();
        accountBillItemVOS.add(itemVO1);
        accountBillItemVOS.add(itemVO2);

        ArrayList<CashBillItemVO> cashBillItemVOS = new ArrayList<CashBillItemVO>();
        cashBillItemVOS.add(itemVO3);
        cashBillItemVOS.add(itemVO4);

        billVO = new AccountBillVO(new Date(),"SKD-20171022-00001", BillState.DRAFT,BillType.RECEIPT,
                "客户甲","营业员1",accountBillItemVOS);


        customerVOS = new ArrayList<CustomerVO>();
        accountVOS = new ArrayList<AccountVO>();

        customerVOS.add(c1);
        customerVOS.add(c2);
        customerVOS.add(c3);
        accountVOS.add(vo1);
        accountVOS.add(vo2);
        accountVOS.add(vo3);

        AccountBillVO billVO1 = new AccountBillVO(new Date(),"SKD-20171022-00001", BillState.DRAFT,BillType.RECEIPT,
                "客户甲","营业员1",accountBillItemVOS);
        AccountBillVO billVO2 = new AccountBillVO(new Date(),"FKD-20171022-00001", BillState.SUBMITTED,BillType.PAYMENT,
                "客户甲","营业员1",accountBillItemVOS);
        CashBillVO billVO3 = new CashBillVO(new Date(),"FKD-20171022-00001", BillState.SUBMITTED,BillType.CASH,
                "营业员1","工商银行账户",cashBillItemVOS,220);
        InventoryBillVO billVO4 = new InventoryBillVO("BYD-20171022-00000", BillType.OVERFLOW, BillState.PASS, new Date(), "栖霞区仓库","王某",new HashMap<GoodsVO, Integer>());
        GoodsItemVO gi2=new GoodsItemVO("01", "霓虹灯",null ,20, 35.0,
                "耐用");
        ArrayList<GoodsItemVO> goodsItemVOS = new ArrayList<GoodsItemVO>();
        goodsItemVOS.add(gi2);
        PurchaseVO billVO5=new PurchaseVO(BillType.PURCHASE,BillState.PASS,"JHD-20171022-00001","供应商1"
                ,"00000001","默认仓库","阿红",goodsItemVOS,"满足客户需求"
                ,new Date());


    }

    public String getNewReceiptID() {
        return "SKD-20171022-00001";
    }

    public String getNewPaymentID() {
        return "FKD-20171022-00001";
    }

    public String getNewCashBillID() {
        return "XJFYD-20171022-00001";
    }

    public String getUserID() {
        return "test1";
    }

    public ArrayList<CustomerVO> getAllCustomer() {
        return customerVOS;
    }

    public ArrayList<AccountVO> getAllAccount() {
        return accountVOS;
    }

    public ResultMessage submit(AccountBillVO vo) {
        if (vo.ID != null && vo.accountBillItems != null
                && vo.userName != null && vo.customerID != null
                && vo.type != null){
            System.out.println("Submit succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Submit failed");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage submit(CashBillVO vo) {
        if (vo.ID != null && vo.cashBillItems != null
                && vo.userName != null && vo.accountName != null
                && vo.type != null){
            System.out.println("Submit succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Submit failed");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage save(AccountBillVO vo) {
        if (vo.ID != null && vo.type != null){
            System.out.println("Save succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Save failed");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage save(CashBillVO vo) {
        if (vo.ID != null && vo.type != null){
            System.out.println("Save succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Save failed");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage updateDraft(AccountBillVO vo) {
        if(vo.ID.equals("SKD-20171022-00001")){
            System.out.println("Update succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Update failed");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage updateDraft(CashBillVO vo) {
        if(vo.ID.equals("XJFYD-20171022-00001")){
            System.out.println("Update succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Update failed");
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ArrayList<AccountBillVO> getDraftAccountBills() {
        ArrayList<AccountBillVO> accountBillVOS = new ArrayList<>();
        accountBillVOS.add(billVO);
        return accountBillVOS;
    }

    @Override
    public ArrayList<AccountBillVO> getSubmittedAccountBills() {
        ArrayList<AccountBillVO> accountBillVOS = new ArrayList<>();
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        return accountBillVOS;
    }

    @Override
    public ArrayList<AccountBillVO> getPassAccountBills() {
        ArrayList<AccountBillVO> accountBillVOS = new ArrayList<>();
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        return accountBillVOS;
    }

    @Override
    public ArrayList<AccountBillVO> getFailedAccountBills() {
        ArrayList<AccountBillVO> accountBillVOS = new ArrayList<>();
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        accountBillVOS.add(billVO);
        return accountBillVOS;
    }

    @Override
    public String getCustomerNameByID(String ID) {
        return "客户A";
    }
}
