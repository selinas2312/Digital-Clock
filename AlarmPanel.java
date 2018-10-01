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
  JLabel hLbl, minLbl, alarmLbl;
  JButton btnSet;
  Font font;
  Color color;
  JComponent editor;
  boolean alarmSet;
  int alarmH, alarmMin;
  Thread th;
//  Date currentTime;



  public AlarmPanel(){

    initComponents();
    this.start();

  }

  private void initComponents(){

    this.alarmSet = false;
    //set Background
    this.setBackground(new Color(60, 63, 65));

    //set Font and color
    this.font = new Font("Century Gothic", Font.PLAIN, 60);
    this.color = new Color(95, 100, 103);

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
    this.hSpinner.setFont(font);
    this.minSpinner.setFont(font);

    //set buttons
    btnSet = new JButton("Set Alarm");
    btnSet.setFont(font);
    btnSet.setBackground(color);

    //set Labels
    this.minLbl = new JLabel("Minutes");
    this.hLbl = new JLabel("Hours");
    this.alarmLbl = new JLabel("");
    this.hLbl.setFont(font);
    this.minLbl.setFont(font);
    this.alarmLbl.setFont(font);
    this.minLbl.setForeground(Color.WHITE);
    this.hLbl.setForeground(Color.WHITE);
    this.alarmLbl.setForeground(new Color(12, 216, 201));

    //set panel setLayout
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    c.insets = new Insets(10, 10, 10, 10);
    c.gridx = 0;
    c.gridy = 0;
    this.add(hLbl, c);
    c.gridx = 1;
    this.add(minLbl, c);
    c.ipadx = 100;
    c.ipady = 40;
    c.gridx = 0;
    c.gridy = 1;
    this.add(hSpinner, c);
    c.gridx = 1;
    this.add(minSpinner, c);
    c.gridx = 2;
    this.add(btnSet, c);
    c.gridx = 0;
    c.gridwidth = 2;
    c.gridy = 2;
    this.add(alarmLbl, c);

    //add action listeners for the buttons
    btnSet.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        btnSetActionPerformed(e);
      }
    });

  }//end initComponents

  public void btnSetActionPerformed(ActionEvent e)
  {

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
          JOptionPane.showMessageDialog(alarm, "ALARMMESSAGE");
          System.out.println("ALARM");
          alarmSet = false;
          alarmH = 0;
          alarmMin = 0;
          this.hSpinner.setValue(0);
          this.minSpinner.setValue(0);
          btnSet.setText("Set Alarm");
          this.alarmLbl.setText("");
          this.alarmSet = false;

        }
      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }

}
