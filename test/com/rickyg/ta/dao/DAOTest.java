package com.rickyg.ta.dao;

import java.util.Calendar;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rickyg.ta.dao.impl.QuoteDAO_Hibernate;
import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.exceptions.TAException;
import com.rickyg.ta.util.ApplicationProperties;

public class DAOTest
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(DAOTest.class);

	private Quote quote = new Quote();
	private QuoteDAO dao;
	private ApplicationProperties props = ApplicationProperties.getInstance();

	@Before
	public void setUp() throws Exception
	{
		Configuration config = null;
		try
		{
			config = new PropertiesConfiguration("application.properties");
			props.setProperties(config);
		}
		catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			LOG.error("Error loading properties file: " + e.getMessage(),e);
		}
		props.setProperties(config);
		quote.setSymbol("TEST");
		quote.setName("TEST_HIBERNATE");
		quote.setDateTime(Calendar.getInstance().getTime());
		quote.setMailSentFlag("N");
		quote.setTradingSignal("TEST");
		
		dao = new QuoteDAO_Hibernate();
	}

	

//	@Test
//	public void testSave()
//	{
//		
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	public void testSaveQuote() throws TAException
	{
		dao.saveQuote(quote);
//		fail("Not yet implemented"); // TODO
	}

//	@Test
//	public void testUpdateQuote()
//	{
//		fail("Not yet implemented"); // TODO
//	}
	
	@After
	public void tearDown() throws Exception
	{
	}

}
