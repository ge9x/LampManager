package bl.salesbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PurchaseVO;

public class PurchaseTest {
	static Purchase purchase;
	static PurchaseVO pur;
	static GoodsItemVO gi;
	static ArrayList<GoodsItemVO> goodsItemList;
	
	@BeforeClass
	public  static void setUp() throws Exception {
    	AppTest appTest = new AppTest();
	}

	@Before
	public void before() throws Exception {
		purchase=new Purchase();
		goodsItemList=new ArrayList<>();
		gi=new GoodsItemVO("01", "霓虹灯",null ,20, 35.0,
				"耐用");
		goodsItemList.add(gi);
		pur=new PurchaseVO(BillType.PURCHASE,BillState.PASS,"JHD-20180101-00001","供应商1"
				,"00000002","默认仓库","zlk",goodsItemList,"满足客户需求"
			     ,LocalDate.now().toString());
	}
	
	@Test
	public void testSubmitPurchase() throws RemoteException {
		ResultMessage resultMessage=purchase.submitPurchase(pur);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testSavePurchase() throws RemoteException {
		pur.ID="JHD-20180101-00002";
		ResultMessage resultMessage=purchase.savePurchase(pur);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testFindPurchaseByID() throws RemoteException {
		PurchaseVO purchaseVO=purchase.findPurchaseByID("JHD-20180101-00001");
		pur.state=BillState.PASS;
		assertEquals(pur.state, purchaseVO.state);
		assertEquals(pur.customerID, "0000000"+purchaseVO.customerID);
	}

	@Test
	public void testFindPurchaseByState() throws RemoteException {
		ArrayList<String> expected=new ArrayList<>();
		ArrayList<String> actual=new ArrayList<>();
		ArrayList<PurchaseVO> purexpected=new ArrayList<>();
		pur.state=BillState.SUBMITTED;
		purexpected.add(pur);
		expected.add(pur.ID);
		ArrayList<PurchaseVO> puractual=purchase.findPurchaseByState(BillState.SUBMITTED);
		actual.add(puractual.get(0).ID);
		assertEquals("JHTHD-20180101-00002", actual.get(0));
	}

	@Test
	public void testUpdatePurchase() throws RemoteException {
		pur.supplier="zlk_Bobule";
		ResultMessage res=purchase.updatePurchase(pur);
		assertEquals(ResultMessage.SUCCESS, res);
	}

	@Test
	public void testDeletePurchase() throws RemoteException {
		ResultMessage resultMessage=purchase.deletePurchase(pur);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testAddPurchase() throws NumberFormatException, RemoteException {
		PurchaseVO purchaseVO=new PurchaseVO(BillType.RETURN,BillState.SUBMITTED,"JHTHD-20180101-00002","供应商2"
				,"00000002","默认仓库","aster",goodsItemList,"好看"
				,LocalDate.now().toString());
		ResultMessage resultMessage=purchase.addPurchase(purchaseVO);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testAddGoodsItem() throws RemoteException {
		GoodsItemVO gi2=new GoodsItemVO("02", "挂灯",null, 10, 35.0,
				"好看");
		ResultMessage resultMessage=purchase.addGoodsItem(gi2);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}


	@Test
	public void testGetnewReturnID() throws RemoteException {
		String string=purchase.getnewReturnID();
		assertEquals("JHTHD-20180101-00012", string);
	}

	@Test
	public void testGetPurchaseByDate() throws RemoteException, ParseException {
		ArrayList<String> expectedstr=new ArrayList<>();
		ArrayList<String> actualstr=new ArrayList<>();
		expectedstr.add("JHD-20180101-00001");
		PurchaseVO pur1=new PurchaseVO(BillType.PURCHASE,BillState.PASS,"JHD-20180101-00001","供应商1"
				,"00000001","默认仓库","zlk",goodsItemList,"满足客户需求"
			     ,LocalDate.now().toString());
		purchase.addPurchase(pur1);
		ArrayList<PurchaseVO> actual=purchase.getPurchaseByDate("2017-12-22", "2018-01-02");
		actualstr.add(actual.get(0).ID);
		assertEquals(expectedstr,actualstr);
	}

	@Test
	public void testGetPurchaseOrderByState() throws RemoteException {
		ArrayList<PurchaseVO> expected=new ArrayList<>();
		ArrayList<PurchaseVO> actual=purchase.getPurchaseOrderByState(BillState.PASS);
		assertEquals("1", actual.get(0).customerID);
	}

	@Test
	public void testGetReturnOrderByState() throws RemoteException {
		ArrayList<PurchaseVO> expected=new ArrayList<>();
		ArrayList<PurchaseVO> actual=purchase.getPurchaseOrderByState(BillState.PASS);
		assertEquals("2018-01-01", actual.get(0).date);
		}

	@Test
	public void testGetPurchaseByDateAndInventory() throws RemoteException {
		ArrayList<PurchaseVO> expected=new ArrayList<>();
		ArrayList<PurchaseVO> actual=purchase.getPurchaseOrderByState(BillState.PASS);
		assertEquals("JHD-20180101-00001", actual.get(0).ID);
	}

	@Test
	public void testRedCover() throws NumberFormatException, RemoteException {
		ResultMessage res=purchase.redCover(pur);
		assertEquals(ResultMessage.SUCCESS, res);
	}

	@Test
	public void testRedCoverAndCopy() throws NumberFormatException, RemoteException {
		ResultMessage res=purchase.redCoverAndCopy(pur);
		assertEquals(ResultMessage.SUCCESS, res);
	}

}
