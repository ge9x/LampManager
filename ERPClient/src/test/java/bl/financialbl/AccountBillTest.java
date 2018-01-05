package bl.financialbl;

import bl.accountbl.Account;
import bl.accountbl.AccountTest;
import com.sun.org.apache.regexp.internal.RE;
import org.ERPClient.AppTest;
import org.junit.*;
import org.junit.runners.MethodSorters;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.AccountVO;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * AccountBill Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>十二月 30, 2017</pre>
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountBillTest {
    static AccountBill accountBill;
    static AccountBillVO receipt;
    static AccountBillVO payment;

    @BeforeClass
    public static void setUp() throws Exception {
        AppTest appTest = new AppTest();
        accountBill = new AccountBill();

        ArrayList<AccountBillItemVO> items = new ArrayList<>();
        AccountVO accountVO = new AccountVO("01","地平线银行", 200000);
        AccountBillItemVO accountBillItemVO = new AccountBillItemVO(accountVO,200,"收款");
        items.add(accountBillItemVO);
        receipt = new AccountBillVO(LocalDate.now().toString(),accountBill.getNewReceiptID(),BillState
                .SUBMITTED, BillType.RECEIPT, "00000001","乐盛捷",items);
        payment = new AccountBillVO(LocalDate.now().toString(),accountBill.getNewPaymentID(),BillState.DRAFT, BillType.PAYMENT,"00000001","乐盛捷",items);
    }


    @Test
    public void a_testGetNewReceiptID() throws Exception {
        String ID = accountBill.getNewReceiptID();
        assertNotNull(ID);
    }


    @Test
    public void b_testGetNewPaymentID() throws Exception {
        String ID = accountBill.getNewPaymentID();
        assertNotNull(ID);
    }


    @Test
    public void c_testSubmit() throws Exception {
        ResultMessage re = accountBill.submit(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void d_testSave() throws Exception {
        ResultMessage re = accountBill.save(payment);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void e_testUpdate() throws Exception {
        receipt.customerID = "00000002";
        receipt.state = BillState.PASS;
        ResultMessage re = accountBill.update(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void f_testGetReceiptsByState() throws Exception {
        ArrayList<AccountBillVO> accountBillVOS = accountBill.getReceiptsByState(BillState.PASS);
        assertEquals(1,accountBillVOS.size());
    }


    @Test
    public void g_testGetPaymentsByState() throws Exception {
        ArrayList<AccountBillVO> accountBillVOS = accountBill.getPaymentsByState(BillState.DRAFT);
        assertEquals(1,accountBillVOS.size());
    }


    @Test
    public void h_testExamine() throws Exception {
        ResultMessage re = accountBill.examine(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }

    @Test
    public void i_testGetBillsByDate() throws Exception {
        ArrayList<AccountBillVO> accountBillVOS = accountBill.getBillsByDate(LocalDate.of(2017,12,20).toString(),LocalDate.now().toString());
        assertEquals(1,accountBillVOS.size());
    }


    @Test
    public void j_testRedCover() throws Exception {
        ResultMessage re = accountBill.redCover(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }


    @Test
    public void k_testRedCoverAndCopy() throws Exception {
        ResultMessage re = accountBill.redCoverAndCopy(receipt);
        assertEquals(ResultMessage.SUCCESS,re);
    }


} 
