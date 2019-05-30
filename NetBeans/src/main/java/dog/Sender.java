
package dog;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


public class Sender {
    private String QUEUE_NAME = "DOGS";
    private String ID;
    private Channel channel;
    /*
    public void setID(String ID){
        this.ID = ID;
    }
*/
    
    public void setQueueName(String ID){
        this.QUEUE_NAME =ID;
    }
    
    
    public Sender(String dogID) throws Exception {
        this.ID=dogID;
        this.QUEUE_NAME=QUEUE_NAME + ID;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://kexupuab:7pMpe6fAQlKe4939Sy_ZTaFQeY4r8hMQ@elephant.rmq.cloudamqp.com/kexupuab");

        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        
        queue_declare();
    }
    
    public void queue_declare() throws Exception{
        boolean durable = false;    //durable - RabbitMQ will never lose the queue if a crash occurs
        boolean exclusive = false;  //exclusive - if queue only will be used by one connection
        boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes

        channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);
        //channel.queueDelete("hello3");
    }
   
    public void send() throws Exception{
        String exchangeName = "";
        String routingKey = QUEUE_NAME;
        String message = ID;
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'" + routingKey);
    }
    
    public void send(String messa) throws Exception{
        String message = messa;
        String exchangeName = "";
        String routingKey = QUEUE_NAME;
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'" );
        }
    
   

}