/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlInput;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dogdb.*;
import java.util.HashMap;
import jdk.nashorn.internal.runtime.Context;

/**
 *
 * @author andreia
 */



public class Receiver {
  
    private static String QUEUE_NAME = "SENSORES";
    private ArrayList<String> arrayDogs = new ArrayList<>();
    private Channel channel;
    private String currentDog  = "Froo";
    private static HashMap<String, Double> hmapTemp = new HashMap<String, Double>();
    
     public void setQueueName(String name){
        this.QUEUE_NAME = name;
    }
    
    
    public String getCurrentDog() {
        return currentDog;
    }

    public void setCurrentDog(String currentDog) {
        this.currentDog = currentDog;
    }

    
    public void recieve() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://kexupuab:7pMpe6fAQlKe4939Sy_ZTaFQeY4r8hMQ@elephant.rmq.cloudamqp.com/kexupuab");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        

        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String message = new String(body, "UTF-8");
                     //DESCOMENTAR PARA INSERIR NA BD

                     //FAZER SLEEP E inserir na BD so de 5 em 5 min
                    String [] partes = message.split("/");
                    String tempID = partes[0];

                    String [] partesSensorT = partes[1].split(",");
                    String [] partesSensorP = partes[2].split(",");
                    dog_insert dog = new dog_insert();

                    handleDogs(tempID);
                    
                    if ("T".equals(partesSensorT[0])){
                        dog.insert_temp_DB(tempID, Double.parseDouble(partesSensorT[1]), partesSensorT[2]);
                    }
                    if ("P".equals(partesSensorP[0])){
                        dog.insert_proxi_DB(tempID, Double.parseDouble(partesSensorP[1]), partesSensorP[2]);
                    }
                    
                    hmapTemp.put(tempID, Double.parseDouble(partesSensorT[1]));
                    
                    

                    System.out.println(" [x] Received '" + message + "'");

                }
            });
    }
    
    

    
    private void handleDogs(String dog_ID){
        if (arrayDogs.contains(dog_ID)){}
        else{
            arrayDogs.add(dog_ID);
        }
    }
    
    public ArrayList<String> getArrayDogs() {
        return arrayDogs;
    }
    
    
    public String getHashValue(String DogID){
        String todo = "Sem valores";
        try{
            todo = hmapTemp.get(DogID).toString();
        } catch (Exception e ){
            todo = "Sem valores";
        }
        
        return todo;
    }


}