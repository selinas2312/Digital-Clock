import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.math.*;

public class TimerPanel extends JPanel implements Runnable{

  JButton btnStart, btnStop, btnReset;
  int size = 7;
  int hours, minutes, seconds;
  SevenSegment h1, h2, min1, min2, s1, s2;
  long startTime, stopTime, elapsedTime, timer = 7200000;
  boolean isRunning, isRestarted, isPaused;
  Thread th;
  Font font;
  GridBagConstraints c;
  Calendar calendar;
  Color lightgrey, darkgrey, turquoise;

  public TimerPanel(){

    h1 = new SevenSegment(20, 100, size);
    h2 = new SevenSegment(100, 100, size);
    min1 = new SevenSegment(200, 100, size);
    min2 = new SevenSegment(280, 100, size);
    s1 = new SevenSegment(380, 100, size);
    s2 = new SevenSegment(460, 100, size);

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

  //currentmillis + timer ((((hours * 60) + minutes)*60) + seconds) * 1000
  public void setStartTime(){
    this.startTime = System.currentTimeMillis();
    this.startTime += this.timer;

  }

  @Override
   public void run(){
     while(th != null)
     {
       try{
        // Calendar cal = Calendar.getInstance();

         if(this.isRunning == true)
         {
           this.hours = Math.toIntExact((this.startTime - System.currentTimeMillis()) / 3600000);
           this.minutes = Math.toIntExact((this.startTime - System.currentTimeMillis())/60000);
           this.seconds = Math.toIntExact((this.startTime - System.currentTimeMillis())/1000);

           this.showTime();
         }
         else if(this.isPaused == true){
           //add one second to the starm time for every second that passes while paused.
           this.startTime+= 1000;

         }
         repaint(); // calls paint method of JPanel
         Thread.sleep(1000);
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

     //set Minute
     min1.setNumberTo((minutes%60)/10);
     min2.setNumberTo(minutes % 10);

     //set Seconds
     s1.setNumberTo((seconds%60)/10);
     s2.setNumberTo(seconds % 10);

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


   //  add dots between h, m, s
     g2.setColor(new Color(0, 215, 230));
     g2.fill(new Rectangle2D.Double(178,65,14,14));
     g2.fill(new Rectangle2D.Double(178,135,14,14));
     g2.fill(new Rectangle2D.Double(358, 65, 14, 14));
     g2.fill(new Rectangle2D.Double(358, 135, 14, 14));
   }

   public void btnStartActionPerformed(ActionEvent e){

     this.isRunning = true;
     if(this.isPaused == false)
     {
        this.setStartTime();
     }

     this.btnStart.setForeground(this.turquoise);
     this.btnStop.setForeground(new Color(51, 51, 51));
     this.btnReset.setForeground(new Color(51, 51, 51));
     this.btnStart.setEnabled(false);
     this.btnStop.setEnabled(true);
   }

   public void btnStopActionPerformed(ActionEvent e){
      //when pause stop timer and when restart reset start time
      this.isRunning = false;
      this.isPaused = true;
      this.btnStop.setForeground(this.turquoise);
      this.btnStart.setForeground(new Color(51, 51, 51));
      this.btnReset.setForeground(new Color(51, 51, 51));
      this.btnStop.setEnabled(false);
      this.btnStart.setEnabled(true);
   }

   public void btnResetActionPerformed(ActionEvent e){

     this.isRunning = false;
     this.isPaused = false;

     this.btnReset.setForeground(this.turquoise);
     this.btnStop.setForeground(new Color(51, 51, 51));
     this.btnStart.setForeground(new Color(51, 51, 51));
     this.btnStart.setEnabled(true);
     this.btnStop.setEnabled(false);

     //reset everything to 0
     this.hours = 0;
     this.minutes = 0;
     this.seconds = 0;
     //this.timer = 0;
     this.startTime = 0;
     this.showTime();
     this.repaint();

   }



}
