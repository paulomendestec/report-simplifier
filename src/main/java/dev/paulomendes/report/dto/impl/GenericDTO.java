package dev.paulomendes.report.dto.impl;

import dev.paulomendes.report.dto.IReportDTO;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.Optional;

/**
 * Abstract representation of a report DTO Object.
 * @author Paulo Mendes
 */
public abstract class GenericDTO implements IReportDTO {

    private JRDataSource dataSource;

    /**
     * Constructor
     * @param dataSource receives a dataSource value to assign to local variable.
     */
    GenericDTO(JRDataSource dataSource){
        this.dataSource = dataSource;
    }

    /**
     * Getting local dataSource
     * @return in case of null dataSource, returns empty dataSource.
     */
    @Override
    public JRDataSource getDataSource() {
        return Optional.of(dataSource).orElse(IReportDTO.getEmptyDataSource());
    }
}
