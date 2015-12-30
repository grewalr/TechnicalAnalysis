package com.rickyg.ta.fx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.rickyg.ta.domain.Quote;

public class AUDUSD implements FX
{
	private static List<Quote> closingQuotesAUDUSD = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesListForCalculatingAUDUSDMA = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesWithMovingAverage_AUDUSD = Collections.synchronizedList(new ArrayList<Quote>());
	private static SortedMap<String, Quote> AUDUSDQuoteAnalysisMap = Collections.synchronizedSortedMap(new TreeMap<String, Quote>());

	private final DecimalFormat DF4 = new DecimalFormat("#.####");
	private final DecimalFormat DF5 = new DecimalFormat("#.#####");
	
	private final double maDelta = 0.00005;
	private boolean emailFlag;
	private final String symbolIdentifier = "AUDUSD";

	public void initialise(Quote quote)
	{
		quote.setName("Australian Dollar vs US Dollar");

		double bid = new Double(DF4.format(new Double(quote.getBid())));
		double offer = new Double(DF4.format(new Double(quote.getOffer())));
		double open = new Double(DF5.format(new Double(quote.getOpen())));

		quote.setBid(bid);
		quote.setOffer(offer);
		quote.setOpen(open);

	}

	public String getSymbolIdentifier()
	{
		return symbolIdentifier;
	}

	public List<Quote> getClosingQuotesList()
	{
		return closingQuotesAUDUSD;
	}

	public List<Quote> getQuotesListForCalculatingMA()
	{
		return quotesListForCalculatingAUDUSDMA;
	}

	public List<Quote> getQuotesWithMovingAverage()
	{
		return quotesWithMovingAverage_AUDUSD;
	}

	public SortedMap<String, Quote> getQuoteAnalysisMap()
	{
		return AUDUSDQuoteAnalysisMap;
	}
	
	public double getMaDelta()
	{
		return maDelta;
	}

	public DecimalFormat getDecimalFormatMA()
	{
		return DF5;
	}
	
	public boolean isEmailNotify()
	{
		return emailFlag;
	}
}
