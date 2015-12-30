package com.rickyg.ta.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TEST
{
	public static void main(String[] args) throws Exception
	{
		Connection conn = null;
		Statement stmt = null;
		try
		{
			File file = new File("D:/eclipse_workspace/Default/TechnicalAnalysis/log/ta.log.2009-06-17");
			FileInputStream in = new FileInputStream(file);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "ecoli0157");
			
			
			
				stmt = conn.createStatement();
//	           stmt.executeQuery("SET GLOBAL max_allowed_packet=209715200");  
				
				
				stmt.executeQuery("SET GLOBAL max_allowed_packet=209715200");
	           ResultSet rs = stmt.executeQuery("SHOW variables LIKE 'max_allowed_packet'");
	           rs.next();
	           System.out.println (rs.getString(1) + ":" + rs.getInt(2)); 
	           
	           
	           String query = "insert into blob_table values(?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setBinaryStream(1, (InputStream) in, (int) file.length());
				pstmt.execute();
				System.out.println("OK");
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			conn.close();
			stmt.close();
			
		}
	}
}
