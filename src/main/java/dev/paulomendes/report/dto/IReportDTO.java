package dev.paulomendes.report.dto;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.util.Map;

/**
 * Interface for report DTOs.
 * @author Paulo Mendes
 */
public interface IReportDTO {

    /**
     * Method that returns a transformation of DTO in a map of parameters.
     * @return Map
     */
    Map<String, Object> getParameters();

    /**
     * Method that returns a datasource to fill the report.
     * @return JRDataSource
     */
    JRDataSource getDataSource();

    /**
     * Static method to get empty dataSource.
     * @return JREmptyDataSource
     */
    static JRDataSource getEmptyDataSource(){
        return new JREmptyDataSource();
    }
}
