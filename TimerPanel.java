import java.util.*;
import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.text.*;
import java.awt.event.*;
import java.text.*;
import java.awt.geom.*;
import java.math.*;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
  * @author Selina Schuh s5124327
  * @version 1.5
  * @since 1.3
  */

public class TimerPanel extends JPanel implements Runnable{
       //class body

  /**
    * variables necessary to set the spinners to set the alarm time
    */
 private List<Integer> hList, minList;
 private SpinnerListModel hModel, minModel;
 private JSpinner hSpinner, minSpinner;
 private JComponent editor;
  /**
    * buttons to start, pause and stop the timer
    */
  private JButton btnStart, btnPause, btnReset;

  /**
    * label to display the name of the panel
    */
  private JLabel panelLabel, placeHolder;

  /**
    * variables to set the k size of the sevensegment number representation,
    * the remaining hours, minutes and seconds of the timer,
    * and the start time, elapsed time and set time of the timer
    */
  private int size = 7;
  private int hours, minutes, seconds;
  private long startTime, elapsedTime, timer;

  /**
    * the SevenSegment variables to represent each number as a sevensegment number
    */
  private SevenSegment h1, h2, min1, min2, s1, s2;

  /**
    * boolean variables to indicate whether the timer is running or paused
    */
  private boolean isRunning, isPaused;
  private Thread th;

