package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Level;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.Score;

import java.awt.Color;
import java.awt.Component;
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
  protected JButton quit = new JButton("Quit");
  protected JButton saveGame = new JButton("Save Game");
  protected JButton hint = new JButton("Hint");
  private ButtonActionListener actionListener;
  protected JLabel timeLabel = new JLabel("Time: 60");
  protected static boolean gameover;

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
    
    timeLabel.setFont(new Font("Euphemia UCAS",Font.PLAIN,30));
    timeLabel.setForeground(new Color(192,192,192));
    
    quit.addActionListener(actionListener);
    StartScreen.buttonLayout(quit);  
    saveGame.addActionListener(actionListener);
    StartScreen.buttonLayout(saveGame);
    hint.addActionListener(actionListener);
    StartScreen.buttonLayout(hint);

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(timeLabel);
    
    box.add(scoreLabel);
    box.add(levelLabel);
    box.add(Box.createVerticalStrut(5));
    box.add(quit);
    box.add(Box.createVerticalStrut(3));
    box.add(saveGame);
    box.add(Box.createVerticalStrut(3));
    box.add(hint);
    box.add(Box.createVerticalGlue());
    add(box);

    Panel.gameover = false;
    
    setScore(0);
    setLevel(1);
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
  
  public static boolean getGameOver() {
    return gameover;
  }
  
  
}
