package main.java.group37.bejeweled.Board;

/**
 * Class HypercubeTile, object for a hypercube gem on the board.
 * @author group37
 */
public class HypercubeTile extends Tile {
  
  private int score;

  /**
   * Constructor for a hypercubetile.
   * @param transX, the x-co of the tile on the board.
   * @param transY, the y-co of the tile on the board.
   */
  public HypercubeTile(int transX, int transY) {
    super(transX, transY);
    score = 50;
  }

  /**
   * get the type of the tile. This method is ONLY for printing purposes!
   * @return type as a string
   */
  public String getType() {
    return "Hypercube"; 
  }
  
  /**
   * get the score from this type of special tile.
   * @return the score as an integer
   */
  public int getScore() {
    return score;
  }
  
}
