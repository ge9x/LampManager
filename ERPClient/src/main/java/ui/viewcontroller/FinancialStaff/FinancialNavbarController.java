package ui.viewcontroller.FinancialStaff;

import bl.userbl.UserController;
import blservice.userblservice.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * Created by Kry·L on 2017/11/4.
 */
public class FinancialNavbarController {
    private FinancialViewController financialViewController;
    UserInfo userInfo = new UserController();
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
    public void initialize() {
        AccountIcon.setText("\ue982");
        ReceiptIcon.setText("\ue60e");
        PaymentIcon.setText("\ue60f");
        CashIcon.setText("\ue626");
        SalesDetailsIcon.setText("\ue62a");
        DocumentDetailsIcon.setText("\ue644");
        ProfitIcon.setText("\ue677");
        InitIcon.setText("\ue6eb");

        Image img = new Image("./images/avatar/financial.jpg");
        avatar.setFill(new ImagePattern(img));
        String userID = userInfo.getCurrentUserID();
        userName.setText(userInfo.getCurrentUserNameByID(userID));
    }

    public void clickAccountButton(){
        financialViewController.showAccountView();
    }
    public void clickReceiptButton(){
        financialViewController.showReceiptView();
    }
    public void clickPaymentButton() {
        financialViewController.showPaymentView();
    }
    public void clickCashBillButton(){
        financialViewController.showCashBillView();
    }
    public void clickSalesDetailsButton(){
        financialViewController.showSalesDetailsView();
    }
    public void clickDocumentDetailsButton(){
        financialViewController.showDocumentDetailsView();
    }
    public void clickProfitView(){
        financialViewController.showProfitView();
    }


    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }
}
