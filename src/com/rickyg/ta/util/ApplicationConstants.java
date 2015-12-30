package com.rickyg.ta.util;

public interface ApplicationConstants
{
	String MAIL_TRANSPORT_PROTOCOL			= "mail.transport.protocol";
	String MAIL_HOST						= "mail.host";
	String MAIL_SMTP_AUTH					= "mail.smtp.auth";
	String MAIL_SMTP_PORT					= "mail.smtp.port";
	String MAIL_SMTP_SOCKETFACTORY_PORT		= "mail.smtp.socketFactory.port";
	String MAIL_SMTP_SOCKETFACTORY_CLASS	= "mail.smtp.socketFactory.class";
	String MAIL_SMTP_SOCKETFACTORY_FALLBACK	= "mail.smtp.socketFactory.fallback";
	String MAIL_SMTP_QUITWAIT				= "mail.smtp.quitwait";
	
	String MAIL_SUBJECT						= "mail.subject";
	String MAIL_BODY						= "mail.body";
	String MAIL_SENDER						= "mail.sender";
	String MAIL_RECIPIENTS					= "mail.recipients";

	String MAILBOX_USER						= "mailbox.user";
	String MAILBOX_PASSWORD					= "mailbox.password";	
	
	String DB_URL							= "db.url";
	String DB_NAME							= "db.name";
	String DB_DRIVER						= "db.driver";
	String DB_USER							= "db.user";
	String DB_PASSWORD						= "db.password";
	
	String SMA								= "SMA";
	String LWMA								= "LWMA";
	String NO_SIGNAL						= "NO SIGNAL";
	
	String TIME_SERIES						= "time.series";
	
	String FX_QUOTES						= "fx.quotes";
	
	String PROPERTIES_FILE					= "application.properties";
	String QUOTE_DAO 						= "quoteDAO";
	
	String EMAIL_NOTIFY						= "email.notify";
}
