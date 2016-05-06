package br.octa.exception;

public class PartnerCommunicationException extends Exception {
	public PartnerCommunicationException() {
		super();
	}

	public PartnerCommunicationException(String msg) {
		super(msg);
	}

	public PartnerCommunicationException(Exception cause) {
		super(cause.getMessage(),cause);
	}

	public PartnerCommunicationException(String msg, Exception cause) {
		super(msg, cause);
	}

}
