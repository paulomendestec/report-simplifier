package dev.paulomendes.report.output.impl;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.output.IReportOutput;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;

/**
 * Implementation of report output for XLS files.
 * @author Paulo Mendes
 */
public class XlsOutput implements IReportOutput {

    /**
     * Unique instance static variable.
     */
    private static XlsOutput instance;

    /**
     * Private constructor to avoid multiple instances.
     */
    private XlsOutput(){

    }

    /**
     * Unique instance check if XlsOutput is already initialized.
     * @return unique instance.
     */
    public static XlsOutput getInstance(){
        if(instance == null){
            synchronized (XlsOutput.class){
                instance = (instance == null) ? new XlsOutput() : instance;
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
     * Method that generate the report XLS file, according to specifications.
     * @param jasper {@link JasperPrint} generated object.
     * @param path {@link Path} where to save the report.
     * @return {@link File} report file.
     * @throws ReportSimplifierException Unable to generate XLS report.
     */
    @Override
    public File generateReport(JasperPrint jasper, Path path) throws ReportSimplifierException {
        try{

            File out = new File(String.format("%s%sreport_%s_%d.xlsx", path.toFile(), File.separator, jasper.getName(), new Date().getTime()));
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasper));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            exporter.exportReport();

            return out;

        }catch (Exception e){
            throw new ReportSimplifierException(String.format("Unable to generate XLS report: %s",e.getMessage()), e);
        }
    }
}
