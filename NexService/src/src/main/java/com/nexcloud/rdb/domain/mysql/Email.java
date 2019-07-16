package com.nexcloud.rdb.domain.mysql;

public class Email {
	private int emailNo;
	private String email;
	private char emailType;
	private String emailPassword;
	public Email() {
		// TODO Auto-generated constructor stub
	}
	public int getEmailNo() {
		return emailNo;
	}
	public void setEmailNo(int emailNo) {
		this.emailNo = emailNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getEmailType() {
		return emailType;
	}
	public void setEmailType(char emailType) {
		this.emailType = emailType;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public Email(int emailNo, String email, char emailType, String emailPassword) {
		super();
		this.emailNo = emailNo;
		this.email = email;
		this.emailType = emailType;
		this.emailPassword = emailPassword;
	}
}
