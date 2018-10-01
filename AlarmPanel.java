import java.util.*;
//import java.awt.*;
import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;

public class AlarmPanel extends JPanel{

  List<Integer> hours, minutes;
  SpinnerListModel hModel, minModel;
  JSpinner hSpinner, minSpinner;
  JLabel hLbl, minLbl;
  Font font;
  Color color;
  public AlarmPanel(){

    //set Background
    this.setBackground(new Color(60, 63, 65));


    //initialize hours and minutes list
    this.hours = new ArrayList<>();
    this.minutes = new ArrayList<>();
    for(int i = 0; i <= 23; i++){
      hours.add(i);
    }
    for(int i = 0; i <= 59; i++){
      minutes.add(i);
    }

    //set SpinnerListModels
    this.minModel = new SpinnerListModel(this.minutes);
    this.hModel = new SpinnerListModel(this.hours);

    //set Spinners
    this.hSpinner = new JSpinner(hModel);
    this.minSpinner = new JSpinner(minModel);

    //set Font and color
    this.font = new Font("Century Gothic", Font.PLAIN, 60);
    this.color = new Color(95, 100, 103);
    this.hSpinner.setFont(font);
    this.minSpinner.setFont(font);
    // this.hSpinner.setBackground(color);
    // this.minSpinner.setBackground(color);

    //set Labels
    this.minLbl = new JLabel("Minutes");
    this.hLbl = new JLabel("Hours");
    this.hLbl.setFont(font);
    this.minLbl.setFont(font);

    //set panel setLayout
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


  //  c.insets = new Insets(0, 10, 10, 10);
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


  }

}
