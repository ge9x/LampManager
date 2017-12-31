package ui.viewcontroller.FinancialStaff;

import bl.financialbl.FinanceBLFactory;
import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import ui.viewcontroller.common.MainUIController;
import util.BillState;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.CashBillVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialCashBillController {
    FinancialViewController financialViewController;
    FinancialCashBillEditController financialCashBillEditController;
    MainUIController mainUIController;

    FinanceBLService financeBLService = FinanceBLFactory.getBLService();

    ArrayList<CashBillVO> draft;
    ArrayList<CashBillVO> submitted;
    ArrayList<CashBillVO> pass;
    ArrayList<CashBillVO> failed;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    BillPane billPane;

    @FXML
    Label addIcon;

    @FXML
    VBox vBox;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");

        draft = financeBLService.getCashBillByState(BillState.DRAFT);
        submitted = financeBLService.getCashBillByState(BillState.SUBMITTED);
        pass = financeBLService.getCashBillByState(BillState.PASS);
        failed = financeBLService.getCashBillByState(BillState.FAILED);


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
        ArrayList<CashBillVO> bills = null;
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
                financialBillController.setFinancialCashBillController(this);
                financialBillController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void showCashBillList(){
        financialViewController.showCashBillView();
    }
    public void clickAddButton(){
        showCashBillEditView();
        financialCashBillEditController.addCashBill();
    }
    public void showCashBillDetailView(CashBillVO vo){
        showCashBillEditView();
        financialCashBillEditController.setForDetailView(vo);
    }
    public void showCashBillEditView(){
        try{
            mainUIController.saveView();
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
    public void deleteCashBill(CashBillVO cashBillVO){
        ResultMessage re = financeBLService.deleteDraftCashBill(cashBillVO.ID);
        if (re == ResultMessage.SUCCESS){
            showCashBillList();
        }
    }
    public void initBillPane(){
        billPane = new BillPane("草稿单据", "待审批单据", "审批通过单据", "审批不通过单据");
        initTabs();
        vBox.getChildren().add(billPane.getTabPane());
        billPane.getTabPane().getSelectionModel().selectLast();
        billPane.getTabPane().getSelectionModel().selectFirst();
    }
    public void setMainUIController(MainUIController mainUIController){
        this.mainUIController = mainUIController;
    }
}
