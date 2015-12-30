package com.rickyg.ta.dde;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.javaparts.dde.AsyncCompletedEvent;
import com.javaparts.dde.DDEEvent;
import com.javaparts.dde.DDEEventListener;
import com.javaparts.dde.ItemChangedEvent;

public class MyDDEEventListener implements DDEEventListener
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(MyDDEEventListener.class);
	
	public void onAsyncCompleted(AsyncCompletedEvent e)
	{
		
	}

	public void onDisconnect(DDEEvent e)
	{
		LOG.fatal("Server has closed connection");
	}

	public void onItemChanged(ItemChangedEvent e)
	{
		String quote = new String(e.getItemValue());
		LOG.info(e.getItemName() + " : " + e.getConversation().getTopic() + " : " + quote);

		new QuoteParsingControllerTask(e).run();
//		new QuoteParsingControllerTask_TEMP(e).run();
	}
}
