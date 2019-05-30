
package dogdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dog_insert {
    
   public void insert_temp_DB(String id, double temp, String data){
      Connection c = null;
      PreparedStatement ps = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/mydb",
            "postgres", "postgres");
         c.setAutoCommit(false);
         
         System.out.println("Opened database successfully");

         String query =  "SELECT insert_temperatura( '" + id + "' ," + temp +", TIMESTAMP '" + data + "');";
         ps = c.prepareStatement(query);

         
         ps.execute();
         ResultSet last_updated_person = ps.getResultSet();
       
         c.commit();
         ps.close();
         c.close();
      } catch (ClassNotFoundException | SQLException e) {
         System.out.println("deu erro");
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
   
   public void insert_proxi_DB(String id, double proxi, String data){
     Connection c = null;
      PreparedStatement ps = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/mydb",
            "postgres", "postgres");
         c.setAutoCommit(false);
         
         System.out.println("Opened database successfully");

         String query =  "SELECT insert_proximidade( '" + id + "' ," + proxi +", TIMESTAMP '" + data + "');";
         ps = c.prepareStatement(query);

         
         ps.execute();
         ResultSet last_updated_person = ps.getResultSet();
       
         c.commit();
         ps.close();
         c.close();
      } catch (ClassNotFoundException | SQLException e) {
         System.out.println("deu erro");
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
}