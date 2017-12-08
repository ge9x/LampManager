package blservice.formblservice;

import bean.SalesDetailsBean;
import util.ResultMessage;
import vo.BillVO;
import vo.ProfitVO;
import vo.SalesDetailVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/11/23.
 */
public interface FormBLService {

    /**
     * 根据ID查找单据
     * @param ID
     * @return
     */
    public BillVO findByID(String ID);

    /**
     * 查看销售明细表
     * @return 商品销售记录列表
     */
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input);

    /**
     * 查看经营历程表
     * @return 所有符合条件的单据列表
     */
    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input);

    /**
     * 红冲单据
     * @param billVO
     * @return 是否成功
     */
    public ResultMessage redCover(BillVO billVO);

    /**
     * 红冲并复制单据
     * @param billVO
     * @return 是否成功
     */
    public ResultMessage redCoverAndCopy(BillVO billVO);

    /**
     * 查看经营情况表
     * @param startDate
     * @param endDate
     * @return 经营情况表
     */
    public ProfitVO getProfit(String startDate, String endDate);

    /**
     * 获得本次期初建账时间
     * @return
     */
    public String getStartDate();

    /**
     * 导出销售明细表
     * @return
     */
    public ResultMessage exportSalesDetails(String filePath, String filename, ArrayList<SalesDetailVO> salesDetailVOS);

    /**
     * 导出经营历程表
     * @param vos
     * @return
     */
    public ResultMessage exportDocumentDetails(ArrayList<BillVO> vos);

    /**
     * 导出经营情况表
     * @param vos
     * @return
     */
    public ResultMessage exportProfit(ArrayList<ProfitVO> vos);


}
