

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.awt.EventQueue;

public class MainFrame extends JFrame implements Runnable{

  private JFrame frame;
  private JLabel placeHolder, placeHolder2;
  private JPanel alarmPanel, btnPanel;
  private JButton btnAlarm, btnStopWatch, btnTimer;
  private Font btnFont;
  private Color btnColor;

  public MainFrame(){
    initComponents();
  }

  private void initComponents(){
    placeHolder = new JLabel("BBBBBBB");
    placeHolder2 = new JLabel("HIIAIUSHDIAUH");
    alarmPanel = new AlarmPanel();
    btnPanel = new JPanel();
    btnAlarm = new JButton("Alarm");
    btnStopWatch = new JButton("Stopwatch");
    btnTimer = new JButton("Timer");
    btnFont = new Font("Century Gothic", Font.PLAIN, 60);
    btnColor = new Color(95, 100, 103);

    //set frame properties
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(2000, 1000);
    setLayout(new GridLayout(2, 2));

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

    //set button Color
    btnAlarm.setBackground(btnColor);
    btnTimer.setBackground(btnColor);
    btnStopWatch.setBackground(btnColor);


    //set layout for btnPanel
    c.ipadx = 352;
    c.ipady = 40;
    c.insets = new Insets(10, 0, 0, 0);
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


    Container contentPane = getContentPane();
    contentPane.setBackground(new Color(60, 63, 65));
    contentPane.add(btnPanel);
    contentPane.add(new ClockPanel());
    contentPane.add(alarmPanel);
    alarmPanel.setVisible(false);


  }

  public void btnAlarmActionPerformed(ActionEvent e)
  {
    if(alarmPanel.isVisible() == false){
      alarmPanel.setVisible(true);
    }
    else{
      alarmPanel.setVisible(false);
    }
  }
  public void btnTimerActionPerformed(ActionEvent e)
  {

  }
  public void btnStopWatchActionPerformed(ActionEvent e)
  {

  }
  public static void main(String[] args){

    java.awt.EventQueue.invokeLater(new Runnable(){
      @Override
      public void run(){
        new MainFrame().setVisible(true);
      }
    });
    // //initialize components used in mainframe
    // JFrame frame = new JFrame("Please WORK");
    // JLabel placeHolder = new JLabel("BBBBBBB");
    // JLabel placeHolder2 = new JLabel("HIIAIUSHDIAUH");
    // JPanel alarmPanel = new AlarmPanel();
    // JPanel btnPanel = new JPanel();
    // JButton btnAlarm = new JButton("Alarm");
    // JButton btnStopWatch = new JButton("Stopwatch");
    // JButton btnTimer = new JButton("Timer");
    // Font btnFont = new Font("Century Gothic", Font.PLAIN, 60);
    // Color btnColor = new Color(95, 100, 103);

    // //set main frame properties
    // frame.setLocationRelativeTo(null);
    // frame.setSize(2000, 1000);
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setLayout(new GridLayout(2, 2));

    // //set btnPanel components
    // btnPanel.setBackground(new Color(60, 63, 65));
    // btnPanel.setLayout(new GridBagLayout());
    // GridBagConstraints c = new GridBagConstraints();

    // //set Actioncommands
    // btnAlarm.setActionCommand("Alarm");
    // btnTimer.setActionCommand("Timer");
    // btnStopWatch.setActionCommand("Stopwatch");

    // //add action listeners for the buttons
    // btnAlarm.addActionListener(new ActionListener(){
    //   public void actionPerformed(ActionEvent e){
    //     btnAlarmActionPerformed(e);
    //   }
    // });
    // btnTimer.addActionListener(new ActionListener(){
    //   public void actionPerformed(ActionEvent e){
    //     btnTimerActionPerformed(e);
    //   }
    // });
    // btnStopWatch.addActionListener(new ActionListener(){
    //   public void actionPerformed(ActionEvent e){
    //     btnStopwatchActionPerformed(e, frame);
    //   }
    // });


    // //set button fonds
    // btnAlarm.setFont(btnFont);
    // btnTimer.setFont(btnFont);
    // btnStopWatch.setFont(btnFont);
    //
    // //set button Color
    // btnAlarm.setBackground(btnColor);
    // btnTimer.setBackground(btnColor);
    // btnStopWatch.setBackground(btnColor);
    //
    // c.ipadx = 352;
    // c.ipady = 40;
    // c.insets = new Insets(10, 0, 0, 0);
    // c.gridx = 0;
    // c.gridy = 0;
    // btnPanel.add(btnAlarm, c);
    //
    // c.ipadx = 369;
    // c.ipady = 40;
    // c.gridy = 1;
    // btnPanel.add(btnTimer, c);
    //
    // c.ipadx = 200;
    // c.ipady = 40;
    // c.gridy = 2;
    // btnPanel.add(btnStopWatch, c);

    // add components
    // Container contentPane = frame.getContentPane();
    // contentPane.setBackground(new Color(60, 63, 65));
    // contentPane.add(btnPanel);
    // contentPane.add(new ClockPanel());
    // contentPane.add(alarmPanel);


    //set frame visible
    // frame.setVisible(true);

  }

  @Override
  public void run(){
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
