package main.java.group37.bejeweled.board;

import javax.swing.ImageIcon;
/**
 * Class HypercubeTile, object for a hypercube gem on the board.
 * @author group37
 */
public class HypercubeTile extends Tile {
  
  private int score;

  /**
   * Constructor for a hypercubetile.
   * @param transX the x-co of the tile on the board.
   * @param transY the y-co of the tile on the board.
   */
  public HypercubeTile(int transX, int transY) {
    super(transX, transY);
    score = 50;
    String[] p1 = {"src/img/hypercube/gem.jpg", 
        "src/img/hypercube/gem.jpg", "src/img/hypercube/gem.jpg", 
        "src/img/hypercube/gem.jpg", "src/img/hypercube/gem.jpg", 
        "src/img/hypercube/gem.jpg", "src/img/hypercube/gem.jpg"};
    paths = p1;
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
  
  /**
   * Create clone of this object.
   * @param coordinateX col index.
   * @param coordinateY row index.
   * @return clone of this.
   */
  @Override
  public Tile clone(int coordinateX, int coordinateY) {
    Tile tile = new HypercubeTile(coordinateX,coordinateY);
    tile.setIndex(this.index);
    tile.setImage(new ImageIcon(paths[index]));
    tile.delete = this.delete;
    tile.setNextType(this.nextType);
    return tile;
  }
  
  /**
   * Returns true iff the compared with object is a hypercubetile
   * and has the same index, X-co and Y-co.
   * @param obj an objet to be compared
   * @return a boolean, true iff the two objects are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof HypercubeTile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index && tile.getX() == this.getX() && tile.getY() == this.getY());
  }
  
}
