package com.rickyg.ta.fx;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class FXFactory
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(FXFactory.class);

	public static synchronized FX getFXInstance(String symbol)
	{
		FX fxInstance = null;

		if(symbol.equals("AUDUSD"))
		{
			fxInstance = new AUDUSD();
		}
		else if(symbol.equals("EURGBP"))
		{
			fxInstance = new EURGBP();
		}		
		else if(symbol.equals("EURJPY"))
		{
			fxInstance = new EURJPY();
		}
		else if(symbol.equals("EURUSD"))
		{
			fxInstance = new EURUSD();
		}
		else if(symbol.equals("GBPUSD"))
		{
			fxInstance = new GBPUSD();
		}	
		else if(symbol.equals("USDCAD"))
		{
			fxInstance = new USDCAD();
		}
		else if(symbol.equals("USDCHF"))
		{
			fxInstance = new USDCHF();
		}		
		else if(symbol.equals("USDJPY"))
		{
			fxInstance = new USDJPY();
		}
		else
		{
			LOG.error("Implemention does not exist for " + symbol);
		}
		
		return fxInstance;
	}
	

}
