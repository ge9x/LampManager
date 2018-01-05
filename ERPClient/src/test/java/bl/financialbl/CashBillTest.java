package bl.financialbl;

import org.ERPClient.AppTest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.CashBillItemVO;
import vo.CashBillVO;

import java.awt.geom.RectangularShape;
import java.rmi.server.RemoteServer;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * CashBill Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>一月 1, 2018</pre>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CashBillTest {

    static CashBill cashBill;
    static CashBillVO cashBillVO;

    @BeforeClass
    public static void setUp() throws Exception {
        AppTest appTest = new AppTest();

        cashBill = new CashBill();
        CashBillItemVO cashBillItemVO = new CashBillItemVO("打车", 200, "报销");
        ArrayList<CashBillItemVO> cashBillItemVOS = new ArrayList<>();
        cashBillItemVOS.add(cashBillItemVO);
        cashBillVO = new CashBillVO(LocalDate.now().toString(), cashBill.getNewCashBillID(), BillState.SUBMITTED, BillType.CASH, "乐盛捷", "01", cashBillItemVOS, 200);
    }

    @Test
    public void a_testGetNewCashBillID() throws Exception {
        String ID = cashBill.getNewCashBillID();
        assertNotNull(ID);
    }


    @Test
    public void b_testSubmit() throws Exception {
        ResultMessage re = cashBill.submit(cashBillVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void c_testSave() throws Exception {
        cashBillVO.ID = cashBill.getNewCashBillID();
        cashBillVO.state = BillState.DRAFT;
        ResultMessage re = cashBill.save(cashBillVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void d_testUpdate() throws Exception {
        cashBillVO.state = BillState.PASS;
        ResultMessage re = cashBill.update(cashBillVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void e_testExamine() throws Exception {
        ResultMessage re = cashBill.examine(cashBillVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void f_testGetCashBillByState() throws Exception {
        ArrayList<CashBillVO> cashBillVOS = cashBill.getCashBillByState(BillState.PASS);
        assertEquals(1,cashBillVOS.size());
    }


    @Test
    public void g_testGetBillsByDate() throws Exception {
        ArrayList<CashBillVO> cashBillVOS = cashBill.getBillsByDate("2017-12-25","2018-01-01");
        assertEquals(1,cashBillVOS.size());
    }


    @Test
    public void h_testRedCover() throws Exception {
        ResultMessage re = cashBill.redCover(cashBillVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void i_testRedCoverAndCopy() throws Exception {
        ResultMessage re = cashBill.redCoverAndCopy(cashBillVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }

    @Test
    public void j_testDeleteBill() throws Exception {
        ResultMessage re = cashBill.deleteBill(cashBillVO.ID);
        assertEquals(ResultMessage.SUCCESS,re);
    }



} 
