package com.rickyg.ta.fx;

import java.text.DecimalFormat;
import java.util.List;
import java.util.SortedMap;

import com.rickyg.ta.domain.Quote;

public interface FX
{
	public void initialise(Quote quote);
	
	public String getSymbolIdentifier();
	
	public List<Quote> getClosingQuotesList();
	
	public List<Quote> getQuotesListForCalculatingMA();
	
	public List<Quote> getQuotesWithMovingAverage();
	
	public SortedMap<String, Quote> getQuoteAnalysisMap();
	
	public double getMaDelta();
	
	public DecimalFormat getDecimalFormatMA();
	
	public boolean isEmailNotify();
	
}
