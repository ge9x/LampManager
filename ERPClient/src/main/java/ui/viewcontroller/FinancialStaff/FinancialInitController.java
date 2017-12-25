package ui.viewcontroller.FinancialStaff;

import bean.AccountBean;
import bean.ClassificationBean;
import bean.CustomerBean;
import bean.GoodsBean;
import bl.initializationbl.InitializationController;
import blservice.initializationblservice.InitializationBLService;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.HBox;
import ui.component.DialogFactory;
import ui.component.Table;
import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/18.
 */
public class FinancialInitController {
    FinancialViewController financialViewController;
    InitializationBLService initializationBLService = new InitializationController();

    Table<ClassificationBean> classificationTable;
    Table<GoodsBean> goodsTable;
    Table<AccountBean> accountTable;
    Table<CustomerBean> customerTable;


    @FXML
    ScrollPane CustomerTable,GoodsTable,ClaTable,AccountTable;

    @FXML
    Label initIcon;

    @FXML
    JFXComboBox<String> DateBox;

    @FXML
    HBox InitDateHBox;

    @FXML
    public void initialize() {
        initIcon.setText("\ue6eb");

        String recentInitDate = initializationBLService.getRecentInitdate();
        if (recentInitDate == null){
            Dialog dialog  = DialogFactory.getInformationAlert();
            dialog.setHeaderText("还没有进行过期初建账！");

            InitDateHBox.setVisible(false);
            CustomerTable.setVisible(false);
            GoodsTable.setVisible(false);
            ClaTable.setVisible(false);
            AccountTable.setVisible(false);

            dialog.showAndWait();
        }else{
            ArrayList<String> initdates = initializationBLService.getAllInitDate();
            DateBox.getItems().addAll(initdates);
            DateBox.getSelectionModel().selectLast();
            DateBox.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<String>>() {
                @Override
                public void changed(ObservableValue<? extends SingleSelectionModel<String>> observable, SingleSelectionModel<String> oldValue, SingleSelectionModel<String> newValue) {
                    showInitInfo();
                }
            });

            initClassificationTable();
            initAccountTable();
            initCustomerTable();
            initGoodsTable();

            CustomerTable.setContent(customerTable.getTable());
            GoodsTable.setContent(goodsTable.getTable());
            ClaTable.setContent(classificationTable.getTable());
            AccountTable.setContent(accountTable.getTable());

            showInitInfo();
        }
    }

    public void initClassificationTable(){
        classificationTable = new Table<>();
        classificationTable.addColumn("分类编号","ID",100);
        classificationTable.addColumn("分类名称","name",150);
    }
    public void initAccountTable(){
        accountTable = new Table<>();
        accountTable.addColumn("账户编号","ID",100);
        accountTable.addColumn("账户名称","name",150);
        accountTable.addColumn("账户余额","money",150);
    }
    public void initCustomerTable(){
        customerTable = new Table<>();
        customerTable.addColumn("客户编号","customerID",60);
        customerTable.addColumn("姓名","customerName",100);
        customerTable.addColumn("分类","category",60);
        customerTable.addColumn("级别","level",60);
        customerTable.addColumn("电话","phone",100);
        customerTable.addColumn("地址","address",100);
        customerTable.addColumn("邮编","postCode",60);
        customerTable.addColumn("电子邮箱","mail",100);
        customerTable.addColumn("应收额度","receivableLimit",100);
        customerTable.addColumn("应收","receive",100);
        customerTable.addColumn("应付","pay",100);
        customerTable.addColumn("默认业务员","salesman",100);
        customerTable.addColumn("客户积分","points",60);
    }
    public void initGoodsTable(){
        goodsTable = new Table<>();
        goodsTable.addColumn("编号","ID",60);
        goodsTable.addColumn("名称","name",100);
        goodsTable.addColumn("型号","model",100);
        goodsTable.addColumn("分类","classification",100);
        goodsTable.addColumn("库存总数","amount",60);
        goodsTable.addColumn("进价","purchasePrice",60);
        goodsTable.addColumn("零售价","recentSalesPrice",60);
        goodsTable.addColumn("最近进价","recentPurchasePrice",60);
        goodsTable.addColumn("最近零售价","recentSalesPrice",60);

    }

    public void showInitInfo(){
        String date = DateBox.getSelectionModel().getSelectedItem();
        InitializationVO initializationVO = initializationBLService.show(date);
        classificationTable.clear();
        accountTable.clear();
        goodsTable.clear();
        customerTable.clear();
        for (ClassificationVO clavo : initializationVO.classifications){
            classificationTable.addRow(new ClassificationBean(clavo.ID,clavo.name));
        }
        for (AccountVO accountVO : initializationVO.accounts){
            accountTable.addRow(new AccountBean(accountVO.accountID,accountVO.accountName,accountVO.money));
        }
        for (GoodsVO goodsVO : initializationVO.goods){
            goodsTable.addRow(new GoodsBean(goodsVO.ID,goodsVO.name,goodsVO.model,goodsVO.classification,goodsVO.alarmAmount,goodsVO.amount,goodsVO.buyingPrice,goodsVO.retailPrice,goodsVO.recentBuyingPrice,goodsVO.recentRetailPrice));
        }
        for (CustomerVO customerVO : initializationVO.customers){
            customerTable.addRow(new CustomerBean(customerVO.customerID,customerVO.category.getValue(),customerVO.level.getValue(),customerVO.customerName,customerVO.phone,customerVO.address,customerVO.postCode,customerVO.mail,customerVO.receivableLimit, customerVO.receive,customerVO.pay
            , customerVO.salesman, customerVO.points));
        }
    }
    public void clickInitButton(){
        ResultMessage re = initializationBLService.init();
        Dialog dialog = DialogFactory.getInformationAlert();
        dialog.setHeaderText("期初建账"+re.toString());
        dialog.showAndWait();

        if (re == ResultMessage.SUCCESS){
            InitDateHBox.setVisible(true);
            CustomerTable.setVisible(true);
            GoodsTable.setVisible(true);
            ClaTable.setVisible(true);
            AccountTable.setVisible(true);

            DateBox.getItems().clear();
            DateBox.getItems().addAll(initializationBLService.getAllInitDate());
            DateBox.getSelectionModel().selectLast();
        }
    }
    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }


}
