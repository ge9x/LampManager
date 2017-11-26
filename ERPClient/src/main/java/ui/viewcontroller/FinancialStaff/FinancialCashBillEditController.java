package ui.viewcontroller.FinancialStaff;

import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
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
import ui.component.DialogFactory;
import util.BillState;
import util.BillType;
import util.Money;
import vo.AccountVO;
import vo.CashBillItemVO;
import vo.CashBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialCashBillEditController {
    FinancialCashBillController financialCashBillController;

    FinanceBLService financeBLService = new FinanceBLService_Stub();
    ArrayList<CashBillItemVO> cashBillItems = new ArrayList<>();

    ArrayList<AccountVO> accounts;
    TableView<CashBillItemBean> itemTable;
    ObservableList<CashBillItemBean> data =
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
    ComboBox Accounts;

    public void initialize(){
        addIcon.setText("\ue61e");
        String name = financeBLService.getUserID();
        Username.setText(name);
        accounts = financeBLService.getAllAccount();

        //初始化Account选择框
        ArrayList<String> accountNames = new ArrayList<>();
        for (AccountVO account : accounts){
            accountNames.add(account.accountName);
        }
        Accounts.getItems().addAll(accountNames);

        //初始化表格
        itemTable = new TableView<>();
        itemTable.setEditable(false);

        TableColumn nameColumn = new TableColumn("条目名");
        nameColumn.setPrefWidth(128);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn moneyColumn = new TableColumn("金额");
        moneyColumn.setPrefWidth(128);
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        TableColumn remarkColumn = new TableColumn("备注");
        remarkColumn.setPrefWidth(190);
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        itemTable.setItems(data);
        itemTable.getColumns().addAll(nameColumn,moneyColumn,remarkColumn);
        vbox.getChildren().add(itemTable);

        //总额Text与绑定转账金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });

    }
    public void addCashBill() {
        String ID = financeBLService.getNewCashBillID();
        BillID.setText(ID);
    }

    public void clickAddButton(){
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("条目名"));
        labels.add(new Label("金额"));
        labels.add(new Label("备注"));

        ArrayList<Node> nodes = new ArrayList<Node>();
        TextField nameTF = new TextField();
        TextField moneyTF = new TextField();
        TextField remarkTF = new TextField();
        nodes.add(nameTF);
        nodes.add(moneyTF);
        nodes.add(remarkTF);

        Dialog dialog = DialogFactory.createDialog(labels,nodes);
        dialog.setResultConverter(dialogButton -> {
            ArrayList<String> result = new ArrayList<>();
            result.add(nameTF.getText());
            result.add(moneyTF.getText());
            result.add(remarkTF.getText());
            if (dialogButton == ButtonType.FINISH) {
                return result;
            }
            return null;
        });

        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            ArrayList<String> values = (ArrayList<String>)result.get();
            Double money = Double.parseDouble(values.get(1));
            CashBillItemVO cashBillItemVO = new CashBillItemVO(values.get(0),money,values.get(2));

            cashBillItems.add(cashBillItemVO);
            data.add(new CashBillItemBean(values.get(0),money,values.get(2)));
            total.set(total.get()+money);
        }
    }

    public void clickSubmitButton(){
        String accountID = accounts.get(Accounts.getSelectionModel().getSelectedIndex()).accountID;
        CashBillVO cashBillVO = new CashBillVO(new Date(),BillID.getText(),
                BillState.SUBMITTED, BillType.CASH,Username.getText(),accountID
                ,cashBillItems,total.get());
        financeBLService.submit(cashBillVO);
        financialCashBillController.showCashBillList();
    }
    public void clickCancelButton(){
        Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("需要保存为草稿吗？");
        Optional result = dialog.showAndWait();


        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
                String accountID = "";
                if (Accounts.getSelectionModel().getSelectedIndex() >= 0){
                    accountID = accounts.get(Accounts.getSelectionModel().getSelectedIndex()).accountID;
                }
                CashBillVO cashBillVO = new CashBillVO(new Date(), BillID.getText(),
                        BillState.DRAFT, BillType.CASH,Username.getText(), accountID
                        , cashBillItems,total.get());
                financeBLService.save(cashBillVO);
            }

            financialCashBillController.showCashBillList();
        }
    }


    public void setFinancialCashBillController(FinancialCashBillController financialCashBillController){
        this.financialCashBillController = financialCashBillController;
    }

    public class CashBillItemBean{
        public StringProperty name;
        public DoubleProperty money;
        public StringProperty remark;

        public CashBillItemBean(String name,double money,String remark) {
            this.name = new SimpleStringProperty(name);
            this.money = new SimpleDoubleProperty(money);
            this.remark = new SimpleStringProperty(remark);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public double getMoney() {
            return money.get();
        }

        public DoubleProperty moneyProperty() {
            return money;
        }

        public void setMoney(double money) {
            this.money.set(money);
        }

        public String getRemark() {
            return remark.get();
        }

        public StringProperty remarkProperty() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark.set(remark);
        }
    }
}
