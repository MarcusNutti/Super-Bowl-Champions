// Version 1.0.0
// 8/14/2019
// By Marcus Nutti

/* This program search in a database
   all seasons where a specific NFL team won a SuperBowl
   or which team won the Super Bowl in a specific season. 
 */

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class SuperBowlChampions {
	public static void main(String args[]) {
		
		  int option;
		  String query;
		  String SELECT_QUERY = "x";
		
		  Scanner inputOption = new Scanner(System.in);
		  Scanner inputQuery = new Scanner(System.in);
		  
		  System.out.println("Would you like to search for a team or a season?\nType 1 for a team or type 2 for a season.");
		  do {
			  option = inputOption.nextInt();		  
		  
			  switch(option) {
		  		case 1:
		  			System.out.println("Type the team:");
		  			query = inputQuery.nextLine();
		  			SELECT_QUERY =
		  				"SELECT season FROM champions WHERE franchise = " + "'" + query + "'";
		  			break;
		  		case 2:
		  			System.out.println("Type the season:");
		  			query = inputQuery.nextLine();
		  			SELECT_QUERY =
		  				"SELECT franchise FROM champions WHERE season = " + query;
		  			break;
		  		default:		  			
		  			System.out.println("Wrong option. Please type 1 or 2.");
		  	
			  }
		  } while(SELECT_QUERY == "x");
		
	      final String DATABASE_URL = "jdbc:mysql://localhost/superbowl?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	      
	      try (  
	         Connection connection = DriverManager.getConnection(
	            DATABASE_URL, "***", "***"); 
	         Statement statement = connection.createStatement(); 
	         ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
	      {
	         ResultSetMetaData metaData = resultSet.getMetaData();
	         int numberOfColumns = metaData.getColumnCount();     
	         	         
	         while (resultSet.next()) 
	         {
	            for (int i = 1; i <= numberOfColumns; i++)
	               System.out.printf("%-8s\t", resultSet.getObject(i));
	            System.out.println();
	         } 
	      }
	      catch (SQLException sqlException)                                
	      {                                                                  
	         sqlException.printStackTrace();
	      }                                                   
	   } 

}
