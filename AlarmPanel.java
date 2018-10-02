import java.util.*;
//import java.awt.*;
import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.text.*;
import java.awt.event.*;
import java.text.*;

public class AlarmPanel extends JPanel implements Runnable{

  List<Integer> hList, minList;
  SpinnerListModel hModel, minModel;
  JSpinner hSpinner, minSpinner;
  JLabel hLbl, minLbl, alarmNameLbl;
  JButton btnSet, btnCancel;
  JComponent editor;
  JTextField txtAlarm;
  Font font;
  Color lightgrey, turquoise;
  boolean alarmSet;
  int alarmH, alarmMin;
  Thread th;
  String alarmName;
//  Date currentTime;



  public AlarmPanel(){

    initComponents();
    this.start();

  }

  private void initComponents(){

    this.alarmSet = false;

    //set Font and color
    this.font = new Font("Century Gothic", Font.PLAIN, 40);
    this.lightgrey = new Color(95, 100, 103);
    this.turquoise = new Color(12, 216, 201);

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
    this.hSpinner.setFont(this.font);
    this.minSpinner.setFont(this.font);

    //set buttons
    this.btnSet = new JButton("Set Alarm");
    this.btnCancel = new JButton("Cancel Alarm");

    this.btnSet.setFont(this.font);
    this.btnSet.setBackground(this.lightgrey);

    this.btnCancel.setFont(this.font);
    this.btnCancel.setBackground(this.lightgrey);

    this.btnCancel.setFocusable(false);
    this.btnSet.setFocusable(false);


    //set Labels
    this.minLbl = new JLabel("Minutes");
    this.hLbl = new JLabel("Hours");
    this.alarmNameLbl = new JLabel("Alarm Name: ");
    this.txtAlarm = new JTextField("Enter an Alarm Name here...");

    this.txtAlarm.setFont(font);
    this.hLbl.setFont(font);
    this.minLbl.setFont(font);
    this.alarmNameLbl.setFont(font);

    this.txtAlarm.setBackground(this.lightgrey);
    this.txtAlarm.setForeground(Color.WHITE);
    this.minLbl.setForeground(Color.WHITE);
    this.hLbl.setForeground(Color.WHITE);
    this.alarmNameLbl.setForeground(Color.WHITE);

    //set panel setLayout
    this.setBackground(new Color(60, 63, 65));
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    //add components
    //first row
    c.insets = new Insets(30, 10, 10, 10);
    c.gridx = 0;
    c.gridy = 1;
    c.ipady = 30;
    c.gridwidth = 2;
    this.add(txtAlarm, c);

    //second row
    c.gridwidth = 1;
    c.ipady = 0;
    c.gridx = 2;
    c.gridy = 2;
    this.add(hLbl, c);

    c.gridx = 3;
    this.add(minLbl, c);

    //3rd row
    c.ipadx = 100;
    c.ipady = 30;
    c.gridx = 2;
    c.gridy = 3;
    this.add(hSpinner, c);

    c.gridx = 3;
    this.add(minSpinner, c);

    c.ipadx = 185;
    c.gridx = 0;
    c.gridy = 2;
    this.add(btnSet, c);

    c.ipadx = 100;
    c.gridy = 3;
    this.add(btnCancel, c);

    //add action listeners for the buttons
    btnSet.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnSetActionPerformed(e);
      }
    });

    btnCancel.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnCancelActionPerformed(e);
      }
    });

  }//end initComponents

  public void btnSetActionPerformed(ActionEvent e)
  {
    this.alarmH = (Integer)this.hSpinner.getValue();
    this.alarmMin = (Integer)this.minSpinner.getValue();
    this.btnSet.setForeground(this.turquoise);
    this.btnCancel.setForeground(new Color(51, 51, 51));
    if(this.txtAlarm.getText() != null){
      this.alarmName = this.txtAlarm.getText();
    }
    else{
      this.alarmName = "";
    }
    this.alarmSet = true;

  }

  public void btnCancelActionPerformed(ActionEvent e)
  {
    this.hSpinner.setValue(0);
    this.minSpinner.setValue(0);
    this.txtAlarm.setText("");
    this.btnCancel.setForeground(this.turquoise);
    this.btnSet.setForeground(new Color(51, 51, 51));
    this.alarmSet = false;

  }//end btnCancelActionPerformed

  private void setSpinnerLookAndFeel(JComponent editor){

    int n = editor.getComponentCount();
    for (int i=0; i<n; i++)
    {
        Component c = editor.getComponent(i);
        if (c instanceof JTextField)
        {
            c.setForeground(Color.WHITE);
            c.setBackground(this.lightgrey);
        }
    }
  }//end setSpinnerLookAndFeel

  public void start(){
    if(th == null)
    {
      th = new Thread(this);
      th.start();
    }
  }

  @Override
  public void run(){
    while(th != null){
      try{
        Calendar currentTime = Calendar.getInstance();

        if(alarmSet == true && alarmH == currentTime.get(Calendar.HOUR_OF_DAY) && alarmMin == currentTime.get(Calendar.MINUTE)){

          //add Dialog to Display Alarm.\
          Object[] option = {"Turn off"};
          JOptionPane alarm = new JOptionPane("ALARM!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, option);
          JOptionPane.showMessageDialog(alarm, this.alarmName, "ALARM!", JOptionPane.WARNING_MESSAGE);

          //reset alarm
          this.alarmSet = false;
          this.alarmH = 0;
          this.alarmMin = 0;
          this.hSpinner.setValue(0);
          this.minSpinner.setValue(0);
          this.alarmSet = false;
          this.txtAlarm.setText("Enter an Alarm Name here...");;
          this.btnSet.setForeground(new Color(51, 51, 51));

          th.sleep(1000);

        }
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }

}
