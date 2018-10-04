import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
  * @author Selina Schuh s5124327
  * @version 1.6
  * @since 1.0
  */


public class ClockPanel extends JPanel implements Runnable{
      //class body

  /**
    * the factor for the size of each seven segment number representation
    */
  int size = 7;

  /**
    * variables for the current time
    */
  int hours, minutes, seconds;

  /**
    * the sevensegment numbers to represent the current time
    */
  SevenSegment h1, h2, m1, m2, s1, s2;

  Thread th;

  /**
    * a label to display the current date and day
    */
  JLabel dayLbl;

  /**
    * the font for the label
    */
  Font font;

  /**
    * the GridBagLayout Constraints to align the components on the panel
    */
  GridBagConstraints c;

  /**
    * a calendar object to get the current time
    */
  Calendar calendar;

  /**
    * Strings representing the current date and day
    */
  String weekday, month, year, day;

  /**
    * a date object to represent the current date and day
    */
  Date date;


  /**
    * the constructor of the class
    * <p>
    * initializes a seven segment object for each number
    * initializes all other fields
    * sets the font and colors of the panel
    * and adds the components to the panel
    * and starts the Thread by calling the start() method of this class
    * </p>
    */
  public ClockPanel() {

    h1 = new SevenSegment(140, 100, size);
    h2 = new SevenSegment(220, 100, size);
    m1 = new SevenSegment(320, 100, size);
    m2 = new SevenSegment(400, 100, size);
    s1 = new SevenSegment(500, 100, size);
    s2 = new SevenSegment(580, 100, size);

    this.font = new Font("Century Gothic", Font.PLAIN, 60);

    this.date = new Date();
    this.weekday = new SimpleDateFormat("EEE, MMM d, YYYY").format(this.date);
    this.dayLbl = new JLabel(this.weekday);

    dayLbl.setForeground(new Color(12, 216, 201));
    dayLbl.setFont(this.font);

    this.setBackground(new Color(60, 63, 65));

    this.setLayout(new GridBagLayout());
    this.c = new GridBagConstraints();
    c.insets = new Insets(80, 80, 0, 250);
    c.gridx = 0;
    c.gridy = 1;
    this.add(dayLbl, c);

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
    * overrides the run method of the runnable interface
    * <p>
    * sets the current time and repaints each segment to display the change in time
    * every second
    * </p>
    */
 @Override
  public void run(){
    while(th != null)
    {
      try{
        Calendar cal = Calendar.getInstance();
        hours = cal.get( Calendar.HOUR_OF_DAY );
        minutes = cal.get( Calendar.MINUTE );
        seconds = cal.get( Calendar.SECOND );

        this.showTime();
        this.c.gridx = 0;
        this.c.gridy = 0;
        repaint(); // calls paint method of JPanel

        Thread.sleep( 1000 );  // interval given in milliseconds
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  } // end of run()


  /**
    * sets each digit to represent the current time
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
    m1.setNumberTo(minutes/10);
    m2.setNumberTo(minutes % 10);

    //set Seconds
    s1.setNumberTo(seconds/10);
    s2.setNumberTo(seconds % 10);


  }

  /**
    * overrides the paint method of the runnable interface
    * <p>
    * calls the paintNumber method of the SevenSegment class
    * to change the digits according to the current time
    * an adds semicolons betwee the hours, minutes and seconds segments
    * </p>
    * @param Graphics g
    */

@Override public void paint(Graphics g){
    super.paint(g);
    Graphics2D g2 = (Graphics2D)g;
    h1.paintNumber(g2);
    h2.paintNumber(g2);
    m1.paintNumber(g2);
    m2.paintNumber(g2);
    s1.paintNumber(g2);
    s2.paintNumber(g2);

  //  add dots between h, m, s
    g2.setColor(new Color(0, 215, 230));
    g2.fill(new Rectangle2D.Double(298,65,14,14));
    g2.fill(new Rectangle2D.Double(298,135,14,14));
    g2.fill(new Rectangle2D.Double(478, 65, 14, 14));
    g2.fill(new Rectangle2D.Double(478, 135, 14, 14));


  }
}
