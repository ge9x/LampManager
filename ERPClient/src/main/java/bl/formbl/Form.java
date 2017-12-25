package bl.formbl;

import bean.SalesDetailsBean;
import bl.initializationbl.InitializationController;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.SalesDetailsInput;
import blservice.initializationblservice.InitInfo;
import util.ResultMessage;
import vo.BillVO;
import vo.ProfitVO;
import vo.SalesDetailVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class Form {

    private SalesDetails salesDetails;
    private DocumentDetails documentDetails;
    private Profit profit;
    InitInfo initInfo;

    public Form(){
        salesDetails = new SalesDetails();
        documentDetails = new DocumentDetails();
        profit = new Profit();
        initInfo = new InitializationController();
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
        return null;
    }

    public ProfitVO getProfit(String startDate, String endDate) {
        return profit.getProfit(startDate,endDate);
    }

    public String getStartDate() {
        return initInfo.getStartDate();
    }

    public ResultMessage exportSalesDetails(String filePath, String filename, ArrayList<SalesDetailVO> salesDetailVOS) {
        return salesDetails.export(filePath,filename,salesDetailVOS);
    }
    public ResultMessage exportProfit(String filePath, String filename, ArrayList<ProfitVO> profitVOS){
        return profit.export(filePath,filename,profitVOS);
    }
}
