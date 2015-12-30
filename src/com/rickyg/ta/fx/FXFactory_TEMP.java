package com.rickyg.ta.fx;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class FXFactory_TEMP
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(FXFactory_TEMP.class);

	public static synchronized FX_TEMP getFXInstance(String symbol)
	{
		FX_TEMP fxInstance = null;

		if(symbol.equals("EURUSD"))
		{
			fxInstance = new EURUSD_TEMP();
		}
		else if(symbol.equals("GBPUSD"))
		{
			fxInstance = new GBPUSD_TEMP();
		}	
		else
		{
			LOG.error("Implemention does not exist for " + symbol);
		}
		
		return fxInstance;
	}
	

}
