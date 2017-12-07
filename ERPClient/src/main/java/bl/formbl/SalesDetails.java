package bl.formbl;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bean.SalesDetailsBean;
import bl.salesbl.Sales;
import bl.salesbl.SalesController;
import blservice.formblservice.SalesDetailsInput;
import blservice.salesblservice.SalesInfo;
import util.FilterType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.SalesDetailVO;
import vo.SalesVO;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class SalesDetails {

    ArrayList<SalesVO> salesVOS;
    SalesInfo salesInfo = new SalesController();

    public SalesDetails(){
        salesVOS = new ArrayList<>();
    }
    public ArrayList<SalesDetailVO> getSalesDetails(SalesDetailsInput input){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();

        salesVOS = salesInfo.getAllSalesOrder(input.startDate,input.endDate);
        if (input.filterType == FilterType.INVENTORY){
            salesDetailVOS = searchByInventory(input.keyword);
        }else if (input.filterType == FilterType.CUSTOMER){
            salesDetailVOS = searchByCustomer(input.keyword);
        }else if (input.filterType == FilterType.SALESMAN){
            salesDetailVOS = searchBySalesman(input.keyword);
        }else if (input.filterType == FilterType.GOODS){
            salesDetailVOS = searchByGoodsName(input.keyword);
        }else {
            salesDetailVOS = getAllSalesDetails();
        }
        return salesDetailVOS;
    }

    public ArrayList<SalesDetailVO> searchByInventory(String keyword){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();
        for (SalesVO salesVO : salesVOS){
            if (salesVO.inventory.contains(keyword)){
                for (GoodsItemVO itemVO:salesVO.goodsItemList){
                    SalesDetailVO vo = new SalesDetailVO(salesVO.date, itemVO.goodsName, itemVO.model, itemVO.number, itemVO.price);
                    salesDetailVOS.add(vo);
                }
            }
        }
        return salesDetailVOS;
    }

    public ArrayList<SalesDetailVO> searchByCustomer(String keyword){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();
        for (SalesVO salesVO : salesVOS){
            if (salesVO.customer.contains(keyword)){
                for (GoodsItemVO itemVO:salesVO.goodsItemList){
                    SalesDetailVO vo = new SalesDetailVO(salesVO.date, itemVO.goodsName, itemVO.model, itemVO.number, itemVO.price);
                    salesDetailVOS.add(vo);
                }
            }
        }
        return salesDetailVOS;
    }

    public ArrayList<SalesDetailVO> searchBySalesman(String keyword){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();
        for (SalesVO salesVO : salesVOS){
            if (salesVO.salesman.contains(keyword)){
                for (GoodsItemVO itemVO:salesVO.goodsItemList){
                    SalesDetailVO vo = new SalesDetailVO(salesVO.date, itemVO.goodsName, itemVO.model, itemVO.number, itemVO.price);
                    salesDetailVOS.add(vo);
                }
            }
        }
        return salesDetailVOS;
    }

    public ArrayList<SalesDetailVO> searchByGoodsName(String keyword){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();
        for (SalesVO salesVO : salesVOS){
            for (GoodsItemVO itemVO:salesVO.goodsItemList) {
                if (itemVO.goodsName.contains(keyword)){
                    SalesDetailVO vo = new SalesDetailVO(salesVO.date, itemVO.goodsName, itemVO.model, itemVO.number, itemVO.price);
                    salesDetailVOS.add(vo);
                }
            }
        }
        return salesDetailVOS;
    }

    public ArrayList<SalesDetailVO> getAllSalesDetails(){
        ArrayList<SalesDetailVO> salesDetailVOS = new ArrayList<>();
        for (SalesVO salesVO : salesVOS){
            for (GoodsItemVO itemVO:salesVO.goodsItemList) {
                SalesDetailVO vo = new SalesDetailVO(salesVO.date, itemVO.goodsName, itemVO.model, itemVO.number, itemVO.price);
                salesDetailVOS.add(vo);
            }
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
