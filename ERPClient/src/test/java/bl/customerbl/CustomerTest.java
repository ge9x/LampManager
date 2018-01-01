package bl.customerbl;

import static org.junit.Assert.assertEquals;

import java.security.spec.ECFieldF2m;
import java.util.ArrayList;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.CustomerCategory;
import util.Level;
import util.ResultMessage;
import vo.CustomerVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {
	
	Customer customer;
	CustomerVO customerVO1;
	CustomerVO customerVO;
	
    @BeforeClass
	public  static void setUp() throws Exception {
    	AppTest appTest = new AppTest();
	}

    @Before
    public void before() throws Exception{
    	customer = new Customer();
	    ArrayList<CustomerVO> customerVOs = customer.show();
	    if (!customerVOs.isEmpty())
	       customerVO = customerVOs.get(customerVOs.size()-1);
	    }
    
    @Test
    public void a_testAddCustomer() throws Exception{
    	CustomerVO customerVO=new CustomerVO("00000001",CustomerCategory.SELLER,Level.LEVEL_FIVE,"仲于芯大金主","15545786610",
				"南京仙林大学城","421000","ddl@163.com",500000000,0.0,0.0,"zlk",125.0,500);
    	ResultMessage res=customer.addCustomer(customerVO);
    	assertEquals(ResultMessage.SUCCESS, res);
    }
    
    @Test
    public void b_testAddCustomer() throws Exception{
    	CustomerVO customerVO=new CustomerVO("00000002",CustomerCategory.PUR_AGENT,Level.LEVEL_ONE,"Bobule","15247678373",
				"南京新街口","421001","dds@163.com",0.0,0.0,0.0,"aster",224.0,600);
    	ResultMessage res=customer.addCustomer(customerVO);
    	assertEquals(ResultMessage.SUCCESS, res);
    }
    
    @Test
    public void c_testFindCustomerById() throws Exception{
    	ArrayList<CustomerVO> customerList=customer.findCustomerByCustomerID("001");
    	assertEquals(1, customerList.size());
    }
    
    @Test
    public void d_testFindCustomerByKeywords() throws Exception{
    	ArrayList<CustomerVO> customerList=customer.findCustomerByKeywords("163");
    	assertEquals(2, customerList.size());
    }
    
    @Test
    public void e_testFindCustomerByKeywords() throws Exception{
    	ArrayList<CustomerVO> customerList=customer.findCustomerByKeywords("金主");
    	assertEquals(1, customerList.size());
    }
    
    @Test
    public void f_testUpdateCustomer() throws Exception{
    	customerVO.customerName="zlk_Bobule";
    	ResultMessage res=customer.updateCustomer(customerVO);
    	assertEquals(ResultMessage.SUCCESS, res);
    }
    
    @Test
    public void g_testShowCustomer() throws Exception{
    	ArrayList<CustomerVO> customerList=customer.show();
    	assertEquals(2, customerList.size());
    }
    
    @Test
    public void h_testGetAllCustomerID() throws Exception{
    	ArrayList<Integer> expected=new ArrayList<>();
    	expected.add(1);
    	expected.add(2);
    	ArrayList<Integer> actual=customer.getAllCustomerID();
    	assertEquals(expected, actual);
    }
    
    @Test
    public void i_testGetAllCustomerName() throws Exception{
    	ArrayList<String> expected=new ArrayList<>();
    	expected.add("仲于芯大金主");
    	expected.add("zlk_Bobule");
    	ArrayList<String> actual=customer.getAllCustomerName();
    	assertEquals(expected, actual);
    }
    
	@Test
    public void k_testRaiseCustomerReceive() throws Exception{
    	customer.raiseCustomerReceive(2,2000);
    	ArrayList<CustomerVO> cusList=customer.show();
    	int receive=(int)cusList.get(1).receive;
    	assertEquals("2000", Integer.toString(receive));
    }
	
	@Test
	public void l_testReduceCustomerReceive() throws Exception{
		customer.reduceCustomerReceive(2, 1000);
		ArrayList<CustomerVO> cusList=customer.show();
    	int receive=(int)cusList.get(1).receive;
    	assertEquals("1000", Integer.toString(receive));
	}
	
	@Test
	public void m_testtRaiseCustomerPay() throws Exception{
		customer.raiseCustomerPay(1, 2000);
		ArrayList<CustomerVO> cusList=customer.show();
		int pay=(int)cusList.get(0).pay;
		assertEquals("2000", Integer.toString(pay));
	}
	
	@Test
	public void n_testReduceCustomerPay() throws Exception{
		customer.reduceCustomerPay(1, 1000);
		ArrayList<CustomerVO> cusList=customer.show();
		int pay=(int)cusList.get(0).pay;
		assertEquals("1000", Integer.toString(pay));
	}
	
}
