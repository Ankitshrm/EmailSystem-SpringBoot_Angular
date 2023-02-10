package com.springboot.emailsys.services;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServices {
	
	public boolean sendEmail(String to,String subject,String text) {
		boolean flag=false;
		
		Properties properties =new Properties();
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.ssl.enable",true);
		properties.put("mail.smtp.port",""); //587 || 465
		properties.put("mail.smtp.host",""); //"smtp.gmail.com
		final String from ="";//ankitsharma97194@gmail.com
		final String password=""; //pass-code change - hrxbdyidzrlczavn
		
		Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ankitsharma97194@gmail.com", password);
			}
		
		 });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

}
