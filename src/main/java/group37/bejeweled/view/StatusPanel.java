package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.model.Game;

import java.util.Observer;

import javax.swing.JButton;

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
    timeLabel.setVisible(false);
  }

}