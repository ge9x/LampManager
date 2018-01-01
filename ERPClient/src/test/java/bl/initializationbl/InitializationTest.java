package bl.initializationbl;

import bl.accountbl.AccountBLFactory;
import bl.classificationbl.Classification;
import bl.classificationbl.ClassificationBLFactory;
import bl.customerbl.CustomerBLFactory;
import bl.goodsbl.GoodsBLFactory;
import blservice.accountblservice.AccountBLService;
import blservice.classificationblservice.ClassificationBLService;
import blservice.customerblservice.CustomerBLService;
import blservice.goodsblservice.GoodsBLService;
import org.ERPClient.AppTest;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.*;

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
        CustomerBLService customerBLService = CustomerBLFactory.getBLService();
        ClassificationBLService classificationBLService = ClassificationBLFactory.getBLService();

        String classificationID = classificationBLService.getNewID();
        ClassificationVO classificationVO = new ClassificationVO(classificationID,"落地灯",null,null,null);
        AccountVO accountVO = new AccountVO("01","地平线银行", 200000);
        accountBLService.addAccount(accountVO);

        GoodsVO goodsVO = new GoodsVO(goodsBLService.getNewID("02"), "地平线牌欧式落地灯", "DPX-0001", "落地灯", "默认仓库", 1, 20, 250, 255, 250, 255);
        goodsBLService.add(goodsVO);

        CustomerVO customerVO = new CustomerVO(customerBLService.getNewCustomerID(), CustomerCategory.SELLER, Level.LEVEL_FIVE, "路人甲", "15545786610",
                "南京仙林大学城", "421000", "ddl@163.com", 1.0, 10000.0, 0.0, "朱雨欣", 125.0, 600);
        customerBLService.addCustomer(customerVO);

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
        assertNotNull(initializationVO);
    }


} 
