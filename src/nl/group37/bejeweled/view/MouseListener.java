package nl.group37.bejeweled.view;

import nl.group37.bejeweled.model.Game;
import nl.group37.bejeweled.model.Tile;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter{

  private Board board;

  public MouseListener(Board board) {
    this.board = board;
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    Point loc = Board.getColAndRow(event.getX(),event.getY());
    int col = loc.x;
    int row = loc.y;
    if (!Board.withinBoundaries(col) || !Board.withinBoundaries(row)) {
      return;
    }
    board.setFocus(loc);
    Game game = board.getGame();
    System.out.println("Mouse Clicked: (" + game.getBoard()[col][row].getLoc().x + ", " 
        + game.getBoard()[col][row].getLoc().y + ") " 
        + Tile.colors[game.getBoard()[col][row].getIndex()]);

  }

  @Override
  public void mouseReleased(MouseEvent event) {
    board.getGame().getSwaptiles().clear();
  }  
}
