package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

/**
 * Class that carries out the disappearing of combinations,
 * gems falling down and new gems filling the board.
 * @author group37
 */
public class Animation implements ActionListener{
  protected Game game;
  protected Main main;
  protected Timer timer;
  protected int frame;

  private IAnimation state = null;
  
  protected DropAnimation dropAnimation;
  protected SwapAnimation swapAnimation;
  protected RemoveAnimation removeAnimation;

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
    this.main = board;
    this.timer = new Timer(10,this);
    this.frame = 0;
    this.type = Type.SWAP;

    dropAnimation = new DropAnimation(this);
    swapAnimation = new SwapAnimation(this);
    removeAnimation = new RemoveAnimation(this);
    this.setType(this.type);
  }
  
  /**
   * Start animation.
   */
  public void start() {
    this.state.start();
  }

  /**
   * Mouse event listeners.
   */
  public void actionPerformed(ActionEvent event) {
    state.performAction();
  }
  
  /**
   * Set tiles for the swap animation.
   * @param t0 tile 1. 
   * @param t1 tile 2.
   */
  public void setTiles(Tile t0, Tile t1) {
    swapAnimation.t0 = t0;
    swapAnimation.t1 = t1;
  }

  /**
   * Start animation for removing tiles on the board.
   * @param tiles list of tiles to remove.
   */
  public void setRemoveTiles(List<Tile> tiles) {
    removeAnimation.tiles = tiles;
  }
  
  /**
   * Set tiles for the drop animation.
   * @param tilesToDrop list with tiles.
   */
  public void setDropTiles(List<Tile> tilesToDrop) {
    dropAnimation.tilesToDrop = tilesToDrop;
  }

  /**
   * Gets the type of the animation.
   * @return type, the type of the animation.
   */
  public Type getType() {
    return type;
  }

  /**
   * Set type of animation.
   * @param type type of animation.
   */
  public void setType(Type type) {
    this.type = type;
    if (type == Type.DROP) {
      this.state = dropAnimation;
    } else if (type == Type.SWAP) {
      this.state = swapAnimation;
    } else if (type == Type.REMOVE) {
      this.state = removeAnimation;
    }
  }
}