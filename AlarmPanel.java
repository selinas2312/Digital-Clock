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
  JLabel hLbl, minLbl, panelLabel;
  JButton btnSet, btnCancel, btnDelete;
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
    this.btnCancel = new JButton("Turn Off");
    this.btnDelete = new JButton("Delete");

    this.btnSet.setFont(this.font);
    this.btnSet.setBackground(this.lightgrey);

    this.btnCancel.setFont(this.font);
    this.btnCancel.setBackground(this.lightgrey);

    this.btnDelete.setFont(this.font);
    this.btnDelete.setBackground(this.lightgrey);

    this.btnCancel.setFocusable(false);
    this.btnSet.setFocusable(false);
    this.btnDelete.setFocusable(false);


    //set Labels
    this.minLbl = new JLabel("Minutes");
    this.hLbl = new JLabel("Hours");
    this.panelLabel = new JLabel("Alarm");
    this.txtAlarm = new JTextField("Enter an Alarm Name here...");

    this.txtAlarm.setFont(this.font);
    this.hLbl.setFont(this.font);
    this.minLbl.setFont(this.font);
    this.panelLabel.setFont(new Font("Century Gothic", Font.PLAIN, 70));

    this.txtAlarm.setBackground(this.lightgrey);
    this.txtAlarm.setForeground(Color.WHITE);
    this.minLbl.setForeground(Color.WHITE);
    this.hLbl.setForeground(Color.WHITE);
    this.panelLabel.setForeground(this.turquoise);

    //set panel setLayout
    this.setBackground(new Color(60, 63, 65));
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    //add components
    //first row
    c.insets = new Insets(30, 10, 10, 0);
    c.gridwidth = 3;
    //c.gridx = 1;
    c.gridy = 0;
    this.add(this.panelLabel, c);

    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    c.ipady = 30;
    this.add(this.hLbl, c);

    c.gridx = 2;
    this.add(this.minLbl, c);

    c.ipadx = 200;
    c.ipady = 33;
    c.gridx = 0;
    c.gridy = 2;
    this.add(this.hSpinner, c);

    c.gridx = 2;;
    this.add(this.minSpinner, c);

    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 3;
    c.ipadx = 300;
    this.add(this.txtAlarm, c);

    c.ipadx = 100;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 4;
    this.add(this.btnSet, c);

    c.gridx = 1;
    this.add(this.btnCancel, c);

    c.gridx = 2;
    this.add(this.btnDelete, c);


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

    btnDelete.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnDeleteActionPerformed(e);
      }
    });

  }//end initComponents

  public void btnSetActionPerformed(ActionEvent e)
  {
    this.alarmH = (Integer)this.hSpinner.getValue();
    this.alarmMin = (Integer)this.minSpinner.getValue();
    this.hLbl.setForeground(Color.WHITE);
    this.minLbl.setForeground(Color.WHITE);
    this.txtAlarm.setForeground(Color.WHITE);
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
    this.btnCancel.setForeground(this.turquoise);
    this.btnSet.setForeground(new Color(51, 51, 51));
    this.hLbl.setForeground(Color.RED);
    this.minLbl.setForeground(Color.RED);
    this.txtAlarm.setForeground(Color.RED);
    this.alarmSet = false;

  }//end btnCancelActionPerformed

  public void btnDeleteActionPerformed(ActionEvent e){

    this.hSpinner.setValue(0);
    this.minSpinner.setValue(0);
    this.txtAlarm.setText("");
    this.hLbl.setForeground(Color.WHITE);
    this.minLbl.setForeground(Color.WHITE);
    this.txtAlarm.setForeground(Color.WHITE);
    this.btnCancel.setForeground(new Color(51, 51, 51));
    this.btnSet.setForeground(new Color(51, 51, 51));
    this.alarmSet = false;

  }

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
          JOptionPane alarm = new JOptionPane();
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

          Thread.sleep(1000);

        }
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }

}
