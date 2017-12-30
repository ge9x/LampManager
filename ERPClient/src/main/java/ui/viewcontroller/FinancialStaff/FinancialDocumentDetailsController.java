package ui.viewcontroller.FinancialStaff;

import bl.accountbl.Account;
import bl.formbl.FormBLFactory;
import bl.formbl.FormController;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBill;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.FormBLService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.component.BillPane;
import ui.component.DialogFactory;
import ui.viewcontroller.common.BillController;
import ui.viewcontroller.common.MainUIController;
import util.BillState;
import util.BillType;
import util.FilterType;
import util.ResultMessage;
import vo.*;

import javax.xml.soap.Text;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/11.
 */
public class FinancialDocumentDetailsController {
    FinancialViewController financialViewController;
    MainUIController mainUIController;
    FormBLService formBLService =  FormBLFactory.getBLService();

    ArrayList<BillVO> bills;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    BillPane billPane;
    DocumentDetailsInput input;


    @FXML
    VBox vBox;

    @FXML
    JFXDatePicker StartDate, EndDate;

    @FXML
    JFXButton redButton,redCopyButton;

    @FXML
    ComboBox<String> filterType;

    @FXML
    TextField keyword;

    @FXML
    Label searchIcon;

    @FXML
    public void initialize(){

        searchIcon.setText("\ue69d");

        String initDate = formBLService.getStartDate();
        StartDate.setValue(LocalDate.parse(initDate));
        EndDate.setValue(LocalDate.now());
        StartDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                input.startDate = StartDate.getValue().toString();
                loadBills(billPane.getSelected());
            }
        });
        EndDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                input.endDate = EndDate.getValue().toString();
                loadBills(billPane.getSelected());
            }
        });



        input = new DocumentDetailsInput(StartDate.getValue().toString(), EndDate.getValue().toString(), null, null, null);

        billPane = new BillPane("报溢单","报损单",
                "进货单","进货退货单","销售单","销售退货单",
                "收款单","付款单","现金费用单");

    }
    public void initTabs(){
        ArrayList<Tab> tabs = billPane.getAllTabs();
        for (int i = 0; i < tabs.size(); i++){
            tabs.get(i).setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    Tab tab = (Tab)event.getSource();
                    if (tab.isSelected()){
                        input.billType = BillType.getEnumByValue(tab.getText());
                        loadBills(tab.getText());
                        changeFilter(BillType.getEnumByValue(tab.getText()));
                    }
                }
            });
        }
    }
    public void changeFilter(BillType type){
        filterType.getItems().clear();
        keyword.clear();
        switch (type){
            case RECEIPT:
            case PAYMENT:filterType.getItems().addAll("客户");break;
            case PURCHASE:
            case RETURN:filterType.getItems().addAll("仓库","客户");break;
            case SALES:
            case SALESRETURN: filterType.getItems().addAll("业务员","仓库","客户");break;
            case OVERFLOW:
            case LOSS:filterType.getItems().addAll("仓库");break;
        }
        input.filterType = null;
    }
    public void loadBills(String tab){
        bills = formBLService.getDocumentDetails(input);
        billNodes.clear();
        fxmlLoaders.clear();
        for (int i = 0; i < bills.size(); i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/common/bill.fxml"));
                VBox node = loader.load();
                fxmlLoaders.add(loader);
                billNodes.add(node);
                BillController billController = loader.getController();
                billController.setFinancialDocumentDetailsController(this);
                billController.setMainUIController(mainUIController);
                billController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        billPane.setContent(billPane.getTabByName(billPane.getSelected()),billNodes);
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

    public void clickRedButton(MouseEvent mouseEvent) {
        for (FXMLLoader loader:fxmlLoaders){
            BillController controller = loader.getController();
            if (controller.isSelected()){
                BillVO billVO = controller.getBill();
                ResultMessage re = formBLService.redCover(billVO);
                Dialog dialog = DialogFactory.getInformationAlert();
                dialog.setHeaderText("红冲单据"+ billVO.ID + re.toString());
                dialog.showAndWait();
            }
        }
    }

    public void clickRedCopyButton(MouseEvent mouseEvent) {
        for (FXMLLoader loader:fxmlLoaders){
            BillController controller = loader.getController();
            if (controller.isSelected()){
                BillVO billVO = controller.getBill();
                ResultMessage re = formBLService.redCoverAndCopy(billVO);
                Dialog dialog = DialogFactory.getInformationAlert();
                dialog.setHeaderText("红冲并复制单据" + billVO.ID + re.toString());
                dialog.showAndWait();
            }
        }
    }

    public void clickSearchButton(MouseEvent mouseEvent) {
        FilterType type = FilterType.getEnumByValue(filterType.getSelectionModel().getSelectedItem());
        input.filterType = type;
        input.keyword = keyword.getText();
        loadBills(billPane.getSelected());
    }
    public void setForGeneralMananger(){
        redButton.setVisible(false);
        redCopyButton.setVisible(false);
    }

    public void setMainUIController(MainUIController mainUIController) {

        this.mainUIController = mainUIController;
    }
    public void initView(){
        initTabs();
        vBox.getChildren().add(billPane.getTabPane());
        billPane.getTabPane().getSelectionModel().selectLast();
        billPane.getTabPane().getSelectionModel().selectFirst();
    }
}
