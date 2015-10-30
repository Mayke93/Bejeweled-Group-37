package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.view.Animation.Type;

import java.awt.Point;
import java.util.List;

/**
 * Class for handling the animation for removing tiles.
 * @author group37
 */
public class RemoveAnimation implements IAnimation{
  private Animation animation;
  protected List<Tile> tiles;

  public RemoveAnimation(Animation animation) {
    this.animation = animation;
  }

  @Override
  public void start() {
    animation.frame = 0;
    for (Tile t: this.tiles) {
      t.translation = new Point(0,0);
      t.size = 0;
    }
    animation.timer.setDelay(2);
    animation.timer.start();
  }

  @Override
  public void performAction() {
    animation.frame++;
    int speed = 1;
    if (animation.frame > 32) {
      end(); 
    } else {
      for (Tile t: this.tiles) {
        t.translation.x += speed;
        t.translation.y += speed;
        t.size += 2 * speed;
      }
    }
    animation.main.repaint();
  }

  @Override
  public void end() {
    animation.timer.stop();
    animation.frame = 0;
    for (Tile t: this.tiles) {
      t.remove = true;
      t.translation = new Point(0,0);
      t.size = 0;
    }

    animation.setType(Type.DROP);
    animation.start();
  }

  /**
   * Start animation for removing tiles on the board.
   * @param tiles list of tiles to remove.
   */
  public void setRemoveTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }
  
}
