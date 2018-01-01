package bl.promotionbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.Level;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PromotionCustomerTest {

	PromotionCustomer promotionCustomer;
	PromotionCustomerVO promotionCustomerVO;
	String newPromotionID = "";
	
	@Before
	public void setUp() throws Exception {
		AppTest appTest = new AppTest();
	}
	
	@Before
    public void before() throws Exception{
        promotionCustomer = new PromotionCustomer();
        promotionCustomerVO = new PromotionCustomerVO("会员促销策略","PC-1", LocalDate.now().toString(), LocalDate.now().plusWeeks(1).toString(), 
        		100, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
    }
	
	@Test
	public void a_testGetNewPromotionCustomerID() throws RemoteException {
		newPromotionID = "PC-1";
		assertEquals(newPromotionID, promotionCustomer.getNewPromotionCustomerID());
	}
	
	@Test
	public void b_testSubmit() throws RemoteException {
		ResultMessage re = promotionCustomer.submit(promotionCustomerVO);
		assertEquals(ResultMessage.SUCCESS, re);
	}

	@Test
	public void c_testShow() throws RemoteException {
		ArrayList<PromotionCustomerVO> promotions = promotionCustomer.show();
		assertEquals(1, promotions.size());
	}

	@Test
	public void d_testFindPromotionByName() throws RemoteException {
		PromotionCustomerVO actual = promotionCustomer.findPromotionByName("会员促销策略");
		assertEquals(promotionCustomerVO.promotionID, actual.promotionID);
	}

	@Test
	public void e_testFindPromotionByID() throws RemoteException {
		PromotionCustomerVO actual = promotionCustomer.findPromotionByID("PC-1");
		assertEquals(promotionCustomerVO.promotionName, actual.promotionName);
	}

	@Test
	public void f_testUpdatePromotion() throws RemoteException {
		PromotionCustomerVO expected = new PromotionCustomerVO("会员元旦回馈策略","PC-1", LocalDate.now().toString(), LocalDate.now().plusWeeks(1).toString(), 
        		100, 0, new ArrayList<GoodsItemVO>(), Level.LEVEL_THREE);
		ResultMessage resultMessage = promotionCustomer.updatePromotion(expected);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}
	
	@Test
	public void g_testDeletePromotion() throws RemoteException {
		ResultMessage resultMessage = promotionCustomer.deletePromotion("PC-1");
		assertEquals(ResultMessage.SUCCESS, resultMessage);
		
		resultMessage = promotionCustomer.deletePromotion("PC-100");
		assertEquals(ResultMessage.FAILED, resultMessage);
	}
	
}
