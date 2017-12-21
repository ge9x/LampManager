package ui.viewcontroller.FinancialStaff;

import bl.accountbl.Account;
import bl.formbl.FormController;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBill;
import blservice.formblservice.DocumentDetailsInput;
import blservice.formblservice.FormBLService;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import ui.component.BillPane;
import ui.viewcontroller.common.BillController;
import util.BillType;
import vo.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/11.
 */
public class FinancialDocumentDetailsController {
    FinancialViewController financialViewController;
    FormBLService formBLService = new FormController();

    ArrayList<BillVO> bills;

    ArrayList<BillVO> receipts;
//    ArrayList<BillVO> payments;
//    ArrayList<BillVO> cashBills;
//
//    ArrayList<BillVO> overflows;
//    ArrayList<BillVO> losses;
//    ArrayList<BillVO> gifts;
//
//    ArrayList<BillVO> sales;
//    ArrayList<BillVO> salesReturns;
//    ArrayList<BillVO> purchases;
//    ArrayList<BillVO> purchaseReturns;

    ArrayList<VBox> billNodes = new ArrayList<>();
    ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
    BillPane billPane;
    DocumentDetailsInput input;

    @FXML
    VBox vBox;
    @FXML
    public void initialize(){
        input = new DocumentDetailsInput("2017-02-02","2017-10-22",null,null,null);
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

}
