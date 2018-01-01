package bl.formbl;

import bl.accountbl.AccountBLFactory;
import bl.financialbl.FinanceBLFactory;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBLFactory;
import bl.salesbl.SalesBLFactory;
import blservice.accountblservice.AccountBLService;
import blservice.financeblservice.FinanceBLService;
import blservice.formblservice.DocumentDetailsInput;
import blservice.inventoryblservice.InventoryBLService;
import blservice.salesblservice.SalesBLService;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.BillState;
import util.BillType;
import vo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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

    @BeforeClass
    public static void setUp() throws Exception {
        documentDetails  = new DocumentDetails();

        input = new DocumentDetailsInput("2017-12-25","2018-01-02",null,null,null);

        addSalesBill();
        addInventoryBill();
        addAccountBill();

    }

    private static void addAccountBill() {
        FinanceBLService financeBLService = FinanceBLFactory.getBLService();

        ArrayList<AccountBillItemVO> items = new ArrayList<>();
        AccountVO accountVO = new AccountVO("01","地平线银行", 200000);
        AccountBillItemVO accountBillItemVO = new AccountBillItemVO(accountVO,200,"收款");
        items.add(accountBillItemVO);

        AccountBillVO receipt = new AccountBillVO(LocalDate.now().toString(),financeBLService.getNewReceiptID(),BillState
                .PASS, BillType.RECEIPT, "00000001","乐盛捷",items);

        financeBLService.submit(receipt);
    }

    private static void addInventoryBill() {
        InventoryBLService inventoryBLService = InventoryBLFactory.getBLService();

        ArrayList<InventoryBillVO> ret = new ArrayList<>();
        HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
        GoodsVO goodsVO = new GoodsVO("02000001", "地平线牌欧式落地灯", "DPX-0001", "落地灯", "默认仓库", 1, 20, 250, 255, 250, 255);
        goodsMap.put(goodsVO, 5);
        InventoryBillVO vo
                = new InventoryBillVO(inventoryBLService.getNewBillIDByType(BillType.OVERFLOW),
                BillType.OVERFLOW, BillState.PASS, LocalDate.now().toString(),
                "默认仓库", "Xun", goodsMap);
        ret.add(vo);

        inventoryBLService.addBill(vo);
    }

    private static void addSalesBill(){
        SalesBLService salesBLService = SalesBLFactory.getSalesBLService();

        GoodsItemVO goodsItemVO = new GoodsItemVO("02000001","地平线牌欧式落地灯","DPX-0001",2,200,"无");
        ArrayList<GoodsItemVO> goodsItemVOS = new ArrayList<>();
        goodsItemVOS.add(goodsItemVO);

        SalesVO vo = new SalesVO(BillType.SALES, BillState.PASS, salesBLService.getnewSalesID(),
                "路人甲", "00000001", "朱雨欣", "朱雨欣", "默认仓库",
                goodsItemVOS, 0, 0, "无", "2018-01-01", null);

        salesBLService.addSales(vo);
    }

    @Test
    public void testGetDocumentDetails() throws Exception {
        documentDetails.getDocumentDetails(input);
    }


    @Test
    public void testSearchByType() throws Exception {
    }


    @Test
    public void testSearchByInventory() throws Exception {
    }


    @Test
    public void testSearchByCustomer() throws Exception {
    }


    @Test
    public void testSearchBySaleman() throws Exception {
    }


    @Test
    public void testGetAllBills() throws Exception {

    }


    @Test
    public void testRedCover() throws Exception {
    }


    @Test
    public void testRedCoverAndCopy() throws Exception {
    }
} 
