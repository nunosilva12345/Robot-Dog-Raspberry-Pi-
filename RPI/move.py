#encoding: UTF8
#!/usr/bin/env python

import sys, traceback, os
from subprocess import call
from time import sleep
from threading import Thread
from ufront import fping
from uback import bping
from led import ledtemp

    # girar 01 + 01 + 01direita ou 00esquerda + valor		
	# andar 01 + 03 + 00tras ou 01frente + valor   	
	# parar 00 + porta 

RUN_A = "gatttool --device=00:07:80:2E:41:EE --adapter=hci0 --char-write --handle=0x0021A --value=0103"
TURN_A = "gatttool --device=00:07:80:2E:41:EE --adapter=hci0 --char-write --handle=0x001A --value=0101"
BREAK_A = "gatttool --device=00:07:80:2E:41:EE --adapter=hci0 --char-write --handle=0x001A --value=00"



class Dog_controller():
	def __init__(self):
		self.actions=[]
		self.next_action= 1
		
	def append_action(self,order):
		self.actions.append(order)
		
	def do_action(self):
		while(True):
			if (self.actions==[] or self.next_action > len(self.actions)):
				sleep(1)
			else:
				for i in range(10):
					self.move_dog_to(self.actions[self.next_action-1])
					sleep(0.1)
				self.next_action +=1

	def move_dog_to(self,direction):
		try:
                        '''
                        #Distancias 
                        front = fping() 
                        if (front < 10 ):
                                f_flag=1
                        else:
                                f_flag=0
                        back = bflag()
                        if (front < 10 ):
                                f_flag=1
                        else:
                                f_flag=0
                        '''
                        action=direction
                        if (action[0] == "L"): 
                                temp = action[5:]
                        action=action[0] 
			a=str(hex(200))[2:]
			#if (f_flag=1) or (b_flag=0):
                        if (action=="B"):
                                call(RUN_A + '00' + a, shell=True)
                                print("Moving the dog Backward")
                        #if (b_flag=1) or (f_flag=0):
                        elif (action=="F"):
                                call(RUN_A + '01' + a, shell=True)
                                print("Moving the dog Forward")
                        elif (action=="Q"):
                                call(RUN_A + '01' + a, shell=True)
                                call(TURN_A + '00' + a, shell=True)
                                print("Turning Left")
                        elif (action=="E"):
                                call(RUN_A + '01' + a, shell=True)
                                call(TURN_A + '01' + a, shell=True)
                                print("Turning Right")
			if (action==""):
				for b in range(4):
					call(BREAK_A + str(hex(b))[2:], shell=True)
				print("Stoping the dog")
			if (action=="L"):
                                ledtemp(temp)
			else:
				print(action)
		except Exception:
			traceback.print_exc(file=sys.stdout)		


