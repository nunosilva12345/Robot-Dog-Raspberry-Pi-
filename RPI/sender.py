#!/usr/bin/env python
import pika, os
from datetime import datetime

class Sender:
  def __init__(self):
    self.queue_name= "SENSORES"
    self.ID = "Froo"
    params=pika.URLParameters('amqp://kexupuab:7pMpe6fAQlKe4939Sy_ZTaFQeY4r8hMQ@elephant.rmq.cloudamqp.com/kexupuab')
    connection=pika.BlockingConnection(params)
    self.channel = connection.channel()
    self.channel.queue_declare(queue=self.queue_name)

  

  def send(self,values):
    val = self.ID + "/"
    for tup in values:
      data_hora = datetime.now()
      val+= str(tup[0]) + "," + "%.2f" % tup[1] + "," + data_hora.strftime('%Y-%m-%d %H:%M:%S') + "/"
    self.channel.basic_publish(exchange='',routing_key=self.queue_name, body=val)
    print("Sent: " +  val +  " to queue: " +  self.queue_name)

