package ui.viewcontroller.common;

import bl.initializationbl.InitializationBLFactory;
import bl.initializationbl.InitializationController;
import bl.logbl.LogBLFactory;
import bl.logbl.LogController;
import blservice.initializationblservice.InitInfo;
import blservice.logblservice.LogBLService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/29.
 */
public class LogViewController {
    MainUIController mainUIController;
    InitInfo initializationController;
    LogBLService logBLService;

    ArrayList<String> logs;

    @FXML
    JFXDatePicker StartDate, EndDate;

    @FXML
    JFXTimePicker StartTime, EndTime;

    @FXML
    TextArea LogContent;

    @FXML
    Label ExitIcon;


    @FXML
    public void initialize(){
        ExitIcon.setText("\ue72c");

        initializationController = InitializationBLFactory.getInfo();
        logBLService = LogBLFactory.getBLService();

        StartDate.setValue(LocalDate.parse(initializationController.getStartDate()));
        EndDate.setValue(LocalDate.now());
        StartTime.setIs24HourView(true);
        StartTime.setValue(LocalTime.now());
        EndTime.setIs24HourView(true);
        EndTime.setValue(LocalTime.now());

        StartDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                refresh();
            }
        });

        EndDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                refresh();
            }
        });
        StartTime.valueProperty().addListener(new ChangeListener<LocalTime>() {
            @Override
            public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue, LocalTime newValue) {
                refresh();
            }
        });
        EndTime.valueProperty().addListener(new ChangeListener<LocalTime>() {
            @Override
            public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue, LocalTime newValue) {
                refresh();
            }
        });

        refresh();
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
    public void getLog(){
        LocalDateTime startDateTime = LocalDateTime.of(StartDate.getValue(),StartTime.getValue());
        LocalDateTime endDateTime = LocalDateTime.of(EndDate.getValue(),EndTime.getValue());
        logs = logBLService.getLogByDate(startDateTime, endDateTime);
    }
    private void updateLog(){
        LogContent.clear();
        for (String log:logs){
            LogContent.appendText(log+System.lineSeparator());
        }
    }
    private void refresh(){
        getLog();
        updateLog();
    }

    public void clickExitButton(MouseEvent mouseEvent) {
        mainUIController.close();
    }
}
