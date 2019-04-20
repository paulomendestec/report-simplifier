package dev.paulomendes.report.output;

import dev.paulomendes.report.exception.ReportSimplifierException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.nio.file.Path;

/**
 * Interface for report Outputs.
 * @author Paulo Mendes
 */
public interface IReportOutput {

    /**
     * Method that generate the report file, according to specifications.
     * @param jasper is the previous generated JasperPrint
     * @param path Path is the path where save the report
     * @return a report File
     * @throws ReportSimplifierException Exception
     */
    File generateReport(JasperPrint jasper, Path path) throws ReportSimplifierException;
}
