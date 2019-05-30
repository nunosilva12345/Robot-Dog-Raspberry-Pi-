#encoding: UTF8
#!/usr/bin/env python

import sys, traceback, os
from time import sleep
from threading import Thread
from subprocess import call
from ufront import fping
from uback import bping

# girar 01 + 01 + 01direita ou 00esquerda + valor           
# andar 01 + 03 + 00tras ou 01frente + valor    
# parar 00 + porta 

RUN_A = "gatttool --device=00:07:80:2E:41:EE --adapter=hci0 --char-write --handle=0x0021A --value=0103"
TURN_A = "gatttool --device=00:07:80:2E:41:EE --adapter=hci0 --char-write --handle=0x001A --value=0101"
BREAK_A = "gatttool --device=00:07:80:2E:41:EE --adapter=hci0 --char-write --handle=0x001A --value=00"

def manobra_b():
                a = str(hex(255))[2:]
                call(TURN_A + '01' + a, shell=True)
                call(RUN_A + '00' + a, shell=True)
                sleep(0.3)
                call(TURN_A + '01' + a, shell=True)
                call(RUN_A + '00' + a, shell=True)
                sleep(0.3)
                call(TURN_A + '01' + a, shell=True)
                call(RUN_A + '00' + a, shell=True)
                sleep(0.3)
                call(TURN_A + '01' + a, shell=True)
                call(RUN_A + '00' + a, shell=True)
                sleep(0.3)
                call(TURN_A + '01' + a, shell=True)
                call(RUN_A + '00' + a, shell=True)
                sleep(0.3)
                call(TURN_A + '01' + a, shell=True)
                call(RUN_A + '00' + a, shell=True)
                sleep(0.3)

def move(i,front,back):
        if (i%10 == 0):
                front = fping()
                back = bping()
                print("Front: " + front + " Back: " + back)
        a = str(hex(255))[2:]
        if (front < 10):
                manobra_b()
                print('Maneuver backward')
                return
        if (back < 10):
                manobra_b()
                print('Maneuver backward')
                return                
        call(RUN_A + '01' + a, shell=True)
        sleep(0.3)
        print('Moving forward')
        

distf=30
distb=30
k=0
while True:
        k+=1
        move(k,distf,distb)
        
