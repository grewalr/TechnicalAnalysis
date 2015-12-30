package com.rickyg.ta.mail;

import java.util.List;

import com.rickyg.ta.domain.Quote;

public interface MessageSender
{
	public boolean send(List<Quote> list);
	
	public boolean send(List<Quote> list, String subject);
}
