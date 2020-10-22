package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class OTPservice {
	public String generateOTP() {
		int randomPin   =(int)(Math.random()*900000)+100000;
		String otp  =String.valueOf(randomPin);
		return otp;
	}
	public boolean sendOTP() {

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("duongbach48@gmail.com", "3Mduongbach");

            }

        });
        //session.setDebug(true);
        	try {
                MimeMessage message = new MimeMessage(session);
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bach.duonghoang@digital-id.vn"));
                message.setSubject("NEAC Email OTP");
                message.setText(generateOTP());
     
                // send message
                Transport.send(message);
     
                System.out.println("Message sent successfully");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
  

	}
}
