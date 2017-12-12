package ExcelUtil.model;

public class AnnotationModel {

    private String orjinalFieldName;
    private String excelColumnName;

    public AnnotationModel() {
    }

    public AnnotationModel(String orjinalFieldName, String excelColumnName) {
        this.orjinalFieldName = orjinalFieldName;
        this.excelColumnName = excelColumnName;
    }

    public String getOrjinalFieldName() {
        return orjinalFieldName;
    }

    public void setOrjinalFieldName(String orjinalFieldName) {
        this.orjinalFieldName = orjinalFieldName;
    }

    public String getExcelColumnName() {
        return excelColumnName;
    }

    public void setExcelColumnName(String excelColumnName) {
        this.excelColumnName = excelColumnName;
    }


   

}
