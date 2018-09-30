import java.util.*;
import java.awt.geom.*;
import java.awt.*;

public class SevenSegment{
  private int x, y, k;
  private Segment[] s;

  public SevenSegment(int x, int y, int k){
    this.x = x;
    this.y = y;
    this.k = k;

    //create array of 7 segments and set it'slocation so a number can be represented
    s = new Segment[7];
    s[0] = new Segment(x, y, k, "v");
    s[1] = new Segment(x, y+10*k, k, "v");
    s[2] = new Segment(x+8*k,y, k,"v");
		s[3] = new Segment(x+8*k,y+10*k, k,"v");
		s[4] = new Segment(x+2*k,y-9*k, k,"h");
		s[5] = new Segment(x+2*k,y+k, k,"h");
		s[6] = new Segment(x+2*k,y+11*k, k,"h");
  }

  public void setNumberTo(int num){
    if(num == 0)
      {
        s[0].setState(true);
        s[1].setState(true);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(false);
        s[6].setState(true);
      }
      else if(num == 1)
      {
        s[0].setState(false);
        s[1].setState(false);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(false);
        s[5].setState(false);
        s[6].setState(false);
      }
      else if(num == 2)
      {
        s[0].setState(false);
        s[1].setState(true);
        s[2].setState(true);
        s[3].setState(false);
        s[4].setState(true);
        s[5].setState(true);
        s[6].setState(true);
      }
      else if(num == 3)
      {
        s[0].setState(false);
        s[1].setState(false);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(true);
        s[6].setState(true);
      }
      else if(num == 4)
      {
        s[0].setState(true);
        s[1].setState(false);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(false);
        s[5].setState(true);
        s[6].setState(false);
      }
      else if(num == 5)
      {
        s[0].setState(true);
        s[1].setState(false);
        s[2].setState(false);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(true);
        s[6].setState(true);
      }
      else if(num == 6)
      {
        s[0].setState(true);
        s[1].setState(true);
        s[2].setState(false);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(true);
        s[6].setState(true);
      }
      else if(num == 7)
      {
        s[0].setState(false);
        s[1].setState(false);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(false);
        s[6].setState(false);
      }
      else if(num == 8)
      {
        s[0].setState(true);
        s[1].setState(true);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(true);
        s[6].setState(true);
      }
      else if(num == 9)
      {
        s[0].setState(true);
        s[1].setState(false);
        s[2].setState(true);
        s[3].setState(true);
        s[4].setState(true);
        s[5].setState(true);
        s[6].setState(true);
      }
  }//end set number to

  public void paintNumber(Graphics2D g2)
  {
    for(int i = 0; i < 7; i++)
    {
      s[i].render(g2);
    }
  }

  public void unpaintNumber(){
    for(int i = 0; i < 7; i++)
    {
      s[i].setState(false);
    }
  }
}
