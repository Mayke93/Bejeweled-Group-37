package main.java.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
/**
 * Class for displaying start and load buttons on the start screen.
 * @author group37
 *
 */
public class StartScreen extends JPanel {
  

  protected JButton newGame = new JButton("New Game");
  protected JButton loadGame = new JButton("Load Game");
  protected JButton timeMode = new JButton("Time Mode");
  
  private ButtonActionListener actionListener;
  private StatusPanel statusPanel;
  public StartScreen startscreen;
  
  /**
   * constructor to initialise the start screen.
   */
  public StartScreen() {
    startscreen = this;
    statusPanel = new StatusPanel();
    init();
  }
  

  public void init() {
    this.actionListener = new ButtonActionListener(statusPanel);
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
    setOpaque(false);
    
    newGame.addActionListener(actionListener);
    buttonLayout(newGame);
    loadGame.addActionListener(actionListener);
    buttonLayout(loadGame);
    timeMode.addActionListener(actionListener);
    buttonLayout(timeMode);

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(newGame);
    box.add(Box.createVerticalStrut(3));
    box.add(loadGame);
    box.add(Box.createVerticalStrut(3));
    box.add(timeMode);
    box.add(Box.createVerticalGlue());
    
    this.add(box, BorderLayout.WEST); 
    
  }
  
  /**
   * method for the button layout.
   */
  public static void buttonLayout(JButton jb) {
    jb.setMaximumSize(new Dimension(200,75));
    jb.setMinimumSize(new Dimension(200,75));
    jb.setFocusPainted(false);
    jb.setFont(new Font("Euphemia UCAS",Font.PLAIN,22));
    jb.setBackground(Color.WHITE);
    jb.setForeground(new Color(100,100,100));
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
