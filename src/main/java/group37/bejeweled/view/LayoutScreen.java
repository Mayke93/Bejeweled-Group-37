package main.java.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LayoutScreen extends Layout{
  
  protected JButton back = new JButton("Back");
  
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
    
    StartScreen.buttonLayout(back);
    back.addActionListener(new LayoutActionListener());

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(back);
    box.add(Box.createVerticalGlue());
    
    this.add(box, BorderLayout.WEST);   
    
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
