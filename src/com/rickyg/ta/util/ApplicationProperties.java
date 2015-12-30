package com.rickyg.ta.util;
import java.util.List;

import org.apache.commons.configuration.Configuration;


public class ApplicationProperties
{
	private static ApplicationProperties instance = new ApplicationProperties();
	private Configuration props;

	private ApplicationProperties()
	{}
	
	public static ApplicationProperties getInstance()
    {
        return instance;
    }
	
	public String getMailTransportProtocol()
	{
		return props.getString(ApplicationConstants.MAIL_TRANSPORT_PROTOCOL);
	}
	
	public String getMailHost()
	{
		return props.getString(ApplicationConstants.MAIL_HOST);
	}
	
	public String getMailSmtpAuth()
	{
		return props.getString(ApplicationConstants.MAIL_SMTP_AUTH);
	}
	
	public String getMailSmtpPort()
	{
		return props.getString(ApplicationConstants.MAIL_SMTP_PORT);
	}
	
	public String getMailSmtpSocketfactoryPort()
	{
		return props.getString(ApplicationConstants.MAIL_SMTP_SOCKETFACTORY_PORT);
	}
	
	public String getMailSmtpSocketfactoryClass()
	{
		return props.getString(ApplicationConstants.MAIL_SMTP_SOCKETFACTORY_CLASS);
	}
	
	public String getMailSmtpSocketfactoryFallback()
	{
		return props.getString(ApplicationConstants.MAIL_SMTP_SOCKETFACTORY_FALLBACK);
	}
	
	public String getMailSmtpQuitwait()
	{
		return props.getString(ApplicationConstants.MAIL_SMTP_QUITWAIT);
	}
	
	public String getMailSubject()
	{
		return props.getString(ApplicationConstants.MAIL_SUBJECT);
	}
	
	public String getMailBody()
	{
		return props.getString(ApplicationConstants.MAIL_BODY);
	}
	
	public String getMailSender()
	{
		return props.getString(ApplicationConstants.MAIL_SENDER);
	}
	
	public String getMailRecipients()
	{
		return props.getString(ApplicationConstants.MAIL_RECIPIENTS);
	}
	
	public String getMailboxUser()
	{
		return props.getString(ApplicationConstants.MAILBOX_USER);
	}
	
	public String getMailboxPassword()
	{
		return props.getString(ApplicationConstants.MAILBOX_PASSWORD);
	}

	public String getDBUrl()
	{
		return props.getString(ApplicationConstants.DB_URL);
	}
	
	public String getDBName()
	{
		return props.getString(ApplicationConstants.DB_NAME);
	}
	
	public String getDBDriver()
	{
		return props.getString(ApplicationConstants.DB_DRIVER);
	}
	
	public String getDBUser()
	{
		return props.getString(ApplicationConstants.DB_USER);
	}
	
	public String getDBPassword()
	{
		return props.getString(ApplicationConstants.DB_PASSWORD);
	}
	
	public int getTimeSeries()
	{
		return props.getInt(ApplicationConstants.TIME_SERIES);
	}
	
	public List<String> getFXQuotes()
	{
		return props.getList(ApplicationConstants.FX_QUOTES);
	}
	
    public Class getQuoteDAO() throws ClassNotFoundException 
    {
    	return Class.forName(props.getString(ApplicationConstants.QUOTE_DAO));
    }
    
    public boolean isEmailNotify()
    {
    	boolean flag = false;
    	String value = props.getString(ApplicationConstants.EMAIL_NOTIFY);
    
    	if(value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("TRUE"))
    	{
    		flag = true;
    	}
    	
    	return flag;
    }
	
	public void setProperties(Configuration props)
    {
    	this.props = props;
    }
	
	

	
}
