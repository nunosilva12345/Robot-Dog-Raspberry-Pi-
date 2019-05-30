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
public enum Control {
    forward, 
    reverse, 
    turnRight, 
    turnLeft, 
    circleRight, 
    circleLeft, 
    dance;
    
    @Override
    public String toString() {
        
        String res = "doing nothing";
        
        switch(this){
            case forward:
                return "moving forward";
            case reverse:
                return "moving backward";
            case turnRight:
                return "turning his wheels right";
            case turnLeft:
                return "turning his wheels left";
            
        }
        
        return res;
    }
}


