package br.octa.exception;

public class NoProcessNFeException extends Exception {
	public NoProcessNFeException() {
		super();
	}

	public NoProcessNFeException(String msg) {
		super(msg);
	}

	public NoProcessNFeException(Exception cause) {
		super(cause.getMessage(),cause);
	}

	public NoProcessNFeException(String msg, Exception cause) {
		super(msg, cause);
	}

}
