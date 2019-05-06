package dev.paulomendes.report.dto.impl;

import dev.paulomendes.report.annotation.RSDataSource;
import dev.paulomendes.report.annotation.RSModel;
import dev.paulomendes.report.annotation.RSParameter;
import dev.paulomendes.report.dto.IReportDTO;
import dev.paulomendes.report.exception.ReportSimplifierException;
import net.sf.jasperreports.engine.JRDataSource;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Class to create {@link IReportDTO} based on Java annotations with reflection API.
 * @author Paulo Mendes
 */
public class AnnotationGeneratedDTO implements IReportDTO {

    private Object object;
    private JRDataSource dataSource;
    private Map<String, Object> parameters;

    /**
     * Constructor
     * @param o receives a {@link RSModel} annotated Object.
     */
    public AnnotationGeneratedDTO(Object o) {
        if (o.getClass().isAnnotationPresent(RSModel.class)) {
            object = o;
            dataSource = this.extractDataSource();
            parameters = this.extractParameters();
        } else {
            throw new ReportSimplifierException(String.format("Can't extract DTO from class %s. This class is not assigned with RSModel annotation.", o.getClass().getCanonicalName()));
        }
    }

    /**
     * Method to extract parameters from {@link RSModel}.
     * @return {@link Map} of parameters.
     */
    private Map<String, Object> extractParameters() {
        Map<String, Object> map = new HashMap<>();
        Stream.of(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(RSParameter.class))
                .map(this::buildParameter)
                .forEach(map::putAll);

        return map;
    }

    /**
     * Method to extract {@link JRDataSource} from {@link RSModel}.
     * @return {@link JRDataSource} object.
     */
    private JRDataSource extractDataSource() {
        return Stream.of(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(RSDataSource.class))
                .findFirst()
                .map(this::buildDataSource)
                .orElse(IReportDTO.getEmptyDataSource());
    }

    /**
     * Method to build parameter.
     * @param field {@link Field} parameter.
     * @return {@link Map} object.
     * @throws ReportSimplifierException in case of any reflection exception.
     */
    private Map<String, Object> buildParameter(Field field) throws ReportSimplifierException {
        try {
            Map<String, Object> map = new HashMap<>();
            RSParameter parameter = field.getAnnotation(RSParameter.class);
            if(parameter.name().isEmpty()){
                throw new ReportSimplifierException(String.format("Annotation RSParameter require a name. Check field %s in class %s.", field.getName(), object.getClass().getCanonicalName()));
            }
            String name = parameter.name();
            Object value;
            if (!parameter.getter().isEmpty()) {
                value = object.getClass()
                        .getDeclaredMethod(parameter.getter())
                        .invoke(object);
            } else if (field.getModifiers() == Field.PUBLIC) {
                value = field.get(object);
            } else {
                value = object.getClass()
                        .getDeclaredMethod(String.format("get%s%s", field.getName().substring(0,1).toUpperCase(),field.getName().substring(1)))
                        .invoke(object);
            }
            map.put(name, value);
            return map;
        } catch (IllegalAccessException e) {
            throw new ReportSimplifierException("Getter is not accessible.", e);
        } catch (InvocationTargetException e) {
            throw new ReportSimplifierException("Getter throws exception.", e);
        } catch (NoSuchMethodException e) {
            throw new ReportSimplifierException("Getter not found.", e);
        }
    }

    /**
     * Method to build {@link JRDataSource} dataSource.
     * @param field {@link Field} parameter.
     * @return {@link JRDataSource} object.
     * @throws ReportSimplifierException in case of any reflection exception.
     */
    private JRDataSource buildDataSource(Field field) throws ReportSimplifierException {
        try {
            if (field.getModifiers() == Field.PUBLIC) {
                return (JRDataSource) field.get(object);
            } else {
                return (JRDataSource) object.getClass()
                        .getDeclaredMethod(String.format("get%s%s", field.getName().substring(0,1).toUpperCase(), field.getName().substring(1)))
                        .invoke(object);
            }
        } catch (IllegalAccessException e) {
            throw new ReportSimplifierException("Getter is not accessible.", e);
        } catch (InvocationTargetException e) {
            throw new ReportSimplifierException("Getter throws exception.", e);
        } catch (NoSuchMethodException e) {
            throw new ReportSimplifierException("Getter not found.", e);
        } catch (ClassCastException e) {
            throw new ReportSimplifierException(String.format("Field %s can't be casted as JRDataSource.", field.getName()), e);
        }
    }

    /**
     * Method that returns a transformation of {@link IReportDTO} DTO into a {@link Map} of parameters.
     * @return {@link Map} object.
     */
    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Method that returns a datasource to fill the report.
     * @return {@link JRDataSource} object.
     */
    @Override
    public JRDataSource getDataSource() {
        return dataSource;
    }

}
