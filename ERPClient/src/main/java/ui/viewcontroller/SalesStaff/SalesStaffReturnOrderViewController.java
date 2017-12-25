package ui.viewcontroller.SalesStaff;

import bl.salesbl.PurchaseController;
import blservice.salesblservice.SalesBLService;
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
import util.BillState;
import util.ResultMessage;
import vo.PurchaseVO;

import java.io.IOException;
import java.util.ArrayList;

public class SalesStaffReturnOrderViewController {
	SalesStaffViewController salesStaffViewController;
	SalesStaffReturnEditViewController salesStaffReturnEditViewController;

    SalesBLService salesBLService = new PurchaseController();

    ArrayList<PurchaseVO> draft;
    ArrayList<PurchaseVO> submitted;
    ArrayList<PurchaseVO> pass;
    ArrayList<PurchaseVO> failed;

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

        draft = salesBLService.getReturnOrderByState(BillState.DRAFT);
        submitted = salesBLService.getReturnOrderByState(BillState.SUBMITTED);
        pass = salesBLService.getReturnOrderByState(BillState.PASS);
        failed = salesBLService.getReturnOrderByState(BillState.FAILED);

        billPane = new BillPane("草稿单据","待审批单据","审批通过单据","审批不通过单据");
        initTabs();
        vBox.getChildren().add(billPane.getTabPane());
        billPane.getTabPane().getSelectionModel().selectLast();
        billPane.getTabPane().getSelectionModel().selectFirst();
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
        ArrayList<PurchaseVO> bills = null;
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
                BillController salesStaffReturnOrderController = loader.getController();
                salesStaffReturnOrderController.hideCheckbox();
                if (tab == "草稿单据"){
                    salesStaffReturnOrderController.showDeleteIcon();
                }
                salesStaffReturnOrderController.setSalesStaffReturnOrderViewController(this);
                salesStaffReturnOrderController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showReturnOrderList(){
    	salesStaffViewController.showReturnOrderView();
    }
    
    public void clickAddButton(){
    	showReturnEditView();
    	salesStaffReturnEditViewController.addReturnOrder();
    }
    
    public void showReturnEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/ReturnOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffReturnEditViewController = pageLoader.getController();
            salesStaffReturnEditViewController.setSalesStaffReturnOrderViewController(this);
            salesStaffViewController.showReturnOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void deleteReturn(PurchaseVO purchaseVO) {
        ResultMessage re = salesBLService.deletePurchase(purchaseVO);
        if (re == ResultMessage.SUCCESS){
            showReturnOrderList();
        }

    }
    public void showReturnDetailView(PurchaseVO purchaseVO){
        showReturnEditView();
        salesStaffReturnEditViewController.setForDetailView(purchaseVO);
    }
}
