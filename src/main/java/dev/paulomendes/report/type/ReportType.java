package dev.paulomendes.report.type;

import dev.paulomendes.report.exception.ReportSimplifierException;
import dev.paulomendes.report.output.IReportOutput;
import dev.paulomendes.report.output.impl.CsvOutput;
import dev.paulomendes.report.output.impl.PdfOutput;
import dev.paulomendes.report.output.impl.XlsOutput;

import java.util.EnumSet;

/**
 * Enum that represents report type.
 * @author Paulo Mendes
 */
public enum ReportType {

    PDF("pdf",1){
        @Override
        public IReportOutput getOutput() {
            return PdfOutput.getInstance();
        }
    },
    CSV("csv", 2){
        @Override
        public IReportOutput getOutput() {
            return CsvOutput.getInstance();
        }
    },
    XLS("xls", 3){
        @Override
        public IReportOutput getOutput() {
            return XlsOutput.getInstance();
        }
    };

    private String type;
    private int value;
    public abstract IReportOutput getOutput();

    ReportType(String type, int value){
        this.type = type;
        this.value = value;
    }

    public static ReportType getReportByValue(int value) throws ReportSimplifierException {
        return EnumSet.allOf(ReportType.class)
                .stream()
                .filter(e -> e.value == value)
                .findAny()
                .orElseThrow(() -> new ReportSimplifierException("Invalid Report Type Value"));
    }

    public static ReportType getReportByType(String type) throws ReportSimplifierException{
        return EnumSet.allOf(ReportType.class)
                .stream()
                .filter(e -> e.type.equals(type))
                .findAny()
                .orElseThrow(() -> new ReportSimplifierException(String.format("Invalid Report Type: %s.", type)));
    }

    public String getType(){
        return type;
    }



}
