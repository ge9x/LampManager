package datastubdriver;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dataservice.financedataservice.FinanceDataService;
import po.*;
import util.BillState;
import util.BillType;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class FinanceDataService_Stub implements FinanceDataService {

    /**
     * 库存单据集
     */

    ArrayList<InventoryBillPO> inventoryBillPOS = new ArrayList<InventoryBillPO>();
    InventoryBillPO po1 = new InventoryBillPO("BYD-20171022-00000", BillType.OVERFLOW, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
    InventoryBillPO po2 = new InventoryBillPO("BSD-20171022-00000", BillType.LOSS, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
    InventoryBillPO po3 = new InventoryBillPO("BJD-20171022-00000", BillType.ALARM, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
    InventoryBillPO po4 = new InventoryBillPO("ZSD-20171022-00000", BillType.GIFT, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());
    InventoryBillPO po5 = new InventoryBillPO("JHD-20171022-00000", BillType.PURCHASE, BillState.PASS, new Date(), "栖霞区仓库", "王二小", new HashMap<GoodsPO, Integer>());

    /**
     * 销售单据集
     */
    ArrayList<SalesPO> salesPOS = new ArrayList<SalesPO>();

    GoodsItemPO gi1=new GoodsItemPO(1, "霓虹灯",null, 20, 35.0,
            "耐用");
    GoodsItemPO gi2=new GoodsItemPO(2, "挂灯",null, 10, 35.0,
            "好看");
    ArrayList<GoodsItemPO> goodsItemList=new ArrayList<GoodsItemPO>();
    {
        goodsItemList.add(gi1);
        goodsItemList.add(gi2);
    }
    SalesPO s1=new SalesPO(BillType.SALES, BillState.DRAFT, "XSD-20171022-00001", "销售商1", "业务员1",
            "阿强", "00000003","默认仓库",goodsItemList , 100,500,  "满足客户需求", new Date());

    /**
     * 财务单据集
     */
    AccountBillItemPO itemPO1 = new AccountBillItemPO("工商银行账户1",300,"卖出灯具20个");
    AccountBillItemPO itemPO2 = new AccountBillItemPO("工商银行账户2",400,"卖出灯具30个");
    CashBillItemPO itemPO3 = new CashBillItemPO("打车",20,"见客户");
    CashBillItemPO itemPO4 = new CashBillItemPO("吃饭",200,"请客户吃中饭");

    ArrayList<AccountBillItemPO> accountBillItemPOS = new ArrayList<AccountBillItemPO>();
    ArrayList<CashBillItemPO> cashBillItemPOS = new ArrayList<CashBillItemPO>();
    AccountBillPO billVO1;
    CashBillPO billVO3;
    AccountBillPO billVO2;
    ArrayList<AccountBillPO> accountBillPOS;
    ArrayList<CashBillPO> cashBillPOS;

    public FinanceDataService_Stub(){
        inventoryBillPOS.add(po1);
        inventoryBillPOS.add(po2);
        inventoryBillPOS.add(po3);
        inventoryBillPOS.add(po4);
        inventoryBillPOS.add(po5);

        salesPOS.add(s1);

        accountBillItemPOS.add(itemPO1);
        accountBillItemPOS.add(itemPO2);
        cashBillItemPOS.add(itemPO3);
        cashBillItemPOS.add(itemPO4);
        billVO1 = new AccountBillPO("SKD-20171022-00001",new Date(), BillType.RECEIPT,BillState.DRAFT,
                00000001,"营业员1",accountBillItemPOS);
        billVO2 = new AccountBillPO("FKD-20171022-00001",new Date(),BillType.PAYMENT, BillState.SUBMITTED,
                00000001,"营业员1",accountBillItemPOS);

        billVO3 = new CashBillPO("FKD-20171022-00001", new Date(), BillType.CASH, BillState.SUBMITTED,
                "营业员1", "工商银行账户", cashBillItemPOS, 220);
        accountBillPOS = new ArrayList<AccountBillPO>();
        accountBillPOS.add(billVO1);
        accountBillPOS.add(billVO2);
        cashBillPOS.add(billVO3);
    }

    public String getNewReceiptID() throws RemoteException {
        return "SKD-20171023-00001";
    }

    public String getNewPaymentID() throws RemoteException {
        return "FKD-20171023-00001";
    }

    public String getNewCashBillID() throws RemoteException {
        return "XJFYD-20171023-00001";
    }

    public ResultMessage addBill(BillPO po) throws RemoteException {
        if (po.getID().equals("SKD-20171023-00001")){
            System.out.println("Add failed");
            return ResultMessage.EXIST;
        }else{
            System.out.println("Add succeed");
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage deleteBill(BillPO po) throws RemoteException {
        if (po.getID().equals("SKD-20171023-00001")){
            System.out.println("Delete succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Delete failed");
            return ResultMessage.NOT_EXIST;
        }
    }

    public ResultMessage updateBill(BillPO po) throws RemoteException {
        if (po.getID().equals("SKD-20171023-00001")){
            System.out.println("Update succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Update failed");
            return ResultMessage.NOT_EXIST;
        }
    }


}
