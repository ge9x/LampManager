package bl.formbl;

import bl.salesbl.Sales;
import bl.salesbl.SalesBLFactory;
import blservice.formblservice.SalesDetailsInput;
import blservice.salesblservice.SalesBLService;
import org.ERPClient.AppTest;
import org.junit.*;
import org.junit.runners.MethodSorters;
import util.BillState;
import util.BillType;
import util.FilterType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.SalesDetailVO;
import vo.SalesVO;

import javax.xml.transform.Result;
import java.awt.image.RescaleOp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * SalesDetails Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>一月 1, 2018</pre>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesDetailsTest {

    static SalesDetails salesDetails;
    static SalesDetailsInput salesDetailsInput;

    @BeforeClass
    public static void setUp() throws Exception {
        AppTest appTest = new AppTest();
        salesDetails = new SalesDetails();

        SalesBLService salesBLService = SalesBLFactory.getSalesBLService();

        GoodsItemVO goodsItemVO = new GoodsItemVO("02000001","地平线牌欧式落地灯","DPX-0001",2,200,"无");
        ArrayList<GoodsItemVO> goodsItemVOS = new ArrayList<>();
        goodsItemVOS.add(goodsItemVO);

        SalesVO vo = new SalesVO(BillType.SALES, BillState.PASS, salesBLService.getnewSalesID(),
                "路人甲", "00000001", "朱雨欣", "朱雨欣", "默认仓库",
                goodsItemVOS, 0, 0, "无", "2018-01-01", null);

        salesBLService.addSales(vo);

        salesDetailsInput = new SalesDetailsInput("2017-01-01","2018-01-01",null,null);
    }

    @Test
    public void testGetSalesDetails() throws Exception {
        ArrayList<SalesDetailVO> salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(1,salesDetailVOS.size());
    }


    @Test
    public void testSearchByInventory() throws Exception {
        salesDetailsInput.filterType = FilterType.INVENTORY;
        salesDetailsInput.keyword = "默认";
        ArrayList<SalesDetailVO> salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(1,salesDetailVOS.size());

        salesDetailsInput.keyword = "鼓楼仓库";
        salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(0,salesDetailVOS.size());

    }


    @Test
    public void testSearchByCustomer() throws Exception {
        salesDetailsInput.filterType = FilterType.CUSTOMER;
        salesDetailsInput.keyword = "甲";
        ArrayList<SalesDetailVO> salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(1,salesDetailVOS.size());

        salesDetailsInput.keyword = "客户";
        salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(0,salesDetailVOS.size());
    }


    @Test
    public void testSearchBySalesman() throws Exception {
        salesDetailsInput.filterType = FilterType.SALESMAN;
        salesDetailsInput.keyword = "朱雨欣";
        ArrayList<SalesDetailVO> salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(1,salesDetailVOS.size());

        salesDetailsInput.keyword = "仲于芯";
        salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(0,salesDetailVOS.size());
    }


    @Test
    public void testSearchByGoodsName() throws Exception {
        salesDetailsInput.filterType = FilterType.GOODS;
        salesDetailsInput.keyword = "落地灯";
        ArrayList<SalesDetailVO> salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(1,salesDetailVOS.size());

        salesDetailsInput.keyword = "台灯";
        salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(0,salesDetailVOS.size());
    }


    @Test
    public void testGetAllSalesDetails() throws Exception {
        ArrayList<SalesDetailVO> salesDetailVOS = salesDetails.getSalesDetails(salesDetailsInput);
        assertEquals(1,salesDetailVOS.size());
    }


    @Test
    public void testExport() throws Exception {
        ResultMessage re = salesDetails.export("C:/Users/Dell/git/LampManager","销售明细表Test",salesDetails.getAllSalesDetails());
        assertEquals(ResultMessage.SUCCESS,re);
    }


} 
