package main.java.group37.bejeweled.Board;

/**
 * Class StarTile, object for a Star gem on the board.
 * @author group37
 */
public class StarTile extends Tile {
  
  private int score;

  /**
   * Constructor for a startile.
   * @param transX, the x-co of the tile on the board.
   * @param transY, the y-co of the tile on the board.
   */
  public StarTile(int transX, int transY) {
    super(transX, transY);
    score = 50;
  }

  /**
   * get the type of the tile.  This method is ONLY for printing purposes!
   * @return type as a string
   */
  public String getType() {
    return "Star"; 
  }
  
  /**
   * get the score from this type of special tile.
   * @return the score as an integer
   */
  public int getScore() {
    return score;
  }
  
}
