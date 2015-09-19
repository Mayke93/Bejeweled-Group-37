package main.java.group37.bejeweled.Board;

/**
 * Class HypercubeTile, object for a hypercube gem on the board.
 * @author group37
 */
public class HypercubeTile extends Tile {

  /**
   * Constructor for a hypercubetile.
   * @param transX, the x-co of the tile on the board.
   * @param transY, the y-co of the tile on the board.
   */
  public HypercubeTile(int transX, int transY) {
    super(transX, transY);
    // TODO Auto-generated constructor stub
  }

  /**
   * get the type of the tile.
   * @return type as a string
   */
  public String getType() {
    return "Hypercube"; 
  }
}
