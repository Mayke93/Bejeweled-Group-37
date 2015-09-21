package main.java.group37.bejeweled.Board;

/**
 * Class FlameTile, object for a flame gem on the board.
 * @author group37
 *
 */
public class FlameTile extends Tile {
  
  private int score;
  

  /**
   * Constructor for a flametile.
   * @param transX the x-co of the tile on the board.
   * @param transY the y-co of the tile on the board.
   */
  public FlameTile(int transX, int transY) {
    super(transX, transY);
    score = 20;
    String[] p1 = {"src/img/flame/gemBlueFlame.png", "src/img/flame/gemGreenFlame.png",
      "src/img/flame/gemOrangeFlame.png", "src/img/flame/gemPurpleFlame.png",
      "src/img/flame/gemRedFlame.png", "src/img/flame/gemWhiteFlame.png",
      "src/img/flame/gemYellowFlame.png"};
    paths = p1;
  }
  
  /**
   * get the type of the tile.  This method is ONLY for printing purposes!
   * @return type as a string
   */
  public String getType() {
    return "Flame"; 
  }
  
  /**
   * get the score from this type of special tile.
   * @return the score as an integer
   */
  public int getScore() {
    return score;
  }

}
