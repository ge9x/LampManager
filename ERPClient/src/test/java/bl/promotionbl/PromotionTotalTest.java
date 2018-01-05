package bl.promotionbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PromotionTotalTest {
	
	PromotionTotal promotionTotal;
	PromotionTotalVO promotionTotalVO;
	String newPromotionID = "";

	@Before
	public void setUp() throws Exception {
		AppTest appTest = new AppTest();
	}
	
	@Before
    public void before() throws Exception{
        promotionTotal = new PromotionTotal();
        promotionTotalVO = new PromotionTotalVO("总价促销策略","PT-1", LocalDate.now().toString(), LocalDate.now().plusWeeks(1).toString(), 1000, 
        		new ArrayList<GoodsItemVO>(), 0);
    }
	
	@Test
	public void a_testGetNewPromotionTotalID() throws RemoteException {
		newPromotionID = "PT-1";
		assertEquals(newPromotionID, promotionTotal.getNewPromotionTotalID());
	}
	
	@Test
	public void b_testSubmit() throws RemoteException {
		ResultMessage re = promotionTotal.submit(promotionTotalVO);
		assertEquals(ResultMessage.SUCCESS, re);
	}

	@Test
	public void c_testShow() throws RemoteException {
		ArrayList<PromotionTotalVO> promotions = promotionTotal.show();
		assertEquals(1, promotions.size());
	}

	@Test
	public void d_testFindPromotionByName() throws RemoteException {
		PromotionTotalVO actual = promotionTotal.findPromotionByName("总价促销策略");
		assertEquals(promotionTotalVO.promotionID, actual.promotionID);
	}

	@Test
	public void e_testFindPromotionByID() throws RemoteException {
		PromotionTotalVO actual = promotionTotal.findPromotionByID("PT-1");
		assertEquals(promotionTotalVO.promotionName, actual.promotionName);
	}
	
	@Test
	public void f_testUpdatePromotion() throws RemoteException {
		PromotionTotalVO expected = new PromotionTotalVO("满减活动","PT-1", LocalDate.now().toString(), LocalDate.now().plusWeeks(1).toString(), 
				1000, new ArrayList<GoodsItemVO>(), 0);
		ResultMessage re = promotionTotal.updatePromotion(expected);
		assertEquals(ResultMessage.SUCCESS, re);
	}

	@Test
	public void g_testDeletePromotion() throws RemoteException {
		ResultMessage resultMessage = promotionTotal.deletePromotion("PT-1");
		assertEquals(ResultMessage.SUCCESS, resultMessage);
		
		resultMessage = promotionTotal.deletePromotion("PT-100");
		assertEquals(ResultMessage.FAILED, resultMessage);
	}

}
