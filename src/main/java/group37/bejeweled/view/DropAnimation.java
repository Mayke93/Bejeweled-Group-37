package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.GameLogic;
import main.java.group37.bejeweled.model.Logger;

import java.util.List;

/**
 * Class for handling the animation for droping the tiles.
 * @author group37
 */
public class DropAnimation implements IAnimation{
  
  protected List<Tile> tilesToDrop;
  private Animation animation;

  public DropAnimation(Animation animation) {
    this.animation = animation;
  }

  @Override
  public void start() {
    if (this.tilesToDrop != null) {
      Logger.log("Start DROP animation");
      animation.timer.setDelay(4);
      animation.timer.start();
    }
  }

  @Override
  public void performAction() {
    animation.frame++;
    if (animation.frame > 20) {
      end();
    }
    int speed = 1;
    for (Tile tile: this.tilesToDrop) {
      tile.updateTranslation(0, speed * tile.getLevel());
    }
    animation.main.repaint();
    
  }

  @Override
  public void end() {
    Logger.log("END drop animations");
    animation.timer.stop();
    animation.frame = 0;

    GameLogic.dropTiles();
    if (!animation.game.possibleMove()) {
      animation.main.getStatusPanel().endGame();
    }
  }
  
  /**
   * Set tiles for the drop animation.
   * @param tilesToDrop list with tiles.
   */
  public void setDropTiles(List<Tile> tilesToDrop) {
    this.tilesToDrop = tilesToDrop;
  }
}
