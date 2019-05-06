package dev.paulomendes.report.service.impl;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.dto.IReportDTO;
import dev.paulomendes.report.service.IReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Class that represents a generic implementation of a report service.
 * @author Paulo Mendes
 */
public class GenericReportService implements IReportService {

    private String reportPath;

    /**
     * Constructor to receive the report path.
     * @param reportPath {@link String} report path.
     */
    public GenericReportService(String reportPath){
        this.reportPath = reportPath;
    }

    /**
     * Generic implementation of processJasperReport, returns directly a {@link JasperPrint} from {@link JasperFillManager}, with {@link IReportDTO} parameters.
     * @param dto {@link IReportDTO} DTO object.
     * @return {@link JasperPrint} object.
     * @throws ReportSimplifierException Was not possible to fill the report.
     */
    @Override
    public JasperPrint processJasperReport(IReportDTO dto) throws ReportSimplifierException {
        try {
            return JasperFillManager.fillReport(reportPath, dto.getParameters(), dto.getDataSource());
        }catch (JRException e){
            throw new ReportSimplifierException(String.format("Was not possible to fill the report: %s", e.getMessage()), e);
        }
    }
}
