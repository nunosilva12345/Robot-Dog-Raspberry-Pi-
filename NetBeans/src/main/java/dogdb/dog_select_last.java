/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dogdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dog_select_last {
    
    private String resultTemp;
    private String resultProxim;

    public void select_last_temp_DB(String id) {
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

            String query = "select temperatura from sensor_temperatura where id= '" + id + "' order by dat desc limit 1";
            
            ps = c.prepareStatement(query);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                System.out.println("O valor é: " + result.getDouble(1));
                resultTemp = Double.toString(result.getDouble(1));
            }


            c.commit();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("deu erro");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public void select_last_proxi_DB(String id) {
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

            // String query =  "SELECT select_temperatura(TIMESTAMP ' " + dateFormat.format(date) + "' );";
            String query = "select proximidade from sensor_proximidade where id= '" + id + "'order by dat desc limit 1";
            ps = c.prepareStatement(query);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                System.out.println("O valor é: " + result.getDouble(1));
                resultProxim = Double.toString(result.getDouble(1));
            }
            
            c.commit();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("deu erro");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public String getResultTemp() {
        return resultTemp;
    }

    public String getResultProxim() {
        return resultProxim;
    }
    
    
}
