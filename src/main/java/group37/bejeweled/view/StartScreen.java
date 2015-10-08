package main.java.group37.bejeweled.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import main.java.group37.bejeweled.Launcher;

@SuppressWarnings("serial")
/**
 * Class for displaying start and load buttons on the start screen.
 * @author group37
 *
 */
public class StartScreen extends JPanel {
  

  protected JButton newGame = new JButton("New Game");
  protected JButton loadGame = new JButton("Load Game");
  private JList lgList = new JList();

  private static final Font font = new Font("Serif", Font.BOLD, 35);
  
  private ButtonActionListener actionListener;
  private StatusPanel statusPanel;
  private Launcher launcher;
  public StartScreen startscreen;
  
  /**
   * constructor to initialise the start screen.
   */
  public StartScreen(Launcher launch) {
    launcher = launch;
    startscreen = this;
    statusPanel = new StatusPanel(launcher);
    this.actionListener = new ButtonActionListener(statusPanel, this, launcher);
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
    setOpaque(false);
    
    newGame.addActionListener(actionListener);
    newGame.setFont(new Font("Serif",Font.PLAIN,25));
    
    loadGame.addActionListener(actionListener);
    loadGame.setFont(new Font("Serif",Font.PLAIN,25));

    Box box = Box.createVerticalBox();
    box.add(Box.createVerticalGlue());
    box.add(newGame);
    box.add(loadGame);
    box.add(Box.createVerticalGlue());
    this.add(box);
    
  }

}
