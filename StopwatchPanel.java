import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.math.*;

/**
  * @author Selina Schuh s5124327
  * @version 1.5
  * @since 1.2
  */

public class StopwatchPanel extends JPanel implements Runnable{
      //class body

      /**
        * buttons to start, stop and reset the stopwatch
        */
  private JButton btnStart, btnStop, btnReset;

  /**
    * label to display the name of the panel
    */
  private JLabel panelLabel, placeHolder;

  /**
    * variables to set the k size of the sevensegment number representation,
    * current hour, minutes, seconds and milliseconds,
    * the hour, minutes, seconds and milliseconds when the stopwatch is paused/stopped
    * and the start time, elapsed time and stop time of the stopwatch
    */
  private int size = 7;
  private int hours, minutes, seconds, milliseconds, stopHour, stopMinute, stopSecond, stopMillisecond;
  private long startTime, stopTime, elapsedTime;

  /**
    * the SevenSegment variables to represent each number of the stopwatch as a sevensegment number
    */
  private SevenSegment h1, h2, min1, min2, s1, s2, mil1, mil2;

  /**
    * variables to indicate whether the stopwatch is running or is restarted
    */
  private boolean isRunning, isRestarted;
  private Thread th;

  /**
   * variable to set the font of the text components of the panel
   */
  private Font font;

  /**
    * GridBagLayout constraints for the location of the components of the panel
    */
  private GridBagConstraints c;

  /**
    * a calendar variable to get the current time
    */
  private Calendar calendar;

  /**
    * color variables to set the colors of the background, lables and buttons
    */
  private Color lightgrey, darkgrey, turquoise, defaultCol;


  /**
    * the constructor of the class
    * <p>
    * initializes the GUI components of the class,
    * initializes the variables
    * and a sevensegment object for each number of the stopwatch
    * sets colors, fonts and locations of all components
    * adds actionlisteners for buttons
    * and adds the components to the panel
    * and starts the thread by calling the thread method of the class
    * </p>
    */
  public StopwatchPanel(){

    this.setStopValues();
    this.isRestarted = false;

    h1 = new SevenSegment(140, 300, size);
    h2 = new SevenSegment(220, 300, size);
    min1 = new SevenSegment(320, 300, size);
    min2 = new SevenSegment(400, 300, size);
    s1 = new SevenSegment(500, 300, size);
    s2 = new SevenSegment(580, 300, size);
    mil1 = new SevenSegment(680, 300, size);
    mil2 = new SevenSegment(760, 300, size);

    this.lightgrey = new Color(95, 100, 103);
    this.darkgrey = new Color(60, 63, 65);
    this.turquoise = new Color(12, 216, 201);
    this.defaultCol = new Color(51, 51, 51);

    this.font = new Font("Century Gothic", Font.PLAIN, 40);

    this.btnStart = new JButton("Start");
    this.btnStop = new JButton("Stop");
    this.btnReset = new JButton("Reset");
    this.panelLabel = new JLabel("Stopwatch");
    this.placeHolder = new JLabel();

    this.btnStart.setBackground(this.lightgrey);
    this.btnStop.setBackground(this.lightgrey);
    this.btnReset.setBackground(this.lightgrey);
    this.panelLabel.setForeground(this.turquoise);

    this.btnStart.setFont(this.font);
    this.btnStop.setFont(this.font);
    this.btnReset.setFont(this.font);
    this.panelLabel.setFont(new Font("Century Gothic", Font.PLAIN, 70));

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

    c.insets = new Insets(30, 10, 10, 0);
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy = 0;
    this.add(this.panelLabel, c);
    c.gridwidth = 1;
    c.ipadx = 170;
    c.ipady = 33;
    c.gridx = 1;
    c.gridy = 2;
    this.add(this.placeHolder, c);
    c.gridy = 3;
    this.add(this.placeHolder, c);
    c.gridy = 4;
    this.add(this.placeHolder, c);
    c.insets = new Insets(100, 10, 10, 0);
    c.gridy = 5;
    this.add(btnStart, c);

    c.gridx = 2;
    this.add(btnStop, c);

    c.gridx = 3;
    this.add(btnReset, c);

    this.start();

  }


  /**
    * initializes and starts the Thread
    */
  public void start(){
    if(th == null)
    {
      th = new Thread(this);
      th.start();
    }
  }


