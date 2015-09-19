package main.java.group37.bejeweled.model;

/**
 * Class StarTile, object for a Star gem on the board.
 * @author group37
 */
public class StarTile extends Tile {

  /**
   * Constructor for a startile.
   * @param transX, the x-co of the tile on the board.
   * @param transY, the y-co of the tile on the board.
   */
  public StarTile(int transX, int transY) {
    super(transX, transY);
    // TODO Auto-generated constructor stub
  }

  /**
   * get the type of the tile.
   * @return type as a string
   */
  public String getType() {
    return "Star"; 
  }
  
}
