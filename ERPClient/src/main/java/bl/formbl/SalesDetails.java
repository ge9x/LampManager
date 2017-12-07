package bl.formbl;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bean.SalesDetailsBean;
import bl.goodsbl.Goods;
import bl.salesbl.SalesController;
import blservice.formblservice.SalesDetailsInput;
import blservice.salesblservice.SalesInfo;
import util.FilterType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.SalesDetailVO;
import vo.SalesVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class SalesDetails {

    private ArrayList< SalesDetailsItem> salesDetailsItem;
    ArrayList<String> salesIDS;
    SalesInfo salesInfo = new SalesController();

    public SalesDetails(){
        salesIDS = new ArrayList<>();
    }
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();

        salesIDS = salesInfo.getSalesOrderIDByDate(input.startDate,input.endDate);
        if (input.filterType == FilterType.GOODS){

        }

        return salesDetailVOS;


    }
    public ResultMessage export(String filePath, String filename, ArrayList<SalesDetailsBean> beans){
        ExportToExcel exporter = new ExportToExcel.Builder(filePath, filename, ExcelType.XLSX)
                .withModel(Model.of(SalesDetailsBean.class, beans)).build();
        if (exporter.export())
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }
}
