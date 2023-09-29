package other.poc;

import java.sql.*;

public class SQLConnectionTest {
  public static void main( String args[] ) {
      // Test connection
      
      /*Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:filetagging.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");*/
      
      //create table
      
      /*Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:filetagging.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE fileinfo " +
                        "(file_name      TEXT," +
                        " file_path      TEXT, " + 
                        " type           TEXT, " + 
                        " created_date   TEXT, " + 
                        " tag            TEXT)"; 
             
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
         
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");*/
        
      Connection connection = null;
        try {
            // create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:filetag.db");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM filetags");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("file_name") + "\t" + resultSet.getString("file_path"));
                System.out.println(resultSet.getString("file_path"));
                //System.out.println(resultSet.getString("file_name\tfile_path\ttype\tcreated_date\tfile_tag"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    
    // end of void main
    }
}


