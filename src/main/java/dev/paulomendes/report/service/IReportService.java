package dev.paulomendes.report.service;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.dto.IReportDTO;
import dev.paulomendes.report.type.ReportType;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.nio.file.Paths;

/**
 * Interface for report services.
 * @author Paulo Mendes
 */
public interface IReportService {

    /**
     * Method that returns {@link JasperPrint} object, with {@link IReportDTO} DTO as parameter.
     * @param dto {@link IReportDTO} DTO object.
     * @return {@link JasperPrint} object.
     * @throws ReportSimplifierException if was not possible to fill the report.
     */
    JasperPrint processJasperReport(IReportDTO dto) throws ReportSimplifierException;

    /**
     * Default implementation to get a report, given a report type.
     * @param type {@link ReportType} object.
     * @param dto {@link IReportDTO} DTO object.
     * @param path {@link String} path to generate the report.
     * @return {@link File} report file.
     * @throws ReportSimplifierException if was not possible to generate the report.
     */
    default File getReport(ReportType type, IReportDTO dto, String path) throws ReportSimplifierException{
        return type.getOutput().generateReport(this.processJasperReport(dto), Paths.get(path));
    }

}
