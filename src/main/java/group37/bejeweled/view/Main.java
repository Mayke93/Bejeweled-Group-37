package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.board.BoardFactory;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SavedGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
  private BoardFactory boardFactory;

  public Animation animations;
  protected Game game;
  private StatusPanel statuspanel;

  /**
   * Initialize the board and create the mouse event listeners.
   * @param panel JPanel with the labels to display the status of the game
   */
  public Main(StatusPanel panel) {
    statuspanel = panel;
    game = new Game(this);
    boardFactory = new BoardFactory(game);
    panel.setGame(game);
    panel.setMain(this);
    animations = new Animation(game,this);
    setOpaque(true);
    Logger.log("# Start new game");
    this.addMouseListener(new MouseListener(this));
    this.addMouseMotionListener(new MouseMotionListener(this));
    
    game.logic.init(panel);
    
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
    
    graphics.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    boardFactory.paintComponent(graphics);
    
    if (focus != null) {
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,SPACE_X,SPACE_Y, null);
    }
  }

  public StatusPanel getStatusPanel() {
    return statuspanel;
  }
}