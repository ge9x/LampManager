package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bl.logbl.LogBLFactory;
import bl.salesbl.GoodsItem;
import bl.salesbl.Purchase;
import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.logblservice.LogInfo;
import blservice.userblservice.UserInfo;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import po.PromotionTotalPO;
import rmi.PromotionRemoteHelper;
import util.OperationObjectType;
import util.OperationType;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import vo.PromotionBargainVO;
import vo.PromotionTotalVO;

public class PromotionTotal extends Promotion{
	
	private ArrayList<PromotionTotalPO> promotionTotalPOs = new ArrayList<>();
	private UserInfo userInfo;
	private LogInfo logInfo;
	
	public PromotionTotal(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
		userInfo = UserBLFactory.getInfo();
		logInfo = LogBLFactory.getInfo();
	}
	
	public ArrayList<PromotionTotalVO> show() throws RemoteException{
		ArrayList<PromotionTotalVO> promotionTotalVOs = new ArrayList<>();
		promotionTotalPOs = promotionDataService.showPT();
		for(PromotionTotalPO po:promotionTotalPOs){
			promotionTotalVOs.add(poTOvo(po));
		}
		return promotionTotalVOs;
	}
	
	public String getNewPromotionTotalID() throws RemoteException{
		return promotionDataService.getNewPromotionTotalID();
	}
	
	public PromotionTotalVO findPromotionByName(String promotionName) throws RemoteException{
		PromotionTotalVO promotionTotalVO = null;
		if(promotionDataService.findPTByName(promotionName)!=null){
			promotionTotalVO = poTOvo(promotionDataService.findPTByName(promotionName));
		}
		return promotionTotalVO;
	}

	public ResultMessage submit(PromotionTotalVO vo) throws RemoteException{
		PromotionTotalPO po = voTOpo(vo);
		ResultMessage re = promotionDataService.addPT(po);
		if(re == ResultMessage.SUCCESS){
			logInfo.record(OperationType.ADD, OperationObjectType.PROMOTION, po.toString());
		}
		return re;
	}
	
	public PromotionTotalVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionTotalVO vo = poTOvo(promotionDataService.findPT(promotionID));
		return vo;
	}
	
	public ResultMessage deletePromotion(String promotionID) throws RemoteException{
		promotionTotalPOs.clear();
		promotionTotalPOs = promotionDataService.showPT();
		for(PromotionTotalPO po:promotionTotalPOs){
			if(po.getPromotionID().equals(promotionID)){
				ResultMessage re = promotionDataService.deletePT(po);
				if(re == ResultMessage.SUCCESS){
					logInfo.record(OperationType.DELETE, OperationObjectType.PROMOTION, po.toString());
				}
				return re;
			}
		}
		return ResultMessage.FAILED;
	}
	
	public ResultMessage updatePromotion(PromotionTotalVO promotionTotalVO) throws RemoteException{
		promotionTotalPOs = promotionDataService.showPT();
		for(PromotionTotalPO po:promotionTotalPOs){
			if(po.getPromotionID().equals(promotionTotalVO.promotionID)){
				po.setPromotionName(promotionTotalVO.promotionName);
				po.setStartDate(promotionTotalVO.startDate);
				po.setEndDate(promotionTotalVO.endDate);
				po.setTotalPrice(promotionTotalVO.totalPrice);
				po.setVoucher(promotionTotalVO.voucher);
				po.getGifts().clear();
				ArrayList<GoodsItemVO> itemVOs = promotionTotalVO.gifts;
				for (GoodsItemVO itemVO : itemVOs) {
                    GoodsItemPO itemPO = GoodsItem.voTopo(itemVO);
                    po.getGifts().add(itemPO);
                }
				ResultMessage re = promotionDataService.updatePT(po);
				if(re == ResultMessage.SUCCESS){
					logInfo.record(OperationType.DELETE, OperationObjectType.PROMOTION, po.toString());
				}
				return re;
			}
		}
		return ResultMessage.FAILED;
	}
	
	public PromotionTotalVO poTOvo(PromotionTotalPO promotionTotalPO){
		ArrayList<GoodsItemVO> gifts = new ArrayList<>();
		for(GoodsItemPO po:promotionTotalPO.getGifts()){
			gifts.add(GoodsItem.poTovo(po));
		}
		return new PromotionTotalVO(promotionTotalPO.getPromotionName(), promotionTotalPO.getPromotionID(), promotionTotalPO.getStartDate(),
				promotionTotalPO.getEndDate(), promotionTotalPO.getVoucher(), gifts, promotionTotalPO.getTotalPrice());
	}
	
	public PromotionTotalPO voTOpo(PromotionTotalVO promotionTotalVO){
		ArrayList<GoodsItemPO> gifts = new ArrayList<>();
		for(GoodsItemVO vo:promotionTotalVO.gifts){
			gifts.add(GoodsItem.voTopo(vo));
		}
		return new PromotionTotalPO(promotionTotalVO.promotionName, promotionTotalVO.startDate, promotionTotalVO.endDate, 
				PromotionType.TOTAL_PROMOTION_STRATEGY, promotionTotalVO.voucher, gifts, promotionTotalVO.totalPrice, promotionTotalVO.promotionID);
	}
	
	public String getCurrentUserName(){
		return userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID());
	}
}
