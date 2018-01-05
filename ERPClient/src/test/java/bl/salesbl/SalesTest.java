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
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.SalesVO;

public class SalesTest {
	
	static Sales sales;
	static SalesVO sal;
	static GoodsItemVO gi;
	static ArrayList<GoodsItemVO> goodsItemList;
	
	@BeforeClass
	public  static void setUp() throws Exception {
    	AppTest appTest = new AppTest();
	}

	@Before
	public void before() throws Exception {
		sales=new Sales();
		goodsItemList=new ArrayList<>();
		gi=new GoodsItemVO("01", "霓虹灯",null ,20, 35.0,
				"耐用");
		goodsItemList.add(gi);
		sal=new SalesVO(BillType.SALES, BillState.SUBMITTED, "XSD-20180101-00001", "销售商1", "00000002",
				"阿强","业务员", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
	}

	
	@Test
	public void testAddSales() throws RemoteException {
		SalesVO salesVO=new SalesVO(BillType.SALES, BillState.DRAFT, "XSD-20180101-00001", "销售商1", "00000002",
				"阿强","业务员", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
		sales.addSales(salesVO);
		ResultMessage resultMessage=sales.addSales(sal);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testGetAllSalesOrder() throws RemoteException, ParseException {
		ArrayList<SalesVO> actual=sales.getAllSalesOrder("2017-10-10", "2018-01-02");
		assertEquals("2018-01-01", actual.get(0).date);
	}

	@Test
	public void testGetAllSalesReturnOrder() throws ParseException {
		ArrayList<SalesVO> actual=sales.getAllSalesReturnOrder("2017-10-10", "2018-01-02");
		assertEquals("2018-01-01", actual.get(0).date);
	}


	@Test
	public void testGetAllSubmittedSales() throws RemoteException {
		ArrayList<SalesVO> actual=sales.getAllSubmittedSales();
		assertEquals("2018-01-01", actual.get(0).date);
	}

	@Test
	public void testAddGoodsItem() throws RemoteException {
		ResultMessage resultMessage=sales.addGoodsItem(gi);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testSubmitSales() throws RemoteException {
		ResultMessage resultMessage=sales.submitSales(sal);
		assertEquals(ResultMessage.FAILED, resultMessage);
	}

	@Test
	public void testSaveSales() throws RemoteException {
		ResultMessage resultMessage=sales.saveSales(sal);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testGetAllSupplier() {
		ArrayList<CustomerVO> name=new ArrayList<>();
		name=sales.getAllSupplier();
		assertEquals("00000002", name.get(0).customerID);
	}

	@Test
	public void testGetAllCustomer() {
		ArrayList<CustomerVO> name=new ArrayList<>();
		name=sales.getAllCustomer();
		assertEquals("00000005", name.get(0).customerID);
	}

	@Test
	public void testGetnewSalesID() throws RemoteException{
		String str=sales.getnewSalesID();
		assertEquals("XSD-20180101-00015", str);
	}

	@Test
	public void testGetnewSalesReturnID() throws RemoteException{
		String str=sales.getnewSalesReturnID();
		assertEquals("XSTHD-20180101-00006", str);
	}

	@Test
	public void testGetSalesOrderByState() throws RemoteException{
		SalesVO salesVO=new SalesVO(BillType.SALESRETURN, BillState.DRAFT, "XSD-20180101-00001", "销售商1", "00000001",
				"阿强","业务员", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
		sales.addSales(salesVO);
		ArrayList<SalesVO> salesVOs=sales.getSalesOrderByState(BillState.PASS);
		assertEquals("XSD-20180101-00003", salesVOs.get(0).ID);
	}

	@Test
	public void testGetSalesreturnOrderByState() throws RemoteException {
		SalesVO salesVO=new SalesVO(BillType.SALESRETURN, BillState.PASS, "XSD-20180101-00001", "销售商1", "00000001",
				"阿强","业务员", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
		sales.addSales(salesVO);
		ArrayList<SalesVO> salesVOs=sales.getSalesreturnOrderByState(BillState.PASS);
		assertEquals("XSTHD-20180101-00001", salesVOs.get(0).ID);
	}

	@Test
	public void testDeleteSalesSalesVO() throws RemoteException {
		ResultMessage resultMessage=sales.saveSales(sal);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testGetSalesByDateAndInventory() throws RemoteException, ParseException {
		ArrayList<SalesVO> salesVOs=sales.getSalesByDateAndInventory("2017-12-23", "2018-02-02","默认仓库", BillType.SALES);
		assertEquals("XSTHD-20180101-00001", salesVOs.get(0).ID);
	}
	
//	@Test
//	public void testDeleteSales() throws RemoteException {
//		SalesVO salesVO=new SalesVO(BillType.SALESRETURN, BillState.PASS, "XSD-20190101-00001", "销售商1", "00000001",
//				"阿强","业务员", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
//		sales.addSales(salesVO);
//		ResultMessage resultMessage=sales.deleteSales(sal);
//		assertEquals(ResultMessage.SUCCESS, resultMessage);
//	}
	
	@Test
	public void testUpdateSales() throws RemoteException {
		SalesVO salesVO=new SalesVO(BillType.SALESRETURN, BillState.PASS, "XSD-20190101-00001", "销售商1", "00000001",
				"阿强","业务员", "默认仓库",goodsItemList , 100,500,  "满足客户需求", LocalDate.now().toString(),"PC-1");
		sales.addSales(salesVO);
		ResultMessage resultMessage=sales.updateSales(sal);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testRedCover() throws RemoteException{
		ResultMessage resultMessage=sales.redCover(sal);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void testRedCoverAndCopy() throws RemoteException{
		ResultMessage resultMessage=sales.redCoverAndCopy(sal);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

}
