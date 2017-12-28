package ui.viewcontroller.common;

import bl.financialbl.AccountBill;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.ERPClient.Main;
import org.omg.PortableInterceptor.INACTIVE;
import ui.viewcontroller.FinancialStaff.*;
import ui.viewcontroller.InventoryStaff.InventorySyncController;
import ui.viewcontroller.InventoryStaff.InventorySyncEditController;
import ui.viewcontroller.SalesStaff.*;
import util.BillType;
import util.Money;
import vo.*;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class BillController {

    public Boolean onlyShow = false;

    BillVO bill;
    MainUIController mainUIController;
    FinancialReceiptController financialReceiptController;
    FinancialPaymentController financialPaymentController;
    FinancialCashBillController financialCashBillController;
    SalesStaffPurchaseOrderViewController salesStaffPurchaseOrderViewController;
    SalesStaffReturnOrderViewController salesStaffReturnOrderViewController;
    SalesStaffSalesOrderViewController salesStaffSalesOrderViewController;
    SalesStaffSalesReturnOrderViewController salesStaffSalesReturnOrderViewController;
    InventorySyncController inventorySyncController;
    FinancialDocumentDetailsController financialDocumentDetailsController;
    @FXML
    Circle circle;

    @FXML
    Label billType;

    @FXML
    Label billIDIcon;

    @FXML
    Label billID;

    @FXML
    Label billDateIcon;

    @FXML
    Label billDate;

    @FXML
    Label billCreaterIcon;

    @FXML
    Label billCreater;

    @FXML
    Label billMoneyIcon;

    @FXML
    Label billMoney;

    @FXML
    Label DetailIcon;

    @FXML
    Label DeleteIcon;

    @FXML
    JFXCheckBox checkBox;

    @FXML
    public void initialize(){
        billIDIcon.setText("\ue67c");
        billDateIcon.setText("\ue60a");
        billCreaterIcon.setText("\ue609");
        billMoneyIcon.setText("\ue611");
        DetailIcon.setText("\ue89d");
        DeleteIcon.setText("\ue619");
        DeleteIcon.setVisible(false);
    }

    public void setBill(BillVO bill){
        this.bill = bill;
        setBillType(bill);
        billID.setText(bill.ID);
        billDate.setText(String.valueOf(bill.date));
    }

    public void setBillType(BillVO bill){
        if(bill.type== BillType.CASH){
            CashBillVO cashBill = (CashBillVO) bill;
            circle.setStyle("-fx-Stroke:#0066FF");
            billType.setText("现");
            billType.setTextFill(Color.web("#0066FF"));
            billCreater.setText(cashBill.userName);
            billMoney.setText(Money.getMoneyString(cashBill.sum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!onlyShow){
                        financialCashBillController.showCashBillDetailView(cashBill);
                    }
                    else {
                        mainUIController.saveView();
                        Pane page = null;
                        FinancialCashBillEditController financialCashBillEditController = null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/financialStaff/CashBillEdit.fxml"));
                            page = pageLoader.load();
                            financialCashBillEditController = pageLoader.getController();
                            financialCashBillEditController.setMainUIController(mainUIController);
                            financialCashBillEditController.onlyShow = true;
                            mainUIController.setCenter(page);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        financialCashBillEditController.setForDetailView((CashBillVO) bill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    financialCashBillController.deleteCashBill(cashBill);
                }
            });
        }
       else if(bill.type==BillType.LOSS){
            InventoryBillVO inventoryBill = (InventoryBillVO) bill;
            circle.setStyle("-fx-Stroke:#99CCFF");
            billType.setText("损");
            billType.setTextFill(Color.web("#99CCFF"));
            billCreater.setText(inventoryBill.user);
            billMoneyIcon.setText("\ue61d");
            billMoney.setText(inventoryBill.inventory);
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    inventorySyncController.deleteBill(inventoryBill);
                }
            });
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                   showInventoryBillDetail(inventoryBill);
                }
            });
        }
        else if(bill.type==BillType.OVERFLOW){
            InventoryBillVO inventoryBill = (InventoryBillVO) bill;
            circle.setStyle("-fx-Stroke:#99CCFF");
            billType.setText("溢");
            billType.setTextFill(Color.web("#99CCFF"));
            billCreater.setText(inventoryBill.user);
            billMoneyIcon.setText("\ue61d");
            billMoney.setText(inventoryBill.inventory);
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    inventorySyncController.deleteBill(inventoryBill);
                }
            });
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                 showInventoryBillDetail(inventoryBill);
                }
            });
        }
        else if(bill.type==BillType.PAYMENT){
            AccountBillVO accountBill = (AccountBillVO) bill;
            circle.setStyle("-fx-Stroke:#00FF99");
            billType.setText("付");
            billType.setTextFill(Color.web("#00FF99"));
            billCreater.setText(accountBill.userName);
            billMoney.setText(Money.getMoneyString(accountBill.sum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (onlyShow){
                        mainUIController.saveView();
                        FinancialPaymentEditController financialPaymentEditController = null;
                        Pane page = null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/financialStaff/PaymentEdit.fxml"));
                            page = pageLoader.load();
                            financialPaymentEditController = pageLoader.getController();
                            financialPaymentEditController.setMainUIController(mainUIController);
                            financialPaymentEditController.onlyShow = true;
                            mainUIController.setCenter(page);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        financialPaymentEditController.setForDetailView((AccountBillVO) bill);
                    }else{
                        financialPaymentController.showPaymentDetailView(accountBill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    financialPaymentController.deletePayment(accountBill);
                }
            });
        }
        else if(bill.type==BillType.RECEIPT){
            AccountBillVO accountBill = (AccountBillVO) bill;
            circle.setStyle("-fx-Stroke:#00FF99");
            billType.setText("收");
            billType.setTextFill(Color.web("#00FF99"));
            billCreater.setText(accountBill.userName);
            billMoney.setText(Money.getMoneyString(accountBill.sum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (onlyShow){
                        mainUIController.saveView();
                        Pane page = null;
                        FinancialReceiptEditController financialReceiptEditController = null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/financialStaff/ReceiptEdit.fxml"));
                            page = pageLoader.load();
                            financialReceiptEditController = pageLoader.getController();
                            financialReceiptEditController.setMainUIController(mainUIController);
                            financialReceiptEditController.onlyShow = true;
                            mainUIController.setCenter(page);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        financialReceiptEditController.setForDetailView((AccountBillVO) bill);
                    }else{

                        financialReceiptController.showReceiptDetailView(accountBill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    financialReceiptController.deleteReceipt(accountBill);
                }
            });

        }
        else if(bill.type==BillType.PURCHASE){
            PurchaseVO purchaseBill = (PurchaseVO) bill;
            circle.setStyle("-fx-Stroke:#FFCCFF");
            billType.setText("进");
            billType.setTextFill(Color.web("#FFCCFF"));
            billCreater.setText(purchaseBill.user);
            billMoney.setText(Money.getMoneyString(purchaseBill.sum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (onlyShow) {
                        mainUIController.saveView();
                        Pane page = null;
                        SalesStaffPurchaseEditViewController salesStaffPurchaseEditViewController = null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/salesStaff/PurchaseOrderEdit.fxml"));
                            page = pageLoader.load();
                            salesStaffPurchaseEditViewController = pageLoader.getController();
                            salesStaffPurchaseEditViewController.setMainUIController(mainUIController);
                            salesStaffPurchaseEditViewController.onlyShow = true;
                            mainUIController.setCenter(page);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        salesStaffPurchaseEditViewController.setForDetailView((PurchaseVO) bill);
                    } else {
                        salesStaffPurchaseOrderViewController.showPurchaseDetailView(purchaseBill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    salesStaffPurchaseOrderViewController.deletePurchase(purchaseBill);
                }
            });
        }
        else if(bill.type==BillType.RETURN){
            PurchaseVO purchaseBill = (PurchaseVO) bill;
            circle.setStyle("-fx-Stroke:#FFCCFF");
            billType.setText("退");
            billType.setTextFill(Color.web("#FFCCFF"));
            billCreater.setText(purchaseBill.user);
            billMoney.setText(Money.getMoneyString(purchaseBill.sum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (onlyShow){
                        Pane page = null;
                        mainUIController.saveView();
                        SalesStaffReturnEditViewController salesStaffReturnEditViewController = null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/salesStaff/ReturnOrderEdit.fxml"));
                            page = pageLoader.load();
                            salesStaffReturnEditViewController = pageLoader.getController();
                            salesStaffReturnEditViewController.setMainUIController(mainUIController);
                            salesStaffReturnEditViewController.onlyShow = true;
                            mainUIController.setCenter(page);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        salesStaffReturnEditViewController.setForDetailView((PurchaseVO) bill);
                    }else{
                        salesStaffReturnOrderViewController.showReturnDetailView(purchaseBill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    salesStaffReturnOrderViewController.deleteReturn(purchaseBill);
                }
            });
        }
        else if(bill.type==BillType.SALES){
            SalesVO salesBill = (SalesVO) bill;
            circle.setStyle("-fx-Stroke:#FF0033");
            billType.setText("销");
            billType.setTextFill(Color.web("#FF0033"));
            billCreater.setText(salesBill.user);
            billMoney.setText(Money.getMoneyString(salesBill.afterSum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (onlyShow){
                        mainUIController.saveView();
                        Pane page = null;
                        SalesStaffSalesEditViewController salesStaffSalesEditViewController = null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesOrderEdit.fxml"));
                            page = pageLoader.load();
                            salesStaffSalesEditViewController = pageLoader.getController();
                            salesStaffSalesEditViewController.setMainUIController(mainUIController);
                            salesStaffSalesEditViewController.onlyShow = true;
                            mainUIController.setCenter(page);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        salesStaffSalesEditViewController.setForDetailView(salesBill);
                    }else{
                        salesStaffSalesOrderViewController.showSalesOrderDetailView(salesBill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    salesStaffSalesOrderViewController.deleteSales(salesBill);
                }
            });
        }
        else if(bill.type==BillType.SALESRETURN){
            SalesVO salesBill = (SalesVO) bill;
            circle.setStyle("-fx-Stroke:#FF0033");
            billType.setText("退");
            billType.setTextFill(Color.web("#FF0033"));
            billCreater.setText(salesBill.user);
            billMoney.setText(Money.getMoneyString(salesBill.afterSum));
            DetailIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (onlyShow){
                        mainUIController.saveView();
                        Pane page = null;
                        SalesStaffSalesReturnEditViewController salesStaffSalesReturnEditViewController= null;
                        try {
                            FXMLLoader pageLoader = new FXMLLoader();
                            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesReturnOrderEdit.fxml"));
                            page = pageLoader.load();
                            salesStaffSalesReturnEditViewController = pageLoader.getController();
                            salesStaffSalesReturnEditViewController.setMainUIController(mainUIController);
                            salesStaffSalesReturnEditViewController.onlyShow = true;
                            mainUIController.setCenter(page);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        salesStaffSalesReturnEditViewController.setForDetailView(salesBill);
                    }else{
                        salesStaffSalesReturnOrderViewController.showSalesReturnDetailView(salesBill);
                    }
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    salesStaffSalesReturnOrderViewController.deleteSalesReturn(salesBill);
                }
            });
        }
    }
    public void hideCheckbox(){
        checkBox.setVisible(false);
    }
    public void showDeleteIcon(){
        DeleteIcon.setVisible(true);
    }

    public void setFinancialReceiptController(FinancialReceiptController financialReceiptController){
        this.financialReceiptController = financialReceiptController;
    }
    public void setFinancialPaymentController(FinancialPaymentController financialPaymentController){
        this.financialPaymentController = financialPaymentController;
    }

    public void setFinancialCashBillController(FinancialCashBillController financialCashBillController){
        this.financialCashBillController = financialCashBillController;
    }
    public void setInventorySyncController(InventorySyncController inventorySyncController){
        this.inventorySyncController = inventorySyncController;
    }
    public void setSalesStaffPurchaseOrderViewController(SalesStaffPurchaseOrderViewController salesStaffPurchaseOrderViewController){
        this.salesStaffPurchaseOrderViewController = salesStaffPurchaseOrderViewController;
    }

    public void setSalesStaffReturnOrderViewController(SalesStaffReturnOrderViewController salesStaffReturnOrderViewController){
        this.salesStaffReturnOrderViewController = salesStaffReturnOrderViewController;
    }

    public void setSalesStaffSalesOrderViewController(SalesStaffSalesOrderViewController salesStaffSalesOrderViewController){
        this.salesStaffSalesOrderViewController = salesStaffSalesOrderViewController;
    }

    public void setSalesStaffSalesReturnOrderViewController(SalesStaffSalesReturnOrderViewController salesStaffSalesReturnOrderViewController){
        this.salesStaffSalesReturnOrderViewController = salesStaffSalesReturnOrderViewController;
    }

    public void setFinancialDocumentDetailsController(FinancialDocumentDetailsController financialDocumentDetailsController) {
        onlyShow = true;
        this.financialDocumentDetailsController = financialDocumentDetailsController;
    }
    public boolean isSelected(){
        return checkBox.isSelected();
    }
    public BillVO getBill(){
        return bill;
    }
    public void setMainUIController(MainUIController mainUIController){
        this.mainUIController = mainUIController;
    }
    private void showInventoryBillDetail(InventoryBillVO inventoryBill){
        if (onlyShow){
            mainUIController.saveView();
            Pane page = null;
            InventorySyncEditController inventorySyncEditController = null;
            try {
                FXMLLoader pageLoader = new FXMLLoader();
                pageLoader.setLocation(getClass().getResource("/view/inventory/SyncEdit.fxml"));
                page = pageLoader.load();
                inventorySyncEditController = pageLoader.getController();
                inventorySyncEditController.setMainUIController(mainUIController);
                inventorySyncEditController.onlyShow = true;
                mainUIController.setCenter(page);
            } catch (IOException e) {
                e.printStackTrace();
            }
            inventorySyncEditController.setForDetailView((InventoryBillVO) bill);
        }else{
            inventorySyncController.setDetailView(inventoryBill);
        }
    }
}
