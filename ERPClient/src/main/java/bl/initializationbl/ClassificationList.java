package bl.initializationbl;

import bl.classificationbl.Classification;
import bl.classificationbl.ClassificationController;
import blservice.classificationblservice.ClassificationInfo;
import dataimpl.initializationdataimpl.InitializationDataServiceImpl;
import dataservice.initializationdataservice.InitializationDataService;
import po.ClassificationPO;
import po.InitClassificationPO;
import vo.ClassificationVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class ClassificationList {
    private ArrayList<ClassificationVO> classificationVOS;
    private ClassificationInfo classificationInfo;
    private InitializationDataService initializationDataService = InitializationDataServiceImpl.getInstance();

    public ClassificationList(){
        classificationInfo = new ClassificationController();
    }
    public ArrayList<InitClassificationPO> getClassifications(){
        ArrayList<InitClassificationPO> pos = new ArrayList<>();
       HashMap<String,String> clas = classificationInfo.getAllClassification();
       for (String ID : clas.keySet()){
           pos.add(new InitClassificationPO(Integer.valueOf(ID),clas.get(ID)));
       }
       return pos;
    }

    public ArrayList<ClassificationVO> posTovos(List<InitClassificationPO> initClassificationPOS) {
        ArrayList<ClassificationVO> vos = new ArrayList<>();
        for (InitClassificationPO po : initClassificationPOS){
            vos.add(new ClassificationVO(po.getID()+"",po.getName(),null,null,null));
        }
        return vos;
    }
}
