package com.rickyg.ta.fx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.rickyg.ta.domain.Quote;

public class USDCAD implements FX
{
	private static List<Quote> closingQuotesUSDCAD = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesListForCalculatingUSDCADMA = Collections.synchronizedList(new ArrayList<Quote>());
	private static List<Quote> quotesWithMovingAverage_USDCAD = Collections.synchronizedList(new ArrayList<Quote>());
	private static SortedMap<String, Quote> USDCADQuoteAnalysisMap = Collections.synchronizedSortedMap(new TreeMap<String, Quote>());

	private final DecimalFormat DF4 = new DecimalFormat("#.####");
	private final DecimalFormat DF5 = new DecimalFormat("#.#####");
	
	private final double maDelta = 0.00005;
	private boolean emailNotify;
	private final String symbolIdentifier = "USDCAD";

	public void initialise(Quote quote)
	{
		quote.setName("US Dollar vs Canadian Dollar");

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
		return closingQuotesUSDCAD;
	}

	public List<Quote> getQuotesListForCalculatingMA()
	{
		return quotesListForCalculatingUSDCADMA;
	}

	public List<Quote> getQuotesWithMovingAverage()
	{
		return quotesWithMovingAverage_USDCAD;
	}

	public SortedMap<String, Quote> getQuoteAnalysisMap()
	{
		return USDCADQuoteAnalysisMap;
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
