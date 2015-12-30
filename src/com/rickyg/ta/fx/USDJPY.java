package com.rickyg.ta.fx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.rickyg.ta.domain.Quote;

public class USDJPY implements FX
{
	private static List<Quote> closingQuotesUSDJPY = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesListForCalculatingUSDJPYMA = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesWithMovingAverage_USDJPY = Collections.synchronizedList(new ArrayList<Quote>());
	private static SortedMap<String, Quote> USDJPYQuoteAnalysisMap = Collections.synchronizedSortedMap(new TreeMap<String, Quote>());
	
	private final double maDelta = 0.005;
	private boolean emailNotify;
	private final String symbolIdentifier = "USDJPY";
	
	private final DecimalFormat DF2 = new DecimalFormat("#.##");
	private final DecimalFormat DF3 = new DecimalFormat("#.###");
		
	public void initialise(Quote quote)
	{
		quote.setName("US Dollar vs Japanese Yen");
		
		double bid = new Double(DF2.format(new Double(quote.getBid())));
		double offer = new Double(DF2.format(new Double(quote.getOffer())));
		double open = new Double(DF3.format(new Double(quote.getOpen())));
			
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
		return closingQuotesUSDJPY;
	}
	
	public List<Quote> getQuotesListForCalculatingMA()
	{
		return quotesListForCalculatingUSDJPYMA;
	}

	public List<Quote> getQuotesWithMovingAverage()
	{
		return quotesWithMovingAverage_USDJPY;
	}

	public SortedMap<String, Quote> getQuoteAnalysisMap()
	{
		return USDJPYQuoteAnalysisMap;
	}
	
	public double getMaDelta()
	{
		return maDelta;
	}
	
	public DecimalFormat getDecimalFormatMA()
	{
		return DF3;
	}

	public boolean isEmailNotify()
	{
		return emailNotify;
	}



}
