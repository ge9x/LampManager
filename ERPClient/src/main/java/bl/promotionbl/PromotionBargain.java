package bl.promotionbl;

import bl.logbl.LogBLFactory;
import bl.salesbl.GoodsItem;
import bl.userbl.UserBLFactory;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import rmi.PromotionRemoteHelper;
import util.OperationObjectType;
import util.OperationType;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PromotionBargain extends Promotion{
	
	ArrayList<PromotionBargainPO> promotionBargainPOs = new ArrayList<>();
	
	public PromotionBargain(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
		userInfo = UserBLFactory.getInfo();
		logInfo = LogBLFactory.getInfo();
	}
	
	public ArrayList<PromotionBargainVO> show() throws RemoteException{
		ArrayList<PromotionBargainVO> promotionBargainVOs = new ArrayList<>();
		promotionBargainPOs = promotionDataService.showPB();
		for(PromotionBargainPO po:promotionBargainPOs){
			promotionBargainVOs.add(poTOvo(po));
		}
		return promotionBargainVOs;
	}
	
	public String getNewPromotionBargainID() throws RemoteException{
		return promotionDataService.getNewPromotionBargainID();
	}
	
	public PromotionBargainVO findPromotionByName(String promotionName) throws RemoteException{
		PromotionBargainVO promotionBargainVO = null;
		if(promotionDataService.findPBByName(promotionName)!=null){
			promotionBargainVO = poTOvo(promotionDataService.findPBByName(promotionName));
		}
		return promotionBargainVO;
	}
	
	public ResultMessage submit(PromotionBargainVO vo) throws RemoteException{
		PromotionBargainPO po = voTOpo(vo);
		ResultMessage re = promotionDataService.addPB(po);
		if(re == ResultMessage.SUCCESS){
			logInfo.record(OperationType.ADD, OperationObjectType.PROMOTION, po.toString());
		}
		return re;
	}
	
	public PromotionBargainVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionBargainVO promotionBargainVO = poTOvo(promotionDataService.findPB(promotionID));
		return promotionBargainVO;
	}
	
	public ResultMessage updatePromotion(PromotionBargainVO promotionBargainVO) throws RemoteException{
		promotionBargainPOs = promotionDataService.showPB();
		for(PromotionBargainPO po:promotionBargainPOs){
			if(po.getPromotionID().equals(promotionBargainVO.promotionID)){
				po.setPromotionName(promotionBargainVO.promotionName);
				po.setStartDate(promotionBargainVO.startDate);
				po.setEndDate(promotionBargainVO.endDate);
				po.setBargainTotal(promotionBargainVO.bargainTotal);
				po.setGoodsTotal(promotionBargainVO.goodsTotal);
				po.getBargains().clear();
				ArrayList<GoodsItemVO> itemVOs = promotionBargainVO.bargains;
				for (GoodsItemVO itemVO : itemVOs) {
                    GoodsItemPO itemPO = GoodsItem.voTopo(itemVO);
                    po.getBargains().add(itemPO);
                }
				ResultMessage re = promotionDataService.updatePB(po);
				if(re == ResultMessage.SUCCESS){
					logInfo.record(OperationType.DELETE, OperationObjectType.PROMOTION, po.toString());
				}
				return re;
			}
		}
		
		return ResultMessage.FAILED;
	}
	
	public PromotionBargainPO voTOpo(PromotionBargainVO promotionBargainVO){
		List<GoodsItemPO> bargains = new ArrayList<>();
		for(GoodsItemVO vo:promotionBargainVO.bargains){
			bargains.add(GoodsItem.voTopo(vo));
		}
		return new PromotionBargainPO(promotionBargainVO.promotionName, promotionBargainVO.startDate, promotionBargainVO.endDate, PromotionType.BARGAIN_STRATEGY, 
				promotionBargainVO.goodsTotal, promotionBargainVO.bargainTotal, bargains, promotionBargainVO.promotionID);
	}
	
	public PromotionBargainVO poTOvo (PromotionBargainPO promotionBargainPO){
		ArrayList<GoodsItemVO> bargains = new ArrayList<>();
		for(GoodsItemPO po:promotionBargainPO.getBargains()){
			bargains.add(GoodsItem.poTovo(po));
		}
		return new PromotionBargainVO(promotionBargainPO.getPromotionName(), promotionBargainPO.getPromotionID(), promotionBargainPO.getGoodsTotal(),
				promotionBargainPO.getBargainTotal(), promotionBargainPO.getStartDate(), promotionBargainPO.getEndDate(), bargains);
	}
	
	public String getCurrentUserName(){
		return userInfo.getCurrentUserNameByID(userInfo.getCurrentUserID());
	}
}
