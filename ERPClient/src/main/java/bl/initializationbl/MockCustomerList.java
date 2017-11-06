package bl.initializationbl;

import bl.customerbl.Customer;
import util.CustomerCategory;
import util.Level;
import vo.CustomerVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockCustomerList extends CustomerList {
    @Override
    public ArrayList<CustomerVO> getCustomers() {
        CustomerVO customer=new CustomerVO("00000003", CustomerCategory.PUR_AGENT, Level.LEVEL_FIVE,"进货商2","15244358373",
                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0);
        ArrayList<CustomerVO> customerVOS = new ArrayList<CustomerVO>();
        customerVOS.add(customer);
        return customerVOS;
    }
}
