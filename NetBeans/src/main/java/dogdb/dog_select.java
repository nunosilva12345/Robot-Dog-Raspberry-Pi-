
package dogdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class dog_select {
    private static ArrayList<Double> lista = new ArrayList();
    
    public void select_temp_DB(){
        Connection c = null;
        PreparedStatement ps = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://localhost:5432/mydb",
               "postgres", "postgres");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            //System.out.println(dateFormat.format(date));

            String query =  "SELECT select_temperatura(TIMESTAMP ' " + dateFormat.format(date) + "' );";

            ps = c.prepareStatement(query);

            ResultSet result = ps.executeQuery();

            while(result.next()){
                System.out.println(result.getInt(1));
            }

            System.out.println(result);

            c.commit();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
           System.out.println("Erro na função select_temp_DB");
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Records created successfully");
     }
   
   
    public void select_temp_byID(String id){
        Connection c = null;
        PreparedStatement ps = null;
        lista.clear();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://localhost:5432/mydb",
               "postgres", "postgres");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            //System.out.println(dateFormat.format(date));

            String query ="SELECT temperatura FROM sensor_temperatura WHERE id= '" + id + "' ORDER BY dat DESC limit 10";

            ps = c.prepareStatement(query);

            ResultSet result = ps.executeQuery();
            
            while(result.next()){
                //System.out.println(result.getDouble(1));
                lista.add(result.getDouble(1));  
            }
            
            Collections.reverse(lista);
            
            System.out.println(result);

            c.commit();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro na função  select_temp_byID");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        
    }
    
    public ArrayList<Double> getLista() {
        return lista;
    }

}