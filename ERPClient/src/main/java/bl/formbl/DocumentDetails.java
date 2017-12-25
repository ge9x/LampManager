package bl.formbl;

import bl.customerbl.CustomerController;
import bl.financialbl.FinanceController;
import bl.inventorybl.InventoryController;
import bl.salesbl.Purchase;
import bl.salesbl.PurchaseController;
import bl.salesbl.Sales;
import bl.salesbl.SalesController;
import blservice.customerblservice.CustomerInfo;
import blservice.financeblservice.FinanceInfo;
import blservice.formblservice.DocumentDetailsInput;
import blservice.inventoryblservice.InventoryInfo;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import util.BillType;
import util.FilterType;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class DocumentDetails{

    SalesInfo salesInfo;
    PurchaseInfo purchaseInfo;
    FinanceInfo financeInfo;
    InventoryInfo inventoryInfo;
    CustomerInfo customerInfo;

    ArrayList<BillVO> billVOS = new ArrayList<>();

    public DocumentDetails(){
        salesInfo = new SalesController();
        purchaseInfo = new PurchaseController();
        financeInfo = new FinanceController();
        inventoryInfo = new InventoryController();
        customerInfo = new CustomerController();
    }

    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input) {
        getAllBills(input.startDate,input.endDate);
        if (input.billType != null) {
            searchByType(input.billType);
            if (input.filterType != null) {
                switch (input.filterType) {
                    case CUSTOMER:
                        return searchByCustomer(billVOS, input.billType, input.keyword);
                    case SALESMAN:
                        return searchBySaleman(billVOS, input.billType, input.keyword);
                    case INVENTORY:
                        return searchByInventory(billVOS, input.billType, input.keyword);
                }
            }
        }
        return billVOS;
    }
    public void searchByType(BillType type){
        if (type != null){
            ArrayList<BillVO> newArr = new ArrayList<>();
            for (BillVO billVO:billVOS){
                if (billVO.type == type)
                    newArr.add(billVO);
            }
            billVOS.clear();
            billVOS.addAll(newArr);
        }
    }
    public ArrayList<BillVO> searchByInventory(ArrayList<BillVO> billVOS,BillType type,String name){
        ArrayList<BillVO> newArr = new ArrayList<>();
        switch (type){
            case SALES:
            case SALESRETURN:
                for (BillVO billVO:billVOS){
                    if (((SalesVO) billVO).inventory.contains(name)){
                        newArr.add(billVO);
                    }
                }
                break;
            case PURCHASE:
            case RETURN:
                for (BillVO billVO:billVOS){
                    if (((PurchaseVO) billVO).inventory.contains(name)){
                        newArr.add(billVO);
                    }
                }
                break;
            case OVERFLOW:
            case LOSS:
            for (BillVO billVO:billVOS){
                    if (((InventoryBillVO) billVO).inventory.contains(name)){
                        newArr.add(billVO);
                    }
                }
        }
        return newArr;
    }
    public ArrayList<BillVO> searchByCustomer(ArrayList<BillVO> billVOS,BillType type ,String name){
        ArrayList<BillVO> newArr = new ArrayList<>();
        switch (type){
            case RECEIPT:
            case PAYMENT:
                for (BillVO billVO:billVOS){
                    if (customerInfo.getCustomerByID(Integer.parseInt(((SalesVO) billVO).customerID))
                            .customerName.contains(name)){
                        newArr.add(billVO);
                    }

                }
                break;
            case SALES:
            case SALESRETURN:
                for (BillVO billVO:billVOS){
                    if (((SalesVO) billVO).customer.contains(name)){
                        newArr.add(billVO);
                    }
                }
                break;
            case PURCHASE:
            case RETURN:
                for (BillVO billVO:billVOS){
                    if (customerInfo.getCustomerByID(Integer.parseInt(((PurchaseVO) billVO).customerID))
                            .customerName.contains(name)){
                        newArr.add(billVO);
                    }
                }
                break;
        }
        return newArr;
    }
    public ArrayList<BillVO> searchBySaleman(ArrayList<BillVO> billVOS,BillType type ,String name){
        ArrayList<BillVO> newArr = new ArrayList<>();
        switch (type){
            case SALES:
            case SALESRETURN:
                for (BillVO billVO:billVOS){
                    if (((SalesVO) billVO).salesman.contains(name)){
                        newArr.add(billVO);
                    }
                }
                break;
        }
        return newArr;
    }
    public void getAllBills(String startDate, String endDate){
        billVOS.addAll(inventoryInfo.getInventoryBillsByDate(startDate, endDate));
        billVOS.addAll(purchaseInfo.getPurchaseByDate(startDate,endDate));
        billVOS.addAll(salesInfo.getAllSalesOrder(startDate, endDate));
        billVOS.addAll(financeInfo.getAccountBillsByDate(startDate, endDate));
        billVOS.addAll(financeInfo.getCashBillsByDate(startDate, endDate));
    }

    public ResultMessage redCover(BillVO billVO) {
        ResultMessage re = ResultMessage.FAILED;
        switch (billVO.type){
            case OVERFLOW:
            case LOSS: re = inventoryInfo.redCover((InventoryBillVO)billVO);break;
            case RECEIPT:
            case PAYMENT: re = financeInfo.redCover((AccountBillVO)billVO);break;
            case CASH: re = financeInfo.redCover((CashBillVO)billVO);break;
            case PURCHASE:
            case RETURN: re = purchaseInfo.redCover((PurchaseVO)billVO);break;
            case SALES:
            case SALESRETURN: re = salesInfo.redCover((SalesVO)billVO);break;

        }
        return re;
    }
    public ResultMessage redCoverAndCopy(BillVO billVO) {
        ResultMessage re = ResultMessage.FAILED;
        switch (billVO.type){
            case OVERFLOW:
            case LOSS: re = inventoryInfo.redCoverAndCopy((InventoryBillVO)billVO);break;
            case RECEIPT:
            case PAYMENT: re = financeInfo.redCoverAndCopy((AccountBillVO)billVO);break;
            case CASH: re = financeInfo.redCoverAndCopy((CashBillVO)billVO);break;
            case PURCHASE:
            case RETURN: re = purchaseInfo.redCoverAndCopy((PurchaseVO)billVO);break;
            case SALES:
            case SALESRETURN: re = salesInfo.redCoverAndCopy((SalesVO)billVO);break;
        }
        return re;
    }
}
