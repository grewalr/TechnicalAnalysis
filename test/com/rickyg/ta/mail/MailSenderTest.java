package com.rickyg.ta.mail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.util.ApplicationProperties;

public class MailSenderTest extends TestCase
{
	private static final Log LOG = LogFactory.getLog(MailSenderTest.class);
	private static ApplicationProperties appProps = ApplicationProperties.getInstance();
	private Emailer mailSender;

	@Override
	@Before
	public void setUp()
	{
		Configuration config = null;
		try
		{
			config = new PropertiesConfiguration("D:/eclipse_workspace/Default/TechnicalAnalysis/etc/application.properties");
		}
		catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			LOG.error("Error loading properties file: " + e.getMessage(),e);
		}
		appProps.setProperties(config);
		mailSender = new Emailer();
		LOG.info("PROPS LOADED");
	}

	@Test
	public void testSendMail()
	{
		Quote q1 = new Quote();
		Quote q2 = new Quote();

		q1.setName("US Dollar vs Japanes Yen");
		q2.setName("US Dollar vs Swiss Franc");
		
		q1.setSymbol("USDJPY");
		

		List<Quote> list = new ArrayList<Quote>();
		list.add(q1);
		list.add(q2);

		mailSender.send(list, "BUY");
	}
	
	public void testUpdateMail()
	{
		Quote quote = new Quote();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String dateTimeStr = "12/06/2009 15:32";
		Date dateTime = null;
		try
		{
			dateTime = dateFormat.parse(dateTimeStr);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		quote.setSymbol("USDJPY");
		quote.setName("US Dollar vs Japanese Yen");
		quote.setOpen(98.197);
		quote.setBid(98.2);
		quote.setOffer(98.23);
		quote.setDateTime(dateTime);
		
//		mailSende
	}

	@Override
	@After
	public void tearDown()
	{
		mailSender = null;
	}
}
