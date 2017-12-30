package bl.accountbl;

import bl.financialbl.AccountBill;
import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Account Tester.
 *
 * @author Kry·L
 * @since <pre>十二月 30, 2017</pre>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountTest {

    Account account;
    AccountVO accountVO;

    @BeforeClass
    public  static void setUp() throws Exception {
        AppTest appTest = new AppTest();
    }

    @Before
    public void before() throws Exception{
        account = new Account();
        ArrayList<AccountVO> accountVOS = account.show();
        if (!accountVOS.isEmpty())
            accountVO = accountVOS.get(accountVOS.size()-1);
    }

    @Test
    public void a_testAddAccount() throws Exception {
        AccountVO accountVO = new AccountVO("01","地平线银行", 200000);
        ResultMessage re = account.addAccount(accountVO);
        assertEquals(ResultMessage.SUCCESS, re);
    }



    @Test
    public void b_testFindAccountByName() throws Exception {
        ArrayList<AccountVO> accountVOS = account.findAccountByName("地平线");
        assertEquals(1, accountVOS.size());
    }


    @Test
    public void c_testUpdateAccount() throws Exception {
        accountVO.accountName = "地平线世界银行";
        ResultMessage re = account.updateAccount(accountVO);
        assertEquals(ResultMessage.SUCCESS,re);
    }

    @Test
    public void d_testShow() throws Exception {
        ArrayList<AccountVO> accountVOS = account.show();
        assertEquals(1,accountVOS.size());
    }

    @Test
    public void e_testGetAllAccountName() throws Exception {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("地平线世界银行");
        ArrayList<String> actual = account.getAllAccountName();
        assertEquals(expected,actual);
    }

    @Test
    public void f_testGetAccountByID() throws Exception {
        AccountVO vo = account.getAccountByID(accountVO.accountID);
        assertEquals(accountVO,vo);
    }


    @Test
    public void g_testChangeMoney() throws Exception {
        ResultMessage re = account.changeMoney(accountVO.accountID,2000);
        assertEquals(ResultMessage.SUCCESS,re);
        re = account.changeMoney(accountVO.accountID,-300000);
        assertEquals(ResultMessage.INSUFFICIENT,re);
    }

    @Test
    public void h_testDeleteAccount() throws Exception {
        ResultMessage re = account.deleteAccount(accountVO.accountID);
        assertEquals(ResultMessage.SUCCESS, re);
    }


} 
