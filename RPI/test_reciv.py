#!/usr/bin/env python
import pika, os
import move as dog
from temp import get_temperature
from ufront import fping
from sender import *
import thread
import time
from bark import loudandclear

queue_name=("dog_Froo")

params=pika.URLParameters('amqp://kexupuab:7pMpe6fAQlKe4939Sy_ZTaFQeY4r8hMQ@elephant.rmq.cloudamqp.com/kexupuab')
connection=pika.BlockingConnection(params)
channel = connection.channel()			#start channel

channel.queue_declare(queue=queue_name)

sende = Sender()
dogo=dog.Dog_controller()
def dogos():
	dogo.do_action()
	
def send_v():
	while True:
                values = []
		t=get_temperature()
                values.append(("T", t))
		p=fping()
		values.append(("P", p))
		sende.send(values)
		time.sleep(10)
		
def callback(ch, method, properties, body):
	dogo.append_action(body)
	print(" [x] Recived %r" % body)
	print body
def sound():
        loudandclear()

channel.basic_consume(callback,
                      queue=queue_name,
                      no_ack=True)

print ('[*] Waiting for messages on queue ', queue_name)
thread.start_new_thread(dogos, ())
thread.start_new_thread(send_v, ())
thread.start_new_thread(sound, ())

try:
	channel.start_consuming()
except (KeyboardInterrupt, SystemExit):
	print("\nExiting...")

