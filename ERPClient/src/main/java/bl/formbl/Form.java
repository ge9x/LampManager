package bl.formbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.SalesDetailsInput;
import util.ResultMessage;
import vo.BillVO;
import vo.ProfitVO;
import vo.SalesDetailVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class Form {

    private SalesDetails salesDetails;
    private DocumentDetails documentDetails;
    private Profit profit;

    public BillVO findByID(String ID) {
        return null;
    }

    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        return null;
    }

    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        return null;
    }

    public ResultMessage redCover(BillVO billVO) {
        return null;
    }

    public ResultMessage redCoverAndCopy(BillVO billVO) {
        return null;
    }

    public ProfitVO getProfit(Date startDate, Date endDate) {
        return null;
    }
}
