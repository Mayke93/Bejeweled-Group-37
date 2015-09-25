package main.java.group37.bejeweled.board;

/**
 * Class StarTile, object for a Star gem on the board.
 * @author group37
 */
public class StarTile extends Tile {
  
  private int score;

  /**
   * Constructor for a startile.
   * @param transX the x-co of the tile on the board.
   * @param transY the y-co of the tile on the board.
   */
  public StarTile(int transX, int transY) {
    super(transX, transY);
    score = 50;
    String[] p1 = {"src/img/star/gemBlueStar.png", 
        "src/img/star/gemGreenStar.png", "src/img/star/gemOrangeStar.png", 
        "src/img/star/gemPurpleStar.png", "src/img/star/gemRedStar.png", 
        "src/img/star/gemWhiteStar.png", "src/img/star/gemYellowStar.png"};
    paths = p1;
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
