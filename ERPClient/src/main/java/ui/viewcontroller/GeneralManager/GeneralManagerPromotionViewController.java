package ui.viewcontroller.GeneralManager;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXNodesList;

import blservice.promotionblservice.promotionTotal.PromotionTotalBLService;
import blservice.promotionblservice.promotionbargain.PromotionBargainBLService;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerBLService;
import blstubdriver.PromotionBargain_Stub;
import blstubdriver.PromotionCustomer_Stub;
import blstubdriver.PromotionTotal_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import vo.PromotionVO;

public class GeneralManagerPromotionViewController {
	
	@FXML
	Label addIcon;
	
	@FXML
	VBox promotionList;
	
	@FXML
	JFXNodesList TypeChooser;
	
	private ArrayList<FXMLLoader> loaders = new ArrayList<>();
    private ArrayList<HBox> cells = new ArrayList<>();

	PromotionBargainBLService promotionBargainBLService = new PromotionBargain_Stub();
	PromotionCustomerBLService promotionCustomerBLService = new PromotionCustomer_Stub();
	PromotionTotalBLService promotionTotalBLService = new PromotionTotal_Stub();
    GeneralManagerViewController generalManagerViewController;
    GeneralManagerPromotionBargainAddViewController generalManagerPromotionBargainAddViewController;
    GeneralManagerPromotionCustomerAddViewController generalManagerPromotionCustomerAddViewController;
    GeneralManagerPromotionTotalAddViewController generalManagerPromotionTotalAddViewController;
	ArrayList<PromotionVO> promotions = new ArrayList<>();
	
	@FXML
	public void initialize(){
		addIcon.setText("\ue61e");
		TypeChooser.setVisible(false);
	}
	
	public void setGeneralManagerViewController(GeneralManagerViewController generalManagerViewController){
		this.generalManagerViewController = generalManagerViewController;
	}
	
	public void showPromotionList(){
		promotionList.getChildren().clear();
        cells.clear();
        loaders.clear();
        promotions.clear();
        promotions.addAll(promotionBargainBLService.show());
        promotions.addAll(promotionCustomerBLService.show());
        promotions.addAll(promotionTotalBLService.show());
        
        for (int i = 0; i < promotions.size(); i++){
            try {
                FXMLLoader promotionLoader = new FXMLLoader();
                promotionLoader.setLocation(getClass().getResource("/view/generalManager/PromotionCell.fxml"));
                HBox cell = promotionLoader.load();
                loaders.add(promotionLoader);
                cells.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i =0; i < promotions.size(); i++){
            GeneralManagerPromotionCellController promotionCellController = loaders.get(i).getController();
            promotionCellController.setPromotion(promotions.get(i));
            promotionCellController.setGeneralManagerPromotionViewController(this);
            promotionList.getChildren().add(cells.get(i));
        }
	}
	
	public void clickAddButton(){
		TypeChooser.setVisible(true);
	}
	
	public void closeTypeChooser(){
		TypeChooser.setVisible(false);
	}
	
	public void clickReturnButton(){
		generalManagerViewController.showPromotionView();
	}
	
	public void clickAddPromotionBargain(){
		Pane promotionAdd = null;
    	try{
	    	FXMLLoader promotionAddLoader = new FXMLLoader();
	    	promotionAddLoader.setLocation(getClass().getResource("/view/generalManager/PromotionBargainAdd.fxml"));
	        promotionAdd = promotionAddLoader.load();
	        
	        generalManagerPromotionBargainAddViewController = promotionAddLoader.getController();
	        generalManagerPromotionBargainAddViewController.setGeneralManagerPromotionViewController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	generalManagerViewController.showPromotionAddView(promotionAdd);
	}
	
	public void clickAddPromotionCustomer(){
		Pane promotionAdd = null;
    	try{
	    	FXMLLoader promotionAddLoader = new FXMLLoader();
	    	promotionAddLoader.setLocation(getClass().getResource("/view/generalManager/PromotionCustomerAdd.fxml"));
	        promotionAdd = promotionAddLoader.load();
	        
	        generalManagerPromotionCustomerAddViewController = promotionAddLoader.getController();
	        generalManagerPromotionCustomerAddViewController.setGeneralManagerPromotionViewController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	generalManagerViewController.showPromotionAddView(promotionAdd);
	}
	
	public void clickAddPromotionTotal(){
		Pane promotionAdd = null;
    	try{
	    	FXMLLoader promotionAddLoader = new FXMLLoader();
	    	promotionAddLoader.setLocation(getClass().getResource("/view/generalManager/PromotionTotalAdd.fxml"));
	        promotionAdd = promotionAddLoader.load();
	        
	        generalManagerPromotionTotalAddViewController = promotionAddLoader.getController();
	        generalManagerPromotionTotalAddViewController.setGeneralManagerPromotionViewController(this);
    	}catch (IOException e){
            e.printStackTrace();
    	}
    	generalManagerViewController.showPromotionAddView(promotionAdd);
	}
}
