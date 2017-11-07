package bl.inventorybl;

import util.ResultMessage;
import vo.InventoryBillVO;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockInventoryBill extends InventoryBill {

	@Override
	public ResultMessage submit() {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage save() {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(InventoryBillVO vo) {
		if(vo.ID.equals("BYD-20171107-00001")){
			System.out.println("Update inventory bill succeed");
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.ERROR;
		}
	}
	
}
