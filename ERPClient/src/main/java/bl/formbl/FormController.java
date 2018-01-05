package bl.formbl;

import bean.SalesDetailsBean;
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

    protected FormController(){
        form = new Form();
    }

    public BillVO findByID(String ID) {
        return null;
    }

    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        return form.getSalesDetails(input);
    }

    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        return form.getDocumentDetails(input);
    }

    public ResultMessage redCover(BillVO billVO) {
        return form.redCover(billVO);
    }

    public ResultMessage redCoverAndCopy(BillVO billVO) {
        return form.redCoverAndCopy(billVO);
    }

    public ProfitVO getProfit(String startDate,String endDate) {
        return form.getProfit(startDate,endDate);
    }
    public String getStartDate(){
        return form.getStartDate();
    }

    @Override
    public ResultMessage exportSalesDetails(String filePath, String filename, ArrayList<SalesDetailVO> salesDetailVOS) {
        return form.exportSalesDetails(filePath,filename,salesDetailVOS);
    }

    @Override
    public ResultMessage exportDocumentDetails(String filePath, ArrayList<BillVO> vos) {
        return form.exportDocumentDetails(filePath,vos);
    }


    @Override
    public ResultMessage exportProfit(String filePath,String filename,ArrayList<ProfitVO> vos) {
        return form.exportProfit(filePath,filename,vos);
    }
}
