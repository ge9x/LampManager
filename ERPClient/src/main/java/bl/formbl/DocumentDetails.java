package bl.formbl;

import ExcelUtil.enums.ExcelType;
import ExcelUtil.impl.ExportToExcel;
import ExcelUtil.model.Model;
import bl.customerbl.CustomerBLFactory;
import bl.customerbl.CustomerController;
import bl.financialbl.CashBill;
import bl.financialbl.FinanceBLFactory;
import bl.financialbl.FinanceController;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import bl.salesbl.*;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        salesInfo = SalesBLFactory.getSalesInfo();
        purchaseInfo = SalesBLFactory.getPurchaseInfo();
        financeInfo = FinanceBLFactory.getInfo();
        inventoryInfo = InventoryBLFactory.getInfo();
        customerInfo = CustomerBLFactory.getInfo();
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

    public ResultMessage export(String filePath, ArrayList<BillVO> vos) {

        for (BillVO billVO : vos){
            switch (billVO.type){
                case OVERFLOW:
                case LOSS:
                    return exportInventoryBill(filePath,(InventoryBillVO) billVO);
                case PURCHASE:
                case RETURN:
                    return exportPurchaseBill(filePath,(PurchaseVO)billVO);
                case SALES:
                case SALESRETURN:
                    return exportSalesBill(filePath,(SalesVO) billVO);
                case RECEIPT:
                case PAYMENT:
                    return exportAccountBill(filePath,(AccountBillVO)billVO);
                case CASH:
                  return exportCashBill(filePath,(CashBillVO)billVO);
            }
        }
        return ResultMessage.FAILED;
    }

    private ResultMessage exportAccountBill(String filePath, AccountBillVO billVO) {
        File file = new File(filePath + "/" + billVO.type.getValue() + billVO.ID + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        write(billVO.toString(), file);
        return ResultMessage.SUCCESS;
    }

    private ResultMessage exportSalesBill(String filePath, SalesVO billVO) {
        File file = new File(filePath + "/" + billVO.type.getValue() + billVO.ID + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        write(billVO.toString(), file);
        return ResultMessage.SUCCESS;
    }

    private ResultMessage exportCashBill(String filePath, CashBillVO billVO) {
        File file = new File(filePath + "/" + billVO.type.getValue() + billVO.ID + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        write(billVO.toString(), file);
        return ResultMessage.SUCCESS;
    }

    private ResultMessage exportPurchaseBill(String filePath, PurchaseVO billVO) {
        File file = new File(filePath + "/" + billVO.type.getValue() + billVO.ID + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        write(billVO.toString(), file);
        return ResultMessage.SUCCESS;
    }


    private void searchByType(BillType type){
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
    private ArrayList<BillVO> searchByInventory(ArrayList<BillVO> billVOS,BillType type,String name){
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
    private ArrayList<BillVO> searchByCustomer(ArrayList<BillVO> billVOS,BillType type ,String name){
        ArrayList<BillVO> newArr = new ArrayList<>();
        switch (type){
            case RECEIPT:
            case PAYMENT:
                for (BillVO billVO:billVOS){
                    if (customerInfo.getCustomerByID(Integer.parseInt(((AccountBillVO) billVO).customerID))
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
    private ArrayList<BillVO> searchBySaleman(ArrayList<BillVO> billVOS,BillType type ,String name){
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
    private void getAllBills(String startDate, String endDate){
        billVOS.clear();
        billVOS.addAll(inventoryInfo.getInventoryBillsByDate(startDate, endDate));
        billVOS.addAll(purchaseInfo.getPurchaseByDate(startDate,endDate));
        billVOS.addAll(salesInfo.getAllSalesOrder(startDate, endDate));
        billVOS.addAll(salesInfo.getAllSalesReturnOrder(startDate, endDate));
        billVOS.addAll(financeInfo.getAccountBillsByDate(startDate, endDate));
        billVOS.addAll(financeInfo.getCashBillsByDate(startDate, endDate));
    }



    private ResultMessage exportInventoryBill(String filePath, InventoryBillVO billVO) {
        File file = new File(filePath + "/" + billVO.type.getValue() + billVO.ID + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        write(billVO.toString(), file);
        return ResultMessage.SUCCESS;
    }
    private void write(String a, File f){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write(a);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
