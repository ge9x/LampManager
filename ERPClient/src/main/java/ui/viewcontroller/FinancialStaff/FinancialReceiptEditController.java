package ui.viewcontroller.FinancialStaff;

import bean.AccountBillItemBean;
import bl.customerbl.Customer;
import bl.financialbl.AccountBill;
import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXButton;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class FinancialReceiptEditController {

    FinancialReceiptController financialReceiptController;
    FinanceBLService financeBLService = new FinanceBLService_Stub();
    FinanceBLService financeBLService2 = new FinanceController();
    ArrayList<AccountBillItemVO> accountBillItems = new ArrayList<>();

    ArrayList<AccountVO> accounts;
    ArrayList<CustomerVO> customers;
    Boolean isNew;

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
    Label title;

    @FXML
    ComboBox Customer;

    @FXML
    JFXButton submitButton;

    @FXML
    JFXButton cancelButton;

    public void initialize(){
        addIcon.setText("\ue61e");
        String name = financeBLService.getUserID();
        Username.setText(name);
        accounts = financeBLService2.getAllAccount();
        customers = financeBLService.getAllCustomer();

        initCustomerCombobox();
        initTable();

        //总额Text与绑定转账金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });

    }

    public void addReceipt() {
        String ID = financeBLService2.getNewReceiptID();
        isNew = true;
        BillID.setText(ID);
    }
    public void initTable(){
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
    }

    public void initCustomerCombobox(){
        //初始化Customer选择框
        ArrayList<String> customerNames = new ArrayList<>();
        customerNames.clear();
        for (CustomerVO customer : customers){
            customerNames.add(customer.customerName);
        }
        Customer.getItems().addAll(customerNames);
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
                BillState.SUBMITTED,BillType.RECEIPT,customerID,
                Username.getText(),accountBillItems);
        financeBLService2.submit(accountBillVO);
        financialReceiptController.showReceiptList();
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
                        BillState.DRAFT, BillType.RECEIPT, customerID,
                        Username.getText(), accountBillItems);

                if (isNew == true){
                    financeBLService2.save(accountBillVO);
                }else{
                    financeBLService2.updateDraft(accountBillVO);
                }
            }

            financialReceiptController.showReceiptList();
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

    public void setFinancialReceiptController(FinancialReceiptController financialReceiptController){
        this.financialReceiptController = financialReceiptController;
    }

    /**
     * 查看单据详情界面
     * 有两种情况
     * 1. 草稿单据，可编辑和删除
     * 2. 已提交单据、审批通过单据和审批失败单据，不可编辑且不可删除
     * @param account
     */
    public void setForDetailView(AccountBillVO account){
        isNew = false;
        BillID.setText(account.ID);

        title.setText("收款单详情");

        addIcon.setVisible(false);

        String customerName = financeBLService.getCustomerNameByID(account.customerID);
        Customer.getItems().clear();
        Customer.getItems().add(customerName);
        Customer.getSelectionModel().selectFirst();
        Customer.setEditable(false);

        /**
         * 返回按钮需要再次询问是否保存为草稿
         */
        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                clickCancelButton();
            }
        });

        //如果是草稿单据，就显示编辑按钮
        if (account.state == BillState.DRAFT){
            submitButton.setText("编 辑");
            submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setForEditView();
                }
            });
        }
        //否则隐藏提交按钮
        else{
            submitButton.setVisible(false);
        }
        for (AccountBillItemVO accountBillItemVO:account.accountBillItems){
            data.add(new AccountBillItemBean(accountBillItemVO.account.accountName,accountBillItemVO.transferMoney,accountBillItemVO.remark));
            total.set(total.get()+accountBillItemVO.transferMoney);
        }
    }

    /**
     * 草稿编辑界面
     * 将隐藏的元素重新显示
     */
    public void setForEditView(){
        addIcon.setVisible(true);

        Customer.setEditable(true);
        initCustomerCombobox();

        submitButton.setText("提 交");
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickSubmitButton();
            }
        });
    }


}
