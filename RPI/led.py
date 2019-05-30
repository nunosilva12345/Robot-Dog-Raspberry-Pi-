from temp import get_temperature
import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(18,GPIO.OUT)

def ledtemp(get_t):
        cur_temp=get_temperature()
        while (cur_temp > int(get_t)):
                GPIO.output(18,True)
                time.sleep(0.5)       
        GPIO.output(18,False)

