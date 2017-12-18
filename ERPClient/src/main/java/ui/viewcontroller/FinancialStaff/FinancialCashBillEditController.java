package ui.viewcontroller.FinancialStaff;

import bean.CashBillItemBean;
import bl.customerbl.Customer;
import bl.financialbl.FinanceController;
import blservice.financeblservice.FinanceBLService;
import blstubdriver.FinanceBLService_Stub;
import com.jfoenix.controls.JFXButton;
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
import ui.component.DialogFactory;
import util.BillState;
import util.BillType;
import util.Money;
import vo.AccountVO;
import vo.CashBillItemVO;
import vo.CashBillVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialCashBillEditController {
    FinancialCashBillController financialCashBillController;
    FinanceBLService financeBLService = new FinanceController();

    ArrayList<CashBillItemVO> cashBillItems = new ArrayList<>();
    ArrayList<AccountVO> accounts;
    Boolean isNew;

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
    Label title;

    @FXML
    JFXButton submitButton;

    @FXML
    JFXButton cancelButton;

    @FXML
    ComboBox Accounts;

    public void initialize(){
        addIcon.setText("\ue61e");
        String name = financeBLService.getUserID();
        Username.setText(name);
        accounts = financeBLService.getAllAccount();

        initAccountCombobox();
        initTable();

        //总额Text与绑定转账金额之和绑定
        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Total.setText(Money.getMoneyString(total.get()));
            }
        });

    }
    public void initAccountCombobox(){
        //初始化Account选择框
        ArrayList<String> accountNames = new ArrayList<>();
        for (AccountVO account : accounts){
            accountNames.add(account.accountName);
        }
        Accounts.getItems().addAll(accountNames);
    }

    public void initTable(){
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
    }
    public void addCashBill() {
        String ID = financeBLService.getNewCashBillID();
        isNew = true;
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
        CashBillVO cashBillVO = new CashBillVO(LocalDate.now().toString(),BillID.getText(),
                BillState.SUBMITTED, BillType.CASH,Username.getText(),accountID
                ,cashBillItems,total.get());
        if (isNew == true){
            financeBLService.submit(cashBillVO);
        }else{
            financeBLService.updateDraft(cashBillVO);
        }
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
                CashBillVO cashBillVO = new CashBillVO(LocalDate.now().toString(), BillID.getText(),
                        BillState.DRAFT, BillType.CASH,Username.getText(), accountID
                        , cashBillItems,total.get());

                if (isNew == true){
                    financeBLService.save(cashBillVO);
                }else{
                    financeBLService.updateDraft(cashBillVO);
                }
            }

            financialCashBillController.showCashBillList();
        }
    }
    public void setForDetailView(CashBillVO cashBillVO){
        isNew = false;
        BillID.setText(cashBillVO.ID);
        title.setText("现金费用单详情");
        addIcon.setVisible(false);


        String accountName = "";
        if (!cashBillVO.accountID.equals("0")){
            accountName = financeBLService.getAccountNameByID(cashBillVO.accountID);
        }
        Accounts.getItems().clear();
        Accounts.getItems().add(accountName);
        Accounts.getSelectionModel().selectFirst();
        Accounts.setEditable(false);

        cancelButton.setText("返 回");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                financialCashBillController.showCashBillList();
            }
        });

        if (cashBillVO.state == BillState.DRAFT){
            submitButton.setText("编 辑");
            submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setForEditView();
                }
            });
        }else{
            submitButton.setVisible(false);
        }
        for (CashBillItemVO cashBillItemVO:cashBillVO.cashBillItems){
            cashBillItems.add(cashBillItemVO);
            data.add(new CashBillItemBean(cashBillItemVO.itemName,cashBillItemVO.money,cashBillItemVO.remark));
            total.set(total.get() + cashBillItemVO.money);
        }
    }

    public void setForEditView(){
        addIcon.setVisible(true);
        title.setText("编辑草稿单");

        Accounts.setEditable(true);
        initAccountCombobox();

        submitButton.setText("提 交");
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickSubmitButton();
            }
        });
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                clickCancelButton();
            }
        });
    }

    public void setFinancialCashBillController(FinancialCashBillController financialCashBillController){
        this.financialCashBillController = financialCashBillController;
    }


}
