package com.rickyg.ta.mail;

import java.security.Security;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.util.ApplicationConstants;
import com.rickyg.ta.util.ApplicationProperties;

public class Emailer implements MessageSender
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(Emailer.class);
	private static ApplicationProperties appProps;
	private static Properties mailProps = new Properties();

	static
	{
		appProps = ApplicationProperties.getInstance();
		mailProps.setProperty(ApplicationConstants.MAIL_TRANSPORT_PROTOCOL, appProps.getMailTransportProtocol());
		mailProps.setProperty(ApplicationConstants.MAIL_HOST, appProps.getMailHost());
		mailProps.put(ApplicationConstants.MAIL_SMTP_AUTH, appProps.getMailSmtpAuth());
		mailProps.put(ApplicationConstants.MAIL_SMTP_PORT, appProps.getMailSmtpPort());
		mailProps.put(ApplicationConstants.MAIL_SMTP_SOCKETFACTORY_PORT, appProps.getMailSmtpSocketfactoryPort());
		mailProps.put(ApplicationConstants.MAIL_SMTP_SOCKETFACTORY_CLASS, appProps.getMailSmtpSocketfactoryClass());
		mailProps.put(ApplicationConstants.MAIL_SMTP_SOCKETFACTORY_FALLBACK, appProps.getMailSmtpSocketfactoryFallback());
		mailProps.put(ApplicationConstants.MAIL_SMTP_QUITWAIT, appProps.getMailSmtpQuitwait());
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	}

	public boolean send(List<Quote> list, String subject)
	{
		boolean mailSent = false;
		String mailBody = "";

		synchronized (this)
		{
			for (Quote quote : list)
			{
				mailBody += quote.toString();
			}

			Session session = Session.getDefaultInstance(mailProps, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(appProps.getMailboxUser(), appProps.getMailboxPassword());
				}
			});

			MimeMessage message = new MimeMessage(session);

			try
			{
				message.setSender(new InternetAddress(appProps.getMailSender()));
				message.setSubject(subject);
				message.setContent(mailBody, "text/plain");

				if (appProps.getMailRecipients().indexOf(',') > 0)
				{
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(appProps.getMailRecipients()));
				}
				else
				{
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(appProps.getMailRecipients()));
				}
				Transport.send(message);
			}
			catch (AddressException e)
			{
				LOG.error("Error sending mail, cause : " + e.getMessage(), e);
			}
			catch (MessagingException e)
			{
				LOG.error("Error sending mail, cause : " + e.getMessage(), e);
			}

			LOG.info("Mail sent to - " + appProps.getMailRecipients());
			mailSent = true;
		}
		
		return mailSent;
		
	}

	public boolean send(List<Quote> list)
	{
		return false;
	}
	
}
