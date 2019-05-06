package dev.paulomendes.report.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for parameter fields.
 * @author Paulo Mendes
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RSParameter {

    /**
     * Represents the name of parameter.
     * @return the name of parameter.
     */
    String name();

    /**
     * Represents the name of the getter method.
     * @return the getter.
     */
    String getter() default "";

}
