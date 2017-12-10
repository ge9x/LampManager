package ui.viewcontroller.SalesStaff;

import java.io.IOException;
import java.util.ArrayList;

import bl.salesbl.Sales;
import blservice.salesblservice.SalesBLService;
import blstubdriver.SalesBLService_Stub;
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
import vo.SalesVO;

public class SalesStaffSalesOrderViewController {
	
	SalesStaffViewController salesStaffViewController;
	SalesStaffSalesEditViewController salesStaffSalesEditViewController;

    SalesBLService salesBLService = new SalesBLService_Stub();

    ArrayList<SalesVO> draft;
    ArrayList<SalesVO> submitted;
    ArrayList<SalesVO> pass;
    ArrayList<SalesVO> failed;

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

        draft = salesBLService.getSalesOrderByState(BillState.DRAFT);
        submitted = salesBLService.getSalesOrderByState(BillState.SUBMITTED);
        pass = salesBLService.getSalesOrderByState(BillState.PASS);
        failed = salesBLService.getSalesOrderByState(BillState.FAILED);

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
                BillController salesStaffSalesOrderController = loader.getController();
                salesStaffSalesOrderController.hideCheckbox();
                if (tab == "草稿单据"){
                    salesStaffSalesOrderController.showDeleteIcon();
                }
                salesStaffSalesOrderController.setSalesStaffSalesOrderViewController(this);
                salesStaffSalesOrderController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSalesStaffViewController(SalesStaffViewController salesStaffViewController){
    	this.salesStaffViewController = salesStaffViewController;
    }
    
    public void showSalesOrderList(){
    	salesStaffViewController.showSalesOrderView();
    }
    
    public void clickAddButton(){
    	showSalesEditView();
    	salesStaffSalesEditViewController.addSalesOrder();
    }
    
    public void showSalesEditView(){
    	try{
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/salesStaff/SalesOrderEdit.fxml"));
            Pane page = pageLoader.load();
            salesStaffSalesEditViewController = pageLoader.getController();
            salesStaffSalesEditViewController.setSalesStaffSalesOrderViewController(this);
            salesStaffViewController.showSalesOrderEditView(page);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void deleteSales(SalesVO salesVO) {
        ResultMessage re = salesBLService.deleteSales(salesVO);
        if (re == ResultMessage.SUCCESS){
            showSalesOrderList();
        }
    }
}
