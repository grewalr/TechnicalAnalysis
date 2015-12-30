package com.rickyg.ta.fx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.rickyg.ta.domain.Quote;

public class EURGBP implements FX
{
	private static List<Quote> closingQuotesEURGBP = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesListForCalculatingEURGBPMA = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesWithMovingAverage_EURGBP = Collections.synchronizedList(new ArrayList<Quote>());
	private static SortedMap<String, Quote> EURGBPQuoteAnalysisMap = Collections.synchronizedSortedMap(new TreeMap<String, Quote>());

	private final DecimalFormat DF4 = new DecimalFormat("#.####");
	private final DecimalFormat DF5 = new DecimalFormat("#.#####");
	
	private final double maDelta = 0.00005;
	private boolean emailNotify;
	private final String symbolIdentifier = "EURGBP";

	public void initialise(Quote quote)
	{
		quote.setName("Euro vs British Pound");

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
		return closingQuotesEURGBP;
	}

	public List<Quote> getQuotesListForCalculatingMA()
	{
		return quotesListForCalculatingEURGBPMA;
	}

	public List<Quote> getQuotesWithMovingAverage()
	{
		return quotesWithMovingAverage_EURGBP;
	}

	public SortedMap<String, Quote> getQuoteAnalysisMap()
	{
		return EURGBPQuoteAnalysisMap;
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
		return emailNotify;
	}
}
