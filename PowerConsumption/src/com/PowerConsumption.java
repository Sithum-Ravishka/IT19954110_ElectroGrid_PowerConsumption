package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class PowerConsumption 
{ //A common method to connect to the DB
	private Connection connect()
	{
		//Create database connection
		//Provide the correct details: dbURL, dbUser, dbPass
		 String dbURL = "jdbc:mysql://localhost:3306/power_consumption";
	     String dbUser = "root";
	     String dbPass = "199808";
	     
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbUser, dbPass);
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
		
		public String insertPCData( String c_ID, String c_commercial, String c_agriculture, String c_residential, String c_date ){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						String query = " insert into powerconsumption (`pc_ID`,`c_ID`, `c_commercial`,`c_agriculture`,`c_residential`, `c_date`)" + " values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, c_ID); 
						preparedStmt.setString(3, c_commercial); 
						preparedStmt.setString(4, c_agriculture); 
						preparedStmt.setString(5, c_residential); 
						preparedStmt.setString(6, c_date);
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newPCData = readPCData(); 
						output = "{\"status\":\"success\",\"data\":\""+newPCData+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the Power Consumption Data.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readPCData() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>PConsumption ID</th>"
		 		+ "<th>Commercial Unit</th>"
		 		+ "<th>Agriculture Unit</th>"
		 		+ "<th>Residential Unit</th>"
		 		+ "<th>PConsumption Date</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from powerconsumption"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String pc_ID = Integer.toString(rs.getInt("pc_ID")); 
		 String c_ID = rs.getString("c_ID"); 
		 String c_commercial = rs.getString("c_commercial"); 
		 String c_agriculture = rs.getString("c_agriculture"); 
		 String c_residential = rs.getString("c_residential"); 
		 String c_date = rs.getString("c_date"); 
		 // Add into the html table
		 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+pc_ID+"'>"+c_ID+"</td>"; 
		 output += "<td>" + c_commercial + "</td>"; 
		 output += "<td>" + c_agriculture + "</td>"; 
		 output += "<td>" + c_residential + "</td>"; 
		 output += "<td>" + c_date + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-pcid='" + pc_ID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-pcid='" + pc_ID + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the Power Consumption Data."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

		
		public String deletePCData(String pc_ID){ 
			
			String output = ""; 
			
			try{ 
				Connection con = connect(); 
				
				if (con == null){
					return "Error while connecting to the database for deleting."; 
					} 
				// create a prepared statement
				String query = "delete from powerconsumption where pc_ID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(pc_ID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newPCData = readPCData(); 
				 output = "{\"status\":\"success\",\"data\":\""+newPCData+"\"}"; 

			} 
			
			catch (Exception e){ 
				output = "{\"status\":\"error\",\"data\":\"Error while deleting the Power Consumption Data.\"}";
				System.err.println(e.getMessage()); 
			} 
			return output; 
	} 
		
		public String updatePCData(String pc_ID, String c_ID, String c_commercial, String c_agriculture, String c_residential, String c_date){ 
			
			String output = ""; 
			
			try{ 
					Connection con = connect(); 
					if (con == null){
						return "Error while connecting to the database for updating.";
						} 
					// create a prepared statement
					String query = "UPDATE powerconsumption SET c_ID=?,c_commercial=?,c_agriculture=?,c_residential=?,c_date=? WHERE pc_ID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setString(1, c_ID); 
					preparedStmt.setString(2, c_commercial); 
					preparedStmt.setString(3, c_agriculture); 
					preparedStmt.setString(4, c_residential); 
					preparedStmt.setString(5, c_date); 
					preparedStmt.setInt(6, Integer.parseInt(pc_ID)); 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					String newPCData = readPCData(); 
					output = "{\"status\":\"success\",\"data\":\""+newPCData+"\"}"; 

			} 
			
			catch (Exception e){ 
				
				output = "{\"status\":\"error\",\"data\":\"Error while updating the Power Consumption Data.\"}"; 

				System.err.println(e.getMessage()); 
				
			} 
			
			return output; 
	} 
}