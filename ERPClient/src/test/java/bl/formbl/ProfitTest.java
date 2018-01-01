package bl.formbl;

import bl.goodsbl.GoodsBLFactory;
import bl.inventorybl.InventoryBLFactory;
import bl.salesbl.SalesBLFactory;
import blservice.goodsblservice.GoodsBLService;
import blservice.inventoryblservice.InventoryBLService;
import blservice.salesblservice.SalesBLService;
import org.ERPClient.AppTest;
import org.junit.*;
import org.junit.runners.MethodSorters;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.*;

import java.awt.image.RescaleOp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Profit Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>һ�� 1, 2018</pre>
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfitTest {
    static Profit profit;

    @BeforeClass
    public static void setUp() throws Exception {
        AppTest apptest = new AppTest();
        profit = new Profit();
        addSalesBill();
        addInventoryBill();

    }
    public static void addInventoryBill() {
        InventoryBLService inventoryBLService = InventoryBLFactory.getBLService();
        GoodsBLService goodsBLService = GoodsBLFactory.getBLService();

        inventoryBLService.addInventory("默认仓库");
        ArrayList<InventoryBillVO> ret = new ArrayList<>();
        HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
        GoodsVO goodsVO = new GoodsVO("02000001", "地平线牌欧式落地灯", "DPX-0001", "落地灯", "默认仓库", 1, 20, 250, 255, 250, 255);
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
    public void a_testGetProfit() throws Exception {
        ProfitVO profitVO = profit.getProfit("2018-01-01", "2018-01-01");
        assertEquals(100,profitVO.allowance,1);
        assertEquals(100,profitVO.voucherIncome,1);
        assertEquals(0,profitVO.buyAndReturnIncome,1);
        assertEquals(0,profitVO.costAdjIncome,1);
        assertEquals(0,profitVO.giftExpense,1);
        assertEquals(0,profitVO.lossExpense,1);
        assertEquals(1250,profitVO.overflowIncome,1);
        assertEquals(1650,profitVO.totalIncome,1);
        assertEquals(1650,profitVO.profit,1);
        assertEquals(0,profitVO.salescost,1);
        assertEquals(400,profitVO.salesIncome,1);
    }


    @Test
    public void b_testExport() throws Exception {
        ArrayList<ProfitVO> profitVOS = new ArrayList<>();
        profitVOS.add(profit.getProfit("2018-01-01", "2018-01-01"));
        ResultMessage re = profit.export("C:/Users/Dell/git/LampManager","经营情况表",profitVOS);
    }


}
