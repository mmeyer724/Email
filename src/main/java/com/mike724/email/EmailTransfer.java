/*
    This file is part of Email.

    Email is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Email is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Email.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.mike724.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTransfer {
	
	private Email plugin;
	private EmailProvider type;
	private String user;
	private String password;
	
	public EmailTransfer(Email plugin, EmailProvider type, String user, String password) {
		this.plugin = plugin;
		this.type = type;
		this.user = user;
		this.password = password;
	}
	
	public void send(String to, String subject, String content) {
		//Handle GMAIL
		if(this.type == EmailProvider.GMAIL) {
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.socketFactory.fallback", "false");
			p.put("mail.smtp.password", this.password);
			p.put("mail.smtp.port", "465");

			Session s = Session.getInstance(p, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			Message m = new MimeMessage(s);
			try {
				m.setFrom(new InternetAddress(this.user));
				m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				m.setSubject(subject);
				m.setText(content);
				Transport.send(m);
				plugin.getLogger().info("Email sent!");
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
		}
		
		//Handle HOTMAIL
		if(this.type == EmailProvider.HOTMAIL) {
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.live.com");
			p.put("mail.smtp.socketFactory.port", "25");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.socketFactory.fallback", "false");
			// This was originally this.user, but this.password seems to make more sense.
			p.put("mail.smtp.password", this.password);
			p.put("mail.smtp.port", "25");

			Session s = Session.getInstance(p, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			Message m = new MimeMessage(s);
			try {
				m.setFrom(new InternetAddress(this.user));
				m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				m.setSubject(subject);
				m.setText(content);
				Transport.send(m);
				plugin.getLogger().info("Email sent!");
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
		}
	}
}