package bl.promotionbl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bl.salesbl.Purchase;
import po.GoodsItemPO;
import po.PromotionBargainPO;
import rmi.PromotionRemoteHelper;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;

public class PromotionBargain extends Promotion{
	
	ArrayList<PromotionBargainPO> promotionBargainPOs;
	
	public PromotionBargain(){
		promotionDataService = PromotionRemoteHelper.getInstance().getPromotionDataService();
	}
	
	public ArrayList<PromotionBargainVO> show() throws RemoteException{
		ArrayList<PromotionBargainVO> promotionBargainVOs = new ArrayList<>();
		promotionBargainPOs = promotionDataService.showPB();
		for(PromotionBargainPO po:promotionBargainPOs){
			promotionBargainVOs.add(poTOvo(po));
		}
		return promotionBargainVOs;
	}
	
	public void addBargain(GoodsItemVO vo){
		
	}
	
	public void setPrice(double price){
		
	}
	
	public void setStartDate(String date){
		
	}
	
	public void setEndDate(String date){
		
	}
	
	public ResultMessage submit(PromotionBargainVO vo) throws RemoteException{
		return promotionDataService.addPB(voTOpo(vo));
	}
	
	public PromotionBargainVO findPromotionByID(String promotionID) throws RemoteException{
		PromotionBargainVO promotionBargainVO = poTOvo(promotionDataService.findPB(promotionID));
		return promotionBargainVO;
	}
	
	public PromotionBargainPO voTOpo(PromotionBargainVO promotionBargainVO){
		List<GoodsItemPO> bargains = new ArrayList<>();
		for(GoodsItemVO vo:promotionBargainVO.bargains){
			bargains.add(Purchase.voTopo(vo));
		}
		return new PromotionBargainPO(promotionBargainVO.promotionName, promotionBargainVO.startDate, promotionBargainVO.endDate, PromotionType.BARGAIN_STRATEGY, 
				promotionBargainVO.goodsTotal, promotionBargainVO.bargainTotal, bargains);
	}
	
	public PromotionBargainVO poTOvo (PromotionBargainPO promotionBargainPO){
		ArrayList<GoodsItemVO> bargains = new ArrayList<>();
		for(GoodsItemPO po:promotionBargainPO.getBargains()){
			bargains.add(Purchase.poTovo(po));
		}
		return new PromotionBargainVO(promotionBargainPO.getPromotionName(), promotionBargainPO.getPromotionID(), promotionBargainPO.getGoodsTotal(),
				promotionBargainPO.getBargainTotal(), promotionBargainPO.getStartDate(), promotionBargainPO.getEndDate(), bargains);
	}
}
