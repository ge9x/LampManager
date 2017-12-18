package ui.viewcontroller.FinancialStaff;

import bl.initializationbl.InitializationController;
import blservice.initializationblservice.InitializationBLService;
import javafx.fxml.FXML;

/**
 * Created by Kry·L on 2017/12/18.
 */
public class FinancialInitController {
    FinancialViewController financialViewController;
    InitializationBLService initializationBLService = new InitializationController();

    @FXML
    public void initialize() {
        initClassificationTable();

    }

    public void initClassificationTable(){

    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }


}
