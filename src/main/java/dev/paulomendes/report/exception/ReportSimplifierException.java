package dev.paulomendes.report.exception;

/**
 * Report Simplifier Exception
 * @author Paulo Mendes
 */
public class ReportSimplifierException extends RuntimeException {

	/**
	 * Constructor
	 * @param message {@link String} message.
	 */
	public ReportSimplifierException(String message){
		super(message);
	}

	/**
	 * Constructor
	 * @param message {@link String} message.
	 * @param e {@link Exception} exception.
	 */
	public ReportSimplifierException(String message, Exception e) {
		super(message);
		e.printStackTrace();
	}

}
