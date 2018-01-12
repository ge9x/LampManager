package ui.viewcontroller.FinancialStaff;

import bl.accountbl.Account;
import bl.userbl.User;
import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


/**
 * Created by Kry·L on 2017/11/4.
 */
public class FinancialNavbarController {
    private FinancialViewController financialViewController;
    UserInfo userInfo = UserBLFactory.getInfo();
    @FXML
    Label AccountIcon;

    @FXML
    Label ReceiptIcon;

    @FXML
    Label PaymentIcon;

    @FXML
    Label CashIcon;

    @FXML
    Label SalesDetailsIcon;

    @FXML
    Label DocumentDetailsIcon;

    @FXML
    Label ProfitIcon;

    @FXML
    Label InitIcon;

    @FXML
    Circle avatar;

    @FXML
    Label userName;
    
    @FXML
    Rectangle AccountRec, AccountSelectRec, ReceiptRec, ReceiptSelectRec, PaymentRec, PaymentSelectRec, CashRec, CashSelectRec, SalesDetailsRec,
    			SalesDetailsSelectRec, DocumentDetailsRec, DocumentDetailsSelectRec, ProfitRec, ProfitSelectRec, InitRec, InitSelectRec;


    @FXML
    public void initialize() {
        AccountIcon.setText("\ue982");
        ReceiptIcon.setText("\ue60e");
        PaymentIcon.setText("\ue60f");
        CashIcon.setText("\ue626");
        SalesDetailsIcon.setText("\ue62a");
        DocumentDetailsIcon.setText("\ue644");
        ProfitIcon.setText("\ue677");
        InitIcon.setText("\ue6eb");

        Image img = new Image("images/avatar/financial.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }

    public void clickAccountButton(){
    	showHighlight(AccountRec, AccountSelectRec);
        financialViewController.showAccountView();
    }
    public void clickReceiptButton(){
    	showHighlight(ReceiptRec, ReceiptSelectRec);
        financialViewController.showReceiptView();
    }
    public void clickPaymentButton() {
    	showHighlight(PaymentRec, PaymentSelectRec);
        financialViewController.showPaymentView();
    }
    public void clickCashBillButton(){
    	showHighlight(CashRec, CashSelectRec);
        financialViewController.showCashBillView();
    }
    public void clickSalesDetailsButton(){
    	showHighlight(SalesDetailsRec, SalesDetailsSelectRec);
        financialViewController.showSalesDetailsView();
    }
    public void clickDocumentDetailsButton(){
    	showHighlight(DocumentDetailsRec, DocumentDetailsSelectRec);
        financialViewController.showDocumentDetailsView();
    }
    public void clickProfitView(){
    	showHighlight(ProfitRec, ProfitSelectRec);
        financialViewController.showProfitView();
    }
    public void clickInitView(){ 
    	showHighlight(InitRec, InitSelectRec);
    	financialViewController.showInitView();
    }

    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }
    
    public void showHighlight(Rectangle rec, Rectangle selectRec){
    	AccountRec.setFill(Paint.valueOf("#272727"));
    	AccountSelectRec.setVisible(false);
    	ReceiptRec.setFill(Paint.valueOf("#272727"));
    	ReceiptSelectRec.setVisible(false);
    	PaymentRec.setFill(Paint.valueOf("#272727"));
    	PaymentSelectRec.setVisible(false);
    	CashRec.setFill(Paint.valueOf("#272727"));
		CashSelectRec.setVisible(false);
		SalesDetailsRec.setFill(Paint.valueOf("#272727"));
		SalesDetailsSelectRec.setVisible(false);
		DocumentDetailsRec.setFill(Paint.valueOf("#272727"));
		DocumentDetailsSelectRec.setVisible(false);
		ProfitRec.setFill(Paint.valueOf("#272727"));
		ProfitSelectRec.setVisible(false);
		InitRec.setFill(Paint.valueOf("#272727"));
		InitSelectRec.setVisible(false);
    	
    	rec.setFill(Paint.valueOf("#000000"));
    	selectRec.setVisible(true);
    }
}
