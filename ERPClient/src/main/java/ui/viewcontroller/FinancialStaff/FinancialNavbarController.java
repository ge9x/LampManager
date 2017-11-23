package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;


/**
 * Created by Kry·L on 2017/11/4.
 */
public class FinancialNavbarController {
    private FinancialViewController financialViewController;
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
    public void initialize() {
        AccountIcon.setText("\ue982");
        ReceiptIcon.setText("\ue60e");
        PaymentIcon.setText("\ue60f");
        CashIcon.setText("\ue626");
        SalesDetailsIcon.setText("\ue62a");
        DocumentDetailsIcon.setText("\ue644");
        ProfitIcon.setText("\ue677");
        InitIcon.setText("\ue6eb");
    }

    public void clickAccountButton(){
        financialViewController.showAccountView();
    }
    public void clickReceiptButton(){
        financialViewController.showReceiptView();
    }

    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }
}
