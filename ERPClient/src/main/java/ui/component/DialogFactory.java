package ui.component;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by Kry·L on 2017/11/20.
 */
public class DialogFactory {

    public static Dialog getTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        initStyle(dialog);
        return dialog;
    }
    public static Dialog getConfirmationAlert(){
        Dialog alert = new Alert(Alert.AlertType.CONFIRMATION);
        initStyle(alert);
        return alert;
    }
    public static Dialog getDoubleTextDialog(String label1,String label2){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField textField1 = new TextField();
        textField1.setPromptText(label1);
        TextField textField2 = new TextField();
        textField2.setPromptText(label2);

        grid.add(new Label(label1), 0, 0);
        grid.add(textField1, 1, 0);
        grid.add(new Label(label2), 0, 1);
        grid.add(textField2, 1, 1);

        Node confirmButton = dialog.getDialogPane().lookupButton(ButtonType.FINISH);
        confirmButton.setDisable(true);

        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || textField2.getText().trim().isEmpty());
        });

        textField2.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || textField1.getText().trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // 默认光标在第一个输入框上
        Platform.runLater(() -> textField1.requestFocus());

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

    public static void initStyle(Dialog dialog){
        dialog.setTitle("Lamp Manager");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setId("CancelButton");
        dialog.getDialogPane().getStylesheets().add("/css/dialog.css");
    }


}