  /**
    * the font for the buttons and labels
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
    * initializes all components of the panel,
    * sets the fonts, colors and locations of the components,
    * adds the components to the panel,
    * adds actionlisteners to the components,
    * and starts the thread by calling the start method of the class
    * </p>
    */
  public TimerPanel(){

    h1 = new SevenSegment(200, 300, size);
    h2 = new SevenSegment(280, 300, size);
    min1 = new SevenSegment(380, 300, size);
    min2 = new SevenSegment(460, 300, size);
    s1 = new SevenSegment(560, 300, size);
    s2 = new SevenSegment(640, 300, size);

    this.lightgrey = new Color(95, 100, 103);
    this.darkgrey = new Color(60, 63, 65);
    this.turquoise = new Color(12, 216, 201);
    this.defaultCol = new Color(51, 51, 51);

    this.font = new Font("Century Gothic", Font.PLAIN, 40);

    this.btnStart = new JButton("Start");
    this.btnPause = new JButton("Stop");
    this.btnReset = new JButton("Reset");
    this.panelLabel = new JLabel("Timer");
    this.placeHolder = new JLabel();

    this.btnStart.setBackground(this.lightgrey);
    this.btnPause.setBackground(this.lightgrey);
    this.btnReset.setBackground(this.lightgrey);
    this.panelLabel.setForeground(this.turquoise);

    this.btnStart.setFont(this.font);
    this.btnPause.setFont(this.font);
    this.btnReset.setFont(this.font);
    this.panelLabel.setFont(new Font("Century Gothic", Font.PLAIN, 70));

    this.btnStart.setFocusable(false);
    this.btnPause.setFocusable(false);
    this.btnReset.setFocusable(false);

    this.btnStart.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnStartActionPerformed(e);
      }
    });
    this.btnPause.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnPauseActionPerformed(e);
      }
    });
    this.btnReset.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnResetActionPerformed(e);
      }
    });

    this.setSpinners();
    this.setLayout(new GridBagLayout());
    this.setBackground(this.darkgrey);

    GridBagConstraints c = new GridBagConstraints();

    c.insets = new Insets(60, 10, 10, 0);
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy = 0;
    this.add(this.panelLabel, c);
    c.insets = new Insets(100, 10, 10, 0);
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
    c.gridy = 5;
    this.add(this.hSpinner, c);
    c.gridx = 2;
    this.add(this.minSpinner, c);
    c.insets = new Insets(30, 10, 10, 0);
    c.gridx = 1;
    c.gridy = 6;
    this.add(this.btnStart, c);

    c.gridx = 2;
    this.add(this.btnPause, c);

    c.gridx = 3;
    this.add(this.btnReset, c);

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
    * initializes the start time
    * <p>
    * initializes the start time by setting it to the current time
    * and adding the set time of the timer to it
    * </p>
    */
  public void setStartTime(){
    this.startTime = System.currentTimeMillis();
    this.startTime += this.timer;

  }

  /**
    * ovverrides the run method of the runnable interface
    * <p>
    * if the timer is running :
    * sets the remaining hours minutes and seconds of the timer
    * by subtracting the current time from the start time
    * and calls the showTime and paint method of this class to update the SevenSegment display
    * to display the remaining time of the timer
    */
  @Override
   public void run(){
     while(th != null)
     {
       try{
         if(this.isRunning == true)
         {

           this.hours = Math.toIntExact((this.startTime - System.currentTimeMillis()) / 3600000);
           this.minutes = Math.toIntExact((this.startTime - System.currentTimeMillis())/60000);
           this.seconds = Math.toIntExact((this.startTime - System.currentTimeMillis())/1000);

           if(this.hours == 0 && this.minutes == 0 && this.seconds == 0)
           {
             //add Dialog to Display Timer is up.\
             this.showTime();
             Object[] option = {"OKAY"};
             JOptionPane alarm = new JOptionPane();
             JOptionPane.showMessageDialog(alarm, "Time is up!!", "Time is up!!", JOptionPane.WARNING_MESSAGE);

             //reset the timer
             this.isRunning = false;
             this.isPaused = false;
             this.hSpinner.setValue(0);
             this.minSpinner.setValue(0);
             this.hours = 0;
             this.minutes = 0;
             this.seconds = 0;
             this.timer = 0;
             this.startTime = 0;
           }

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


   /**
     * sets each digit to represent the remaining time of the timer
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

     //set Minute
     min1.setNumberTo((minutes%60)/10);
     min2.setNumberTo(minutes % 10);

     //set Seconds
     s1.setNumberTo((seconds%60)/10);
     s2.setNumberTo(seconds % 10);

   }


   /**
     * overrides the paint method of the runnable interface
     * <p>
     * calls the paintNumber method of the SevenSegment class
     * to change the digits according to the remaining time of the timer
     * an adds semicolons betwee the hours, minutes and seconds segments
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


   //  add dots between h, m, s
     g2.setColor(new Color(0, 215, 230));
     g2.fill(new Rectangle2D.Double(358,265,14,14));
     g2.fill(new Rectangle2D.Double(358,335,14,14));
     g2.fill(new Rectangle2D.Double(538, 265, 14, 14));
     g2.fill(new Rectangle2D.Double(538, 335, 14, 14));
   }


   /**
    * starts the timer when start button is clicked
    * <p>
    * starts the timer by setting the isRunning variable to true,
    * setting the start time, unless the timer is resuming after being paused
    * hence when isPaused is true,
    * enables the Pause button and disables the start button
    * @param ActionEvent e
    */
   public void btnStartActionPerformed(ActionEvent e){

     this.isRunning = true;
     this.timer = ((Integer)this.hSpinner.getValue()) * 3600000 + ((Integer)this.minSpinner.getValue()) * 60000;
     if(this.isPaused == false)
     {
        this.setStartTime();
     }

     this.btnStart.setForeground(this.turquoise);
     this.btnPause.setForeground(this.defaultCol);
     this.btnReset.setForeground(this.defaultCol);
     this.btnStart.setEnabled(false);
     this.btnPause.setEnabled(true);
   }


   /**
    * Pauses the timer when pause button is clicked
    * <p>
    * pauses the timer by setting the isRunning variable to false and the isPaused variable to true
    * enables the Start button and disables the Pause button
    * @param ActionEvent e
    */
   public void btnPauseActionPerformed(ActionEvent e){
      //when pause stop timer and when restart reset start time
      this.isRunning = false;
      this.isPaused = true;
      this.btnPause.setForeground(this.turquoise);
      this.btnStart.setForeground(new Color(51, 51, 51));
      this.btnReset.setForeground(new Color(51, 51, 51));
      this.btnPause.setEnabled(false);
      this.btnStart.setEnabled(true);
   }


   /**
    * resets the timer when reset button is clicked
    * <p>
    * resets the timer by setting the isRunning and isPaused variables to false,,
    * resets the hours, minutes, seconds and startTime variables to 0,
    * and calls the showTime and repaint methods of this class to update the sevensegment numbers
    * @param ActionEvent e
    */
   public void btnResetActionPerformed(ActionEvent e){

     this.isRunning = false;
     this.isPaused = false;

     this.btnReset.setForeground(this.turquoise);
     this.btnPause.setForeground(new Color(51, 51, 51));
     this.btnStart.setForeground(new Color(51, 51, 51));
     this.btnStart.setEnabled(true);
     this.btnPause.setEnabled(false);

     //reset everything to 0
     this.hSpinner.setValue(0);
     this.minSpinner.setValue(0);
     this.hours = 0;
     this.minutes = 0;
     this.seconds = 0;
     this.timer = 0;
     this.startTime = 0;
     this.showTime();
     this.repaint();

   }

   /**
     * sets the color and font of the JSpinners
     * @param JComponent editor
     */
   private void setSpinnerLookAndFeel(JComponent editor){

     int n = editor.getComponentCount();
     for (int i=0; i<n; i++)
     {
         Component c = editor.getComponent(i);
         if (c instanceof JTextField)
         {
             c.setForeground(Color.WHITE);
             c.setBackground(this.lightgrey);
         }
     }
   }//end setSpinnerLookAndFeel

   /**
     * set Spinners
     * <p>
     * set minute and hour spinners to select the time of the timer
     * by initializing a list for minutes and a list for hours
     * and adding each list element to the actual spinner component
     * and call the setSpinnerLookAndFeel function to set fonts and colors
     * </p>
     */
   private void setSpinners(){

     //initialize hours and minutes list
     this.hList = new ArrayList<>();
     this.minList = new ArrayList<>();
     for(int i = 0; i <= 23; i++){
       hList.add(i);
     }
     for(int i = 0; i <= 59; i++){
       minList.add(i);
     }

     //set SpinnerListModels
     this.minModel = new SpinnerListModel(this.minList);
     this.hModel = new SpinnerListModel(this.hList);

     //set Spinners
     this.hSpinner = new JSpinner(hModel);
     this.minSpinner = new JSpinner(minModel);

     //set color and font for spinners
     this.editor = minSpinner.getEditor();
     this.setSpinnerLookAndFeel(editor);
     this.editor = hSpinner.getEditor();
     this.setSpinnerLookAndFeel(editor);
     this.hSpinner.setFont(this.font);
     this.minSpinner.setFont(this.font);
   }



}
