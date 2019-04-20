package dev.paulomendes.report.service;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.dto.IReportDTO;
import dev.paulomendes.report.dto.impl.GenericDTO;
import dev.paulomendes.report.type.ReportType;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.nio.file.Paths;

/**
 * Interface for report services.
 *
 * @author Paulo Mendes
 */
public interface IReportService {

    /**
     * Method that returns JasperPrint object, builded with DTO.
     *
     * @param dto DTO
     * @return JasperPrint
     * @throws ReportSimplifierException if was not possible to fill the report.
     */
    JasperPrint processJasperReport(IReportDTO dto) throws ReportSimplifierException;

    /**
     * Default implementation of getting a report, given a report type.
     * @param type is the report type
     * @param dto DTO
     * @param path is a path to generate the report.
     * @return File
     * @throws ReportSimplifierException if was not possible to generate the report.
     */
    default File getReport(ReportType type, GenericDTO dto, String path) throws ReportSimplifierException{
        return type.getOutput().generateReport(this.processJasperReport(dto), Paths.get(path));
    }

}
