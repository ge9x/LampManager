package bl.classificationbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import po.ClassificationPO;
import util.ResultMessage;
import vo.ClassificationVO;

/**
 * 约定：启动前数据库中只有一条ID为1的商品分类数据且自增为2<br>
 * Created on 2018/1/1
 * 
 * @author 巽
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassificationTest {
	private Classification classification;
	private static ClassificationVO classificationVO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new AppTest();
		Classification classification = new Classification();
		ClassificationVO root = classification.showDetails("01");
		classificationVO = new ClassificationVO(classification.getNewID(), "测试专用", root, new ArrayList<>(),
				new ArrayList<>());
	}

	@Before
	public void setUp() throws Exception {
		classification = new Classification();
	}

	@Test
	public void c_testShow() throws RemoteException {
		int size = classification.show().size();
		assertEquals(2, size);
	}

	@Test
	public void d_testFind() throws RemoteException {
		int number = classification.find(classificationVO.name).size();
		assertEquals(1, number);
	}

	@Test
	public void d_testShowDetails() throws NumberFormatException, RemoteException {
		ClassificationVO found = classification.showDetails(classificationVO.ID);
		assertEquals(classificationVO.ID, found.ID);
		assertEquals(classificationVO.name, found.name);
		assertEquals(classificationVO.father.name, found.father.name);
	}

	@Test
	public void b_testAdd() throws NumberFormatException, RemoteException {
		ResultMessage result = classification.add(classificationVO);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void g_testDelete() throws NumberFormatException, RemoteException {
		ResultMessage result = classification.delete(classificationVO.ID);
		assertEquals(ResultMessage.SUCCESS, result);
		int number = classification.show().size();
		assertEquals(1, number);
	}

	@Test
	public void e_testUpdate() throws RemoteException {
		classificationVO.name = "测试专用改";
		ResultMessage result = classification.update(classificationVO);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void a_testGetNewID() throws RemoteException {
		String newID = classification.getNewID();
		assertEquals("02", newID);
	}

	@Test
	public void f_testExactlyFindByName() throws RemoteException {
		ClassificationPO po = classification.exactlyFindByName(classificationVO.name);
		ClassificationVO vo = Classification.poToVO(po);
		assertEquals(classificationVO.ID, vo.ID);
		assertEquals(classificationVO.name, vo.name);
		assertEquals(classificationVO.father.name, vo.father.name);
	}

}
