package ui.component;


import bean.GoodsBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Kry·L on 2017/12/15.
 */
public class Table<T> {

    TableView tableView;

    public  ObservableList<T> data = FXCollections.observableArrayList();

    public Table(){
        tableView = new TableView();
        tableView.setItems(data);
        tableView.setPrefHeight(200);
    }

    /**
     * 设置可否编辑
     * @param editable
     */
    public void setEditable(boolean editable){
        tableView.setEditable(editable);
    }

    /**
     * 增加新的一列
     * @param name 列名称
     * @param target 列对应的数据对象T的成员变量
     * @param width 烈的宽度
     */
    public void addColumn(String name, String target, double width){
        TableColumn column = new TableColumn(name);
        column.setPrefWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(target));

        tableView.getColumns().add(column);
    }

    /**
     * 获得表格
     * @return
     */
    public TableView getTable(){
        return tableView;
    }

    /**
     * 增加一行
     * @param row
     */
    public void addRow(T row){
        data.add(row);
    }

    public void clear() {
        data.clear();
    }

    public T getSelectedItem() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index == -1)
            return null;
        else
            return data.get(index);
    }
}
