package ui.viewcontroller.FinancialStaff;

import bl.accountbl.Account;
import bl.formbl.FormController;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBill;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.FormBLService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import util.BillType;
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
    FormBLService formBLService = new FormController();

    ArrayList<BillVO> bills;

    ArrayList<BillVO> receipts;
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
    ComboBox filterType;

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
        redButton.setText("\ue635");
        redCopyButton.setText("\ue68c");


        input = new DocumentDetailsInput(StartDate.getValue().toString(), EndDate.getValue().toString(), null, null, null);

        billPane = new BillPane("全部","库存报溢单","库存报损单","库存赠送单",
                "进货单","进货退货单","销售单","销售退货单",
                "收款单","付款单","现金费用单");
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
        switch (tab){
            case "全部": break;
            case "库存报溢单": input.billType = BillType.OVERFLOW;break;
            case "库存报损单": input.billType = BillType.LOSS; break;
            case "库存赠送单": input.billType = BillType.GIFT; break;
            case "进货单": input.billType = BillType.PURCHASE; ;break;
            case "进货退货单": input.billType = BillType.RETURN;break;
            case "销售单": input.billType = BillType.SALES;break;
            case "销售退货单": input.billType = BillType.SALESRETURN;break;
            case "收款单": input.billType = BillType.RECEIPT; break;
            case "付款单": input.billType = BillType.PAYMENT;break;
            case "现金费用单": input.billType = BillType.CASH; break;
        }
        bills = formBLService.getDocumentDetails(input);
        for (int i = 0; i < bills.size(); i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/common/bill.fxml"));
                VBox node = loader.load();
                fxmlLoaders.add(loader);
                billNodes.add(node);
                BillController billController = loader.getController();
                billController.hideCheckbox();
//                billController.setFinancialDocumentDetailsController(this);
                billController.setBill(bills.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }

    public void clickRedButton(MouseEvent mouseEvent) {

    }

    public void clickRedCopyButton(MouseEvent mouseEvent) {

    }

    public void clickSearchButton(MouseEvent mouseEvent) {
    }
}
