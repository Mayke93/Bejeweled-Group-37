package main.java.group37.bejeweled.board;

import javax.swing.ImageIcon;

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

  /**
   * Create clone of this object.
   * @param coordinateX col index.
   * @param coordinateY row index.
   * @return clone of this.
   */
  @Override
  public Tile clone(int coordinateX, int coordinateY) {
    Tile tile = new FlameTile(coordinateX,coordinateY);
    tile.setIndex(this.index);
    tile.setImage(new ImageIcon(paths[index]));
    return tile;
  }
  
  /**
   * Returns true iff the compared with object is a flametile
   * and has the same index, X-co and Y-co.
   * @param obj an objet to be compared
   * @return a boolean, true iff the two objects are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof FlameTile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index && tile.getX() == this.getX() && tile.getY() == this.getY());
  }
  
  /**
   * When detonating a special gem, a 'special' score is also needed.
   * @return the score for detonating tiles with a flame tile.
   */
  @Override
  public int scoreSpecialTile() {
    return 20;
  }

}
