package ui.viewcontroller.FinancialStaff;

import bl.financialbl.AccountBill;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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

    @FXML
    JFXTabPane tabPane;

    FinancialViewController financialViewController;
    FinancialReceiptEditController financialReceiptEditController;

    FinanceBLService financeBLService = new FinanceBLService_Stub();

    ArrayList<AccountBillVO> draft;
    ArrayList<AccountBillVO> submitted;
    ArrayList<AccountBillVO> pass;
    ArrayList<AccountBillVO> failed;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();


    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");

        draft = financeBLService.getDraftAccountBills();
        submitted = financeBLService.getSubmittedAccountBills();
        pass = financeBLService.getPassAccountBills();
        failed = financeBLService.getFailedAccountBills();


        initTabPane();
    }

    public void initTabPane(){
        Tab draftTab = new Tab();
        Tab submittedTab = new Tab();
        Tab passTab = new Tab();
        Tab failedTab = new Tab();

        draftTab.setText("草稿单据");
        submittedTab.setText("待审批单据");
        passTab.setText("审批通过单据");
        failedTab.setText("审批不通过单据");

        TilePane billPane = new TilePane();
        billPane.setPrefColumns(3);

        draftTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                loadBills();
                billPane.getChildren().addAll(billNodes);
                ((Tab)event.getSource()).setContent(billPane);
            }
        });
        billPane.getChildren().addAll(billNodes);
        draftTab.setContent(billPane);


        tabPane.getTabs().addAll(draftTab,submittedTab,passTab,failedTab);
        tabPane.getSelectionModel().selectFirst();
    }
    public void loadBills(){
        for (int i = 0; i < draft.size(); i++){
           FXMLLoader loader = fxmlLoaders.get(i);
//           loader.setLocation(getClass().getResource());
        }
    }
    public void showReceiptList() {
        financialViewController.showReceiptView();
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
