package dev.paulomendes.report.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for DataSource field. Only for {@link net.sf.jasperreports.engine.JRDataSource} field.
 * @author Paulo Mendes
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RSDataSource {

}
