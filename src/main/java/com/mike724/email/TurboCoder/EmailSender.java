
package com.mike724.email.TurboCoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author TurboCoder
 */

public class EmailSender {
private String EMAIL_FROM_PLAYER;
private String EMAIL_PASSWORD;
private String EMAIL_TO_PLAYER;
private String SUBJECT;
private String MESSAGE;

    public EmailSender(String EMAIL_FROM_PLAYER,String EMAIL_PASSWORD, String EMAIL_TO_PLAYER,String SUBJECT, String MESSAGE) {
        this.EMAIL_FROM_PLAYER = EMAIL_FROM_PLAYER;
        this.EMAIL_PASSWORD = EMAIL_PASSWORD;
        this.EMAIL_TO_PLAYER = EMAIL_TO_PLAYER;
        this.SUBJECT= SUBJECT;
        this.MESSAGE = MESSAGE;
    }
    
    
    public void send(){
        
     if(this.EMAIL_FROM_PLAYER.contains("gmail")){    
    Properties p= new Properties();
    p.put("mail.smtp.host","smtp.gmail.com");
    p.put("mail.smtp.socketFactory.port","465");
    p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.auth","true");
    p.put("mail.smtp.socketFactory.fallback","false");
    p.put("mail.smtp.password",this.EMAIL_PASSWORD);
    p.put("mail.smtp.port","465");
            
                
Session s= Session.getInstance(p,
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                
            return new PasswordAuthentication(EMAIL_FROM_PLAYER,EMAIL_PASSWORD);
            }
            }
            );
    
      Message m=new MimeMessage(s);
        try {
            m.setFrom(new InternetAddress(this.EMAIL_FROM_PLAYER));
            m.setRecipients(Message.RecipientType.TO,InternetAddress.parse(this.EMAIL_TO_PLAYER));
            m.setSubject(this.SUBJECT);
            m.setText(this.MESSAGE);
            Transport.send(m);
            System.out.println("MESSAGE SENT");
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    if(this.EMAIL_FROM_PLAYER.contains("hotmail")){    
    Properties p= new Properties();
    p.put("mail.smtp.host","smtp.live.com");
    p.put("mail.smtp.socketFactory.port","25");
    p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.auth","true");
    p.put("mail.smtp.socketFactory.fallback","false");
    p.put("mail.smtp.password",this.EMAIL_FROM_PLAYER);
    p.put("mail.smtp.port","25");
    
    Session s= Session.getInstance(p,
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                
            return new PasswordAuthentication(EMAIL_FROM_PLAYER,EMAIL_PASSWORD);
            }
            }
            );
    
      Message m=new MimeMessage(s);
        try {
            m.setFrom(new InternetAddress(this.EMAIL_FROM_PLAYER));
            m.setRecipients(Message.RecipientType.TO,InternetAddress.parse(this.EMAIL_TO_PLAYER));
            m.setSubject(this.SUBJECT);
            m.setText(this.MESSAGE);
            Transport.send(m);
            System.out.println("MESSAGE SENT");
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    }

   
    
}
