package com.rickyg.ta.fx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.rickyg.ta.domain.Quote;

public class GBPUSD_TEMP implements FX_TEMP
{
	private static List<Quote> closingQuotesGBPUSD = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesListForCalculatingGBPUSDMA = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesWithMovingAverage_GBPUSD = Collections.synchronizedList(new ArrayList<Quote>());
	private static SortedMap<String, Quote> gbpusdQuoteAnalysisMap = Collections.synchronizedSortedMap(new TreeMap<String, Quote>());
	private static SortedMap<String, Quote> GBPUSDquotesMap = Collections.synchronizedSortedMap(new TreeMap<String, Quote>());

	private final double maDelta = 0.00005;
	private boolean emailNotify;
	private final String symbolIdentifier = "GBPUSD";

	private final DecimalFormat DF4 = new DecimalFormat("#.####");
	private final DecimalFormat DF5 = new DecimalFormat("#.#####");

	public void initialise(Quote quote)
	{
		quote.setName("British Pound vs US Dollar");

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
		return closingQuotesGBPUSD;
	}
	
	@Override
	public SortedMap<String, Quote> getQuotesMap()
	{
		return GBPUSDquotesMap;
	}
	

	public List<Quote> getQuotesListForCalculatingMA()
	{
		return quotesListForCalculatingGBPUSDMA;
	}

	public List<Quote> getQuotesWithMovingAverage()
	{
		return quotesWithMovingAverage_GBPUSD;
	}

	public SortedMap<String, Quote> getQuoteAnalysisMap()
	{
		return gbpusdQuoteAnalysisMap;
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
