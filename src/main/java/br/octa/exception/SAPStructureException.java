package br.octa.exception;

public class SAPStructureException extends Exception {
	public SAPStructureException() {
		super();
	}

	public SAPStructureException(String msg) {
		super(msg);
	}

	public SAPStructureException(Exception cause) {
		super(cause.getMessage(),cause);
	}

	public SAPStructureException(String msg, Exception cause) {
		super(msg, cause);
	}

}
