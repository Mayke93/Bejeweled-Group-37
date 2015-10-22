package main.java.group37.bejeweled.board;

import main.java.group37.bejeweled.model.Game;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardFactory extends JPanel {
  
  public static final int SIZE = 8; //Board size is 8x8
  public static final Point LOCATION = new Point(241,40);
  private Point focus = null;

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
    int spaceX = 65;
    int spaceY = 65;

    Board board = game.getBoard();
    ImageIcon boardImage  = new ImageIcon("src/img/board.png");
    graphics.drawImage(boardImage.getImage(), 0, 0, board.getWidth(), board.getHeight(), null);
    int ix = LOCATION.x;
    int iy = LOCATION.y;

    for (int i = 0, x = ix, y = iy; i < SIZE; i++) {
      x = ix;
      for (int j = 0; j < SIZE; j++, x += spaceX) {
        IDrawable draw = board.getTileAt(j, i);
        draw.paintComponent(graphics, x, y);
      }
      y += spaceY;
    }
         
    if (focus != null) {
      ImageIcon focusImage = new ImageIcon("src/img/focus.png");
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,spaceX,spaceY, null);
    }
  }

}
