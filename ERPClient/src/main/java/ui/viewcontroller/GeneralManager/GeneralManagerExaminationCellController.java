package ui.viewcontroller.GeneralManager;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.viewcontroller.FinancialStaff.FinancialCashBillEditController;
import ui.viewcontroller.FinancialStaff.FinancialPaymentEditController;
import ui.viewcontroller.FinancialStaff.FinancialReceiptEditController;
import ui.viewcontroller.SalesStaff.SalesStaffPurchaseEditViewController;
import ui.viewcontroller.SalesStaff.SalesStaffReturnEditViewController;
import ui.viewcontroller.SalesStaff.SalesStaffSalesEditViewController;
import util.BillType;
import util.Money;
import vo.AccountBillVO;
import vo.BillVO;
import vo.CashBillVO;
import vo.InventoryBillVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class GeneralManagerExaminationCellController {

	BillVO bill;
	GeneralManagerExaminationViewController generalManagerExaminationViewController;
	SalesStaffSalesEditViewController salesStaffSalesEditViewController;
	SalesStaffPurchaseEditViewController salesStaffPurchaseEditViewController;
	SalesStaffReturnEditViewController salesStaffReturnEditViewController;
	FinancialCashBillEditController financialCashBillEditController;
	FinancialPaymentEditController financialPaymentEditController;
	FinancialReceiptEditController financialReceiptEditController;
	
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
	JFXCheckBox checkBox;
	
	@FXML
    public void initialize(){
		billIDIcon.setText("\ue67c");
		billDateIcon.setText("\ue60a");
		billCreaterIcon.setText("\ue609");
		billMoneyIcon.setText("\ue611");
        DetailIcon.setText("\ue89d");
    }
	
	public void setGeneralManagerExaminationViewController(GeneralManagerExaminationViewController generalManagerExaminationViewController){
		this.generalManagerExaminationViewController = generalManagerExaminationViewController;
	}
	
	public void setBill(BillVO bill){
		this.bill = bill;
		setBillType(bill);
		billID.setText(bill.ID);
		billDate.setText(String.valueOf(bill.date));
	}
	
	public void setBillType(BillVO bill){
		if(bill.type==BillType.CASH){
			CashBillVO cashBill = (CashBillVO) bill;
			circle.setStyle("-fx-Stroke:#0066FF");
			billType.setText("现");
			billType.setTextFill(Color.web("#0066FF"));
			billCreater.setText(cashBill.userName);
			billMoney.setText(Money.getMoneyString(cashBill.sum));
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
		}
		else if(bill.type==BillType.RECEIPT){
			AccountBillVO accountBill = (AccountBillVO) bill;
			circle.setStyle("-fx-Stroke:#00FF99");
			billType.setText("收");
			billType.setTextFill(Color.web("#00FF99"));
			billCreater.setText(accountBill.userName);
			billMoney.setText(Money.getMoneyString(accountBill.sum));
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
	
	public void clickDetailButton(){
		Pane pane = null;
		if(bill.type==BillType.PURCHASE){
			pane = showPurchaseOrderBill();
		}
		else if(bill.type==BillType.SALES){
			pane = showSalesOrderBill();
		}
		else if(bill.type==BillType.RETURN){
			pane = showReturnOrderBill();
		}
		else if(bill.type==BillType.CASH){
			pane = showCashBill();
		}
		else if(bill.type==BillType.PAYMENT){
			pane = showPaymentBill();
		}
		else if(bill.type==BillType.RECEIPT){
			pane = showReceiptBill();
		}
		generalManagerExaminationViewController.showBillDetail(pane);
	}
	
	public Pane showSalesOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffSalesEditViewController = pageLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
		return page;
	}
	
	public Pane showPurchaseOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/PurchaseOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffPurchaseEditViewController = pageLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
		return page;
	}
	
	public Pane showReturnOrderBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/ReturnOrderEdit.fxml"));
            page = pageLoader.load();
            salesStaffReturnEditViewController = pageLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
		return page;
	}
	
	public Pane showCashBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/CashBillEdit.fxml"));
            page = pageLoader.load();
            financialCashBillEditController = pageLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
		financialCashBillEditController.setForDetailView((CashBillVO) bill);
		return page;
	}
	
	public Pane showPaymentBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/PaymentEdit.fxml"));
            page = pageLoader.load();
            financialPaymentEditController = pageLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
		financialPaymentEditController.setForDetailView((AccountBillVO) bill);
		return page;
	}
	
	public Pane showReceiptBill(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/ReceiptEdit.fxml"));
            page = pageLoader.load();
            financialReceiptEditController = pageLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
		financialReceiptEditController.setForDetailView((AccountBillVO) bill);
		return page;
	}
}
