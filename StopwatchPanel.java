import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.math.*;

public class StopwatchPanel extends JPanel implements Runnable{

  JButton btnStart, btnStop, btnReset;
  int size = 7;
  int hours, minutes, seconds, milliseconds, stopHour, stopMinute, stopSecond, stopMillisecond;
  SevenSegment h1, h2, min1, min2, s1, s2, mil1, mil2;
  long startTime, stopTime, elapsedTime;
  boolean isRunning, isRestarted;
  Thread th;
  Font font;
  GridBagConstraints c;
  Calendar calendar;
  Color lightgrey, darkgrey, turquoise;

  public StopwatchPanel(){

    this.setStopValues();
    this.isRestarted = false;

    h1 = new SevenSegment(20, 100, size);
    h2 = new SevenSegment(100, 100, size);
    min1 = new SevenSegment(200, 100, size);
    min2 = new SevenSegment(280, 100, size);
    s1 = new SevenSegment(380, 100, size);
    s2 = new SevenSegment(460, 100, size);
    mil1 = new SevenSegment(560, 100, size);
    mil2 = new SevenSegment(640, 100, size);

    this.lightgrey = new Color(95, 100, 103);
    this.darkgrey = new Color(60, 63, 65);
    this.turquoise = new Color(12, 216, 201);

    this.font = new Font("Century Gothic", Font.PLAIN, 40);

    this.btnStart = new JButton("Start");
    this.btnStop = new JButton("Stop");
    this.btnReset = new JButton("Reset");

    this.btnStart.setBackground(this.lightgrey);
    this.btnStop.setBackground(this.lightgrey);
    this.btnReset.setBackground(this.lightgrey);

    this.btnStart.setFont(this.font);
    this.btnStop.setFont(this.font);
    this.btnReset.setFont(this.font);

    this.btnStart.setFocusable(false);
    this.btnStop.setFocusable(false);
    this.btnReset.setFocusable(false);

    this.btnStart.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnStartActionPerformed(e);
      }
    });
    this.btnStop.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnStopActionPerformed(e);
      }
    });
    this.btnReset.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnResetActionPerformed(e);
      }
    });

    this.setLayout(new GridBagLayout());
    this.setBackground(this.darkgrey);

    GridBagConstraints c = new GridBagConstraints();

    c.insets = new Insets(100, 10, 10, 0);
    c.ipadx = 170;
    c.ipady = 33;
    c.gridx = 1;
    c.gridy = 1;
    this.add(btnStart, c);

    c.gridx = 2;
    this.add(btnStop, c);

    c.gridx = 3;
    this.add(btnReset, c);

    this.start();

  }

  public void start(){
    if(th == null)
    {
      th = new Thread(this);
      th.start();
    }
  }

  public void startStopWatch() {
  this.startTime = System.currentTimeMillis();
  this.isRunning = true;
}


public void stopStopwatch() {
  this.stopTime = System.currentTimeMillis();
  this.isRunning = false;
}


  @Override
   public void run(){
     while(th != null)
     {
       try{
         Calendar cal = Calendar.getInstance();

         if(this.isRunning == true)
         {
           this.elapsedTime = System.currentTimeMillis() - this.startTime;
           this.milliseconds = (this.stopMillisecond%1000 + Math.toIntExact((this.elapsedTime%1000)))%1000;
           this.seconds = this.stopSecond + Math.toIntExact(this.elapsedTime/1000);
           this.minutes = this.stopMinute + Math.toIntExact(this.elapsedTime/60000);
           this.hours =this.stopHour + Math.toIntExact(this.elapsedTime/3600000);
           System.out.println(this.elapsedTime);
          // this.setStopValues();
         }

         this.showTime();
         repaint(); // calls paint method of JPanel
         Thread.sleep(10);
       } catch(Exception e){
         e.printStackTrace();
       }
     }
   } // end of run()

   public void showTime(){

     if(hours < 10)
     {
       h1.setNumberTo(0);
       h2.setNumberTo(hours);
     }
     else{
       h1.setNumberTo(hours/10);
       h2.setNumberTo(hours % 10);
     }

     //set Minutes
     min1.setNumberTo(minutes/10);
     min2.setNumberTo(minutes % 10);

     //set Seconds
     s1.setNumberTo((seconds%60)/10);
     s2.setNumberTo(seconds % 10);

     //set milliseconds
     mil1.setNumberTo(milliseconds/100);
     mil2.setNumberTo((milliseconds%100)/10);
   }

 @Override public void paint(Graphics g){
     super.paint(g);
     Graphics2D g2 = (Graphics2D)g;
     h1.paintNumber(g2);
     h2.paintNumber(g2);
     min1.paintNumber(g2);
     min2.paintNumber(g2);
     s1.paintNumber(g2);
     s2.paintNumber(g2);
     mil1.paintNumber(g2);
     mil2.paintNumber(g2);


   //  add dots between h, m, s
     g2.setColor(new Color(0, 215, 230));
     g2.fill(new Rectangle2D.Double(178,65,14,14));
     g2.fill(new Rectangle2D.Double(178,135,14,14));
     g2.fill(new Rectangle2D.Double(358, 65, 14, 14));
     g2.fill(new Rectangle2D.Double(358, 135, 14, 14));
     g2.fill(new Rectangle2D.Double(538, 65, 14, 14));
     g2.fill(new Rectangle2D.Double(538, 135, 14, 14));
   }

   public void btnStartActionPerformed(ActionEvent e){

     this.isRunning = true;
     this.startStopWatch();

     this.btnStart.setForeground(this.turquoise);
     this.btnStop.setForeground(new Color(51, 51, 51));
     this.btnReset.setForeground(new Color(51, 51, 51));
     this.btnStart.setEnabled(false);
     this.btnStop.setEnabled(true);
   }

   public void btnStopActionPerformed(ActionEvent e){

     this.isRunning = false;
     this.isRestarted = true;

     //save current time
     this.stopHour = this.hours;
     this.stopMinute = this.minutes;
     this.stopSecond = this.seconds;
     this.stopMillisecond = this.milliseconds;
     System.out.println("Stop MIlli second ; " + stopMillisecond);

     //set buttons
     this.btnStop.setForeground(this.turquoise);
     this.btnStart.setForeground(new Color(51, 51, 51));
     this.btnReset.setForeground(new Color(51, 51, 51));
     this.btnStop.setEnabled(false);
     this.btnStart.setEnabled(true);
   }

   public void btnResetActionPerformed(ActionEvent e){

     this.isRunning = false;
     this.btnReset.setForeground(this.turquoise);
     this.btnStop.setForeground(new Color(51, 51, 51));
     this.btnStart.setForeground(new Color(51, 51, 51));

     //reset everything to 0
     this.hours = 0;
     this.minutes = 0;
     this.seconds = 0;
     this.milliseconds = 0;
     this.setStopValues();
     this.showTime();
     this.repaint();
   }

   private void setStopValues(){
     this.stopHour = 0;
     this.stopMinute = 0;
     this.stopSecond = 0;
     this.stopMillisecond = 0;
   }


}