  /**
    * starts the stopwatch
    * <p>
    * Starts the Stopwatch by setting the start time to the current time in milliseconds
    * and the isRunning boolean variable to true
    * </p>
    */
  public void startStopWatch() {
  this.startTime = System.currentTimeMillis();
  this.isRunning = true;
}

/**
  * stops the stopwatch
  * <p>
  * stops the stopwatch by setting the stoptime to the current time
  * and setting the isRunning boolean variable to false.
  * </p>
  */
public void stopStopwatch() {
  this.stopTime = System.currentTimeMillis();
  this.isRunning = false;
}


  /**
    * overrides the run method of the runnable interface
    *  <p>
    * when the isRunning variable is set to true
    * the elapsed time is calculated by subtracting the start time from the current time
    * calculates the milliseconds, seconds, minutes and hours based on the elapsed time (given in milliseconds)
    * calls the showTime and repaint methods of this class to update the sevensegment number displays
    * </p>
    */
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
         }

         this.showTime();
         repaint(); // calls paint method of JPanel
         Thread.sleep(10);
       } catch(Exception e){
         e.printStackTrace();
       }
     }
   } // end of run()


   /**
     * sets each digit to represent the elapsed time of the stopwatch
     */
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


   /**
     * overrides the paint method of the runnable interface
     * <p>
     * calls the paintNumber method of the SevenSegment class
     * to change the digits according to the elapsed time of the stopwatch
     * an adds semicolons betwee the hours, minutes and seconds and milliseconds segments
     * </p>
     * @param Graphics g
     */
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
     g2.fill(new Rectangle2D.Double(298,265,14,14));
     g2.fill(new Rectangle2D.Double(298,335,14,14));
     g2.fill(new Rectangle2D.Double(478, 265, 14, 14));
     g2.fill(new Rectangle2D.Double(478, 335, 14, 14));
     g2.fill(new Rectangle2D.Double(658, 265, 14, 14));
     g2.fill(new Rectangle2D.Double(658, 335, 14, 14));
   }


   /**
    * starts the stopwatch
    * <p>
    * starts the stopwatch by setting the isRunning variable to true
    * calling the startStopWatch method to set the start time
    * and enabling the the stop button
    * </p>
    * @param ActionEvent e
    */
   public void btnStartActionPerformed(ActionEvent e){

     this.isRunning = true;
     this.startStopWatch();

     this.btnStart.setForeground(this.turquoise);
     this.btnStop.setForeground(this.defaultCol);
     this.btnReset.setForeground(this.defaultCol);
     this.btnStart.setEnabled(false);
     this.btnStop.setEnabled(true);
   }

   /**
    * stops(pauses) the stopwatch
    * <p>
    * stops / pauses the stopwatch by setting the isRunning variable to false
    * and the isRestarted variable to true so next time the
    * start button is clicked the stopwatch resumes instead of starting from 0 again
    * sets the stop time variables
    * and enables the start button
    * </p>
    * @param ActionEvent e
    */
   public void btnStopActionPerformed(ActionEvent e){

     this.isRunning = false;
     this.isRestarted = true;

     //save current time
     this.stopHour = this.hours;
     this.stopMinute = this.minutes;
     this.stopSecond = this.seconds;
     this.stopMillisecond = this.milliseconds;

     //set buttons
     this.btnStop.setForeground(this.turquoise);
     this.btnStart.setForeground(this.defaultCol);
     this.btnReset.setForeground(this.defaultCol);
     this.btnStop.setEnabled(false);
     this.btnStart.setEnabled(true);
   }


   /**
    * resets the stopwatch
    * <p>
    * resets the stopwatch by setting the is running variable to false
    * and resetting all time variables to 0
    * and calling the showTime and repaint methods of the class to reset
    * the SevenSegment representation of the numbers to 0
    * </p>
    * @param ActionEvent e
    */
   public void btnResetActionPerformed(ActionEvent e){

     this.isRunning = false;
     this.btnReset.setForeground(this.turquoise);
     this.btnStop.setForeground(this.defaultCol);
     this.btnStart.setForeground(this.defaultCol);

     //reset everything to 0
     this.hours = 0;
     this.minutes = 0;
     this.seconds = 0;
     this.milliseconds = 0;
     this.setStopValues();
     this.showTime();
     this.repaint();
   }

   /**
    * resets the stopvalues to 0
    */
   private void setStopValues(){
     this.stopHour = 0;
     this.stopMinute = 0;
     this.stopSecond = 0;
     this.stopMillisecond = 0;
   }


}
