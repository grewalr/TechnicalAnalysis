package com.rickyg.ta.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rickyg.ta.dao.QuoteDAO;
import com.rickyg.ta.domain.Quote;
import com.rickyg.ta.exceptions.TAException;
import com.rickyg.ta.util.ApplicationProperties;

public class QuoteDAO_JDBC implements QuoteDAO
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(QuoteDAO_JDBC.class);
	private static ApplicationProperties appProps;
	private static Connection conn;
	
	static
	{
		appProps = ApplicationProperties.getInstance();
		
		String url = appProps.getDBUrl();
		String db = appProps.getDBName();
		String driver = appProps.getDBDriver();
		String user = appProps.getDBUser();
		String password = appProps.getDBPassword();
		
		Properties dbprops = new Properties(); 
		dbprops.setProperty("max_allowed_packet", "209715200");		
		try
		{
			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager.getConnection(url+db+"?user="+user+"&password="+password, dbprops);
		}
		catch (InstantiationException e)
		{
			LOG.error(e);
		}
		catch (IllegalAccessException e)
		{
			LOG.error(e);
		}
		catch (ClassNotFoundException e)
		{
			LOG.error(e);
		}
		catch (SQLException e)
		{
			LOG.error(e);
		} 
		
	}
	
	
	
	public void save(List<Quote> quotes) throws TAException
	{
		

	}

	public void updateQuote(Quote quote) throws TAException
	{
		PreparedStatement pst = null;
		
		try
		{
			String updateStatement = "UPDATE QUOTES SET CLOSE = ? WHERE SYMBOL = ? AND DATE_TIME = ?";
			
			pst = (PreparedStatement) conn.prepareStatement(updateStatement);
			pst.setDouble(1, quote.getClose());
            pst.setString(2, quote.getSymbol());
            pst.setTimestamp(3, new java.sql.Timestamp(quote.getDateTime().getTime()));
            
			LOG.info("Using JDBC statement : " + updateStatement);
			LOG.info("with values : "+quote.getOpen()+ " , "+quote.getSymbol()+ " , " + new java.sql.Timestamp(quote.getDateTime().getTime()));
			pst.executeUpdate();
			LOG.info("JDBC statement successfuly executed!");
		}
		catch (SQLException sqle)
		{
			LOG.error("SQL statement is not executed!", sqle);
		}
		finally
		{

			try
			{
				pst.close();
				pst = null;
			}
			catch (SQLException sqle)
			{
				LOG.error("Could not close statement/connection ", sqle);
			}
		}
	
	}

	/**
	 * Method saveQuote
	 */
	public void saveQuote(Quote quote) throws TAException
	{
		Statement st = null;
		
		try
		{
			st = (Statement) conn.createStatement();
			
			String insertStatement = "INSERT INTO QUOTES(SYMBOL, NAME, DATE_TIME, BID, OFFER, OPEN, LAST, QUOTES.CHANGE, HIGH, LOW, CLOSE, PRICE, LWMA, SMA, MAIL_SENT, TRADING_SIGNAL) "
					+ "VALUES ( '"
					+ quote.getSymbol()
					+ "','"
					+ quote.getName()
					+ "','"
					+ new java.sql.Timestamp(quote.getDateTime().getTime())
					+ "',"
					+ quote.getBid()
					+ ","
					+ quote.getOffer()
					+ ","
					+ quote.getOpen()
					+ ","
					+ quote.getLast()
					+ ","
					+ quote.getChange()
					+ ","
					+ quote.getHigh()
					+ ","
					+ quote.getLow()
					+ ","
					+ quote.getClose()
					+ ","
					+ quote.getPrice()
					+ ","
					+ quote.getLwma5() 
					+ "," 
					+ quote.getSma8() 
					+ ",'"
					+ quote.getMailSentFlag()
					+ "','"
					+ quote.getTradingSignal()
					+ "')";

			LOG.info("Using JDBC statement : " + insertStatement);
			st.executeUpdate(insertStatement);
			LOG.info("JDBC statement successfuly executed!");
		}
		catch (SQLException sqle)
		{
			LOG.error("SQL statement is not executed!", sqle);
		}
		finally
		{

			try
			{
				st.close();
				st = null;
			}
			catch (SQLException sqle)
			{
				LOG.error("Could not close statement/connection ", sqle);
			}
		}

	}
}
