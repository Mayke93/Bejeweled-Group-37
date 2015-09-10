package nl.group37.bejeweled.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter{
  private Main board;
  
  public MouseMotionListener(Main board) {
    this.board = board;
  }
  
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
