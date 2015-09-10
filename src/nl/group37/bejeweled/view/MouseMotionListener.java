package nl.group37.bejeweled.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter{
  private Board board;
  
  public MouseMotionListener(Board board) {
    this.board = board;
  }
  
  @Override
  public void mouseDragged(MouseEvent event) {
    Point loc = Board.getColAndRow(event.getX(),event.getY());
    int col = loc.x;
    int row = loc.y;

    if (!Board.withinBoundaries(col) || !Board.withinBoundaries(row)) {
      return;
    }
    board.getGame().addTile(loc);
  }
}
