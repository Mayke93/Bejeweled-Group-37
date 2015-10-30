package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
  
  public DropAnimation dropAnimation;
  public SwapAnimation swapAnimation;
  public RemoveAnimation removeAnimation;

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