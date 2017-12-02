package ui.viewcontroller.GeneralManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import util.PromotionType;
import vo.PromotionVO;

public class GeneralManagerPromotionCellController {
	
	PromotionVO promotion;
	GeneralManagerPromotionViewController generalManagerPromotionViewController;

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
}
