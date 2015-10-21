package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Level;
import main.java.group37.bejeweled.model.Score;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel implements Observer {
  
  private JLabel scoreLabel = new JLabel("Score: ");
  private JLabel levelLabel = new JLabel("Level:");
  protected JButton button = new JButton("Quit");
  protected JButton saveGame = new JButton("Save Game");
  protected JButton hint = new JButton("Hint");
  private ButtonActionListener actionListener;

  public Game game;
  public Main main;
  
  /**
   * Create labels for displaying the status of the game.
   */
  public Panel() {
    this.actionListener = new ButtonActionListener(this);
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
    setOpaque(false);

    scoreLabel.setFont(new Font("Euphemia UCAS",Font.PLAIN,30));
    scoreLabel.setForeground(new Color(192,192,192));

    levelLabel.setFont(new Font("Euphemia UCAS",Font.PLAIN,30));
    levelLabel.setForeground(new Color(192,192,192));
    
    button.addActionListener(actionListener);
    StartScreen.buttonLayout(button);  
    saveGame.addActionListener(actionListener);
    StartScreen.buttonLayout(saveGame);
    hint.addActionListener(actionListener);
    StartScreen.buttonLayout(hint);

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(scoreLabel);
    box.add(levelLabel);
    box.add(Box.createVerticalStrut(5));
    box.add(button);
    box.add(Box.createVerticalStrut(3));
    box.add(saveGame);
    box.add(Box.createVerticalStrut(3));
    box.add(hint);
    box.add(Box.createVerticalGlue());
    add(box);

    setScore(0);
    setLevel(1);
  }
  
  
  public void setGame(Game game) {
    this.game = game;
  }

  public void setMain(Main main) {
    this.main = main;
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

  /**
   * update the score label if there is a change in the score.
   */
  public void update(Observable op, Object arg) {
    if (arg instanceof Score) {
      this.setScore(((Score) arg).getScore());        
    }
    if (arg instanceof Level) {
      this.setLevel((int) ((Level) arg).getLevel());        
    }
  }
  
}
