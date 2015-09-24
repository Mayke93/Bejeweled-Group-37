package main.java.group37.bejeweled.board;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
//import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.group37.bejeweled.model.Game;

//import main.java.group37.bejeweled.view.Animation;
//import main.java.group37.bejeweled.view.StatusPanel;

@SuppressWarnings("serial")
public class BoardFactory extends JPanel {
  
  public static final int SIZE = 8; //Board size is 8x8
  public static final Point LOCATION = new Point(241,40);
  public static final int SPACE_X = 65;
  public static final int SPACE_Y = 65;
  private static ImageIcon boardImage  = new ImageIcon("src/img/board.png");
  private static ImageIcon focusImage = new ImageIcon("src/img/focus.png");
  private Point focus = null;

 
  //public Animation animations;

  //Panel with score label and level label
  //private StatusPanel panel;

  private Game game;

  
  public BoardFactory(Game ga) {
    game = ga;
  }
  
  /**
   * Draw board on the screen.
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    Board board = game.getBoard();
    graphics.drawImage(boardImage.getImage(), 0, 0, board.getWidth(), board.getHeight(), null);
    int ix = LOCATION.x;
    int iy = LOCATION.y;
    Tile tile = null;

    for (int i = 0,x = ix, y = iy; i < SIZE; i++) {
      x = ix;
      for (int j = 0; j < SIZE; j++, x += SPACE_X) {
        tile = board.getTileAt(j, i);
        if (tile.remove) {
          continue;
        }
        graphics.drawImage(tile.getImage().getImage(), x + tile.translation.x , 
            y + tile.translation.y,SPACE_X - tile.size,SPACE_Y - tile.size, null);
      }
      y += SPACE_Y;
    }

    if (focus != null) {
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,SPACE_X,SPACE_Y, null);
    }
  }

}
