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
     * Method that returns a transformation of {@link IReportDTO} DTO into a {@link Map} of parameters.
     * @return {@link Map} object.
     */
    Map<String, Object> getParameters();

    /**
     * Method that returns a datasource to fill the report.
     * @return {@link JRDataSource} object.
     */
    JRDataSource getDataSource();

    /**
     * Static method to get empty dataSource.
     * @return {@link JREmptyDataSource} object.
     */
    static JRDataSource getEmptyDataSource(){
        return new JREmptyDataSource();
    }
}
