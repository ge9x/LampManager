package ui.viewcontroller.FinancialStaff;

import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import vo.AccountBillVO;
import vo.AccountVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/25.
 */

public class FinancialPaymentController {


    @FXML
    Label addIcon;

    @FXML
    JFXTabPane tabPane;

    FinancialViewController financialViewController;
    FinancialPaymentEditController financialPaymentEditController;

    FinanceBLService financeBLService = new FinanceBLService_Stub();
    FinanceBLService financeBLService2 = new FinanceController();

    ArrayList<AccountBillVO> draft;
    ArrayList<AccountBillVO> submitted;
    ArrayList<AccountBillVO> pass;
    ArrayList<AccountBillVO> failed;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    TilePane billPane;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");
    }
    public void showPaymentList(){
        financialViewController.showPaymentView();
    }
    public void clickAddButton(){
        showPaymentEditView();
        financialPaymentEditController.addPayment();
    }
    public void showPaymentEditView(){
        try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/financialStaff/PaymentEdit.fxml"));
            Pane page = pageLoader.load();
            financialPaymentEditController = pageLoader.getController();
            financialPaymentEditController.setFinancialPaymentController(this);
            financialViewController.showPaymentEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }




}
