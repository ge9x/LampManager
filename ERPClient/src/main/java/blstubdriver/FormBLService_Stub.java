package blstubdriver;

import bean.SalesDetailsBean;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.FormBLService;
import blservice.formblservice.SalesDetailsInput;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class FormBLService_Stub implements FormBLService {
    ProfitVO profitVO;
    BillVO billVO;
    ArrayList<BillVO> billVOS;
    ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();

    public FormBLService_Stub(){
        AccountVO vo1 = new AccountVO("001","工商银行账户1",2000);
        AccountVO vo2 = new AccountVO("001","工商银行账户2",1000);
        AccountVO vo3 = new AccountVO("001","工商银行账户3",4000);
        AccountBillItemVO itemVO1 = new AccountBillItemVO(vo1,300,"卖出灯具20个");
        AccountBillItemVO itemVO2 = new AccountBillItemVO(vo2,400,"卖出灯具30个");
        ArrayList<AccountBillItemVO> accountBillItemVOS = new ArrayList<AccountBillItemVO>();
        accountBillItemVOS.add(itemVO1);
        accountBillItemVOS.add(itemVO2);
        billVO = new AccountBillVO(LocalDate.now().toString(),"SKD-20171022-00001", BillState.DRAFT, BillType.RECEIPT,
                "客户甲","营业员1",accountBillItemVOS);
        profitVO = new ProfitVO("","",10000,3000,
                200,400,900,9500,5000,
                4000,1000,500,5500,4000);
        SalesDetailVO salesDetailVO1 = new SalesDetailVO("2017-1-1","霓虹灯", "大", 20, 35.0);
        SalesDetailVO salesDetailVO2 = new SalesDetailVO("2017-1-1","挂灯", "xxdd", 10, 35.0);
        salesDetailVOS.add(salesDetailVO1);
        salesDetailVOS.add(salesDetailVO2);
    }
    public ResultMessage redCoverAndCopy(BillVO vo) {
        if (vo.ID.equals(billVO.ID)){
            System.out.println("RedCoverAndCopy succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("RedCoverAndCopy failed");
            return ResultMessage.FAILED;
        }
    }

    public ProfitVO getProfit(String startDate, String endDate) {
        if (startDate.equals(LocalDate.of(2017, 11, 29).toString())) {
            profitVO.salesIncome = 100.0;
            return profitVO;
        } else {
            profitVO.salesIncome = 200.0;
            return profitVO;
        }
    }
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input) {
        return salesDetailVOS;
    }

    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        return billVOS;
    }

    public ResultMessage redCover(BillVO vo) {
        if (vo.ID.equals(billVO.ID)){
            System.out.println("RedCover succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("RedCover failed");
            return ResultMessage.FAILED;
        }
    }
    public BillVO findByID(String ID) {
        if (ID.equals("SKD-20171022-00001")){
            return billVO;
        }else{
            return null;
        }
    }
    public String getStartDate(){
        return "2017-1-1";
    }

    @Override
    public ResultMessage exportSalesDetails(String filePath, String filename, ArrayList<SalesDetailVO> salesDetailVOS) {
        return null;
    }


    @Override
    public ResultMessage exportDocumentDetails(ArrayList<BillVO> vos) {
        System.out.println("Export Succeed");
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage exportProfit(String filePath, String filename, ArrayList<ProfitVO> vos) {
        return null;
    }


}
