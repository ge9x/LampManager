package ui.viewcontroller.FinancialStaff;

import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import vo.AccountBillVO;
import vo.AccountVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class FinancialReceiptController {


    @FXML
    Label addIcon;

    FinancialViewController financialViewController;
    FinancialReceiptEditController financialReceiptEditController;

    FinanceBLService financeBLService = new FinanceBLService_Stub();
    AccountBillVO accountBillVO;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    public void showReceiptList(){

    }
    public void clickAddButton(){
        showReceiptEditView();
        financialReceiptEditController.addReceipt();
    }
    public void showReceiptEditView(){
        try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/ReceiptEdit.fxml"));
            Pane page = pageLoader.load();
            financialReceiptEditController = pageLoader.getController();
            financialReceiptEditController.setFinancialReceiptController(this);
            financialViewController.showReceiptEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }




}
