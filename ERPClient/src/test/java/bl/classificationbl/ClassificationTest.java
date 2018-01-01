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
 * 约定：启动前数据库中只有一条ID为1，名字为“灯”的商品分类数据且自增为2<br>
 * Created on 2018/1/1
 * 
 * @author 巽
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassificationTest {
	private Classification classification;
	private ClassificationVO classificationVO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new AppTest();
	}

	@Before
	public void setUp() throws Exception {
		classification = new Classification();
		ArrayList<ClassificationVO> vos = classification.show();
		if (vos.size() > 1) {
			classificationVO = vos.get(1);
		}
	}

	@Test
	public void c_testShow() throws RemoteException {
		int size = classification.show().size();
		assertEquals(2, size);
	}

	@Test
	public void d_testFind() throws RemoteException {
		int number = classification.find("台").size();
		assertEquals(1, number);
	}

	@Test
	public void d_testShowDetails() throws NumberFormatException, RemoteException {
		ClassificationVO found = classification.showDetails("02");
		assertEquals("02", found.ID);
		assertEquals("台灯", found.name);
		assertEquals("灯", found.father.name);
	}

	@Test
	public void b_testAdd() throws NumberFormatException, RemoteException {
		ClassificationVO father = classification.showDetails("01");
		classificationVO = new ClassificationVO(classification.getNewID(), "台灯", father, new ArrayList<>(),
				new ArrayList<>());
		ResultMessage result = classification.add(classificationVO);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void z_testDelete() throws NumberFormatException, RemoteException {
		ResultMessage result = classification.delete("02");
		assertEquals(ResultMessage.SUCCESS, result);
		int number = classification.show().size();
		assertEquals(1, number);
	}

	@Test
	public void e_testUpdate() throws RemoteException {
		classificationVO.name = "落地灯";
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
		ClassificationPO po = classification.exactlyFindByName("落地灯");
		ClassificationVO vo = Classification.poToVO(po);
		assertEquals("02", vo.ID);
		assertEquals("落地灯", vo.name);
		assertEquals("灯", vo.father.name);
	}

}
