import java.util.*;
import java.awt.*;
import java.swing.*;

public Class Alarm{

  //fields
  private boolean alarmSet;
  private int alarmTime;

  //Constructor
  public Alarm(int time){

    //set alarm to given time
    this.alarmTime = time;
    this.alarmSet = true;
  }

  public int getAlarm(){
    return this.alarmTime;
  }

  public void changeAlarm(int time){
    this.alarmTime = time;
  }

  public boolean isSet(){
    return this.alarmSet;
  }
  public void turnOff(){
    this.alarmSet = false;
  }

  public void turnOn(){
    this.alarmSet = true;
  }

  
}
