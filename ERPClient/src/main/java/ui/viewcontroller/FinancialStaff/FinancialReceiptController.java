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
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.BillVO;

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class FinancialReceiptController {


    @FXML
    Label addIcon;

    @FXML
    VBox vBox;

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
    BillPane billPane;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");

        draft = financeBLService2.getDraftAccountBills();
        submitted = financeBLService2.getSubmittedAccountBills();
        pass = financeBLService2.getPassAccountBills();
        failed = financeBLService2.getFailedAccountBills();

        billPane = new BillPane("草稿单据","待审批单据","审批通过单据","审批不通过单据");
        initTabs();
        vBox.getChildren().add(billPane.getTabPane());
    }
    public void initTabs(){
        ArrayList<Tab> tabs = billPane.getAllTabs();
        for (int i = 0; i < tabs.size(); i++){
            tabs.get(i).setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    Tab tab = (Tab)event.getSource();
                    if (tab.isSelected()){
                        billNodes.clear();
                        fxmlLoaders.clear();
                        loadBills(tab.getText());
                        billPane.setContent(tab,billNodes);
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
                if (tab == "草稿单据"){
                    financialBillController.showDeleteIcon();
                }
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


    public void deleteReceipt(AccountBillVO accountBill) {
        ResultMessage re = financeBLService2.deleteDraftAccountBill(accountBill.ID);
        if (re == ResultMessage.SUCCESS){
            showReceiptList();
        }
    }
}
