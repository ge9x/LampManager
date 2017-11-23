package bl.formbl;

import blservice.formblservice.DocumentDetailsInput;
import blservice.inventoryblservice.InventoryInfo;
import blservice.salesblservice.SalesInfo;
import vo.BillVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class DocumentDetails{

    SalesInfo salesInfo;
    InventoryInfo inventoryInfo;

    public DocumentDetails(){

    }
    public ArrayList<BillVO> getDocumentDetails(DocumentDetailsInput input){
        return null;
    }

}
