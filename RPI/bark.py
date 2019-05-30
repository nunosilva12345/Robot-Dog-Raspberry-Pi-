#!/usr/bin/python
import RPi.GPIO as GPIO
import time
import os

#GPIO SETUP
channel = 17
GPIO.setmode(GPIO.BCM)
GPIO.setup(channel, GPIO.IN)
def loudandclear(channel):
        while (True):
                if GPIO.input(channel):
                        print "Sound Detected! if"
                        os.system('aplay /home/pi/IES_Project/bark.wav')
                else:
                        print "Sound Detected! else"
                        os.system('aplay /home/pi/IES\ -\ Project/bark.wav')
                time.sleep(1)
 
GPIO.add_event_detect(channel, GPIO.RISING, bouncetime=300)  # let us know when the pin goes HIGH or LOW
GPIO.add_event_callback(channel, callback)  # assign function to GPIO PIN, Run function on change
 
# infinite loop

	
