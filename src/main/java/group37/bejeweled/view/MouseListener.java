package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.Tile;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * Class that listens to mouse events.
 * @author group37
 */
public class MouseListener extends MouseAdapter{

  private Main board;

  /**
   * Constructor of the mouse listener.
   * @param board, the board of the game where the mouse event takes place.
   */
  public MouseListener(Main board) {
    this.board = board;
  }

  /**
   * Method that sets the focus on the correct tile where the mouse was clicked.
   */
  @Override
  public void mouseClicked(MouseEvent event) {
    Point loc = Main.getColAndRow(event.getX(),event.getY());
    int col = loc.x;
    int row = loc.y;
    if (!Main.withinBoundaries(col) || !Main.withinBoundaries(row)) {
      return;
    }
    board.setFocus(loc);
    Game game = board.getGame();
    Logger.log("Mouse Clicked: (" + game.getBoard()[col][row].getLoc().x + ", " 
        + game.getBoard()[col][row].getLoc().y + ") " 
        + Tile.colors[game.getBoard()[col][row].getIndex()]);

  }

  /**
   * Clears the list of tiles to be swapped when the mouse is released.
   */
  @Override
  public void mouseReleased(MouseEvent event) {
    board.getGame().getSwaptiles().clear();
  }  
}
