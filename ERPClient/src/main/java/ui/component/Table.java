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
    public void setEditable(boolean editable){
        tableView.setEditable(editable);
    }
    public void addColumn(String name, String target, double width){
        TableColumn column = new TableColumn(name);
        column.setPrefWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(target));

        tableView.getColumns().add(column);
    }
    public TableView getTable(){
        return tableView;
    }
}
