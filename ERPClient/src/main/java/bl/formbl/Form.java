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
        Profit profit = new Profit();
        initInfo = new InitializationController();
    }
    public BillVO findByID(String ID) {
        return null;
    }

    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        return salesDetails.getSalesDetails(input);
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

    public ProfitVO getProfit(String startDate, String endDate) {
        return null;
    }

    public String getStartDate() {
        return initInfo.getStartDate();
    }

    public ResultMessage exportSalesDetails(String filePath, String filename, ArrayList<SalesDetailsBean> beans) {
        return salesDetails.export(filePath,filename,beans);
    }
}
