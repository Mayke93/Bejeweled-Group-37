package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SwapHandler;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * Class that listens to mouse events.
 * @author group37
 */
public class MouseListener extends MouseAdapter{

  private Main main;

  /**
   * Constructor of the mouse listener.
   * @param board, the board of the game where the mouse event takes place.
   */
  public MouseListener(Main board) {
    this.main = board;
  }

  /**
   * Method that sets the focus on the correct tile where the mouse was clicked.
   */
  @Override
  public void mouseClicked(MouseEvent event) {
    if (Panel.getGameOver() == false) {
      Point loc = Main.getColAndRow(event.getX(),event.getY());
      int col = loc.x;
      int row = loc.y;
      if (!main.getGame().getBoard().validBorders(col, row)) {
        return;
      }
      main.setFocus(loc);
      Game game = main.getGame();
      Logger.log("Mouse Clicked: (" + game.getBoard().getTileAt(col, row).getLoc().x + ", " 
          + game.getBoard().getTileAt(col, row).getLoc().y + ") " 
          + Tile.colors[game.getBoard().getTileAt(col, row).getIndex()]
              + " " + game.getBoard().getTileAt(col, row).getType());
      Logger.log(game.getBoard().getTileAt(col, row).toString());
    }
  }

  /**
   * Clears the list of tiles to be swapped when the mouse is released.
   */
  @Override
  public void mouseReleased(MouseEvent event) {
    SwapHandler.getSwapTiles().clear();
  }  
}
