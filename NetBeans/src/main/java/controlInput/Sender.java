/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlInput;

/**
 *
 * @author jarturcosta
 */

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


public class Sender {
    private String QUEUE_NAME = "dog_";
    private String ID;
    private Channel channel;
    
    public void setID(String ID){
        this.ID = ID;
    }

    
    public void setQueueName(String ID){
        this.QUEUE_NAME = "dog_" + ID;
    }
    
    
    public Sender() throws Exception {
        this.ID="Froo";
        this.QUEUE_NAME=QUEUE_NAME + ID;
        System.out.println(QUEUE_NAME);
        
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
    }

    
    public void send(String messa) throws Exception{
        String message = messa;
        String exchangeName = "";
        String routingKey = QUEUE_NAME;
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'" + QUEUE_NAME);
        }
}

   