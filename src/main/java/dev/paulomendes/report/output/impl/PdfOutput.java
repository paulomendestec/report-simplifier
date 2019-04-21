package dev.paulomendes.report.output.impl;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.output.IReportOutput;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;

/**
 * Implementation of report output for PDF files.
 * @author Paulo Mendes
 */
public class PdfOutput implements IReportOutput {

    /**
     * Method that generate the PDF report file, according to specifications.
     * @param jasper is the previous generated JasperPrint
     * @param path Path is the path where save the report
     * @return a report File
     * @throws ReportSimplifierException Exception
     */
    @Override
    public File generateReport(JasperPrint jasper, Path path) throws ReportSimplifierException {
        try{

            File out = new File(String.format("%s%sreport_%s_%d.pdf", path.toFile(), File.separator, jasper.getName(), new Date().getTime()));
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasper));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
            exporter.exportReport();

            return out;

        }catch (Exception e){
            throw new ReportSimplifierException(String.format("Unable to generate XLS report: %s",e.getMessage()), e);
        }
    }
}