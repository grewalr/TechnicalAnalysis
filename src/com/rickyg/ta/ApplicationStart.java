package com.rickyg.ta;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.javaparts.dde.Conversation;
import com.javaparts.dde.DDEClient;
import com.javaparts.dde.DDEException;
import com.rickyg.ta.dde.MyDDEEventListener;
import com.rickyg.ta.util.ApplicationConstants;
import com.rickyg.ta.util.ApplicationProperties;

public class ApplicationStart
{
	public static final Log LOG = LogFactory.getLog(ApplicationStart.class);
	private static ApplicationProperties appProps = ApplicationProperties.getInstance();

	public static void main(String[] args)
	{
		try
		{
			Configuration config = new PropertiesConfiguration(ApplicationConstants.PROPERTIES_FILE);
			
			LOG.info("Loaded configuration file: " + ApplicationConstants.PROPERTIES_FILE);

			// Set the application properties
			appProps.setProperties(config);
		}
		catch (ConfigurationException e)
		{
			LOG.error("Error loading properties file: " + e.getMessage());
			LOG.error("Configuration error");
			exitOnError();
		}

		DDEClient client = DDEClient.getInstance();
		MyDDEEventListener eventListener = new MyDDEEventListener();
		client.addDDEEventListener(eventListener);
		LOG.info("Added event listener : " + eventListener.getClass());

		Conversation conversation = null;

		List<String> fxQuotes = appProps.getFXQuotes();
		Iterator<String> it = fxQuotes.iterator();
		while (it.hasNext())
		{
			try
			{
				String quoteSymbol = it.next();
				conversation = client.connect("MT4", "QUOTE");
				conversation.startAdvice(quoteSymbol);
				LOG.info("Successfully started advice for : " + quoteSymbol);
			}
			catch (DDEException e)
			{
				LOG.error("Exception occurred : ", e);
				System.exit(-1);
			}
		}

		for (;;)
		{
		}

	}

	private static void exitOnError()
	{
		System.out.println("Exiting.........");
		System.exit(-1);
	}
}
