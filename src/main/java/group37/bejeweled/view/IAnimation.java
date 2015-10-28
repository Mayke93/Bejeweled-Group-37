package main.java.group37.bejeweled.view;

public interface IAnimation {
  /**
   * Start animation.
   */
  public void start();

  /**
   * Perform one operation of an specific animation.
   */
  public void performAction();

  /**
   * End the animation.
   */
  public void end();
}
