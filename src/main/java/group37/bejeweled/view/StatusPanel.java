package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Logger;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
/**
 * Class for diplaying the status of the game.
 * @author group37
 *
 */
public class StatusPanel extends Panel implements Observer{
  protected JButton button = new JButton("Quit");
  protected JButton saveGame = new JButton("Save Game");
  protected JButton hint = new JButton("Hint");

  public Game game;
  public Main main;
  

  /**
   * Create labels for displaying the status of the game.
   */
  public StatusPanel() {
    
  }

  /**
   * This shows the text that will end the game.
   * 
   */
  public void endGame() {
    Logger.log("End Game");

    JLabel label = new JLabel("<html>No More Combinations!<br>Press Quit</html>", JLabel.CENTER);
    label.setForeground(Color.WHITE);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setFont(new Font("Euphemia UCAS",Font.PLAIN,40)); 
    label.setOpaque(true);
    label.setBackground(Color.BLACK);
    setAlignmentX(Component.CENTER_ALIGNMENT);
    add(label);
    saveGame.setVisible(false);
    
    this.main.repaint();
    this.repaint();

    Launcher.launcher.getContentPane().validate();
    Launcher.launcher.getContentPane().repaint();
  }

}