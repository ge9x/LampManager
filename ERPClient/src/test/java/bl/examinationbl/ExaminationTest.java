package bl.examinationbl;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.ERPClient.AppTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mysql.jdbc.EscapeTokenizer;

import bl.accountbl.Account;
import bl.financialbl.Finance;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.AccountVO;
import vo.BillVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExaminationTest {
	
	Examination examination;
	AccountBillVO accountBill;
	AccountBillVO accountBill2;

	@Before
	public void setUp() throws Exception {
		AppTest appTest = new AppTest();
	}

	@Before
    public void before() throws Exception{
        examination = new Examination();
        Finance finance = new Finance();
        accountBill = new AccountBillVO(LocalDate.now().toString(), "SKD-20180101-00001", BillState.SUBMITTED, BillType.RECEIPT, 
        		"00000001", "Aster",new ArrayList<AccountBillItemVO>());
        accountBill2 = new AccountBillVO(LocalDate.now().toString(), "SKD-20180101-00002", BillState.SUBMITTED, BillType.RECEIPT, 
        		"00000001", "Aster",new ArrayList<AccountBillItemVO>());
        finance.submit(accountBill);
        finance.submit(accountBill2);
        
    }
	
	@Test
	public void a_testShow() {
		ArrayList<BillVO> actual = examination.show();
		assertEquals(2, actual.size());
	}

	@Test
	public void b_testModifyReceipt() {
		AccountBillVO accountBill = new AccountBillVO(LocalDate.now().toString(), "SKD-20180101-00001", BillState.SUBMITTED, BillType.RECEIPT, 
        		"00000001", "ZYX",new ArrayList<AccountBillItemVO>());
		ResultMessage re = examination.modifyReceipt(accountBill);
		assertEquals(ResultMessage.SUCCESS, re);
		
	}

	@Test
	public void c_testApproveReceipt() {
		ResultMessage re = examination.approveReceipt(accountBill);
		assertEquals(ResultMessage.SUCCESS, re);
	}

	@Test
	public void d_testRefuseReceipt() {
		ResultMessage re = examination.refuseReceipt(accountBill2);
		assertEquals(ResultMessage.SUCCESS, re);
	}

}
