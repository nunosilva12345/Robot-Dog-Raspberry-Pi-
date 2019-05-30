
package dog;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class Dog {
    private final static String dogID = "DV1";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
    public static void main(String[] argv) throws Exception{ 
        Recv recebedor = new Recv();
        recebedor.main();   
        List<String> array = new ArrayList<>();
        array.add("Temperatura");
        array.add("Proximidade");
        Sender sed = new Sender (dogID);
        sed.setQueueName("SENSORES");
        while (0==0){
           // sed.setQueueName("DOGS");
           //S sed.send();
            
            sed.send(dogID + "/" + getSensorValues(array));
            Thread.sleep(10000);
        }
    }
    
    public static String getSensorValues(List<String> array){
        String result="";
        for (String sensor : array) {
            if ("Temperatura".equals(sensor)){
                String S = "T";
                String valor = String.valueOf(Math.round((Math.random() * 10 +15) * 100.0) / 100.0);
                 
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String data = (sdf.format(timestamp));
                
                result = result + S + "," + valor + "," + data + "/";             
            }
            else if("Proximidade".equals(sensor)){
                String S = "P";
                String valor = String.valueOf(Math.round((Math.random() * 5 +20) * 10.0) / 10.0);
           
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String data = (sdf.format(timestamp));
                
                result = result + S + "," + valor + "," + data + "/"; 
            }
        }
        return result;
    }
}