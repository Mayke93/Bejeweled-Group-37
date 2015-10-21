package main.java.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

public class LayoutScreen extends Layout{
  
  protected JButton back = new JButton("Back");
  protected DefaultListModel<String> buttonList = new DefaultListModel<>();
  protected JList<String> blist = new JList<>(buttonList);
  protected DefaultListModel<String> labelList = new DefaultListModel<>();
  protected JList<String> llist = new JList<>(labelList);
  protected DefaultListModel<String> backList = new DefaultListModel<>();
  protected JList<String> balist = new JList<>(backList);
  
  private JLabel buttonlabel = new JLabel("Button color");
  private JLabel labellabel = new JLabel("label color");
  private JLabel backlabel = new JLabel("label Button color");
  
  /**
   * init layout screen.
   */
  public LayoutScreen() {
    init();
    
  }

  @Override
  public void init() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
    setOpaque(false);
    buttonlabel.setForeground(Color.WHITE);
    labellabel.setForeground(Color.WHITE);
    backlabel.setForeground(Color.WHITE);
    StartScreen.buttonLayout(back);
    back.addActionListener(new LayoutActionListener());
    
    String[] colors = {"Yellow", "Green", "Blue", "Red", "default"};
    for (int i = 0; i < colors.length; i++) {
      buttonList.addElement(colors[i]);
      labelList.addElement(colors[i]);
      backList.addElement(colors[i]);
    }
    
    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(back);
    box.add(Box.createVerticalGlue());

    Box boxR = Box.createVerticalBox();
    boxR.add(Box.createVerticalGlue());
    boxR.add(buttonlabel);
    boxR.add(blist);
    boxR.add(Box.createVerticalStrut(5));
    boxR.add(labellabel);
    boxR.add(llist);
    boxR.add(Box.createVerticalStrut(5));
    boxR.add(backlabel);
    boxR.add(balist);
    boxR.add(Box.createVerticalGlue());
    
    this.add(box, BorderLayout.WEST); 
    this.add(boxR, BorderLayout.EAST); 

    
  }
  
  /**
   * Draw background on the screen.
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    ImageIcon boardImage  = new ImageIcon("src/img/board.png");
    
    graphics.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
  
  }

}
