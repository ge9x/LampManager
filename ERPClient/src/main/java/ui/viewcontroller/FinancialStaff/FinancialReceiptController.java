package ui.viewcontroller.FinancialStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class FinancialReceiptController {
    @FXML
    Label addIcon;

    FinancialViewController financialViewController;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    public void showReceiptList(){

    }
    public void clickAddButton(){

    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

}
