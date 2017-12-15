package ui.viewcontroller.InventoryStaff;

import bean.InventoryCheckBean;
import bl.inventorybl.InventoryController;
import blservice.inventoryblservice.InventoryBLService;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.component.DialogFactory;
import ui.component.Table;
import util.Money;
import util.ResultMessage;
import vo.GoodsVO;
import vo.InventoryCheckVO;

import java.io.File;
import java.time.LocalDate;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class  InventoryCheckController {
    InventoryBLService inventoryBLService = new InventoryController();
    InventoryViewController inventoryViewController;
    InventoryCheckVO inventoryCheck;

    Table<InventoryCheckBean> table;

    int totalNum;
    double totalValue,avgValue;

    @FXML
    Label TotalIcon,ValueIcon,AvgPriceIcon,TotalNum,TotalValue,AvgValue;

    @FXML
    ScrollPane TablePane;

    @FXML
    public void initialize(){
        TotalIcon.setText("\ue6e3");
        ValueIcon.setText("\ue605");
        AvgPriceIcon.setText("\ueb73");

        initTable();
        showInventoryCheck();
        TotalNum.setText("库存数量合计："+totalNum+"件");
        TotalValue.setText("库存总价值："+ Money.getMoneyString(totalValue));
        AvgValue.setText("库存均价："+Money.getMoneyString(avgValue));

    }
    public void initTable(){
        table = new Table<>();
        table.setEditable(false);
        table.addColumn("行号","line",100);
        table.addColumn("商品编号","ID",100);
        table.addColumn("商品名称","name",100);
        table.addColumn("型号","model",100);
        table.addColumn("库存均价","avg",100);
        TablePane.setContent(table.getTable());
    }
    public void clickExportButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出库存盘点");
        fileChooser.setInitialFileName("库存盘点"+ LocalDate.now().toString());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel表格", "*.xlxs"));
        File f = fileChooser.showSaveDialog(new Stage());

        ResultMessage re = inventoryBLService.exportExcel(inventoryCheck);
        if (re == ResultMessage.SUCCESS){
            Dialog alert = DialogFactory.getInformationAlert();
            alert.setHeaderText("导出成功");
            alert.show();
        }

    }
    public void showInventoryCheck(){
        inventoryCheck = inventoryBLService.check();
        int n = 1;
        table.clear();
        for (GoodsVO good : inventoryCheck.averagePrice.keySet()){
            double avg = inventoryCheck.averagePrice.get(good);
            table.addRow(new InventoryCheckBean(n++,good.ID,good.name,good.model,good.amount,avg));
            totalNum += good.amount;
            totalValue += good.amount * avg;
            avgValue += avg;
        }
    }
    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }

}

