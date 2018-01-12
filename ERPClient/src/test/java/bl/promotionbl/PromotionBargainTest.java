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

import bl.accountbl.Account;
import bl.examinationbl.Examination;
import util.ResultMessage;
import vo.AccountVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PromotionBargainTest {
	
	PromotionBargain promotionBargain;
	PromotionBargainVO promotionBargainVO;
	String newPromotionID = "";

	@Before
	public void setUp() throws Exception {
		AppTest appTest = new AppTest();
	}
	
	@Before
    public void before() throws Exception{
        promotionBargain = new PromotionBargain();
        promotionBargainVO = new PromotionBargainVO("特价包策略","PB-1", 0, 0, LocalDate.now().toString(), 
        		LocalDate.now().plusWeeks(1).toString(), new ArrayList<GoodsItemVO>());
    }
	
	@Test
	public void a_testGetNewPromotionBargainID() throws RemoteException {
		newPromotionID = "PB-1";
		assertEquals(newPromotionID , promotionBargain.getNewPromotionBargainID());
	}

	@Test
	public void b_testSubmit() throws RemoteException {
		ResultMessage re = promotionBargain.submit(promotionBargainVO);
		assertEquals(ResultMessage.SUCCESS, re);
	}
	
	@Test
	public void c_testShow() throws RemoteException {
		ArrayList<PromotionBargainVO> promotions = promotionBargain.show();
		assertEquals(1, promotions.size());
	}

	@Test
	public void d_testFindPromotionByName() throws RemoteException {
		PromotionBargainVO actual = promotionBargain.findPromotionByName("特价包策略");
		assertEquals(promotionBargainVO.promotionID, actual.promotionID);
	}

	@Test
	public void e_testFindPromotionByID() throws RemoteException {
		PromotionBargainVO actual = promotionBargain.findPromotionByID("PB-1");
		assertEquals(promotionBargainVO.promotionName, actual.promotionName);
	}
	
	@Test
	public void f_testUpdatePromotion() throws RemoteException {
		PromotionBargainVO expected = new PromotionBargainVO("周末特价包","PB-1", 0, 0, LocalDate.now().toString(), 
				LocalDate.now().plusWeeks(1).toString(), new ArrayList<GoodsItemVO>());
		ResultMessage re = promotionBargain.updatePromotion(expected);
		assertEquals(ResultMessage.SUCCESS, re);
	}

}
