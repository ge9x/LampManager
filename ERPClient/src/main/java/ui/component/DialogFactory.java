package ui.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/20.
 */
public class DialogFactory {

    public static Dialog getTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        Button confirmButton = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        confirmButton.setDisable(true);
        TextField input =  dialog.getEditor();

        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                confirmButton.setDisable(dialog.getEditor().getText().trim().isEmpty());
            }
        });
        initStyle(dialog);
        return dialog;
    }
    public static Dialog getConfirmationAlert(){
        Dialog alert = new Alert(Alert.AlertType.CONFIRMATION);
        initStyle(alert);
        return alert;
    }

    public static Dialog getInformationAlert(){
        Dialog alert = new Alert(Alert.AlertType.INFORMATION);
        initStyle(alert);
        return alert;
    }
    public static Dialog getChoiceDialog(ArrayList<String> choices){
        Dialog dialog = new ChoiceDialog(choices.get(0),choices);
        initStyle(dialog);
        return dialog;
    }
    public static Dialog getDoubleTextDialog(String label1,String label2){


        TextField textField1 = new TextField();
        textField1.setPromptText(label1);
        TextField textField2 = new TextField();
        textField2.setPromptText(label2);

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label(label1));
        labels.add(new Label(label2));

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(textField1);
        nodes.add(textField2);
        Dialog<Pair<String,String>> dialog = createDialog(labels,nodes);

        Node confirmButton = dialog.getDialogPane().lookupButton(ButtonType.FINISH);
        confirmButton.setDisable(true);

        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || textField2.getText().trim().isEmpty());
        });

        textField2.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || textField1.getText().trim().isEmpty());
        });


        // 确认按钮后，将结果转为pair
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.FINISH) {
                return new Pair<>(textField1.getText(), textField2.getText());
            }
            return null;
        });
        initStyle(dialog);
        return dialog;
    }



    public static Dialog createDialog(ArrayList<Label> labels, ArrayList<Node> nodes) {
        Dialog dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        for (int i = 0; i < labels.size(); i++){
            grid.add(labels.get(i), 0, i);
            grid.add(nodes.get(i), 1, i);
        }
        dialog.getDialogPane().setContent(grid);

        initStyle(dialog);
        return dialog;
    }
    public static void initStyle(Dialog dialog){
        dialog.setTitle("Lamp Manager");
        Node cancelButton = dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        if (cancelButton != null)
            cancelButton.setId("CancelButton");
        dialog.getDialogPane().getStylesheets().add("/css/dialog.css");
    }



}