package dataimpl.messagedataimpl;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import po.MessagePO;
import util.BillState;
import util.ResultMessage;
import util.UserPosition;

/**
 * 约定：启动前数据库中：<br>
 * 没有任何消息数据且自增为1<br>
 * Created on 2018/1/2
 * 
 * @author 巽
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageDataServiceImplTest {
	private MessageDataServiceImpl messageDataServiceImpl;
	private static MessagePO messagePO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messagePO = new MessagePO(BillState.SUBMITTED, "BYD-20180102-00001", LocalDate.now().toString(), UserPosition.INVENTORY_STAFF);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		messageDataServiceImpl = MessageDataServiceImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void b_testShow() {
		ArrayList<MessagePO> pos = messageDataServiceImpl.show(messagePO.getPosition());
		assertEquals(1, pos.size());
		if(!pos.isEmpty()){
			assertEquals(messagePO.getMessageTime(), pos.get(0).getMessageTime());
		}
	}

	@Test
	public void a_testAdd() {
		ResultMessage result = messageDataServiceImpl.add(messagePO);
		assertEquals(ResultMessage.SUCCESS, result);
	}

	@Test
	public void c_testDelete() {
		ResultMessage result = messageDataServiceImpl.delete(messagePO.getMessageID());
		assertEquals(ResultMessage.SUCCESS, result);
	}

}
