package ui.viewcontroller.GeneralManager;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.PromotionType;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PromotionVO;

public class GeneralManagerPromotionCellController {
	
	PromotionVO promotion;
	GeneralManagerPromotionViewController generalManagerPromotionViewController;
	GeneralManagerPromotionBargainAddViewController generalManagerPromotionBargainAddViewController;
	GeneralManagerPromotionCustomerAddViewController generalManagerPromotionCustomerAddViewController;
	GeneralManagerPromotionTotalAddViewController generalManagerPromotionTotalAddViewController;

	@FXML
	Label promotionID;
	
	@FXML
	Label promotionName;
	
	@FXML
	Label promotionType;
	
	@FXML
	Label startTime;
	
	@FXML
	Label endTime;
	
	@FXML
	Label detailIcon;
	
	@FXML
	public void initialize(){
		detailIcon.setText("\ue89d");
	}
	
	public void setGeneralManagerPromotionViewController(GeneralManagerPromotionViewController generalManagerPromotionViewController){
		this.generalManagerPromotionViewController = generalManagerPromotionViewController;
	}
	
	public void setPromotion(PromotionVO promotion){
		this.promotion = promotion;
		promotionID.setText(promotion.promotionID);
		promotionName.setText(promotion.promotionName);
		startTime.setText(promotion.startDate.toString());
		endTime.setText(promotion.endDate.toString());
		setPromotionType(promotion.type);
	}
	
	public void setPromotionType(PromotionType type){
		promotionType.setText(type.getValue());
		if(type==PromotionType.BARGAIN_STRATEGY){
			promotionType.setTextFill(Color.web("#0000CC"));
			promotionType.setStyle("-fx-border-color:#0000CC");
		}
		else if(type==PromotionType.MEMBER_PROMOTION_STRATEGY){
			promotionType.setTextFill(Color.web("#FF9900"));
			promotionType.setStyle("-fx-border-color:#FF9900");
		}
		else{
			promotionType.setTextFill(Color.web("#FF3399"));
			promotionType.setStyle("-fx-border-color:#FF3399");
		}
	}
	
	public void clickDetailButton(){
		Pane pane = null;
		if(promotion.type == PromotionType.BARGAIN_STRATEGY){
			pane = showBargainView();
		}
		else if(promotion.type == PromotionType.MEMBER_PROMOTION_STRATEGY){
			pane = showCustomerView();
		}
		else{
			pane = showTotalView();
		}
		generalManagerPromotionViewController.showPromotionDetail(pane);
	}
	
	public Pane showBargainView(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/generalManager/PromotionBargainAdd.fxml"));
            page = pageLoader.load();
            generalManagerPromotionBargainAddViewController = pageLoader.getController();
            generalManagerPromotionBargainAddViewController.setGeneralManagerPromotionViewController(generalManagerPromotionViewController);

        } catch (IOException e) {
            e.printStackTrace();
        }
		generalManagerPromotionBargainAddViewController.setForDetailView((PromotionBargainVO) promotion);
		generalManagerPromotionBargainAddViewController.addPromotionBargain();
		return page;
	}
	
	public Pane showCustomerView(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/generalManager/PromotionCustomerAdd.fxml"));
            page = pageLoader.load();
            generalManagerPromotionCustomerAddViewController = pageLoader.getController();
            generalManagerPromotionCustomerAddViewController.setGeneralManagerPromotionViewController(generalManagerPromotionViewController);

        } catch (IOException e) {
            e.printStackTrace();
        }
		generalManagerPromotionCustomerAddViewController.setForDetailView((PromotionCustomerVO) promotion);
		generalManagerPromotionCustomerAddViewController.addPromotionCustomer();
		return page;
	}
	
	public Pane showTotalView(){
		Pane page = null;
		try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/view/generalManager/PromotionTotalAdd.fxml"));
            page = pageLoader.load();
            generalManagerPromotionTotalAddViewController = pageLoader.getController();
            generalManagerPromotionTotalAddViewController.setGeneralManagerPromotionViewController(generalManagerPromotionViewController);

        } catch (IOException e) {
            e.printStackTrace();
        }
		generalManagerPromotionTotalAddViewController.setForDetailView((PromotionTotalVO) promotion);
		generalManagerPromotionTotalAddViewController.addPromotionTotal();
		return page;
	}
}
