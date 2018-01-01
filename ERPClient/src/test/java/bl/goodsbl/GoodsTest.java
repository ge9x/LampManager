package bl.goodsbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import po.GoodsPO;
import util.ResultMessage;
import vo.GoodsVO;

/**
 * 约定：启动前数据库中：<br>
 * 只有一条ID为1，名字为“灯”的商品分类数据<br>
 * 没有任何商品数据且自增为1<br>
 * Created on 2018/1/1
 * 
 * @author 巽
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoodsTest {
	private Goods goods;
	private GoodsVO goodsVO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new AppTest();
	}

	@Before
	public void setUp() throws Exception {
		goods = new Goods();
		ArrayList<GoodsVO> vos = goods.show();
		if (!vos.isEmpty()) {
			goodsVO = vos.get(0);
		}
	}

	@Test
	public void z_testShow() throws RemoteException {
		int number = goods.show().size();
		assertEquals(0, number);
	}

	@Test
	public void c_testFind() throws RemoteException {
		ArrayList<GoodsVO> vos = goods.find("奢华");
		assertEquals(1, vos.size());
	}

	@Test
	public void e_testShowDetails() throws NumberFormatException, RemoteException {
		GoodsVO vo = goods.showDetails("01000001");
		assertEquals(goodsVO.ID, vo.ID);
		assertEquals(goodsVO.name, vo.name);
		assertEquals(goodsVO.model, vo.model);
	}

	@Test
	public void b_testAdd() throws RemoteException {
		goodsVO = new GoodsVO(goods.getNewID("01"), "SJ牌欧洲奢华落地灯", "SJ-0001", "灯", 0, 7, 233, 250, 233, 250);
		ResultMessage result = goods.add(goodsVO);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void y_testDelete() throws NumberFormatException, RemoteException {
		ResultMessage result = goods.delete("01000001");
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void d_testUpdate() throws NumberFormatException, RemoteException {
		goodsVO.name = "SJ牌北欧极简主义落地灯";
		ResultMessage result = goods.update(goodsVO);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void a_testGetNewID() throws RemoteException {
		String ID = goods.getNewID("01");
		assertEquals("01000001", ID);
	}

	@Test
	public void e_testGetGoodsByID() throws NumberFormatException, RemoteException {
		GoodsPO po = goods.getGoodsByID("01000001");
		GoodsVO vo = Goods.poToVO(po);
		assertEquals(goodsVO.ID, vo.ID);
		assertEquals(goodsVO.name, vo.name);
		assertEquals(goodsVO.model, vo.model);
	}

}
