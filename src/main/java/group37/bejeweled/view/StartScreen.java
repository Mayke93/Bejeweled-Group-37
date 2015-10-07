package main.java.group37.bejeweled.view;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;

public class StartScreen {
  
  private JButton newGame = new JButton("New Game");
  private JButton loadGame = new JButton("Load Game");
  private JList lgList = new JList();
  
  private ButtonActionListener actionListener;

  private static final Font font = new Font("Serif", Font.BOLD, 35);
  
  private StatusPanel statusPanel;
  
  /**
   * 
   */
  public StartScreen() {

    
  }

}
