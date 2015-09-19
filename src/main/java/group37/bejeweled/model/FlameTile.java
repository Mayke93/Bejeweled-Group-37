package main.java.group37.bejeweled.model;

/**
 * Class FlameTile, object for a flame gem on the board.
 * @author group37
 *
 */
public class FlameTile extends Tile {

  /**
   * Constructor for a flametile.
   * @param transX, the x-co of the tile on the board.
   * @param transY, the y-co of the tile on the board.
   */
  public FlameTile(int transX, int transY) {
    super(transX, transY);
    // TODO Auto-generated constructor stub
  }
  
  /**
   * get the type of the tile.
   * @return type as a string
   */
  public String getType() {
    return "Flame"; 
  }

}
