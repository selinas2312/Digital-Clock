import java.util.*;
import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Component;
import java.text.*;
import java.awt.event.*;
import java.text.*;
import java.math.*;

/**
  * @author Selina Schuh s5124327
  * @version 1.4
  * @since 1.0
  */

public class MainFrame extends JFrame implements Runnable{
      //class body


  /**
    * the custom panels that represent the components the main frame is made up of
    */
  private JPanel alarmPanel, btnPanel, stopwatchPanel, timerPanel, plainPanel;

  /**
    * the buttons that are part of the button panel
    */
  private JButton btnAlarm, btnStopWatch, btnTimer;

  /**
    * the fonts for labels and buttons
    */
  private Font btnFont, lblFont;

  /**
    * the different colors for the components of the main frame
    */
  Color lightgrey, darkgrey, turquoise, defaultCol;


  /**
    * The constructor of the class
    * <p>
    * calls the initComponents() method of the class to initalize the components
    * </p>
    */
  public MainFrame(){
    initComponents();
  }


  /**
    * Initialize the GUI componenets of this class
    * <p>
    * Initializes all GUI components and sets their color font and location
    * adds them to the main frame
    * sets the properties of the main frame
    * also initializes ActionListeners for components
    * </p>
    */
  private void initComponents(){

    this.alarmPanel = new AlarmPanel();
    this.stopwatchPanel = new StopwatchPanel();
    this.timerPanel = new TimerPanel();
    this.btnPanel = new JPanel();
    this.plainPanel = new JPanel();

    this.btnAlarm = new JButton("Alarm");
    this.btnStopWatch = new JButton("Stopwatch");
    this.btnTimer = new JButton("Timer");

    this.btnFont = new Font("Century Gothic", Font.PLAIN, 60);

    this.lightgrey = new Color(95, 100, 103);
    this.darkgrey = new Color(60, 63, 65);
    this.turquoise = new Color(12, 216, 201);
    this.defaultCol = new Color(51, 51, 51);

    //set frame properties
    setTitle("Digital Clock");
    //setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(3, 2, 30, 10));

    this.setBtnPanel();
    this.plainPanel.setBackground(this.darkgrey);

    Container contentPane = getContentPane();
    contentPane.setBackground(this.darkgrey);
    contentPane.add(new ClockPanel());
    contentPane.add(alarmPanel);
    contentPane.add(btnPanel);
    contentPane.add(timerPanel);
    contentPane.add(this.plainPanel);
    contentPane.add(stopwatchPanel);
    this.timerPanel.setVisible(false);
    alarmPanel.setVisible(false);
    stopwatchPanel.setVisible(false);

    pack();


  }

  /**
    * sets the colors and fonts for the buttonpanel,
    * sets the layout and location for the buttonpanel,
    * and adds actionlisteners for each button
    * and adds the button to the button panel
    */
  private void setBtnPanel(){
    //set btnPanel components
    btnPanel.setBackground(this.darkgrey);
    btnPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    //add action listeners for the buttons
    btnAlarm.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnAlarmActionPerformed(e);
      }
    });
    btnTimer.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnTimerActionPerformed(e);
      }
    });
    btnStopWatch.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnStopWatchActionPerformed(e);
      }
    });

    //set button fonds
    btnAlarm.setFont(btnFont);
    btnTimer.setFont(btnFont);
    btnStopWatch.setFont(btnFont);

    //remove little white box around button text when clicked
    btnAlarm.setFocusable(false);
    btnTimer.setFocusable(false);
    btnStopWatch.setFocusable(false);

    //set button Color
    btnAlarm.setBackground(this.lightgrey);
    btnTimer.setBackground(this.lightgrey);
    btnStopWatch.setBackground(this.lightgrey);


    //set layout for btnPanel
    c.ipadx = 352;
    c.ipady = 40;
    c.insets = new Insets(10, 0, 0, 100);
    c.gridx = 0;
    c.gridy = 0;
    btnPanel.add(btnAlarm, c);

    c.ipadx = 369;
    c.ipady = 40;
    c.gridy = 1;
    btnPanel.add(btnTimer, c);

    c.ipadx = 200;
    c.ipady = 40;
    c.gridy = 2;
    btnPanel.add(btnStopWatch, c);
  } // end setBtnPanel()



  /**
    * changes the visibility of the alarmpanel
    * <p>
    * changes the visibility of the alarmpanel depending on whether it is currently
    * visible or not
    * and changes the colors of the button texts accordingly
    * </p>
    * @param ActionEvent e
    */
  public void btnAlarmActionPerformed(ActionEvent e)
  {
    if(alarmPanel.isVisible() == false){
      alarmPanel.setVisible(true);
      this.btnAlarm.setForeground(this.turquoise);
      this.btnTimer.setForeground(this.defaultCol);
      this.btnStopWatch.setForeground(this.defaultCol);
    }
    else{
      alarmPanel.setVisible(false);
      this.btnAlarm.setForeground(this.defaultCol);
    }
  }

  /**
    * changes the visibility of the timer panel
    * <p>
    * changes the visibility of the timer panel depending on whether it is currently
    * visible or not
    * and changes the colors of the button texts accordingly
    * </p>
    * @param ActionEvent e
    */
  public void btnTimerActionPerformed(ActionEvent e)
  {
    if(this.timerPanel.isVisible() == false){
    this.timerPanel.setVisible(true);

      this.btnTimer.setForeground(this.turquoise);
      this.btnAlarm.setForeground(this.defaultCol);
      this.btnStopWatch.setForeground(this.defaultCol);
    }
    else{
      this.timerPanel.setVisible(false);
      this.btnTimer.setForeground(this.defaultCol);
    }

  }

  /**
    * changes the visibility of the stopwatch panel
    * <p>
    * changes the visibility of the stopwatch panel depending on whether it is currently
    * visible or not
    * and changes the colors of the button texts accordingly
    * </p>
    * @param ActionEvent e
    */
  public void btnStopWatchActionPerformed(ActionEvent e)
  {
    //this.alarmPanel.setVisible(false);
    if(this.stopwatchPanel.isVisible() == false){

      this.stopwatchPanel.setVisible(true);
      this.btnStopWatch.setForeground(this.turquoise);
      this.btnTimer.setForeground(this.defaultCol);
      this.btnAlarm.setForeground(this.defaultCol);
    } else {
      this.stopwatchPanel.setVisible(false);
      this.btnStopWatch.setForeground(this.defaultCol);
    }

  }


  /**
    * the main method of the application
    * <p>
    * the main methid of the application which initializes an new MainFrame object
    * and sets its visibility to true
    */
  public static void main(String[] args){

    java.awt.EventQueue.invokeLater(new Runnable(){
      @Override
      public void run(){
        new MainFrame().setVisible(true);
    }
  });
}

  /**
    * overrides the run method of the Runnable interface
    */
  @Override
  public void run(){
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
