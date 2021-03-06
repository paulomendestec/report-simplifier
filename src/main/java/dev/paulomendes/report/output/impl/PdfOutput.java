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
     * Unique instance static variable.
     */
    private static PdfOutput instance;

    /**
     * Private constructor to avoid multiple instances.
     */
    private PdfOutput(){

    }

    /**
     * Unique instance check if PdfOutput is already initialized.
     * @return unique instance.
     */
    public static PdfOutput getInstance(){
        if(instance == null){
            synchronized (PdfOutput.class){
                instance = (instance == null) ? new PdfOutput() : instance;
            }
        }
        return instance;
    }

    /**
     * Avoiding clone.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("CsvOutput can't be cloned. Single instance object.");
    }

    /**
     * Method that generate the report PDF file, according to specifications.
     * @param jasper {@link JasperPrint} generated object.
     * @param path {@link Path} where to save the report.
     * @return {@link File} report file.
     * @throws ReportSimplifierException Unable to generate PDF report.
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
