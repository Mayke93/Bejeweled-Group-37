package main.java.group37.bejeweled.view;

import java.awt.BorderLayout;
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
  //private static final Font font = new Font("Serif", Font.BOLD, 35);
  
  private ButtonActionListener actionListener;
  private StatusPanel statusPanel;
  public StartScreen startscreen;
  
  /**
   * constructor to initialise the start screen.
   */
  public StartScreen() {
    startscreen = this;
    statusPanel = new StatusPanel();
    this.actionListener = new ButtonActionListener(statusPanel);
    setLayout(new BorderLayout());
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
