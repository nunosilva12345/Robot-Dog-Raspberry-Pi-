#!/usr/bin/python
 
import spidev
import time
import RPi.GPIO as GPIO




#Define Variables
delay = 0.5
ldr_channel = 0

#Create SPI
spi = spidev.SpiDev()
spi.open(0, 0)
 
def readadc(adcnum):
    # read SPI data from the MCP3008, 8 channels in total
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, 8 + adcnum << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data
    

def get_temperature():
	GPIO.setmode(GPIO.BCM)
        GPIO.setwarnings(False)
        GPIO.setup(18,GPIO.OUT)
	ldr_value = (3.3*readadc(ldr_channel) / 10.24)-273
	return ldr_value


def print_it():
        GPIO.setmode(GPIO.BCM)
        GPIO.setwarnings(False)
        GPIO.setup(18,GPIO.OUT)
	while True:
		ldr_value = (3.3*readadc(ldr_channel) / 10.24)-273
		print "---------------------------------------"
		print("LDR Value: %d" % ldr_value)
		time.sleep(delay)
                if ldr_value > 22:
                    GPIO.output(18,GPIO.HIGH)
                    time.sleep(0.5)
                GPIO.output(18,GPIO.LOW)


