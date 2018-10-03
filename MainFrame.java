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

public class MainFrame extends JFrame implements Runnable{

  private JPanel alarmPanel, btnPanel, stopwatchPanel, timerPanel, plainPanel;
  private JButton btnAlarm, btnStopWatch, btnTimer;
  private Font btnFont, lblFont;
  Color lightgrey, darkgrey, turquoise, defaultCol;

  public MainFrame(){
    initComponents();
  }

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


  public static void main(String[] args){

    java.awt.EventQueue.invokeLater(new Runnable(){
      @Override
      public void run(){
        new MainFrame().setVisible(true);
    }
  });
}

  @Override
  public void run(){
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
