package bl.accountbl;

import blservice.accountblservice.AccountBLService;
import blservice.accountblservice.AccountInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * AccountBLFactory Tester.
 *
 * @author Kry·L
 * @version 1.0
 * @since <pre>一月 1, 2018</pre>
 */
public class AccountBLFactoryTest {

    @Before
    public void before() throws Exception {
    }


    @Test
    public void testGetBLService() throws Exception {
        AccountBLService accountBLService = AccountBLFactory.getBLService();
        assertNotNull(accountBLService);
    }


    @Test
    public void testGetInfo() throws Exception {
        AccountInfo accountInfo = AccountBLFactory.getInfo();
        assertNotNull(accountInfo);
    }


} 
