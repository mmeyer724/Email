package com.mike724.email;

public class EmailTask implements Runnable {

	private EmailTransfer mailman;
	private String toEmail;
	private String emailSubject;
	private String emailContent;
	
	public EmailTask(EmailTransfer mailman, String toEmail, String emailSubject, String emailContent) {
		this.mailman = mailman;
		this.toEmail = toEmail;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
	}

	@Override
	public void run() {
		mailman.send(toEmail, emailSubject, emailContent);
	}

}
