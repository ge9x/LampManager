package bl.userbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bl.accountbl.Account;
import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;
import vo.AccountVO;
import vo.UserVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
	
	User user;
	UserVO userVO;

	@Before
	public void setUp() throws Exception {
		AppTest appTest = new AppTest();
	}
	
	@Before
    public void before() throws Exception{
        user = new User();
        userVO = new UserVO("00001", "1234", "Admin", UserPosition.ADMIN, UserLimits.STAFF);
    }
	
	@Test
	public void a_testAddUser() throws RemoteException {
		 ResultMessage resultMessage = user.addUser(userVO);
		 assertEquals(ResultMessage.SUCCESS, resultMessage);
	}
	
	@Test
	public void b_testShow() throws RemoteException {
		ArrayList<UserVO> users = user.show();
		assertEquals(1, users.size());
	}

	@Test
	public void c_testLogin() throws RemoteException {
		ResultMessage resultMessage = user.login("00001", "1234");
		assertEquals(ResultMessage.SUCCESS, resultMessage);
		
		resultMessage = user.login("00001", "123");
		assertEquals(ResultMessage.FAILED, resultMessage);
		
		resultMessage = user.login("00020", "1234");
		assertEquals(ResultMessage.NOT_EXIST, resultMessage);
	}
	
	@Test
	public void d_testGetCurrentUserID() throws RemoteException {
		user.login("00001", "1234");
		assertEquals("00001", user.getCurrentUserID());
	}

	@Test
	public void e_testModifyUser() throws RemoteException {
		user.addUser(new UserVO("00002", "12345", "HBL", UserPosition.INVENTORY_STAFF, UserLimits.STAFF));
		UserVO expected = new UserVO("00002", "1234", "HBL", UserPosition.INVENTORY_STAFF, UserLimits.STAFF);
		ResultMessage resultMessage = user.modifyUser(expected);
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}

	@Test
	public void f_testFindUsersByKeywords() throws RemoteException {
		ArrayList<UserVO> actual = user.findUsersByKeywords("Admin");
		assertEquals(1, actual.size());
	}

	@Test
	public void g_testFindUsersByID() throws RemoteException {
		ArrayList<UserVO> actual = user.findUsersByID("00001");
		assertEquals(1, actual.size());
	}

	@Test
	public void h_testFindUserByID() throws RemoteException {
		UserVO actual = user.findUserByID("00001");
		assertEquals(userVO.name, actual.name);
		assertEquals(userVO.password, actual.password);
		assertEquals(userVO.position, actual.position);
		assertEquals(userVO.limit, actual.limit);
	}

	@Test
	public void i_testGetAllSalesmen() throws RemoteException {
		UserVO salesman = new UserVO("00003", "1234", "ZLK", UserPosition.SALES_STAFF, UserLimits.MANAGER);
		user.addUser(salesman);
		ArrayList<UserVO> salesmen = user.getAllSalesmen();
		assertEquals(1, salesmen.size());
	}

	@Test
	public void j_testDeleteUser() throws RemoteException {
		ResultMessage resultMessage = user.deleteUser("00003");
		assertEquals(ResultMessage.SUCCESS, resultMessage);
	}
	
}
