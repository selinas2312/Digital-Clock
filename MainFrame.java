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

  private JFrame frame;
  private JLabel placeHolder, placeHolder2;
  private JPanel alarmPanel, btnPanel, stopwatchPanel;
  private JButton btnAlarm, btnStopWatch, btnTimer;
  private Font btnFont, lblFont;
  private Color btnColor;

  public MainFrame(){
    initComponents();
  }

  private void initComponents(){
    this.placeHolder = new JLabel("BBBBBBB");
    this.placeHolder2 = new JLabel("HIIAIUSHDIAUH");
    this.alarmPanel = new AlarmPanel();
    this.stopwatchPanel = new StopwatchPanel();
    this.btnPanel = new JPanel();
    this.btnAlarm = new JButton("Alarm");
    this.btnStopWatch = new JButton("Stopwatch");
    this.btnTimer = new JButton("Timer");
    this.btnFont = new Font("Century Gothic", Font.PLAIN, 60);
    this.btnColor = new Color(95, 100, 103);

    //set frame properties
    setTitle("Digital Clock");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  //  setSize(2000, 1000);
    setLayout(new GridLayout(2, 2));

    this.setBtnPanel();

    Container contentPane = getContentPane();
    contentPane.setBackground(new Color(60, 63, 65));
    contentPane.add(btnPanel);
    contentPane.add(new ClockPanel());
    contentPane.add(alarmPanel);
    contentPane.add(stopwatchPanel);
    alarmPanel.setVisible(false);
    stopwatchPanel.setVisible(false);

    pack();


  }

  private void setBtnPanel(){
    //set btnPanel components
    btnPanel.setBackground(new Color(60, 63, 65));
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
    btnAlarm.setBackground(btnColor);
    btnTimer.setBackground(btnColor);
    btnStopWatch.setBackground(btnColor);


    //set layout for btnPanel
    c.ipadx = 352;
    c.ipady = 40;
    c.insets = new Insets(10, 10, 0, 100);
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
      this.btnAlarm.setForeground(new Color(12, 216, 201));
      this.btnTimer.setForeground(new Color(51, 51, 51));
      this.btnStopWatch.setForeground(new Color(51, 51, 51));
    }
    else{
      alarmPanel.setVisible(false);
      this.btnAlarm.setForeground(new Color(51, 51, 51));
    }
  }
  public void btnTimerActionPerformed(ActionEvent e)
  {
    this.alarmPanel.setVisible(false);
    this.stopwatchPanel.setVisible(false);

    this.btnTimer.setForeground(new Color(12, 216, 201));
    this.btnAlarm.setForeground(new Color(51, 51, 51));
    this.btnStopWatch.setForeground(new Color(51, 51, 51));

  }
  public void btnStopWatchActionPerformed(ActionEvent e)
  {
    //this.alarmPanel.setVisible(false);
    if(this.stopwatchPanel.isVisible() == false){
      this.stopwatchPanel.setVisible(true);
      this.btnStopWatch.setForeground(new Color(12, 216, 201));
      this.btnTimer.setForeground(new Color(51, 51, 51));
      this.btnAlarm.setForeground(new Color(51, 51, 51));
    } else {
      this.stopwatchPanel.setVisible(false);
      this.btnStopWatch.setForeground(new Color(51, 51, 51));
      this.btnTimer.setForeground(new Color(51, 51, 51));
      this.btnAlarm.setForeground(new Color(51, 51, 51));
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
