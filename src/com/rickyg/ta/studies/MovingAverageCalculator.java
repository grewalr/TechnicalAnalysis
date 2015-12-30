package com.rickyg.ta.studies;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import webcab.lib.finance.trading.indicators.MovingAverage;

import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.util.ApplicationConstants;

public final class MovingAverageCalculator
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(MovingAverageCalculator.class);

	private MovingAverageCalculator()
	{
	}

	public static double calculateMovingAverage(List<Quote> quotesListForCalculatingMA, int period,
			String movingAverageType)
	{
		double movingAverage = 0.0;
		double quotes[] = new double[period];
		int j = period - 1;

		quotes[j] = quotesListForCalculatingMA.get(quotesListForCalculatingMA.size() - 1).getOpen();
		j--;

		for (int i = quotesListForCalculatingMA.size() - 2; i >= 0; i--)
		{
			quotes[j] = quotesListForCalculatingMA.get(i).getClose();

			j--;

			if (j < 0)
			{
				break;
			}

		}

		for (int i = 0; i < quotes.length; i++)
		{
			LOG.debug("calculate" + movingAverageType + " -- quotes[" + i + "] : " + quotes[i]);
		}

		MovingAverage ma = new MovingAverage();

		double movingAverageArray[] = null;

		if (movingAverageType.equals(ApplicationConstants.SMA))
		{
			movingAverageArray = ma.simpleMovingAverage(quotes, period);
		}
		else if (movingAverageType.equals(ApplicationConstants.LWMA))
		{
			movingAverageArray = ma.linearlyWeightedMovingAverage(quotes, period);
		}

		movingAverage = movingAverageArray[0];
		
		LOG.debug("Moving Average for "+movingAverageType+" is : "+movingAverage);

		return movingAverage;

	}

	public static String determineMACrossOverSignalFromQuotes(List<Quote> quotesWithMovingAverage, double maDelta)
	{
		String crossOverSignal = ApplicationConstants.NO_SIGNAL;
		int present = quotesWithMovingAverage.size() - 1;
		int mid = present - 1;
		int previous = present - 2;

		Quote presentQuote = quotesWithMovingAverage.get(present);
		Quote midQuote = quotesWithMovingAverage.get(mid);
		Quote previousQuote = quotesWithMovingAverage.get(previous);

		double presentSMA8 = presentQuote.getSma8();
		double presentLWMA5 = presentQuote.getLwma5();
		double midSMA8 = midQuote.getSma8();
		double midLWMA5 = midQuote.getLwma5();
		double previousSMA8 = previousQuote.getSma8();
		double previousLWMA5 = previousQuote.getLwma5();

		BigDecimal bigPresentLWMA5 = new BigDecimal(presentLWMA5);
		BigDecimal bigPresentSMA8 = new BigDecimal(presentSMA8);

		BigDecimal bigResultLWMA5_SMA8 = bigPresentLWMA5.subtract(bigPresentSMA8);
		BigDecimal bigResultSMA8_LWMA5 = bigPresentSMA8.subtract(bigPresentLWMA5);

		double resultDblLWMA5_SMA8 = new Double(bigResultLWMA5_SMA8.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());
		double resultDblSMA8_LWMA5 = new Double(bigResultSMA8_LWMA5.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());

		if (LOG.isDebugEnabled())
		{
			LOG.debug("previousSMA8 : " + previousSMA8);
			LOG.debug("previousLWMA5 : " + previousLWMA5);
			LOG.debug("midSMA8 : " + midSMA8);
			LOG.debug("midLWMA5 : " + midLWMA5);
			LOG.debug("presentSMA8 : " + presentSMA8);
			LOG.debug("presentLWMA5 : " + presentLWMA5);
			

			LOG.debug("resultDblLWMA5_SMA8 : " + resultDblLWMA5_SMA8);
			LOG.debug("resultDblSMA8_LWMA5 : " + resultDblSMA8_LWMA5);
		}

		if (previousSMA8 > previousLWMA5)
		{
			if (midSMA8 >= midLWMA5 || midLWMA5 >= midSMA8)
			{
				if ((presentLWMA5 > presentSMA8) && (resultDblLWMA5_SMA8 >= maDelta))
				{
					crossOverSignal = "BUY : " + presentQuote.getSymbol();
				}
			}
		}

		if (previousLWMA5 > previousSMA8)
		{
			if (midLWMA5 >= midSMA8 || midSMA8 >= midLWMA5)
			{
				if ((presentSMA8 > presentLWMA5) && (resultDblSMA8_LWMA5 >= maDelta))
				{
					crossOverSignal = "SELL : " + presentQuote.getSymbol();
				}
			}
		}

		return crossOverSignal;

	}

}
