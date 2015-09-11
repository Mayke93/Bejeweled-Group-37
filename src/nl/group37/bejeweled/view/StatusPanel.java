package nl.group37.bejeweled.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Class for diplaying the status of the game.
 * @author samuelsital
 *
 */
public class StatusPanel extends JPanel{
  private JLabel scoreLabel = new JLabel("Score: ");
  private JLabel levelLabel = new JLabel("Level:");

  private static final Font font = new Font("Serif", Font.BOLD, 35);

  /**
   * Create labels for displaying the status of the game.
   */
  public StatusPanel() {
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
    setOpaque(false);

    scoreLabel.setFont(font);
    scoreLabel.setForeground(Color.white);

    levelLabel.setFont(font);
    levelLabel.setForeground(Color.white);

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(scoreLabel);
    box.add(levelLabel);
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
}