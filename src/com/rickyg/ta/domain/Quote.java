package com.rickyg.ta.domain;

import java.lang.reflect.Field;
import java.util.Date;

public class Quote
{
	private long id;
	private String symbol;
	private String name;
	private double open, last, change, high, low, close, price, bid, offer; 
	private Date dateTime;
	private double sma8, lwma5;
	private String mailSentFlag, tradingSignal;
	
	public Quote() {}
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * @return the symbol
	 */
	public String getSymbol()
	{
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the open
	 */
	public double getOpen()
	{
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(double open)
	{
		this.open = open;
	}

	/**
	 * @return the last
	 */
	public double getLast()
	{
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(double last)
	{
		this.last = last;
	}

	/**
	 * @return the change
	 */
	public double getChange()
	{
		return change;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(double change)
	{
		this.change = change;
	}

	/**
	 * @return the high
	 */
	public double getHigh()
	{
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(double high)
	{
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public double getLow()
	{
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(double low)
	{
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public double getClose()
	{
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(double close)
	{
		this.close = close;
	}

	/**
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * @return the bid
	 */
	public double getBid()
	{
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(double bid)
	{
		this.bid = bid;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime()
	{
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime)
	{
		this.dateTime = dateTime;
	}

	public void setOffer(double offer)
	{
		this.offer = offer;
	}

	public double getOffer()
	{
		return offer;
	}

	/**
	 * @return the sma8
	 */
	public double getSma8()
	{
		return sma8;
	}

	/**
	 * @param sma8 the sma8 to set
	 */
	public void setSma8(double sma8)
	{
		this.sma8 = sma8;
	}

	/**
	 * @return the lwma5
	 */
	public double getLwma5()
	{
		return lwma5;
	}

	/**
	 * @param lwma5 the lwma5 to set
	 */
	public void setLwma5(double lwma5)
	{
		this.lwma5 = lwma5;
	}

	/**
	 * @return the mailSentFlag
	 */
	public String getMailSentFlag()
	{
		return mailSentFlag;
	}

	/**
	 * @param mailSentFlag the mailSentFlag to set
	 */
	public void setMailSentFlag(String mailSentFlag)
	{
		this.mailSentFlag = mailSentFlag;
	}

	/**
	 * @return the crossOverSignal
	 */
	public String getTradingSignal()
	{
		return tradingSignal;
	}

	/**
	 * @param crossOverSignal the crossOverSignal to set
	 */
	public void setTradingSignal(String crossOverSignal)
	{
		this.tradingSignal = crossOverSignal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(change);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(close);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tradingSignal == null) ? 0 : tradingSignal.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		temp = Double.doubleToLongBits(high);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(last);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(low);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lwma5);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mailSentFlag == null) ? 0 : mailSentFlag.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(offer);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(open);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sma8);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Quote other = (Quote) obj;
		if (Double.doubleToLongBits(bid) != Double.doubleToLongBits(other.bid))
		{
			return false;
		}
		if (Double.doubleToLongBits(change) != Double.doubleToLongBits(other.change))
		{
			return false;
		}
		if (Double.doubleToLongBits(close) != Double.doubleToLongBits(other.close))
		{
			return false;
		}
		if (tradingSignal == null)
		{
			if (other.tradingSignal != null)
			{
				return false;
			}
		}
		else if (!tradingSignal.equals(other.tradingSignal))
		{
			return false;
		}
		if (dateTime == null)
		{
			if (other.dateTime != null)
			{
				return false;
			}
		}
		else if (!dateTime.equals(other.dateTime))
		{
			return false;
		}
		if (Double.doubleToLongBits(high) != Double.doubleToLongBits(other.high))
		{
			return false;
		}
		if (Double.doubleToLongBits(last) != Double.doubleToLongBits(other.last))
		{
			return false;
		}
		if (Double.doubleToLongBits(low) != Double.doubleToLongBits(other.low))
		{
			return false;
		}
		if (Double.doubleToLongBits(lwma5) != Double.doubleToLongBits(other.lwma5))
		{
			return false;
		}
		if (mailSentFlag == null)
		{
			if (other.mailSentFlag != null)
			{
				return false;
			}
		}
		else if (!mailSentFlag.equals(other.mailSentFlag))
		{
			return false;
		}
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(other.name))
		{
			return false;
		}
		if (Double.doubleToLongBits(offer) != Double.doubleToLongBits(other.offer))
		{
			return false;
		}
		if (Double.doubleToLongBits(open) != Double.doubleToLongBits(other.open))
		{
			return false;
		}
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
		{
			return false;
		}
		if (Double.doubleToLongBits(sma8) != Double.doubleToLongBits(other.sma8))
		{
			return false;
		}
		if (symbol == null)
		{
			if (other.symbol != null)
			{
				return false;
			}
		}
		else if (!symbol.equals(other.symbol))
		{
			return false;
		}

		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("\n" + this.getClass().getName());
		sb.append("\nObject \n(\n");

		// Get the name of the fields denoted in this class
		Field[] fields = this.getClass().getDeclaredFields();

		// Print field names paired with their values
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("  ");
			
			try
			{
				sb.append(fields[i].getName());
				sb.append(": ");
				sb.append(fields[i].get(this));
			}
			catch (IllegalAccessException ex)
			{
				System.out.println(ex);
			}
			sb.append("\n");
		}
		sb.append("}");

		return sb.toString();
	}
	
	

}
