package dev.paulomendes.report.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for model Class, which can be turned into {@link dev.paulomendes.report.dto.IReportDTO} objects.
 * @author Paulo Mendes
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RSModel {

}
