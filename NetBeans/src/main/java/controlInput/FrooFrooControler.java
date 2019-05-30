/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlInput;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "FrooFrooControler")
@ViewScoped
public class FrooFrooControler  implements Serializable{
   
    private Sender moveSender;
    private Receiver sensor_recive;
    private String TempToShow;
    private String currentDog;
    private String tempDefiner;

    
    public FrooFrooControler() throws Exception {
        //inicializar e fazer configuração inicial de senders e recivers
        moveSender = new Sender();
        sensor_recive = new Receiver();
        sensor_recive.setQueueName("SENSORES");
        moveSender.setID("DV1");
        sensor_recive.recieve();
        tempDefiner = "0.0";
        
    }
    

    public String getCurrentDog() {
        return currentDog;
    }

    public void setCurrentDog(String currentDog) {
        moveSender.setID(currentDog);
        sensor_recive.setCurrentDog(currentDog);
        this.currentDog = currentDog;
    }

    public String getTempToShow() {
        TempToShow = sensor_recive.getHashValue(currentDog);
        return TempToShow;
    }

    public void setTempToShow(String TempToShow) {
        this.TempToShow = TempToShow;
    }
    
    public String getTempDefiner() {
        return tempDefiner;
    }

    public void setTempDefiner(String tempDefiner) {
        this.tempDefiner = tempDefiner;
    }

    public ArrayList<String> getDogsDisponiveis() {
        return sensor_recive.getArrayDogs();
    }



    

       
  
    
    
    public void setDirectionForward() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "It's moving!", "Froo Froo started moving forward"));
        moveSender.send("W FORWARD");    
    }
    
    public void setDirectionReverse() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "It's moving!", "Froo Froo started moving backwards"));
        moveSender.send("S BACKWARD");
    }
    
    public void setDirectionForwardRight() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "It's moving!", "Froo Froo turned his wheels right"));
        moveSender.send("E FORWARD RIGHT");
    }
    
    public void setDirectionForwardLeft() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "It's moving!", "Froo Froo turned his wheels left"));
        moveSender.send("Q FORWARD LEFT");    
    }
    
    public void setDirectionBackwardRight() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "It's moving!", "Froo Froo is turning right backwards"));
        moveSender.send("D BACKWARD RIGHT");    
    }
    
    public void setDirectionBackwardLeft() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "It's moving!", "Froo Froo is turning left backwards"));
        moveSender.send("A BACKWARD LEFT");    
    }
   
    
    public void setTemperature() throws Exception {
        //moveSender.send("HEY");
        moveSender.send("LEDS:"+tempDefiner);    
    }

   





}




