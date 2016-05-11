package br.octa.model;

public class Destination {
	private String sapHost;
	private String sapClient;
	private String user;
	private String password;
	private String language;
	private String sapGateway;
	private String sapProgramId;
	private String sapConnectionCount;

	public Destination(String sapHost, String sapClient, String user, String password, String language,
			String sapGateway, String sapProgramId, String sapConnectionCount) {
		this.sapHost = sapHost;
		this.sapClient = sapClient;
		this.user = user;
		this.password = password;
		this.language = language;
		this.sapGateway = sapGateway;
		this.sapProgramId = sapProgramId;
		this.sapConnectionCount = sapConnectionCount;
	}

	public String getSapHost() {
		return sapHost;
	}

	public void setSapHost(String sapHost) {
		this.sapHost = sapHost;
	}

	public String getSapClient() {
		return sapClient;
	}

	public void setSapClient(String sapClient) {
		this.sapClient = sapClient;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSapGateway() {
		return sapGateway;
	}

	public void setSapGateway(String sapGateway) {
		this.sapGateway = sapGateway;
	}

	public String getSapProgramId() {
		return sapProgramId;
	}

	public void setSapProgramId(String sapProgramId) {
		this.sapProgramId = sapProgramId;
	}

	public String getSapConnectionCount() {
		return sapConnectionCount;
	}

	public void setSapConnectionCount(String sapConnectionCount) {
		this.sapConnectionCount = sapConnectionCount;
	}
}
