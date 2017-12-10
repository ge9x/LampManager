package ui.viewcontroller.common;

import bl.financialbl.AccountBill;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.viewcontroller.FinancialStaff.FinancialCashBillController;
import ui.viewcontroller.FinancialStaff.FinancialPaymentController;
import ui.viewcontroller.FinancialStaff.FinancialReceiptController;
import ui.viewcontroller.InventoryStaff.InventorySyncController;
import ui.viewcontroller.SalesStaff.SalesStaffPurchaseOrderViewController;
import util.BillType;
import util.Money;
import vo.*;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class BillController {

    BillVO bill;
    FinancialReceiptController financialReceiptController;
    FinancialPaymentController financialPaymentController;
    FinancialCashBillController financialCashBillController;
    SalesStaffPurchaseOrderViewController salesStaffPurchaseOrderViewController;
    InventorySyncController inventorySyncController;

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
                    financialCashBillController.showCashBillDetailView(cashBill);
                }
            });
            DeleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    financialCashBillController.deleteCashBill(cashBill);
                }
            });
        }
        else if(bill.type==BillType.GIFT){
            InventoryBillVO inventoryBill = (InventoryBillVO) bill;
            circle.setStyle("-fx-Stroke:#FFCC00");
            billType.setText("赠");
            billType.setTextFill(Color.web("#FFCC00"));
            billCreater.setText(inventoryBill.user);
            billMoney.setText(Money.getMoneyString(0.0));
        }
        else if(bill.type==BillType.LOSS){
            InventoryBillVO inventoryBill = (InventoryBillVO) bill;
            circle.setStyle("-fx-Stroke:#99CCFF");
            billType.setText("损");
            billType.setTextFill(Color.web("#99CCFF"));
            billCreater.setText(inventoryBill.user);
            billMoney.setText(Money.getMoneyString(0.0));
        }
        else if(bill.type==BillType.OVERFLOW){
            InventoryBillVO inventoryBill = (InventoryBillVO) bill;
            circle.setStyle("-fx-Stroke:#99CCFF");
            billType.setText("溢");
            billType.setTextFill(Color.web("#99CCFF"));
            billCreater.setText(inventoryBill.user);
            billMoney.setText(Money.getMoneyString(0.0));
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
                    financialPaymentController.showPaymentDetailView(accountBill);
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
                    financialReceiptController.showReceiptDetailView(accountBill);
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
        }
        else if(bill.type==BillType.RETURN){
            PurchaseVO purchaseBill = (PurchaseVO) bill;
            circle.setStyle("-fx-Stroke:#FFCCFF");
            billType.setText("退");
            billType.setTextFill(Color.web("#FFCCFF"));
            billCreater.setText(purchaseBill.user);
            billMoney.setText(Money.getMoneyString(purchaseBill.sum));
        }
        else if(bill.type==BillType.SALES){
            SalesVO salesBill = (SalesVO) bill;
            circle.setStyle("-fx-Stroke:#FF0033");
            billType.setText("销");
            billType.setTextFill(Color.web("#FF0033"));
            billCreater.setText(salesBill.user);
            billMoney.setText(Money.getMoneyString(salesBill.afterSum));
        }
        else if(bill.type==BillType.SALESRETURN){
            SalesVO salesBill = (SalesVO) bill;
            circle.setStyle("-fx-Stroke:#FF0033");
            billType.setText("退");
            billType.setTextFill(Color.web("#FF0033"));
            billCreater.setText(salesBill.user);
            billMoney.setText(Money.getMoneyString(salesBill.afterSum));
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

}
