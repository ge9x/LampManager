package ui.viewcontroller.FinancialStaff;

import bean.AccountBillItemBean;
import bl.customerbl.Customer;
import bl.financialbl.AccountBill;
import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import ui.component.DialogFactory;
import util.BillState;
import util.BillType;
import util.Money;
import vo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/23.
 */
public class FinancialPaymentEditController {

    FinancialPaymentController financialPaymentController;
    FinanceBLService financeBLService = new FinanceController();
    ArrayList<AccountBillItemVO> accountBillItems = new ArrayList<>();

    ArrayList<AccountVO> accounts;
    ArrayList<CustomerVO> customers;
    TableView<AccountBillItemBean> itemTable;
    ObservableList<AccountBillItemBean> data =
            FXCollections.observableArrayList();
    DoubleProperty total = new SimpleDoubleProperty(0);

    @FXML
    Label addIcon;

    @FXML
    Label BillID;

    @FXML
    Text Username;

    @FXML
    VBox vbox;

    @FXML
    Text Total;

    @FXML
    ComboBox Customer;

    public void initialize(){
        addIcon.setText("\ue61e");
        String name = financeBLService.getUserID();
        Username.setText(name);
        accounts = financeBLService.getAllAccount();
        customers = financeBLService.getAllCustomer();
        //初始化Customer选择框
        ArrayList<String> customerNames = new ArrayList<>();
        for (CustomerVO customer : customers){
            customerNames.add(customer.customerName);
        }
        Customer.getItems().addAll(customerNames);

        //初始化表格
        itemTable = new TableView<>();
        itemTable.setEditable(false);

        TableColumn accountColumn = new TableColumn("银行账户");
        accountColumn.setPrefWidth(128);
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        TableColumn moneyColumn = new TableColumn("转账金额");
        moneyColumn.setPrefWidth(128);
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        TableColumn remarkColumn = new TableColumn("备注");
        remarkColumn.setPrefWidth(190);
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        itemTable.setItems(data);
        itemTable.getColumns().addAll(accountColumn,moneyColumn,remarkColumn);
        vbox.getChildren().add(itemTable);

        //总额Text与绑定转账金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });

    }
    public void addPayment() {
        String ID = financeBLService.getNewPaymentID();
        BillID.setText(ID);
    }

    public void clickAddButton(){
        Dialog<ArrayList<String>> dialog = getAccountBillItemDialog();
        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            ArrayList<String> values = (ArrayList<String>)result.get();

            AccountVO account = accounts.get(Integer.parseInt(values.get(0)));
            double money = Double.parseDouble(values.get(1));
            String remark = values.get(2);

            AccountBillItemVO accountBillItemVO = new AccountBillItemVO(account,money,remark);
            accountBillItems.add(accountBillItemVO);
            data.add(new AccountBillItemBean(account.accountName,money,remark));
            total.set(total.get()+money);
        }
    }

    public void clickSubmitButton(){
        String customerID = customers.get(Customer.getSelectionModel().getSelectedIndex()).customerID;
        AccountBillVO accountBillVO = new AccountBillVO(LocalDate.now().toString(),BillID.getText(),
                BillState.SUBMITTED,BillType.PAYMENT,customerID,
                Username.getText(),accountBillItems);
        financeBLService.submit(accountBillVO);
        financialPaymentController.showPaymentList();
    }
    public void clickCancelButton(){
        Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("需要保存为草稿吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
                String customerID = "";
                if (Customer.getSelectionModel().getSelectedIndex() >= 0){
                    customerID = customers.get(Customer.getSelectionModel().getSelectedIndex()).customerID;
                }
                AccountBillVO accountBillVO = new AccountBillVO(LocalDate.now().toString(), BillID.getText(),
                        BillState.DRAFT, BillType.PAYMENT, customerID,
                        Username.getText(), accountBillItems);
                financeBLService.save(accountBillVO);
            }

            financialPaymentController.showPaymentList();
        }
    }
    public  Dialog getAccountBillItemDialog(){
        JFXComboBox name = new JFXComboBox();
        ArrayList<String> names = new ArrayList<>();
        for (AccountVO account:accounts){
            names.add(account.accountName);
        }
        name.getItems().addAll(names);

        TextField money = new TextField();
        TextField remarks = new TextField();
        Label label1 = new Label("银行账户");
        Label label2 = new Label("转账金额");
        Label label3 = new Label("备注");
        ArrayList<Label> labels = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        nodes.add(name);
        nodes.add(money);
        nodes.add(remarks);

        Dialog<ArrayList<String>> dialog = DialogFactory.createDialog(labels,nodes);

        Platform.runLater(() -> name.requestFocus());

        //获得输入
        dialog.setResultConverter(dialogButton -> {
            ArrayList<String> result = new ArrayList<>();
            String accountName = String.valueOf(name.getSelectionModel().getSelectedIndex());
            String transferMoney = money.getText();
            String remark = remarks.getText();
            result.add(accountName);
            result.add(transferMoney);
            result.add(remark);
            if (dialogButton == ButtonType.FINISH) {
                return result;
            }
            return null;
        });

        return dialog;
    }

    public void setFinancialPaymentController(FinancialPaymentController financialPaymentController){
        this.financialPaymentController = financialPaymentController;
    }

}
