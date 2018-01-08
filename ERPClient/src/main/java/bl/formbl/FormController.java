package bl.formbl;

import bean.SalesDetailsBean;
import bl.initializationbl.InitializationBLFactory;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.FormBLService;
import blservice.formblservice.SalesDetailsInput;
import blservice.initializationblservice.InitInfo;
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
    SalesDetails salesDetails;
    DocumentDetails documentDetails;
    Profit profit;
    InitInfo initInfo;

    protected FormController() {
        initInfo = InitializationBLFactory.getInfo();
        salesDetails = new SalesDetails();
        documentDetails = new DocumentDetails();
        profit = new Profit();
    }

    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        return salesDetails.getSalesDetails(input);
    }

    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        return documentDetails.getDocumentDetails(input);
    }

    public ResultMessage redCover(BillVO billVO) {
        return documentDetails.redCover(billVO);
    }

    public ResultMessage redCoverAndCopy(BillVO billVO) {
        return documentDetails.redCoverAndCopy(billVO);
    }

    public ProfitVO getProfit(String startDate,String endDate) {
        return profit.getProfit(startDate,endDate);
    }
    public String getStartDate(){
        return initInfo.getStartDate();
    }

    @Override
    public ResultMessage exportSalesDetails(String filePath, String filename, ArrayList<SalesDetailVO> salesDetailVOS) {
        return salesDetails.export(filePath,filename,salesDetailVOS);
    }

    @Override
    public ResultMessage exportDocumentDetails(String filePath, ArrayList<BillVO> vos) {
        return documentDetails.export(filePath,vos);
    }


    @Override
    public ResultMessage exportProfit(String filePath,String filename,ArrayList<ProfitVO> vos) {
        return profit.export(filePath,filename,vos);
    }
}
