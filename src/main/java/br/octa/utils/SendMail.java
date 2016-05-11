package br.octa.utils;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendMail {
	
	private static String SMTP_AUTH_USER;
    private static String SMTP_AUTH_PWD;
	
//	public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException	{
//		
//		String debugMail = ConfigUtils.getProperty("smtp.debug.mail");
//		if(debugMail == null || debugMail.trim().equals("")) debugMail = "false";
//	    boolean debug = Boolean.parseBoolean(debugMail);  
//		
//		//Set the host smtp address
//		Properties props = new Properties();
//		props.put("mail.smtp.host", ConfigUtils.getProperty("smtp.host.name"));
//		
//		String authentication = ConfigUtils.getProperty("authentication.required"); 
//		if(authentication == null || authentication.trim().equals("")) authentication = "false";
//		
//		Session session = null;
//		
//		if(Boolean.parseBoolean(authentication)){
//			SMTP_AUTH_USER = ConfigUtisl.getProperty("smtp.auth.user");
//		    SMTP_AUTH_PWD = Util.criptografiaSenha(ConfigUtil.getProperty("smtp.auth.pass"));
//			Authenticator auth = new SMTPAuthenticator();
//			props.put("mail.smtp.auth", "true");			
//			session = Session.getDefaultInstance(props, auth);		    
//		} else { 
//		    // create some properties and get the default Session
//		    session = Session.getDefaultInstance(props, null);
//		}
//	    session.setDebug(debug);
//	    // create a message
//	    Message msg = new MimeMessage(session);
//
//	    // set the from and to address
//	    InternetAddress addressFrom = new InternetAddress(from);
//	    msg.setFrom(addressFrom);
//
//	    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
//	    for (int i = 0; i < recipients.length; i++)
//	    {
//	        addressTo[i] = new InternetAddress(recipients[i]);
//	    }
//	    msg.setRecipients(Message.RecipientType.TO, addressTo);
//	   
//
//	    // Optional : You can also set your custom headers in the Email if you Want
//	    //msg.addHeader("MyHeaderName", "myHeaderValue");
//
//	    // Setting the Subject and Content Type
//	    msg.setSubject(subject);
//	    msg.setContent(message, "text/plain");
//	    Transport.send(msg);
//	}

	/**
	* SimpleAuthenticator is used to do simple authentication
	* when the SMTP server requires it.
	*/
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}

	
}
