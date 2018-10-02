import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class ClockPanel extends JPanel implements Runnable{

  int size = 7;
  int hours, minutes, seconds;
  SevenSegment h1, h2, m1, m2, s1, s2;
  Thread th;
  JLabel dayLbl;
  Font font;
  GridBagConstraints c;
  Calendar calendar;
  String weekday, month, year, day;
  Date date;

  public ClockPanel() {

    h1 = new SevenSegment(20, 100, size);
    h2 = new SevenSegment(100, 100, size);
    m1 = new SevenSegment(200, 100, size);
    m2 = new SevenSegment(280, 100, size);
    s1 = new SevenSegment(380, 100, size);
    s2 = new SevenSegment(460, 100, size);

    // this.calendar  = Calendar.getInstance();
    // this.year = Integer.toString(calendar.get(Calendar.YEAR));
    // this.month = Integer.toString(calendar.get(Calendar.MONTH));
    // this.date = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    // this.day = this.getDay(calendar.get(Calendar.DAY_OF_WEEK));
    // this.dateLbl = new JLabel(this.date + " " + this.month + " " + this.year);
    // this.dayLbl = new JLabel(this.day + ",");
    this.font = new Font("Century Gothic", Font.PLAIN, 60);

    this.date = new Date();
    this.weekday = new SimpleDateFormat("EEE, MMM d, YYYY").format(this.date);
    this.dayLbl = new JLabel(this.weekday);

    dayLbl.setForeground(new Color(12, 216, 201));
    dayLbl.setFont(this.font);

    this.setBackground(new Color(60, 63, 65));

    this.setLayout(new GridBagLayout());
    this.c = new GridBagConstraints();
    c.insets = new Insets(80, 0, 0, 250);
    c.gridx = 0;
    c.gridy = 1;
    this.add(dayLbl, c);

    this.start();
  }

  public void start(){
    if(th == null)
    {
      th = new Thread(this);
      th.start();
    }
  }

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
    g2.fill(new Rectangle2D.Double(178,65,14,14));
    g2.fill(new Rectangle2D.Double(178,135,14,14));
    g2.fill(new Rectangle2D.Double(358, 65, 14, 14));
    g2.fill(new Rectangle2D.Double(358, 135, 14, 14));


  }

  private String getDay(int day){

    String dayString;
    switch(day){
      case 1:
          dayString = "Sunday";
          break;
      case 2:
          dayString = "Monday";
          break;
      case 3:
          dayString = "Tuesday";
          break;
      case 4:
          dayString = "Wednesday";
          break;
      case 5:
          dayString = "Thursday";
          break;
      case 6:
          dayString = "Friday";
          break;
      case 7:
          dayString = "Saturday";
          break;

      default:
          dayString = "Date currently not available.";

    }
    return dayString;

  }
}
