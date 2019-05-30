
package dog;
import com.rabbitmq.client.*;
import java.io.IOException;

public class Recv {
  
    private final static String QUEUE_NAME = "dog_DV1";

    //public Recv() throws Exception {}
    
    public static void main() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://kexupuab:7pMpe6fAQlKe4939Sy_ZTaFQeY4r8hMQ@elephant.rmq.cloudamqp.com/kexupuab");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");



        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                if ("FORWARD".equals(message) || "RIGHT".equals(message) || "LEFT".equals(message) || "BACKWARD".equals(message) || "STOP".equals(message)){
                    handleMoving(message);
                }
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
    
    public static void handleMoving(String command){
         String toprint = "";      
        switch (command){
            case "FORWARD": toprint = "moving forward";
            break;
             
            case "BACKWARD": toprint = "moving backward";
            break;
            
            case "FORWARD RIGHT": toprint = "turning his wheels right";
            break;
            
            case "FORWARD LEFT": toprint = "turning his wheels left";
            break;
           
            case "BACKWARD RIGHT": toprint = "turning right backward";
            break;
            
            case "BACKWARD LEFT": toprint = "turning left backward";
            break;
            
            case "STOP": toprint= "stopping";
            break;
                      
        }
        System.out.println(" [x] Received '" + toprint + "'");
    }
    

 
}