package bl.formbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.FormBLService;
import blservice.formblservice.SalesDetailsInput;
import javafx.beans.property.StringProperty;
import util.ResultMessage;
import vo.BillVO;
import vo.ProfitVO;
import vo.SalesDetailVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formattable;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class FormController implements FormBLService{
    Form form;

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

    public ProfitVO getProfit(String startDate,String endDate) {
        return null;
    }
    public String getStartDate(){
       return null;
    }

    @Override
    public ResultMessage exportSalesDetails(ArrayList<SalesDetailVO> vos) {
        return null;
    }

    @Override
    public ResultMessage exportDocumentDetails(ArrayList<BillVO> vos) {
        return null;
    }

    @Override
    public ResultMessage exportProfit(ArrayList<ProfitVO> vos) {
        return null;
    }
}
