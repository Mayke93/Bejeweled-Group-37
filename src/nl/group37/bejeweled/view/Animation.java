package nl.group37.bejeweled.view;

import nl.group37.bejeweled.model.Game;
import nl.group37.bejeweled.model.Tile;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;


public class Animation implements ActionListener{
  private Game game;
  private Main board;
  private Timer timer;
  private int frame;
  private Tile t0;
  private Tile t1;
  private List<Tile> tiles;

  public static enum Type{
    SWAP,REMOVE,DROP;
  }

  private Type type;

  /**
   * Create animation object for animations.
   * @param game game object.
   * @param board board(GUI) object.
   */
  public Animation(Game game, Main board) {
    this.game = game;
    this.board = board;
    this.timer = new Timer(10,this);
    this.frame = 0;
    this.t0 = null;
    this.t1 = null;
    this.tiles = null;
    this.type = Type.SWAP;
  }

  /**
   * Switch two tiles on the board.
   * @param t0 tile 1.
   * @param t1 tile 2.
   */
  public void swap(Tile t0, Tile t1) {
    this.t0 = t0;
    this.t1 = t1;
    timer.setDelay(10);
    timer.start();
  }

  /**
   * Start animation for removing tiles on the board.
   * @param tiles list of tiles to remove.
   */
  public void startRemove(List<Tile> tiles) {
    this.tiles = tiles;
    this.frame = 0;
    System.out.println(tiles.size());
    for (Tile t: this.tiles) {
      t.translation = new Point(0,0);
      t.size = 0;
    }
    timer.setDelay(2);
    timer.start();
  }

  //@Override
  /**
   * Mouse event listeners.
   */
  public void actionPerformed(ActionEvent event) {
    if (this.type == Type.SWAP) {
      frame++;
      int speed = 4;
      if (frame > 16) {
        endSwap();
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
      board.repaint();
    } else if (this.type == Type.REMOVE) {
      frame++;
      int speed = 1;
      if (frame > 32) {
        endRemove(); 
      } else {
        for (Tile t: this.tiles) {
          t.translation.x += speed;
          t.translation.y += speed;
          t.size += 2 * speed;
        }
      }
      board.repaint();
    }
    /*else if (this.type == Type.DROP) {

    }*/
  }

  private void endRemove() {
    this.timer.stop();
    this.frame = 0;
    for (Tile t: this.tiles) {
      t.remove = true;
      t.translation = new Point(0,0);
      t.size = 0;
    }
    game.dropTiles();
    if (!game.possibleMove()) {
      board.endGame();
    }
  }

  private void endSwap() {
    this.timer.stop();
    this.frame = 0;
    System.out.println("end");

    t0.resetD();
    t1.resetD();
    game.swapTiles(t0,t1);
    game.deleteTiles();
  }

  public void setTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
