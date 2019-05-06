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
     * @param jasper {@link JasperPrint} generated object.
     * @param path {@link Path} where to save the report.
     * @return {@link File} report file.
     * @throws ReportSimplifierException Exception when is not possible to generate the report.
     */
    File generateReport(JasperPrint jasper, Path path) throws ReportSimplifierException;
}
