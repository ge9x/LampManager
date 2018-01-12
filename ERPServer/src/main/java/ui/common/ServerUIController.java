package ui.common;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.ERPServer.Server;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.RemoteHelper;

public class ServerUIController {
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private int millisecond = 0;

	@FXML
	JFXToggleButton startButton;
	
	@FXML
	Text startTime;
	
	@FXML
	Label close;
	
	@FXML
	JFXTextField portText;
	
	Stage primaryStage;
	
	Timer timer;
	
	@FXML
	public void initialize(){
		startTime.setText("0:0:0:0");
		close.setText("\ue72c");
		portText.setText("8000");
		portText.setEditable(false);
		
		startButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(startButton.isSelected()){
					int port = Integer.parseInt(portText.getText());
					new RemoteHelper(port);
					runTime();
				}
				else{
					RemoteHelper.disableNetwork();
					stopTime();
				}
			}

		});
	}
	
	public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
	
	public void runTime(){
		resetTime();
		timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						addTime();
					}
				});
			}
		}, 0, 100);
	}
	
	public void resetTime(){
		hour = 0;
		minute = 0;
		second = 0;
		millisecond = 0;
	}
	
	public void addTime(){
		millisecond++;
		if (millisecond == 10) {
			millisecond -= 10;
			second++;
			if (second == 60) {
				second -= 60;
				minute++;
				if (minute == 60) {
					minute -= 60;
					hour++;
				}
			}
		}
		startTime.setText(hour+":"+minute+":"+second+":"+millisecond);
	}
	
	public void stopTime(){
		timer.cancel();
		startTime.setText("0:0:0:0");
	}
	
	public void close(){
		primaryStage.close();
		System.exit(0);
	}
}
