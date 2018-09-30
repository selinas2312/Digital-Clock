import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class ClockApp extends JPanel implements Runnable{

  int size = 7;
  int hours, minutes, seconds;
  SevenSegment h1, h2, m1, m2, s1, s2;
  Thread th;

  public ClockApp() {
    h1 = new SevenSegment(20, 100, size);
    h2 = new SevenSegment(100, 100, size);
    m1 = new SevenSegment(200, 100, size);
    m2 = new SevenSegment(280, 100, size);
    s1 = new SevenSegment(380, 100, size);
    s2 = new SevenSegment(460, 100, size);

    this.setBackground(new Color(240, 240, 240));
    this.setLayout(new BorderLayout());

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
        // if( hours > 12 )
        // {
        //   hours -= 12;
        // }
        minutes = cal.get( Calendar.MINUTE );
        seconds = cal.get( Calendar.SECOND );

        this.showTime();
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
    g2.setColor(new Color(69, 69, 69));
    g2.fill(new Rectangle2D.Double(178,65,14,14));
    g2.fill(new Rectangle2D.Double(178,135,14,14));
    g2.fill(new Rectangle2D.Double(358, 65, 14, 14));
    g2.fill(new Rectangle2D.Double(358, 135, 14, 14));


  }
}
