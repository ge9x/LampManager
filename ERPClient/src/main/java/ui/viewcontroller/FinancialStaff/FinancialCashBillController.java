package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialCashBillController {
    FinancialViewController financialViewController;
    FinancialCashBillEditController financialCashBillEditController;

    @FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    public void showCashBillList(){
        financialViewController.showCashBillView();
    }
    public void clickAddButton(){
        showCashBillEditView();
        financialCashBillEditController.addCashBill();
    }
    public void showCashBillEditView(){
        try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/CashBillEdit.fxml"));
            Pane page = pageLoader.load();
            financialCashBillEditController = pageLoader.getController();
            financialCashBillEditController.setFinancialCashBillController(this);
            financialViewController.showCashBillEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }
}
