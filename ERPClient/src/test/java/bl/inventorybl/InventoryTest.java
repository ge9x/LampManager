package bl.inventorybl;

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
import vo.InventoryCheckVO;

/**
 * 约定：启动前数据库中：<br>
 * 只有一条名字为“默认仓库”的仓库数据<br>
 * Created on 2018/1/1
 * 
 * @author 巽
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryTest {
	private Inventory inventory;
	private String name;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new AppTest();
	}

	@Before
	public void setUp() throws Exception {
		inventory = new Inventory();
		ArrayList<String> names = inventory.showInventory();
		if (names.size() > 1) {
			name = names.get(1);
		}
		else {
			name = "仙林区仓库";
		}
	}

	@Test
	public void b_testShowInventory() throws RemoteException {
		int number = inventory.showInventory().size();
		assertEquals(2, number);
	}

	@Test
	public void f_testCheck() {
		InventoryCheckVO vo = inventory.check();
		assertNotNull(vo);
		assertEquals(LocalDate.now().toString(), vo.date);
	}

	@Test
	public void f_testExportExcel() {
		InventoryCheckVO vo = inventory.check();
		ResultMessage result = inventory.exportExcel("s:/", "testExcel" + LocalDate.now().toString(), vo);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void a_testAddInventory() throws RemoteException {
		ResultMessage result = inventory.addInventory(name);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void e_testDeleteInventory() throws RemoteException {
		ResultMessage result = inventory.deleteInventory("鼓楼区仓库");
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void c_testUpdateInventory() throws RemoteException {
		ResultMessage result = inventory.updateInventory(name, "鼓楼区仓库");
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void d_testGetInventoryByName() throws RemoteException {
		assertNotNull(inventory.getInventoryByName("鼓楼区仓库"));
	}

	@Test
	public void d_testRaiseInventory() throws RemoteException {
		ResultMessage result = inventory.raiseInventory(new ArrayList<>(), name);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void d_testReduceInventory() throws RemoteException {
		ResultMessage result = inventory.raiseInventory(new ArrayList<>(), name);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void testGetStartDate() {
		assertNotNull(inventory.getStartDate());
	}

}
