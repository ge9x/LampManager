package ui.viewcontroller.GeneralManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import bl.examinationbl.ExaminationBLFactory;
import bl.examinationbl.ExaminationController;
import blservice.examinationblservice.ExaminationBLService;
import blstubdriver.ExaminationBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import ui.component.DialogFactory;
import vo.BillVO;

public class GeneralManagerExaminationViewController {

	@FXML
	TilePane billList;
	
	@FXML
	JFXButton passButton;
	
	@FXML
	JFXButton noPassButton;
	
	private ArrayList<FXMLLoader> loaders = new ArrayList<>();
    private ArrayList<VBox> cells = new ArrayList<>();
    
    ExaminationBLService examinationBLService = ExaminationBLFactory.getBLService();
    GeneralManagerViewController generalManagerViewController;
    ArrayList<BillVO> bills;
    
    @FXML
    public void initialize(){
        billList.setHgap(35);
        billList.setVgap(30);
        billList.setPrefColumns(3);

//        showBillList();
    }
    
    public void setGeneralManagerViewController(GeneralManagerViewController generalManagerViewController){
    	this.generalManagerViewController = generalManagerViewController;
    }
    
    public void showBillList(){
    	billList.getChildren().clear();
    	loaders.clear();
    	cells.clear();
        bills = examinationBLService.show();
        for (int i = 0; i < bills.size(); i++){
            try {
                FXMLLoader billLoader = new FXMLLoader();
                billLoader.setLocation(getClass().getResource("/view/generalManager/ExaminationCell.fxml"));
                VBox cell = billLoader.load();
                loaders.add(billLoader);
                cells.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i =0; i < bills.size(); i++){
            GeneralManagerExaminationCellController generalManagerExaminationCellController = loaders.get(i).getController();
            generalManagerExaminationCellController.setBill(bills.get(i));
            generalManagerExaminationCellController.setGeneralManagerExaminationViewController(this);
            billList.getChildren().add(cells.get(i));
        }
    }
    
    public void clickPassButton(){
    	Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定通过选中单据吗？");
        Optional result = dialog.showAndWait();

        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	for(int i=0;i<loaders.size();i++){
            		GeneralManagerExaminationCellController generalManagerExaminationCellController = loaders.get(i).getController();
            		if(generalManagerExaminationCellController.checkBox.isSelected()){
            			examinationBLService.approveReceipt(bills.get(i));
            		}
            	}
            	generalManagerViewController.showExaminationView();
            }
        }
    }
    
    public void clickNoPassButton(){
    	Dialog dialog = DialogFactory.getConfirmationAlert();
        dialog.setHeaderText("确定不通过选中单据吗？");
        Optional result = dialog.showAndWait();

        if (result.isPresent()){
            if (result.get() == ButtonType.OK) {
            	for(int i=0;i<loaders.size();i++){
            		GeneralManagerExaminationCellController generalManagerExaminationCellController = loaders.get(i).getController();
            		if(generalManagerExaminationCellController.checkBox.isSelected()){
            			examinationBLService.refuseReceipt(bills.get(i));
            		}
            	}
            	generalManagerViewController.showExaminationView();
            }
        }
    }
    
    public void showBillDetail(Pane pane){
    	generalManagerViewController.showBillDetail(pane);
    }
    
    public void clickReturnButton(){
    	generalManagerViewController.showExaminationView();
    }
}
