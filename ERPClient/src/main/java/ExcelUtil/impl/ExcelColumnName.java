package ExcelUtil.impl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumnName {

    String value();

    ShowType showType() default ShowType.NONE;

    public enum ShowType {
        UPPERCASE, LOWERCASE, NONE;
    }
}
