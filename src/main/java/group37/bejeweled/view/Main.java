package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.BoardFactory;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SavedGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.border.Border;
//import javax.swing.border.LineBorder;


@SuppressWarnings("serial")

/**
 * Class that is responsible for the frames, images and text in the GUI.
 * @author group37
 */
public class Main extends JPanel {

  public static final int SIZE = 8; //Board size is 8x8
  public static final Point LOCATION = new Point(241,40);
  public static final int SPACE_X = 65;
  public static final int SPACE_Y = 65;
  private static ImageIcon boardImage  = new ImageIcon("src/img/board.png");
  private static ImageIcon focusImage = new ImageIcon("src/img/focus.png");
  private Point focus = null;

  private final JFrame frame;
  public Animation animations;
  private SavedGame saveGame;
  //private BoardFactory boardfactory;

  //Panel with score label and level label
  private StatusPanel panel;

  private Game game;

  /**
   * Initialize the board and create the mouse event listeners.
   * @param frame JFrame of the game
   * @param panel JPanel with the labels to display the status of the game
   */
  public Main(final JFrame frame,StatusPanel panel) {
    this.frame = frame;
    this.panel = panel;
    game = new Game(this,panel);
    animations = new Animation(game,this);
    setOpaque(true);
    Logger.log("# Start new game");
    this.addMouseListener(new MouseListener(this));
    this.addMouseMotionListener(new MouseMotionListener(this));
    
    SavedGame.getInstance().setGame(game);
    SavedGame.getInstance().loadGame();
    panel.setLevel(game.getLevel());
    panel.setScore(game.getScore());
    System.out.println("level:" +game.getLevel());
    //boardfactory = new BoardFactory(game);
  }

  /**
   * Gets the game.
   * @return game, the game.
   */
  public Game getGame() {
    return game;
  }

  /**
   * swap the tiles in the list.
   * @param swapTiles the list of (two) tiles that should be swapped.
   */
  public void swapTiles(List<Tile> swapTiles) {
    animations.setType(Animation.Type.SWAP);
    animations.swap(swapTiles.get(0), swapTiles.get(1));
    Logger.log("Swap tiles: " + swapTiles.get(0).getLoc() + ", " + swapTiles.get(1).getLoc());
  }

  /**
   * Check if index x is within the boundaries of the board.
   * @param x index to check
   * @return true iff x is inside the boundaries.
   */
  public static boolean withinBoundaries(int ix) {
    return (ix >= 0 && ix < SIZE);
  }

  /**
   * Get the index of the focused jewel based on the coordinated of the mouse event.
   * @param loc location of the mouse event
   */
  public void setFocus(Point loc) {
    int ix = loc.x * SPACE_X + LOCATION.x;
    int iy = loc.y * SPACE_Y + LOCATION.y;
    focus = new Point(ix,iy);
    repaint();
  }

  /**
   * Get the col and row index based on the coordinates on the screen.
   * @param ix x-coordinate of the mouse event
   * @param iy y-coordinate of the mouse event
   * @return point calculated based on the x,y coordinates from x and y.
   */
  public static Point getColAndRow(int ix,int iy) {
    int col = (ix - LOCATION.x) / SPACE_X;
    int row = (iy - LOCATION.y) / SPACE_Y;
    return new Point(col,row);
  }
   
  /**
   * This shows the text that will end the game.
   * 
   */
  public void endGame() {
    
    
    
    Logger.log("End Game");
    this.frame.setLayout(new BorderLayout());
    JPanel pl = new JPanel(new GridLayout(4,1));
    pl.setBackground(Color.BLACK);

    JButton button = new JButton();
    //Border thickBorder = new LineBorder(Color.BLACK, 12);
    button.setText("New Game");
    button.setSize(400, 50);
    button.setMinimumSize(new Dimension(400,200));
    button.setFont(new Font("Serif",Font.BOLD, 45)); 
    button.setForeground(Color.BLACK);
    button.addActionListener(new ButtonListener());

    JLabel label = new JLabel("Game over frown-emoticon",JLabel.CENTER);
    label.setForeground(Color.WHITE);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setFont(new Font("Serif",Font.BOLD, 45)); 

    pl.setAlignmentX(Component.CENTER_ALIGNMENT);
    pl.add(label);
    pl.add(button);
    this.frame.setContentPane(pl);
    this.frame.setVisible(true);
  }

  /**
   * Draw board on the screen.
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    graphics.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    game.boardFactory.paintComponent(graphics);
    
    if (focus != null) {
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,SPACE_X,SPACE_Y, null);
    }
  }
}