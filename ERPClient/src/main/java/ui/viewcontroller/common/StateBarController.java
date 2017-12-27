package ui.viewcontroller.common;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.hssf.record.LabelSSTRecord;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXSnackbar;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.VideoTrack;
import javafx.scene.text.Font;
import util.BillState;

/**
 * Created by Kry·L on 2017/12/12.
 */
public class StateBarController {
    MainUIController mainUIController;

    @FXML
    Label exitIcon, InfoIcon;

    @FXML
    Pane container;
    
    @FXML
    JFXBadge badge;

    JFXSnackbar snackbar;
    
    VBox vBox;
    
    ScrollPane scrollPane2;
    
    IntegerProperty numOfInfo = new SimpleIntegerProperty(0);
    
    private ArrayList<FXMLLoader> loaders = new ArrayList<>();
    private ArrayList<Pane> cells = new ArrayList<>();

    @FXML
    public void initialize(){
        exitIcon.setText("\ue72c");
        InfoIcon.setText("\ue60b");
        snackbar = new JFXSnackbar(container);
        
//        showMessage("导出成功");
        
        numOfInfo.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				if(numOfInfo.get()==0){
					badge.setEnabled(false);
				}
				else{
					badge.setEnabled(true);
				}
				badge.setText(String.valueOf(numOfInfo.get()));
			}

		});
        
        AnchorPane container = new AnchorPane();
        container.setPrefSize(650.0, 400.0);
        container.setMaxSize(650, 400);
        container.setMinSize(650, 400);
        ScrollPane scrollPane = new ScrollPane();
        vBox = new VBox();
        scrollPane.setPrefSize(160.0, 400.0);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        vBox.setPrefWidth(158.0);
        container.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        scrollPane2 = new ScrollPane();
        scrollPane2.setPrefSize(490.0, 400.0);
        scrollPane2.setLayoutX(160.0);
        container.getChildren().add(scrollPane2);
        
        
        JFXPopup popup = new JFXPopup(container);
        badge.setOnMouseClicked(e -> popup.show(InfoIcon, PopupVPosition.TOP, PopupHPosition.LEFT, -580, 35));
        
        addMessageCell(BillState.PASS, LocalDate.now().toString(), "XSD-20171227-00001");
        addMessageCell(BillState.FAILED, LocalDate.now().toString(), "XSD-20171227-00001");
        addMessageCell(BillState.SUBMITTED, LocalDate.now().toString(), "XSD-20171227-00001");
        addMessageCell(BillState.FAILED, LocalDate.now().toString(), "XSD-20171227-00001");
        addMessageCell(BillState.PASS, LocalDate.now().toString(), "XSD-20171227-00001");
        addMessageCell(BillState.SUBMITTED, LocalDate.now().toString(), "XSD-20171227-00001");
        
    }
    
    public void addMessageCell(BillState state, String time, String billID){
    	AnchorPane messageCellPane = new AnchorPane();
    	messageCellPane.setPrefSize(160.0, 70.0);
    	Label Icon = new Label();
    	Icon.setPrefSize(35, 35);
    	Icon.setLayoutX(8);
    	Icon.setLayoutY(8);
    	Label hint = new Label();
    	hint.setPrefSize(75, 35);
    	hint.setLayoutX(57);
    	hint.setLayoutY(8);
    	hint.setWrapText(true);
    	Label date = new Label();
    	date.setPrefSize(97, 15);
    	date.setLayoutX(57);
    	date.setLayoutY(50);
    	hint.setFont(new Font("Microsoft YaHei", 12));
    	date.setFont(new Font("Microsoft YaHei", 10));
    	
    	Icon.getStylesheets().add("icon-label");
    	
    	date.setText(time);
		if(state == BillState.SUBMITTED){
			Icon.setText("\ue602");
			hint.setText("您有一张单据已提交");
		}
		else if(state == BillState.PASS){
			Icon.setText("\ue625");
			hint.setText("您有一张单据已通过");
		}
		else if(state == BillState.FAILED){
			Icon.setText("\ue624");
			hint.setText("您有一张单据未通过");
		}
    	
    	messageCellPane.getChildren().add(Icon);
    	messageCellPane.getChildren().add(hint);
    	messageCellPane.getChildren().add(date);
    	
    	messageCellPane.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Label label = null;
				if(state == BillState.SUBMITTED){
					label = new Label("已提交");
				}
				else if(state == BillState.PASS){
					label = new Label("已通过");
				}
				else if(state == BillState.FAILED){
					label = new Label("未通过");
				}
				scrollPane2.setContent(label);
			}
		});
        
        vBox.getChildren().add(messageCellPane);
        numOfInfo.set(numOfInfo.get()+1);
    }

    public void showMessage(String message){
        snackbar.show(message,3000);
    }
    public void clickCloseButton(){
        mainUIController.close();
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
    
}
