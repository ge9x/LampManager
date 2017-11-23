package bl.formbl;

import blservice.formblservice.SalesDetailsInput;
import vo.SalesDetailVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/7.
 */
public class MockSalesDetails extends SalesDetails {
    @Override
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        SalesDetailVO salesDetailVO1 = new SalesDetailVO(new Date(), "霓虹灯", "大", 20, 35.0);
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<SalesDetailVO>();
        salesDetailVOS.add(salesDetailVO1);
        return salesDetailVOS;
    }
}
