package ui.viewcontroller.SalesStaff;

import bl.salesbl.SalesBLFactory;
import bl.salesbl.SalesController;
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
import vo.SalesVO;

import java.io.IOException;
import java.util.ArrayList;

public class SalesStaffSalesReturnOrderViewController {
	SalesStaffViewController salesStaffViewController;
	SalesStaffSalesReturnEditViewController salesStaffSalesReturnEditViewController;
    SalesBLService salesBLService = SalesBLFactory.getSalesBLService();


    ArrayList<SalesVO> draft;
    ArrayList<SalesVO> submitted;
    ArrayList<SalesVO> pass;
    ArrayList<SalesVO> failed;


    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    BillPane billPane;


    @FXML
    VBox vBox;
    @FXML
    Label addIcon;

    @FXML
    public void initialize(){
        addIcon.setText("\ue61e");

        draft = salesBLService.getSalesreturnOrderByState(BillState.DRAFT);
        submitted = salesBLService.getSalesreturnOrderByState(BillState.SUBMITTED);
        pass = salesBLService.getSalesreturnOrderByState(BillState.PASS);
        failed = salesBLService.getSalesreturnOrderByState(BillState.FAILED);

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
        ArrayList<SalesVO> bills = null;
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
                BillController salesStaffSalesReturnController = loader.getController();
                salesStaffSalesReturnController.hideCheckbox();
                if (tab == "草稿单据"){
                    salesStaffSalesReturnController.showDeleteIcon();
                }
                salesStaffSalesReturnController.setSalesStaffSalesReturnOrderViewController(this);
                salesStaffSalesReturnController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showSalesReturnOrderList(){
    	salesStaffViewController.showSalesReturnOrderView();
    }
    
    public void clickAddButton(){
    	showSalesEditView();
    	salesStaffSalesReturnEditViewController.addSalesReturnOrder();
    }
    
    public void showSalesEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesReturnOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffSalesReturnEditViewController = pageLoader.getController();
            salesStaffSalesReturnEditViewController.setSalesStaffSalesReturnOrderViewController(this);
            salesStaffViewController.showEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void deleteSalesReturn(SalesVO salesVO) {
        ResultMessage re = salesBLService.deleteSales(salesVO);
        if (re == ResultMessage.SUCCESS){
            showSalesReturnOrderList();
        }
    }
    public void showSalesReturnDetailView(SalesVO salesVO){
        showSalesEditView();
        salesStaffSalesReturnEditViewController.setForDetailView(salesVO);
    }
}
