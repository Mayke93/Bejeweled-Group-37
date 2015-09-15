package main.java.group37.bejeweled.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class to listen to motions of the mouse.
 * @author group37
 */
public class MouseMotionListener extends MouseAdapter{
  private Main board;
  
  /**
   * Constructor of the mouse listener.
   * @param board, the board of the game to which the mouse listener listens.
   */
  public MouseMotionListener(Main board) {
    this.board = board;
  }
  
  /**
   * Adds the tiles from the locations where the mouse was dragged, when the mouse is dragged.
   */
  @Override
  public void mouseDragged(MouseEvent event) {
    Point loc = Main.getColAndRow(event.getX(),event.getY());
    int col = loc.x;
    int row = loc.y;

    if (!Main.withinBoundaries(col) || !Main.withinBoundaries(row)) {
      return;
    }
    board.getGame().addTile(loc);
  }
}
