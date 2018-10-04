import java.util.*;
import java.awt.*;
import java.awt.geom.*;

/**
  * @author Selina Schuh s5124327
  * @version 1.6
  * @since 1.0
  */


public class Segment{
      //class body


  //fields

  /**
    * coordinates for Polygon
    */
  private int x, y, k;

  /**
    *polygon for shape of segment
    */
  private Polygon pol;

  /**
    * represents whether polygon is horizontal or vertical
    */
  private String type;

  /**
    * represents whether the segment is turned on(lit up) or turned Off
    */
  private boolean isOn = false;


  /**
    * the constructor for the class
    * <p>
    * initializes the coordinates for the polygon and the type
    * creates a new Polygon object
    * and depending on the type of the polygon (horizontal or vertical)
    * adds all required points to the polygon
    * </p>
    * @param int x         the x coordinate
    * @param int y         the y coordinate
    * @param int k         the factor of the coordinates
    * @param String type   horizontal or vertical alignment
    */
  public Segment(int x, int y, int k, String type){

    this.x = x;
    this.y = y;
    this.type = type;

    pol = new Polygon();

    if(this.type == "v"){
      pol.addPoint(x, y);
      pol.addPoint(x+k, y+k);
      pol.addPoint(x+2*k, y);
      pol.addPoint(x+2*k, y-8*k);
      pol.addPoint(x+k,y-9*k);
      pol.addPoint(x,y-8*k);
    }
    else if(this.type == "h"){
      pol.addPoint(x,y);
      pol.addPoint(x+k,y+k);
      pol.addPoint(x+5*k,y+k);
      pol.addPoint(x+6*k,y);
      pol.addPoint(x+5*k,y-k);
      pol.addPoint(x+k,y-k);
    }
  }


  /**
    * sets the current start
    * @param boolean bool   true if turning on, false if turning Off
    */
  public void setState(boolean bool){
    this.isOn = bool;
  }

  /**
    * gets the current start
    * @return boolean bool   true if turned on, false if turned Off
    */
  public boolean getState(){
    return this.isOn;
  }

  /**
    * actually changes the color of the segment depending on the current state
    * @param Graphics g
    */
  public void render(Graphics g){
    if(isOn == false){
      g.setColor(new Color(68, 72, 74));
    }
    else{
      g.setColor(new Color(12, 216, 201));
    }

    g.fillPolygon(this.pol);
  }
}//end class
