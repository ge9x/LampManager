package ui.viewcontroller.InventoryStaff;

import bean.GoodsBean;
import bl.goodsbl.GoodsBLFactory;
import bl.goodsbl.GoodsController;
import blservice.goodsblservice.GoodsBLService;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.component.DialogFactory;
import ui.component.Table;
import util.ResultMessage;
import vo.GoodsVO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Kry·L on 2017/11/27.
 */
public class InventoryGoodsController {
    GoodsBLService goodsBLService;

    InventoryViewController inventoryViewController;
    ArrayList<GoodsVO> goods;

    Table<GoodsBean> table = new Table();
    ObservableList<GoodsBean> data = table.data;
    private Executor executor;

    @FXML
    ScrollPane TablePane;

    @FXML
    Label searchIcon, DeleteIcon, ModifyIcon;

    @FXML
    TextField SearchField;

    @FXML
    public void initialize(){
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        goodsBLService = GoodsBLFactory.getBLService();
        searchIcon.setText("\ue69d");
        DeleteIcon.setText("\ue606");
        ModifyIcon.setText("\ue601");

        initTable();
        Task<ArrayList<GoodsVO>> task = new Task<ArrayList<GoodsVO>>() {
            @Override
            protected ArrayList<GoodsVO> call() throws Exception {
                return goodsBLService.show();
            }
        };

        task.setOnSucceeded(e -> {
            goods = task.getValue();
            showGoods();
        });

        executor.execute(task);

    }

    public void initTable(){

        table.setEditable(true);
        table.addColumn("编号","ID",90);
        table.addColumn("商品名称","name",90);
        table.addColumn("商品型号","model",90);
        table.addColumn("商品分类","classification",90);
        table.addColumn("库存数量","amount",49);
        table.addColumn("警戒数量","alarmAmount",49);
        table.addColumn("进价","recentPurchasePrice",70);
        table.addColumn("最近进价","purchasePrice",70);
        table.addColumn("零售价","recentSalesPrice",70);
        table.addColumn("最近零售价","salesPrice",70);

        TablePane.setContent(table.getTable());
        table.getTable().setPrefHeight(300);

        SearchField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                    clickSearchButton();
            }
        });
    }
    public void showGoods(){
        data.clear();
        if (goods == null)
            return ;
        for (GoodsVO good : goods){
            data.add(new GoodsBean(good.ID,good.name,good.model,good.classification,good.alarmAmount,good.amount,good.recentBuyingPrice,good.recentRetailPrice,good.buyingPrice,good.retailPrice));
        }

    }
    public void clickModifyButton(){
        GoodsBean bean = getSelectedItem();
        if (bean != null){
            Dialog dialog = getModifyDialog(bean);
            Optional<ArrayList<String>> result = dialog.showAndWait();
            if (result.isPresent()){
                ArrayList<String> results = result.get();
                GoodsVO goodsVO = goodsBLService.showDetails(bean.getID());
                goodsVO.name = results.get(0);
                goodsVO.model = results.get(1);
                goodsVO.alarmAmount = Integer.parseInt(results.get(2));
                goodsVO.buyingPrice = Double.parseDouble(results.get(3));
                goodsVO.retailPrice = Double.parseDouble(results.get(4));
                ResultMessage re = goodsBLService.update(goodsVO);
                if (re == ResultMessage.SUCCESS){
                    bean.setName(goodsVO.name);
                    bean.setModel(goodsVO.model);
                    bean.setAlarmAmount(goodsVO.alarmAmount);
                    bean.setPurchasePrice(goodsVO.buyingPrice);
                    bean.setSalesPrice(goodsVO.retailPrice);
                } else{
                    dialog = DialogFactory.getInformationAlert();
                    dialog.setHeaderText("修改商品信息失败");
                    dialog.showAndWait();
                }

            }
        }
    }
    public Dialog getModifyDialog(GoodsBean bean){
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("商品编号"));
        labels.add(new Label("商品名称"));
        labels.add(new Label("商品型号"));
        labels.add(new Label("进价"));
        labels.add(new Label("零售价"));
        labels.add(new Label("警戒数量"));

        ArrayList<Node> nodes = new ArrayList<>();
        Label ID = new Label(bean.getID());
        JFXTextField nameTF = new JFXTextField(bean.getName());
        JFXTextField modelTF = new JFXTextField(bean.getModel());
        JFXTextField purchaseTF = new JFXTextField(bean.getPurchasePrice()+"");
        JFXTextField salesTF = new JFXTextField(bean.getSalesPrice()+"");
        JFXTextField alarmTF = new JFXTextField(bean.getAlarmAmount()+"");

        nodes.add(ID);
        nodes.add(nameTF);
        nodes.add(modelTF);
        nodes.add(purchaseTF);
        nodes.add(salesTF);
        nodes.add(alarmTF);

        Dialog dialog = DialogFactory.createDialog(labels,nodes);

        dialog.setHeaderText("请输入新的商品信息");
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.FINISH) {
                ArrayList<String> result = new ArrayList<>();
                if (nameTF.getText().isEmpty() || modelTF.getText().isEmpty() || alarmTF.getText().isEmpty()||purchaseTF.getText().isEmpty()||salesTF.getText().isEmpty()){
                    Dialog dialog1 = DialogFactory.getInformationAlert();
                    dialog1.setHeaderText("商品信息填写不完整");
                    dialog1.showAndWait();
                }else{
                    result.add(nameTF.getText());
                    result.add(modelTF.getText());
                    result.add(alarmTF.getText());
                    result.add(purchaseTF.getText());
                    result.add(salesTF.getText());
                    return result;
                }
            }
            return null;
        });
        return dialog;
    }

    public void clickSearchButton(){
        String keyword = SearchField.getText();
        goods = goodsBLService.find(keyword);
        showGoods();
    }
    public void clickDeleteButton(){
        GoodsBean bean = getSelectedItem();
        if (bean != null){
            Dialog dialog = DialogFactory.getConfirmationAlert();
            dialog.setHeaderText("确定要删除商品: " + bean.getName() + " 吗？");
            Optional result = dialog.showAndWait();
            if (result.isPresent()){
                if (result.get() == ButtonType.OK){
                    ResultMessage re = goodsBLService.delete(bean.getID());
                    dialog = DialogFactory.getInformationAlert();
                    if (re == ResultMessage.SUCCESS) {
                        data.remove(bean);
                        goods = goodsBLService.show();
                        dialog.setHeaderText("删除商品成功");
                        dialog.showAndWait();
                        showGoods();
                    }
                    else {
                        dialog.setHeaderText("删除商品失败");

                        dialog.showAndWait();
                    }
                }
            }
        }
    }
    public GoodsBean getSelectedItem(){
        GoodsBean goodsBean = table.getSelectedItem();
        if(goodsBean == null){
            Dialog dialog = DialogFactory.getInformationAlert();
            dialog.setHeaderText("请先选择商品记录");
            dialog.showAndWait();
            return null;
        }
        return goodsBean;
    }

    public void setInventoryViewController(InventoryViewController inventoryViewController){
        this.inventoryViewController = inventoryViewController;
    }

}
