package bl.initializationbl;

import bl.classificationbl.ClassificationController;
import blservice.classificationblservice.ClassificationInfo;
import dataimpl.initializationdataimpl.InitializationDataServiceImpl;
import dataservice.initializationdataservice.InitializationDataService;
import vo.ClassificationVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class ClassificationList {
    private ArrayList<ClassificationVO> classificationVOS;
    private ClassificationInfo classificationInfo;
    private InitializationDataService initializationDataService = InitializationDataServiceImpl.getInstance();

    public ClassificationList(){

    }
    public ArrayList<ClassificationVO> getClassifications(){
        initializationDataService.show();
        return classificationVOS;
    }
}
