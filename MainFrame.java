

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class MainFrame{
  public static void main(String[] args){
    JFrame frame = new JFrame("Please WORK");
    frame.setLocationRelativeTo(null);
    frame.setSize(900, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new ClockApp(), BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
