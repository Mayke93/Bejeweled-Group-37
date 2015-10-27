package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.BoardPanel;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.GameLogic;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SavedGame;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")

/**
 * Class that is responsible for the frames, images and text in the GUI.
 * @author group37
 */
public class Main extends JPanel {

  public static final Point LOCATION = new Point(241,40);
  public static final int SPACE_X = 65;
  public static final int SPACE_Y = 65;
  
  private Point focus = null;
  private Point t0 = null;
  private Point t1 = null;
  
  private BoardPanel boardPanel;
  public Animation animations;
  protected Game game;
  private Panel statuspanel;

  /**
   * Initialize the board and create the mouse event listeners.
   * @param panel JPanel with the labels to display the status of the game
   */
  public Main(Panel panel) {
    statuspanel = panel;
    game = new Game(this);
    boardPanel = new BoardPanel(game);
    panel.setGame(game);
    panel.setMain(this);
    animations = new Animation(game,this);
    setOpaque(true);
    Logger.log("# Start new game");
    this.addMouseListener(new MouseListener(this));
    this.addMouseMotionListener(new MouseMotionListener(this));
    
    GameLogic.init(panel);
    
    SavedGame.getInstance().setGame(game);
  }

  /**
   * Gets the game.
   * @return game, the game.
   */
  public Game getGame() {
    return game;
  }
  
  /**
   * sets the game.
   */
  public void setGame(Game ga) {
    this.game = ga;
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
   * Get the index of the focused jewel based on the coordinated of the mouse event.
   * @param t00 first tile that can be swapped
   * @param t11 second tile that can be swapped with the first one.
   */
  public void setFocusHint(Point t00, Point t11) {
    int ix = t00.x * SPACE_X + LOCATION.x;
    int iy = t00.y * SPACE_Y + LOCATION.y;
    int jx = t11.x * SPACE_X + LOCATION.x;
    int jy = t11.y * SPACE_Y + LOCATION.y;
    t0 = new Point(ix,iy);
    t1 = new Point(jx, jy);
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
   * Draw board on the screen.
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    ImageIcon boardImage  = new ImageIcon("src/img/board.png");
    ImageIcon focusImage = new ImageIcon("src/img/focus.png");
    ImageIcon focusHintImage = new ImageIcon("src/img/focusHint.png");
    
    graphics.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    boardPanel.paintComponent(graphics);
    
    if (focus != null) {
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,SPACE_X,SPACE_Y, null);
    }
    if (t0 != null && t1 != null) {
      graphics.drawImage(focusHintImage.getImage(), t0.x, t0.y,SPACE_X,SPACE_Y, null);
      graphics.drawImage(focusHintImage.getImage(), t1.x, t1.y,SPACE_X,SPACE_Y, null);
    }
    t0 = null;
    t1 = null;   
  }

  public Panel getStatusPanel() {
    return statuspanel;
  }
}