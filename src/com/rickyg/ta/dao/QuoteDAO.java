package com.rickyg.ta.dao;

import java.util.List;

import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.exceptions.TAException;

public interface QuoteDAO
{
	public void save(List<Quote> quotes) throws TAException;
	
	public void saveQuote(Quote quote) throws TAException;
	
	public void updateQuote(Quote quote) throws TAException;
}
