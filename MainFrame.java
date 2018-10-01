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

public class MainFrame extends JFrame implements Runnable{

  private JFrame frame;
  private JLabel placeHolder, placeHolder2, hLbl, minLbl, alarmLbl;
  private JPanel alarmPanel, btnPanel;
  private JButton btnAlarm, btnStopWatch, btnTimer, btnSet;
  private Font btnFont, lblFont;
  private Color btnColor;
  private List<Integer> hList, minList;
  private SpinnerListModel hModel, minModel;
  private JSpinner hSpinner, minSpinner;
  private JComponent editor;
  private boolean alarmSet;
  private int alarmH, alarmMin;


  public MainFrame(){
    initComponents();
  }

  private void initComponents(){
    this.placeHolder = new JLabel("BBBBBBB");
    this.placeHolder2 = new JLabel("HIIAIUSHDIAUH");
    this.alarmPanel = new JPanel();
    this.btnPanel = new JPanel();
    this.btnAlarm = new JButton("Alarm");
    this.btnStopWatch = new JButton("Stopwatch");
    this.btnTimer = new JButton("Timer");
    this.btnFont = new Font("Century Gothic", Font.PLAIN, 60);
    this.btnColor = new Color(95, 100, 103);

    //set frame properties
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(2000, 1000);
    setLayout(new GridLayout(2, 2));

    this.setBtnPanel();
    this.setAlarmPanel();


    Container contentPane = getContentPane();
    contentPane.setBackground(new Color(60, 63, 65));
    contentPane.add(btnPanel);
    contentPane.add(new ClockPanel());
    contentPane.add(alarmPanel);
    alarmPanel.setVisible(false);


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
  } // end setBtnPanel()

  private void setAlarmPanel(){

    this.alarmSet = false;
    alarmPanel.setBackground(new Color(60, 63, 65));

    //initialize hours and minutes list
    this.hList = new ArrayList<>();
    this.minList = new ArrayList<>();
    for(int i = 0; i <= 23; i++){
      hList.add(i);
    }
    for(int i = 0; i <= 59; i++){
      minList.add(i);
    }

    //set SpinnerListModels
    this.minModel = new SpinnerListModel(this.minList);
    this.hModel = new SpinnerListModel(this.hList);

    //set Spinners
    this.hSpinner = new JSpinner(hModel);
    this.minSpinner = new JSpinner(minModel);

    //set color and font for spinners
    this.editor = minSpinner.getEditor();
    this.setSpinnerLookAndFeel(editor);
    this.editor = hSpinner.getEditor();
    this.setSpinnerLookAndFeel(editor);
    this.hSpinner.setFont(btnFont);
    this.minSpinner.setFont(btnFont);

    //set buttons
    btnSet = new JButton("Set Alarm");
    btnSet.setFont(btnFont);
    btnSet.setBackground(btnColor);

    //set Labels
    this.minLbl = new JLabel("Minutes");
    this.hLbl = new JLabel("Hours");
    this.alarmLbl = new JLabel("");
    this.hLbl.setFont(btnFont);
    this.minLbl.setFont(btnFont);
    this.alarmLbl.setFont(btnFont);
    this.minLbl.setForeground(Color.WHITE);
    this.hLbl.setForeground(Color.WHITE);
    this.alarmLbl.setForeground(new Color(12, 216, 201));

    //set panel setLayout
    alarmPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    c.insets = new Insets(10, 10, 10, 10);
    c.gridx = 0;
    c.gridy = 0;
    alarmPanel.add(hLbl, c);
    c.gridx = 1;
    alarmPanel.add(minLbl, c);
    c.ipadx = 100;
    c.ipady = 40;
    c.gridx = 0;
    c.gridy = 1;
    alarmPanel.add(hSpinner, c);
    c.gridx = 1;
    alarmPanel.add(minSpinner, c);
    c.gridx = 2;
    alarmPanel.add(btnSet, c);
    c.gridx = 0;
    c.gridwidth = 2;
    c.gridy = 2;
    alarmPanel.add(alarmLbl, c);

    //add action listeners for the buttons
    btnSet.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnSetActionPerformed(e);
      }
    });
  }//end set Alarm panel


  private void setSpinnerLookAndFeel(JComponent editor){

    int n = editor.getComponentCount();
    for (int i=0; i<n; i++)
    {
        Component c = editor.getComponent(i);
        if (c instanceof JTextField)
        {
            c.setForeground(Color.WHITE);
            c.setBackground(new Color(95, 100, 103));
        }
    }
  }//end setSpinnerLookAndFeel

  public void btnSetActionPerformed(ActionEvent e){

    if(btnSet.getText().equals("Set Alarm")){

      this.alarmH = (Integer)this.hSpinner.getValue();
      this.alarmMin = (Integer)this.minSpinner.getValue();
      btnSet.setText("Turn off");
      this.alarmLbl.setText("Alarm set");
      this.alarmSet = true;

    }
    else{
      this.hSpinner.setValue(0);
      this.minSpinner.setValue(0);
      btnSet.setText("Set Alarm");
      this.alarmLbl.setText("");
      this.alarmSet = false;
    }
  }//end btnSetActionPerformed

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
}

  @Override
  public void run(){
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
