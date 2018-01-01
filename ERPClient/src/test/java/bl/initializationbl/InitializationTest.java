package bl.initializationbl;

import bl.accountbl.AccountBLFactory;
import bl.goodsbl.GoodsBLFactory;
import blservice.accountblservice.AccountBLService;
import blservice.goodsblservice.GoodsBLService;
import org.ERPClient.AppTest;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import po.InitializationPO;
import util.ResultMessage;
import vo.AccountVO;
import vo.GoodsVO;
import vo.InitAccountVO;
import vo.InitializationVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Initialization Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>一月 1, 2018</pre>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InitializationTest {
    static Initialization initialization;
    @BeforeClass
    public static void setUp() throws Exception {
        AppTest appTest = new AppTest();
        initialization = new Initialization();
        GoodsBLService goodsBLService = GoodsBLFactory.getBLService();
        AccountBLService accountBLService = AccountBLFactory.getBLService();

        AccountVO accountVO = new AccountVO("01","地平线银行", 200000);
        accountBLService.addAccount(accountVO);

        GoodsVO goodsVO = new GoodsVO("02000001", "地平线牌欧式落地灯", "DPX-0001", "落地灯", "默认仓库", 1, 20, 250, 255, 250, 255);
        goodsBLService.add(goodsVO);

    }

    @Test
    public void a_testInit() throws Exception {
        ResultMessage re = initialization.init();
        assertEquals(ResultMessage.SUCCESS,re);
    }

    @Test
    public void b_testGetAllInitDate() throws Exception {
        ArrayList<String> dates = initialization.getAllInitDate();
        assertEquals(1,dates.size());
    }


    @Test
    public void c_testGetRecentInitDate() throws Exception {
        String date = initialization.getRecentInitDate();
        assertEquals("2018-01-01",date);
    }

    @Test
    public void d_testShow() throws Exception {
        InitializationVO initializationVO = initialization.show("2018-01-01");
        assertEquals("2018-01-01",initializationVO);
    }


} 
