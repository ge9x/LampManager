package ui.component;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Kry·L on 2017/12/15.
 */
public class Table<T> {

    TableView tableView;

    public  ObservableList<T> data = FXCollections.observableArrayList();

    public Table(){
        tableView = new TableView();
        tableView.setItems(data);
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
}
