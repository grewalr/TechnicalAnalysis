package com.rickyg.ta.exceptions;

import org.apache.commons.lang.exception.NestableException;

public class TAException extends NestableException
{
	private static final long serialVersionUID = -7388920733420956193L;

	public TAException()
	{
		super();
	}

	public TAException(String msg)
	{
		super(msg);
	}

	public TAException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public TAException(Throwable cause)
	{
		super(cause);
	}
}
