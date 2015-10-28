package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.GameLogic;
import main.java.group37.bejeweled.model.SwapHandler;

/**
 * Class for handing the swap animation of two tiles.
 * @author group37
 */
public class SwapAnimation implements IAnimation{
  protected Tile t0;
  protected Tile t1;
  private Animation animation;

  public SwapAnimation(Animation animation) {
    this.animation = animation;
  }

  @Override
  public void start() {
    animation.timer.setDelay(10);
    animation.timer.start();
  }

  @Override
  public void performAction() {
    animation.frame++;
    int speed = 4;
    if (animation.frame > 16) {
      end();
    } else {
      int direction = 1;
      if (t0.getX() == t1.getX()) {
        if (t0.getY() < t1.getY()) { 
          direction = 1; 
        } else { 
          direction = -1;
        }
        t0.updateTranslation(0,speed * direction);
        t1.updateTranslation(0,-speed * direction);
      } else {                
        if (t0.getX() < t1.getX()) { 
          direction = 1;
        } else {
          direction = -1;
        }
        t0.updateTranslation(speed * direction, 0);
        t1.updateTranslation(-speed * direction, 0);
      }
    }    
    animation.main.repaint(); 
  }

  @Override
  public void end() {
    animation.timer.stop();
    animation.frame = 0;

    t0.resetD();
    t1.resetD();
    SwapHandler.swappedTiles(t0,t1);
    if (t0 instanceof HypercubeTile) {
      GameLogic.deleteTiles(SwapHandler.getTilesToDeleteHypercube(t1,t0));
    } else if (t1 instanceof HypercubeTile) {
      GameLogic.deleteTiles(SwapHandler.getTilesToDeleteHypercube(t0,t1));
    } else {
      GameLogic.deleteChains();
    }
  }
}
