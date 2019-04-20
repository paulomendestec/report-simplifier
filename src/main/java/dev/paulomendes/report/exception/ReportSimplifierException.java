package dev.paulomendes.report.exception;

/**
 * Report Simplifier Exception
 * @author Paulo Mendes
 */
public class ReportSimplifierException extends Exception {

	private static final long serialVersionUID = 2555312524814908417L;

	/**
	 * Constructor
	 * @param message is the message
	 */
	public ReportSimplifierException(String message){
		super(message);
	}

	/**
	 * Constructor
	 * @param message is the message
	 * @param e is the Exception
	 */
	public ReportSimplifierException(String message, Exception e) {
		super(message);
		e.printStackTrace();
	}

}
