package bl.initializationbl;

import blservice.customerblservice.CustomerInfo;
import po.InitCustomerPO;
import util.CustomerCategory;
import util.Level;
import vo.CustomerVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class CustomerList {
    private ArrayList<CustomerVO> customerVOS;
    private CustomerInfo customerInfo;

    public ArrayList<CustomerVO> getCustomers(){
        return customerVOS;
    }

    public ArrayList<CustomerVO> posTovos(List<InitCustomerPO> initCustomerPOS) {
        ArrayList<CustomerVO> vos = new ArrayList<>();
        for (InitCustomerPO po : initCustomerPOS){
            Level level = Level.levelToString(po.getLevel());
            CustomerCategory category = CustomerCategory.categoryToString(po.getCategory());
            vos.add(new CustomerVO(po.getCustomerID(),category,level,po.getCustomerName(),po.getPhone(),po.getAddress(),po.getPostCode(),po.getMail(),po.getReceivableLimit(),po.getReceive(),po.getPay(),po.getSalesman(),po.getPoints(),0));
        }
        return vos;
    }
}
