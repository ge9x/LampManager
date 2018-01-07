package ui.viewcontroller.common;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.xmlbeans.impl.jam.annotation.LineDelimitedTagParser;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXSnackbar;

import bl.messagebl.MessageBLFactory;
import bl.userbl.UserBLFactory;
import blservice.messageblservice.MessageBLService;
import blservice.userblservice.UserInfo;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.VideoTrack;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import ui.component.DialogFactory;
import util.BillState;
import vo.MessageVO;

import javax.swing.text.html.Option;

/**
 * Created by Kry·L on 2017/12/12.
 */
public class StateBarController {
    MainUIController mainUIController;
    MessageBLService messageBLService = MessageBLFactory.getBLService();
    UserInfo userInfo = UserBLFactory.getInfo();

    @FXML
    Label exitIcon, InfoIcon;

    @FXML
    Pane container;
    
    @FXML
    JFXBadge badge;

    JFXSnackbar snackbar;
    
    VBox vBox;
    
    ScrollPane scrollPane2;
    
    IntegerProperty numOfInfo = new SimpleIntegerProperty(1);
    ArrayList<Integer> hasRemove = new ArrayList<>();
    ArrayList<MessageVO> messageVOs = new ArrayList<>();
    static int count = 0;

    @FXML
    public void initialize(){
        exitIcon.setText("\ue72c");
        InfoIcon.setText("\ue60b");


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

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(480.0, 300.0);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);


        vBox = new VBox();
        vBox.setPrefWidth(479.0);

        scrollPane.setContent(vBox);

        JFXPopup popup = new JFXPopup(scrollPane);
//        badge.setOnMouseClicked(e -> popup.show(InfoIcon, PopupVPosition.TOP, PopupHPosition.LEFT, -400, 35));
        badge.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				popup.show(InfoIcon, PopupVPosition.TOP, PopupHPosition.LEFT, -400, 35);
				messageVOs = messageBLService.show(userInfo.findUserByID(userInfo.getCurrentUserID()).position);
				numOfInfo.set(messageVOs.size());
				vBox.getChildren().clear();
		    	count = 0;
		    	for(MessageVO vo : messageVOs){
		    		addMessageCell(vo);
		    	}
			}
		});
        
//        addMessageCell(BillState.PASS, LocalDate.now().toString() + " " +LocalTime.now().toString(), "XSD-20171227-00001");
//        addMessageCell(BillState.FAILED, LocalDate.now().toString() + " " +LocalTime.now().toString(), "XSD-20171227-00001");
//        addMessageCell(BillState.SUBMITTED, LocalDate.now().toString() + " " +LocalTime.now().toString(), "XSD-20171227-00001");
//        addMessageCell(BillState.FAILED, LocalDate.now().toString() + " " +LocalTime.now().toString(), "XSD-20171227-00001");
//        addMessageCell(BillState.PASS, LocalDate.now().toString() + " " +LocalTime.now().toString(), "XSD-20171227-00001");
//        addMessageCell(BillState.SUBMITTED, LocalDate.now().toString() + " " +LocalTime.now().toString(), "XSD-20171227-00001");
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						refreshMessageBox();
					}
				});
			}
		}, 0, 1000);
    }
    
    public void refreshMessageBox(){
    	messageVOs = messageBLService.show(userInfo.findUserByID(userInfo.getCurrentUserID()).position);
	    if(numOfInfo.get()!=messageVOs.size()){
		    numOfInfo.set(messageVOs.size());
	    }
    }
    
    public void addMessageCell(MessageVO vo){
    	AnchorPane messageCellPane = null;
    	try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/common/MessageCell.fxml"));
            messageCellPane = loader.load();
            MessageCellController messageCellController = loader.getController();
            messageCellController.setMessage(vo);
            messageCellController.setStateBarController(this);
            messageCellController.setOrder(count);
            
        }catch (IOException e){
            e.printStackTrace();
        }
        
        vBox.getChildren().add(messageCellPane);
        count++;
    }
    
    public void deleteMessageCell(int order){
    	hasRemove.add(order);
    	Collections.sort(hasRemove);
    	int index = hasRemove.indexOf(order);
    	vBox.getChildren().remove(order-index);
    	
    	messageBLService.deleteMessage(messageVOs.get(order-index).messageID);
    }

    public void showMessage(String message){
        snackbar.show(message,3000);
    }
    public void clickCloseButton(){
        Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("您确定要退出本系统吗？");
        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            if (result.get() == ButtonType.OK)
                mainUIController.close();
        }
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
    
}
