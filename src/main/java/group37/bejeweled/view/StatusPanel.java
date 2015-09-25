package main.java.group37.bejeweled.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.model.Game;

@SuppressWarnings("serial")
/**
 * Class for diplaying the status of the game.
 * @author group37
 *
 */
public class StatusPanel extends JPanel{
  private JLabel scoreLabel = new JLabel("Score: ");
  private JLabel levelLabel = new JLabel("Level:");
  private JButton button = new JButton("New Game");
  private ButtonActionListener actionListener;

  private static final Font font = new Font("Serif", Font.BOLD, 35);
  public Board board;
  public Game game;
  public Main main;
  public StatusPanel statusPanel;

  /**
   * Create labels for displaying the status of the game.
   */
  public StatusPanel() {
    this.actionListener = new ButtonActionListener(this);
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
    setOpaque(false);

    scoreLabel.setFont(font);
    scoreLabel.setForeground(Color.white);

    levelLabel.setFont(font);
    levelLabel.setForeground(Color.white);
    
    button.addActionListener(actionListener);
    button.setFont(new Font("Serif",Font.PLAIN,25));

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(scoreLabel);
    box.add(levelLabel);
    box.add(button);
    box.add(Box.createVerticalGlue());
    add(box);

    setScore(0);
    setLevel(1);
  }

  /**
   * Gives the game appropriate values as the score and displays
   * it on the screen.
   * @param score a value that increases by finding the right combinations.
   */
  public void setScore(int score) {
    this.scoreLabel.setText("Score: " + Integer.toString(score));
  }

  /**
   * Gives the game an appropriate value as its level and displays
   * it on the screen.
   * @param level the value of the level the player is currently playing.
   */
  public void setLevel(int level) {
    this.levelLabel.setText("Level: " + Integer.toString(level));
  }

  /**
   * Get the scoreLabel
   * @return scoreLabel, the label that contains a string with the score.
   */
  public JLabel getScoreLabel() {
    return scoreLabel;
  }

  /**
   * Get the level label
   * @return levelLabel, the label with a string with the level number.
   */
  public JLabel getLevelLabel() {
    return levelLabel;
  }
  
  public void setGame(Game game) {
    this.game = game;
  }
  
  public void setMain(Main main) {
    this.main = main;
  }
}