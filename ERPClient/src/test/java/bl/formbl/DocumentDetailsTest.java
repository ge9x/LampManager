package bl.formbl;

import bl.accountbl.AccountBLFactory;
import bl.financialbl.FinanceBLFactory;
import bl.goodsbl.Goods;
import bl.goodsbl.GoodsBLFactory;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBLFactory;
import bl.salesbl.SalesBLFactory;
import blservice.accountblservice.AccountBLService;
import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.DocumentDetailsInput;
import blservice.goodsblservice.GoodsBLService;
import blservice.inventoryblservice.InventoryBLService;
import blservice.salesblservice.SalesBLService;
import org.ERPClient.AppTest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.BillState;
import util.BillType;
import util.FilterType;
import util.ResultMessage;
import vo.*;

import java.awt.geom.RectangularShape;
import java.awt.image.RescaleOp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * DocumentDetails Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>??? 1, 2018</pre>
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocumentDetailsTest {

    static DocumentDetails documentDetails;
    static DocumentDetailsInput input;
    static AccountBillVO receipt;

    @BeforeClass
    public static void setUp() throws Exception {
        AppTest appTest = new AppTest();
        documentDetails  = new DocumentDetails();

        input = new DocumentDetailsInput("2018-01-01","2018-01-02",null,null,null);

        addSalesBill();
        addInventoryBill();
        addAccountBill();

    }

    public static void addAccountBill() {
        FinanceBLService financeBLService = FinanceBLFactory.getBLService();

        ArrayList<AccountBillItemVO> items = new ArrayList<>();
        AccountVO accountVO = new AccountVO("01","地平线银行", 200000);
        AccountBillItemVO accountBillItemVO = new AccountBillItemVO(accountVO,200,"收款");
        items.add(accountBillItemVO);

        receipt = new AccountBillVO(LocalDate.now().toString(),financeBLService.getNewReceiptID(),BillState
                .PASS, BillType.RECEIPT, "00000001","乐盛捷",items);

        financeBLService.submit(receipt);
    }

    public static void addInventoryBill() {
        InventoryBLService inventoryBLService = InventoryBLFactory.getBLService();
        GoodsBLService goodsBLService = GoodsBLFactory.getBLService();

        inventoryBLService.addInventory("默认仓库");
        ArrayList<InventoryBillVO> ret = new ArrayList<>();
        HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
        GoodsVO goodsVO = new GoodsVO(goodsBLService.getNewID("02"), "地平线牌欧式落地灯", "DPX-0001", "落地灯", "默认仓库", 1, 20, 250, 255, 250, 255);
        goodsBLService.add(goodsVO);

        goodsMap.put(goodsVO, 5);
        InventoryBillVO vo
                = new InventoryBillVO(inventoryBLService.getNewBillIDByType(BillType.OVERFLOW),
                BillType.OVERFLOW, BillState.PASS, LocalDate.now().toString(),
                "默认仓库", "Xun", goodsMap);
        ret.add(vo);

        inventoryBLService.addBill(vo);
    }

    public static void addSalesBill(){
        SalesBLService salesBLService = SalesBLFactory.getSalesBLService();

        GoodsItemVO goodsItemVO = new GoodsItemVO("02000001","地平线牌欧式落地灯","DPX-0001",2,200,"无");
        ArrayList<GoodsItemVO> goodsItemVOS = new ArrayList<>();
        goodsItemVOS.add(goodsItemVO);

        SalesVO vo = new SalesVO(BillType.SALES, BillState.PASS, salesBLService.getnewSalesID(),
                "路人甲", "00000001", "朱雨欣", "朱雨欣", "默认仓库",
                goodsItemVOS, 100, 500, "无", "2018-01-01", null);

        salesBLService.addSales(vo);
    }

    @Test
    public void a_testGetDocumentDetails() throws Exception {
        ArrayList<BillVO> billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(3,billVOS.size());
    }


    @Test
    public void b_testSearchByType() throws Exception {
        input.billType = BillType.RECEIPT;
        ArrayList<BillVO> billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(1,billVOS.size());
    }


    @Test
    public void c_testSearchByInventory() throws Exception {
        input.billType = BillType.OVERFLOW;
        input.filterType = FilterType.INVENTORY;
        input.keyword = "默认";
        ArrayList<BillVO> billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(1,billVOS.size());

        input.keyword = "栖霞区仓库";
        billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(0,billVOS.size());
    }


    @Test
    public void d_testSearchByCustomer() throws Exception {
        input.billType = BillType.SALES;
        input.filterType = FilterType.CUSTOMER;
        input.keyword = "路人甲";
        ArrayList<BillVO> billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(1,billVOS.size());

        input.keyword = "仓库";
        billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(0,billVOS.size());
    }

    @Test
    public void e_testSearchBySaleman() throws Exception {
        input.billType = BillType.SALES;
        input.filterType = FilterType.SALESMAN;
        input.keyword = "朱雨欣";
        ArrayList<BillVO> billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(1,billVOS.size());

        input.keyword = "仓库";
        billVOS = documentDetails.getDocumentDetails(input);
        assertEquals(0,billVOS.size());
    }


    @Test
    public void f_testRedCover() throws Exception {
        ResultMessage re = documentDetails.redCover(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void g_testRedCoverAndCopy() throws Exception {
        ResultMessage re = documentDetails.redCoverAndCopy(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }
} 
