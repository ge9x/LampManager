package blstubdriver;

import blservice.financeblservice.FinanceBLService;
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
    ArrayList<SalesDetailVO> salesDetailVOS;
    ProfitVO profitVO;
    ArrayList<BillVO> billVOS;

    public FinanceBLService_Stub(){
        CustomerVO c1=new CustomerVO("00000001", CustomerCategory.SELLER, Level.LEVEL_FIVE,"金主","15545786610",
                "南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0);
        CustomerVO c2=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
                "南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0);
        CustomerVO c3=new CustomerVO("00000003",CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0);

        AccountVO vo1 = new AccountVO("工商银行账户1",2000);
        AccountVO vo2 = new AccountVO("工商银行账户2",1000);
        AccountVO vo3 = new AccountVO("工商银行账户3",4000);

        AccountBillItemVO itemVO1 = new AccountBillItemVO(vo1,300,"卖出灯具20个");
        AccountBillItemVO itemVO2 = new AccountBillItemVO(vo2,400,"卖出灯具30个");
        CashBillItemVO itemVO3 = new CashBillItemVO("打车",20,"见客户");
        CashBillItemVO itemVO4 = new CashBillItemVO("吃饭",200,"请客户吃中饭");

        SalesDetailVO salesDetailVO1 = new SalesDetailVO(new Date(),"霓虹灯", "大", 20, 35.0);
        SalesDetailVO salesDetailVO2 = new SalesDetailVO(new Date(),"挂灯", "xxdd", 10, 35.0);
        salesDetailVOS.add(salesDetailVO1);
        salesDetailVOS.add(salesDetailVO2);

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
        CashBillVO billVO3 = new CashBillVO(new Date(),"FKD-20171022-00001", BillState.SUBMITTED,BillType.PAYMENT,
                "营业员1","工商银行账户",cashBillItemVOS,220);
        InventoryBillVO billVO4 = new InventoryBillVO("BYD-20171022-00000", BillType.OVERFLOW, BillState.PASS, new Date(), new HashMap<GoodsVO, Integer>());
        GoodsItemVO gi2=new GoodsItemVO("000002", "挂灯", "xxdd", 10, 35.0, 350,
                "好看");
        PurchaseVO billVO5=new PurchaseVO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20171022-00002","供应商2"
                ,"默认仓库","操作员1",gi2,"好看"
                ,700.0,new Date());

        profitVO = new ProfitVO(new Date(),new Date(),10000,3000,
                200,400,900,9500,5000,
                4000,1000,500,5500,4000);
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
                && vo.userName != null && vo.customerName != null
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
        return null;
    }

    public ResultMessage updateDraft(CashBillVO vo) {
        return null;
    }

    public BillVO findByID(String ID) {
        if (ID.equals("SKD-20171022-00001")){
            return billVO;
        }else{
            return null;
        }
    }

    public ArrayList<SalesDetailVO> getSalesDetails(Date startDate, Date endDate, String goodName, String customerName, String salesman, String inventory) {
        return salesDetailVOS;
    }

    public ArrayList<BillVO> getDocumentDetails(Date startDate, Date endDate, BillType billType, String customerName, String salesman, String inventory) {
        return null;
    }

    public ResultMessage redCover(BillVO vo) {
        if (vo.ID.equals(billVO.ID)){
            System.out.println("RedCover succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("RedCover failed");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage redCoverAndCopy(BillVO vo) {
        if (vo.ID.equals(billVO.ID)){
            System.out.println("RedCoverAndCopy succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("RedCoverAndCopy failed");
            return ResultMessage.FAILED;
        }
    }

    public ProfitVO getProfit(Date startDate, Date endDate) {
        return profitVO;
    }
}
