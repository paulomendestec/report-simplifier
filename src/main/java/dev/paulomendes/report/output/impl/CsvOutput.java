package dev.paulomendes.report.output.impl;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.output.IReportOutput;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;

/**
 * Implementation of report output for CSV files.
 * @author Paulo Mendes
 */
public class CsvOutput implements IReportOutput {

    /**
     * Unique instance static variable.
     */
    private static CsvOutput instance;

    /**
     * Private constructor to avoid multiple instances.
     */
    private CsvOutput(){

    }

    /**
     * Unique instance check if CsvOutput is already initialized.
     * @return unique instance.
     */
    public static CsvOutput getInstance(){
        if(instance == null){
            synchronized (CsvOutput.class){
                instance = (instance == null) ? new CsvOutput() : instance;
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
     * Method that generate the CSV report file, according to specifications.
     * @param jasper is the previous generated JasperPrint
     * @param path Path is the path where save the report
     * @return a report File
     * @throws ReportSimplifierException Exception
     */
    @Override
    public File generateReport(JasperPrint jasper, Path path) throws ReportSimplifierException {
        try{

            File out = new File(String.format("%s%sreport_%s_%d.csv", path.toFile(), File.separator, jasper.getName(), new Date().getTime()));
            JRCsvExporter exporter = new JRCsvExporter();
            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            configuration.setFieldDelimiter(";");
            exporter.setConfiguration(configuration);
            exporter.setExporterInput(new SimpleExporterInput(jasper));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(out));
            exporter.exportReport();

            return out;

        }catch (Exception e){
            throw new ReportSimplifierException(String.format("Unable to generate XLS report: %s",e.getMessage()), e);
        }
    }
}
