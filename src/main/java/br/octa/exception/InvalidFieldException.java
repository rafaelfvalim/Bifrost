package br.octa.exception;

public class InvalidFieldException extends Exception {
	public InvalidFieldException() {
		super();
	}

	public InvalidFieldException(String msg) {
		super(msg);
	}

	public InvalidFieldException(Exception cause) {
		super(cause.getMessage(),cause);
	}

	public InvalidFieldException(String msg, Exception cause) {
		super(msg, cause);
	}

}
