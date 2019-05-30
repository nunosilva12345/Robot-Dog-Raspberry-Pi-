package dogdb;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class dogBD {
   public static void main(String args[]) {
      Connection c = null;
       ArrayList<Double> lista = new ArrayList();
       ArrayList<Double> lista1 = new ArrayList();
        
       //dog_insert dog = new dog_insert();
       //dog.insert_DB("DV1", 29.5, "2017-11-23 17:00:09");
                dog_select dog = new dog_select();
                dog_select dog1 = new dog_select();
                dog.select_temp_byID("Froo");
                lista = dog.getLista();
                System.out.println(lista);

                
                
                
                System.out.println(lista);
                
                for (Double elem:lista){
                 //   System.out.print( "el "+ elem);
                }
                
                
                
                for (Double elem1:lista1){
                   // System.out.print("el1 "+elem1);
                }
                
                
       //dog_select_last ultimo= new dog_select_last();
       //ultimo.select_last_temp_DB("DV1");
       

        
}
}