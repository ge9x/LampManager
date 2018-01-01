package bl.inventorybl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.ERPClient.AppTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bl.goodsbl.GoodsBLFactory;
import blservice.goodsblservice.GoodsBLService;
import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryPO;
import rmi.InventoryRemoteHelper;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryBillVO;

/**
 * 约定：启动前数据库中：<br>
 * 只有一条ID为1，名字为“灯”的商品分类数据<br>
 * 有一条名字为“默认仓库”的仓库数据<br>
 * 没有任何库存类单据(InventoryBill)数据<br>
 * Created on 2018/1/1
 * 
 * @author 巽
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryBillTest {
	private InventoryBill inventoryBill;
	private static InventoryBillVO overflow;
	private static InventoryBillVO loss;
	private static GoodsVO goodsVO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new AppTest();
		GoodsBLService goods = GoodsBLFactory.getBLService();
		goodsVO = new GoodsVO(goods.getNewID("01"), "SJ牌欧洲奢华落地灯", "SJ-0001", "灯", 0, 7, 233, 250, 233, 250);
		goods.add(goodsVO);
		String date = LocalDate.now().toString();
		HashMap<GoodsVO, Integer> goodsMap = new HashMap<>();
		goodsMap.put(goodsVO, 7);
		overflow = new InventoryBillVO("BYD-" + date.replaceAll("-", "") + "-00001", BillType.OVERFLOW,
				BillState.DRAFT, date, "默认仓库", "InventoryBillTest", goodsMap);
		goodsMap = new HashMap<>();
		goodsMap.put(goodsVO, 6);
		loss = new InventoryBillVO("BSD-" + date.replaceAll("-", "") + "-00001", BillType.LOSS, BillState.DRAFT, date,
				"默认仓库", "InventoryBillTest", goodsMap);
	}

	@Before
	public void setUp() throws Exception {
		inventoryBill = new InventoryBill();
	}

	@AfterClass
	public static void AfterClass() {
		GoodsBLService goods = GoodsBLFactory.getBLService();
		goods.delete(goodsVO.ID);
	}

	@Test
	public void c_testSubmit() throws RemoteException {
		ResultMessage result = inventoryBill.submit(loss);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void b_testAdd() throws RemoteException {
		ResultMessage result = inventoryBill.add(overflow);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void k_testDelete() throws RemoteException {
		ArrayList<InventoryBillVO> vos = inventoryBill.show();
		for (InventoryBillVO vo : vos) {
			ResultMessage result = inventoryBill.delete(vo.ID);
			if (vo.state == BillState.PASS) {
				assertEquals(ResultMessage.FAILED, result);
			}
			else {
				assertEquals(ResultMessage.SUCCESS, result);
			}
		}
	}

	@Test
	public void e_testUpdate() throws NumberFormatException, RemoteException {
		overflow.state = BillState.SUBMITTED;
		ResultMessage result = inventoryBill.update(overflow);
		assertEquals(ResultMessage.SUCCESS, result);
		loss.state = BillState.SUBMITTED;
		result = inventoryBill.update(loss);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void l_testShow() throws RemoteException {
		int number = inventoryBill.show().size();
		assertEquals(6, number);
	}

	@Test
	public void d_testShowDetails() throws NumberFormatException, RemoteException {
		InventoryBillVO vo = inventoryBill.showDetails(overflow.ID);
		assertEquals(overflow.ID, vo.ID);
		assertEquals(overflow.date, vo.date);
		assertEquals(overflow.user, vo.user);
		assertEquals(overflow.inventory, vo.inventory);
		assertEquals(overflow.type, vo.type);
		assertEquals(overflow.state, vo.state);
		vo = inventoryBill.showDetails(loss.ID);
		assertEquals(loss.ID, vo.ID);
		assertEquals(loss.date, vo.date);
		assertEquals(loss.user, vo.user);
		assertEquals(loss.inventory, vo.inventory);
		assertEquals(loss.type, vo.type);
		assertEquals(loss.state, vo.state);
	}

	@Test
	public void d_testFindByType() throws RemoteException {
		int number = inventoryBill.findByType(BillType.OVERFLOW).size();
		assertEquals(1, number);
		number = inventoryBill.findByType(BillType.LOSS).size();
		assertEquals(1, number);
	}

	@Test
	public void d_testFindByStateAndType() throws RemoteException {
		int number = inventoryBill.findByStateAndType(BillType.OVERFLOW, BillState.DRAFT).size();
		assertEquals(1, number);
		number = inventoryBill.findByStateAndType(BillType.LOSS, BillState.DRAFT).size();
		assertEquals(1, number);
	}

	@Test
	public void a_testGetNewIDByType() throws RemoteException {
		String ID = inventoryBill.getNewIDByType(BillType.OVERFLOW);
		assertEquals(overflow.ID, ID);
		ID = inventoryBill.getNewIDByType(BillType.LOSS);
		assertEquals(loss.ID, ID);
	}

	@Test
	public void h_testGetPassBillsByDate() throws RemoteException {
		int number = inventoryBill.getPassBillsByDate(LocalDate.now().toString(), LocalDate.now().toString()).size();
		assertEquals(2, number);
	}

	@Test
	public void h_testGetPassBillsByDateAndInventory() throws RemoteException {
		InventoryDataService inventoryDataService = InventoryRemoteHelper.getInstance().getInventoryDataService();
		InventoryPO inventory = inventoryDataService.findInventoryByName("默认仓库");
		int number = inventoryBill
				.getPassBillsByDateAndInventory(LocalDate.now().toString(), LocalDate.now().toString(), inventory)
				.size();
		assertEquals(2, number);
	}

	@Test
	public void g_testExamine() throws RemoteException {
		overflow.state = BillState.PASS;
		ResultMessage result = inventoryBill.examine(overflow);
		assertEquals(ResultMessage.SUCCESS, result);
		loss.state = BillState.PASS;
		result = inventoryBill.examine(loss);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void f_testGetAllSubmittedBill() throws RemoteException {
		int number = inventoryBill.getAllSubmittedBill().size();
		assertEquals(2, number);
	}

	@Test
	public void i_testRedCover() throws RemoteException {
		ResultMessage result = inventoryBill.redCover(overflow);
		assertEquals(ResultMessage.SUCCESS, result);
		result = inventoryBill.redCover(loss);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void j_testRedCoverAndCopy() throws RemoteException {
		ResultMessage result = inventoryBill.redCoverAndCopy(overflow);
		assertEquals(ResultMessage.SUCCESS, result);
		result = inventoryBill.redCoverAndCopy(loss);
		assertEquals(ResultMessage.SUCCESS, result);
	}

}
