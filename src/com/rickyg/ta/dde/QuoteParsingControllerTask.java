package com.rickyg.ta.dde;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.javaparts.dde.ItemChangedEvent;
import com.rickyg.ta.dao.QuoteDAO;
import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.exceptions.TAException;
import com.rickyg.ta.fx.FX;
import com.rickyg.ta.fx.FXFactory;
import com.rickyg.ta.mail.Emailer;
import com.rickyg.ta.mail.MessageSender;
import com.rickyg.ta.studies.MovingAverageCalculator;
import com.rickyg.ta.util.ApplicationConstants;
import com.rickyg.ta.util.ApplicationProperties;

public class QuoteParsingControllerTask implements Runnable
{
	private static final Log LOG = LogFactory.getLog(QuoteParsingControllerTask.class);
	private ItemChangedEvent ice;
	private static final int SMA8 = 8;
	private static final int LWMA5 = 5;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static final MessageSender MAILER = new Emailer();
	private static ApplicationProperties props = ApplicationProperties.getInstance();
	private QuoteDAO dao;
	

	public QuoteParsingControllerTask(ItemChangedEvent ice)
	{
		super();
		this.ice = ice;
	}

	public void run()
	{
		LOG.info("PARSING QUOTE ==> " + ice.getItemName() + " : " + new String(ice.getItemValue()));
		String dateStr = null;
		String timeStr = null;
		String dateTimeStr = null;
		String bidStr = null;
		String offerStr = null;
		Date dateTime = null;
		String symbol = "";
		String key = "";
		Calendar cal = Calendar.getInstance();
		String crossOverSignal = ApplicationConstants.NO_SIGNAL;

		StringTokenizer st = new StringTokenizer(new String(ice.getItemValue()), " ");
		while (st.hasMoreTokens())
		{
			symbol = ice.getItemName();
			dateStr = st.nextToken();
			timeStr = st.nextToken();
			bidStr = st.nextToken();
			offerStr = st.nextToken();
			dateTimeStr = dateStr + " " + timeStr;

			try
			{
				dateTime = dateFormat.parse(dateTimeStr);
				cal.setTime(dateTime);
			}
			catch (ParseException e)
			{
				LOG.error("Could not parse date time : ", e);
			}
		}

		Quote quote = new Quote();
		quote.setSymbol(symbol);
		quote.setDateTime(dateTime);
		quote.setBid(new Double(bidStr));
		quote.setOffer(new Double(offerStr));
		quote.setOpen(new Double(bidStr));
		quote.setMailSentFlag("N");

		key = symbol + " : " + dateTimeStr;

		FX fx = FXFactory.getFXInstance(symbol);
		fx.initialise(quote);
		fx.getClosingQuotesList().add(quote);
		int secondLastPosition = fx.getClosingQuotesList().size() - 2;
		int lastPosition = fx.getClosingQuotesList().size() - 1;
		
//		if (fx.getClosingQuotesList().size() > 1)
//		{
//			Quote lastQuote = fx.getClosingQuotesList().get(lastPosition);
//			Quote secondLastQuote = fx.getClosingQuotesList().get(secondLastPosition);
//
//			secondLastQuote.setClose(lastQuote.getOpen());
//
//			fx.getClosingQuotesList().clear();
//			fx.getClosingQuotesList().add(secondLastQuote);
//			fx.getClosingQuotesList().add(lastQuote);
//		}

		if (cal.get(Calendar.MINUTE) % props.getTimeSeries() == 0)
		{
			if (fx.getQuoteAnalysisMap().containsKey(key))
			{
				Quote q = fx.getQuoteAnalysisMap().get(key);
//				q.setClose(fx.getClosingQuotesList().get(secondLastPosition).getClose());
				q.setClose(new Double(bidStr));
				fx.getQuoteAnalysisMap().put(key, q);
			}

			if (!fx.getQuoteAnalysisMap().containsKey(key))
			{
//				String oldKey = "";
//				Quote q = fx.getQuoteAnalysisMap().get(oldKey);
//				q.setClose(fx.getClosingQuotesList().get(secondLastPosition).getClose());
//				fx.getQuoteAnalysisMap().put(oldKey, q);
//				fx.getQuoteAnalysisMap().put(key, quote);
//				oldKey = key;

				try
				{
					dao = (QuoteDAO) props.getQuoteDAO().newInstance();
				}
				catch (InstantiationException e1)
				{
					LOG.error(e1);
				}
				catch (IllegalAccessException e1)
				{
					LOG.error(e1);
				}
				catch (ClassNotFoundException e1)
				{
					LOG.error(e1);
				}

//				if (fx.getClosingQuotesList().size() >= 2)
//				{
//					try
//					{
//						dao.updateQuote(fx.getQuoteAnalysisMap().get(oldKey));
//					}
//					catch (TAException e)
//					{
//						LOG.error("Problems encountered during db update : " + e);
//					}
//
//				}

				fx.getQuoteAnalysisMap().put(key, quote);
				fx.getQuotesListForCalculatingMA().clear();

				Collection<Quote> c = fx.getQuoteAnalysisMap().values();
				Iterator<Quote> itr = c.iterator();
				while (itr.hasNext())
				{
					Quote tempQuote = itr.next();
					fx.getQuotesListForCalculatingMA().add(tempQuote);
					LOG.debug("Adding... " + tempQuote + " to getQuotesListForCalculatingMA()");
				}

				if (fx.getQuotesListForCalculatingMA().size() >= SMA8)
				{
					double sma8 = MovingAverageCalculator.calculateMovingAverage(fx.getQuotesListForCalculatingMA(), SMA8, ApplicationConstants.SMA);
					double lwma5 = MovingAverageCalculator.calculateMovingAverage(fx.getQuotesListForCalculatingMA(), LWMA5, ApplicationConstants.LWMA);

					fx.getQuoteAnalysisMap().get(key).setLwma5(new Double(fx.getDecimalFormatMA().format(lwma5)));
					fx.getQuoteAnalysisMap().get(key).setSma8(new Double(fx.getDecimalFormatMA().format(sma8)));

					fx.getQuotesWithMovingAverage().add(fx.getQuoteAnalysisMap().get(key));

					if (fx.getQuotesWithMovingAverage().size() > 2)
					{
						LOG.debug("Determining trading signal with delta " + fx.getMaDelta());
						crossOverSignal = MovingAverageCalculator.determineMACrossOverSignalFromQuotes(fx.getQuotesWithMovingAverage(), fx.getMaDelta());
						boolean mailSentStatus = false;

						LOG.debug("Trading signal returned from calculating moving average : " + crossOverSignal);

						List<Quote> mailList = new ArrayList<Quote>();
						mailList.add(fx.getQuotesWithMovingAverage().get(fx.getQuotesWithMovingAverage().size() - 3));
						mailList.add(fx.getQuotesWithMovingAverage().get(fx.getQuotesWithMovingAverage().size() - 2));
						mailList.add(fx.getQuotesWithMovingAverage().get(fx.getQuotesWithMovingAverage().size() - 1));

						LOG.info("Trading signal is : " + crossOverSignal);

						if (!crossOverSignal.equals(ApplicationConstants.NO_SIGNAL))
						{
							if (props.isEmailNotify())
							{
								LOG.info("Sending mail for " + crossOverSignal + " signal... ");
								mailSentStatus = MAILER.send(mailList, crossOverSignal);
								LOG.info("Mail sent successfully!");
							}

						}

						if (mailSentStatus)
						{
							fx.getQuoteAnalysisMap().get(key).setMailSentFlag("Y");
						}

					}

				}

				fx.getQuoteAnalysisMap().get(key).setTradingSignal(crossOverSignal);

				try
				{
					LOG.info("Saving quote to DB... " + quote);
					dao.saveQuote(fx.getQuoteAnalysisMap().get(key));
					LOG.info("Quote successfully saved");
					dao = null;
					quote = null;
				}
				catch (TAException tae)
				{
					LOG.error("Failed to save quote... " + quote, tae);
				}

			}

		}

	}

}
