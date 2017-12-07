package ui.viewcontroller.FinancialStaff;

import bean.SalesDetailsBean;
import bl.formbl.FormController;
import blservice.formblservice.FormBLService;
import blservice.formblservice.SalesDetailsInput;
import blstubdriver.FormBLService_Stub;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import ui.component.DialogFactory;
import util.FilterType;
import util.ResultMessage;
import vo.SalesDetailVO;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/25.
 */
public class FinancialSalesDetailsController {
    FinancialViewController financialViewController;
    FormBLService formBLService = new FormBLService_Stub();
    FormBLService formBLService2 = new FormController();
    ArrayList<SalesDetailVO> salesDetails = new ArrayList<>();

    TableView<SalesDetailsBean> table = new TableView<SalesDetailsBean>();
    ObservableList<SalesDetailsBean> data = FXCollections.observableArrayList();

    @FXML
    Label searchIcon;
    @FXML
    TextField Search;
    @FXML
    AnchorPane TablePane;
    @FXML
    ScrollPane TableScrollPane;
    @FXML
    ComboBox<String> Filters;

    @FXML
    JFXDatePicker StartDate;

    @FXML
    JFXDatePicker EndDate;



    public void initialize(){
        searchIcon.setText("\ue69d");
        table = new TableView<>();
        table.setEditable(false);

        Filters.getItems().addAll("商品名","客户","业务员","仓库");

        TableColumn dateColumn = new TableColumn("时间");
        dateColumn.setPrefWidth(136);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn nameColumn = new TableColumn("商品名称");
        nameColumn.setPrefWidth(136);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn modelColumn = new TableColumn("型号");
        modelColumn.setPrefWidth(136);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn amountColumn = new TableColumn("数量");
        amountColumn.setPrefWidth(68);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn priceolumn = new TableColumn("单价");
        priceolumn.setPrefWidth(68);
        priceolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn sumColumn = new TableColumn("总额");
        sumColumn.setPrefWidth(130);
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));

        table.setItems(data);
        table.getColumns().addAll(dateColumn,nameColumn,modelColumn,amountColumn,priceolumn,sumColumn);
        TableScrollPane.setContent(table);

        String start = formBLService.getStartDate();
        String[] dateArr = start.split("-");
        StartDate.setValue(LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2])));
        EndDate.setValue(LocalDate.now());
        StartDate.valueProperty().addListener(new javafx.beans.value.ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showSalesDetails();
            }
        });
        EndDate.valueProperty().addListener(new javafx.beans.value.ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showSalesDetails();
            }
        });

        showSalesDetails();
    }

    public void clickSearchButton(){
        showSalesDetails();
    }

    public void clickExportButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());

        ArrayList<SalesDetailsBean> beans = new ArrayList<>();
        beans.addAll(data);
        ResultMessage re = formBLService.exportSalesDetails("","",beans);
        if (re == ResultMessage.SUCCESS){
            Dialog alert = DialogFactory.getInformationAlert();
            alert.setHeaderText("导出成功");
            alert.show();
        }
    }

    public void showSalesDetails(){
        String filters = Filters.getSelectionModel().getSelectedItem();
        if (filters == null){
            filters = "";
        }
        String searchInput = Search.getText();
        SalesDetailsInput input = new SalesDetailsInput(StartDate.getValue().toString(),EndDate.getValue().toString(),searchInput,null);
        switch (filters){
            case "商品名": input.filterType = FilterType.GOODS;break;
            case "客户": input.filterType = FilterType.CUSTOMER;break;
            case "业务员": input.filterType = FilterType.SALESMAN;break;
            case "仓库": input.filterType = FilterType.INVENTORY;break;
            default: input.filterType = null;
        }
        salesDetails = formBLService2.getSalesDetails(input);

        data.clear();
        for (SalesDetailVO vo : salesDetails){
            data.add(new SalesDetailsBean(vo.date,vo.goodName,vo.type,vo.amount,vo.price,vo.sum));
        }
    }

    public void setFinancialViewController(FinancialViewController financialViewController){
        this.financialViewController = financialViewController;
    }


}
