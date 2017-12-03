package ui.viewcontroller.FinancialStaff;

import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import ui.viewcontroller.common.BillController;
import vo.AccountBillVO;
import vo.BillVO;

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

        draft = financeBLService2.getDraftAccountBills();
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



        initTabs(draftTab,submittedTab,passTab,failedTab);
        tabPane.getTabs().addAll(draftTab,submittedTab,passTab,failedTab);
        tabPane.getSelectionModel().selectFirst();
    }
    public void initTabs(Tab...tabs){
        for (int i = 0; i < tabs.length; i++){
            tabs[i].setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    Tab tab = (Tab)event.getSource();
                    if (tab.isSelected()){
                        ScrollPane scrollPane = new ScrollPane();
                        billPane = new TilePane();
                        billPane.setPadding(new Insets(10,7,0,10));
                        billPane.setPrefColumns(3);
                        billPane.setHgap(25);
                        billPane.setVgap(20);
                        billPane.getChildren().clear();
                        billNodes.clear();
                        fxmlLoaders.clear();
                        loadBills(tab.getText());
                        billPane.getChildren().addAll(billNodes);
                        scrollPane.setContent(billPane);
                        tab.setContent(scrollPane);
                    }
                }
            });
        }
    }
    public void loadBills(String tab){
        ArrayList<AccountBillVO> bills = null;
        switch (tab){
            case "草稿单据": bills = draft;break;
            case "待审批单据": bills = submitted;break;
            case "审批通过单据": bills = pass;break;
            case "审批不通过单据": bills = failed;break;

        }
        for (int i = 0; i < bills.size(); i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/common/bill.fxml"));
                VBox node = loader.load();
                fxmlLoaders.add(loader);
                billNodes.add(node);
                BillController financialBillController = loader.getController();
                financialBillController.hideCheckbox();
                financialBillController.setFinancialReceiptController(this);
                financialBillController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void showReceiptList() {
        financialViewController.showReceiptView();
    }
    public void clickAddButton(){
        showReceiptEditView();
        financialReceiptEditController.addReceipt();
    }

    public void showReceiptDetailView(AccountBillVO vo){
        showReceiptEditView();
        financialReceiptEditController.setForDetailView(vo);
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
