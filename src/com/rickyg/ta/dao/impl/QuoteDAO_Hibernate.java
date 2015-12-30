package com.rickyg.ta.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.rickyg.ta.dao.QuoteDAO;
import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.exceptions.TAException;
import com.rickyg.ta.util.HibernateUtil;

public class QuoteDAO_Hibernate implements QuoteDAO
{
	private Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	public void save(List<Quote> quotes) throws TAException
	{
		// TODO Auto-generated method stub
		
	}
	
	public void saveQuote(Quote quote) throws TAException
	{
		session.beginTransaction();
		session.save(quote);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public void updateQuote(Quote quote) throws TAException
	{
		// TODO Auto-generated method stub
		
	}
	
	


}
