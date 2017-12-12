package ui.component;

import com.jfoenix.controls.JFXTabPane;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/3.
 */
public class BillPane {

    JFXTabPane tabPane;
    ArrayList<Tab> tabs;

    ScrollPane scrollPane;
    TilePane tilePane;

    public BillPane(String...tabName){
        tabPane = new JFXTabPane();
        tabs = new ArrayList<>();

        for (int i = 0; i < tabName.length; i++){
            Tab tab = new Tab(tabName[i]);
            tabs.add(tab);
        }

        tabPane.getTabs().clear();
        tabPane.getTabs().addAll(tabs);
        tabPane.getSelectionModel().selectFirst();

        initScrollPane();
        tabPane.getStylesheets().add(getClass().getResource("/css/BillPane.css").toExternalForm());
    }

    public void initScrollPane(){
        scrollPane = new ScrollPane();
        tilePane = new TilePane();
        tilePane.setPadding(new Insets(10,7,0,10));
        tilePane.setPrefColumns(3);
        tilePane.setPrefHeight(400);
        tilePane.setHgap(25);
        tilePane.setVgap(20);
        scrollPane.setContent(tilePane);
    }
    public void setContent(Tab tab,ArrayList<VBox> nodes){
        initScrollPane();
        tilePane.getChildren().clear();
        tilePane.getChildren().addAll(nodes);
        tab.setContent(scrollPane);
    }
    public Tab getTabByName(String name) {
        for(Tab tab:tabs) {
            if (tab.getText() == name) {
                return tab;
            }
        }
        return null;
    }
    public TabPane getTabPane(){
        return tabPane;
    }
    public ArrayList<Tab> getAllTabs(){
        return tabs;
    }

}
