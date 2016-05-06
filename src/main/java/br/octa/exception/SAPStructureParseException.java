package br.octa.exception;

public class SAPStructureParseException extends Exception{
	public SAPStructureParseException() {
		super();
	}

	public SAPStructureParseException(String msg) {
		super(msg);
	}

	public SAPStructureParseException(Exception cause) {
		super(cause.getMessage(),cause);
	}

	public SAPStructureParseException(String msg, Exception cause) {
		super(msg, cause);
	}
}
