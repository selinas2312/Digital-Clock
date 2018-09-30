import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Segment{

  //fields
  private int x, y, k;
  private Polygon pol;
  private String type;
  private boolean isOn = false;

  //Constants
  private final static Color dark = Color.green.darker();
  private final static Color light = Color.green.brighter();

  //Constructor
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

  public void setState(boolean bool){
    this.isOn = bool;
  }

  public boolean getState(){
    return this.isOn;
  }

  public void render(Graphics g){
    if(isOn == false){
      g.setColor(dark);
    }
    else{
      g.setColor(light);
    }

    g.fillPolygon(this.pol);
  //  g.drawPolygon(this.pol);
  }
}
