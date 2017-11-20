package ui.component;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;

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

    public static void initStyle(Dialog dialog){
        dialog.setTitle("Lamp Manager");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setId("CancelButton");
        dialog.getDialogPane().getStylesheets().add("/css/dialog.css");
    }


}
