package main.java.group37.bejeweled.board;

import javax.swing.ImageIcon;

public class NormalTile extends Tile {

  private int score;
  
  /**
   * Constructor for a normaltile.
   * @param transX the x-co of the tile on the board.
   * @param transY the y-co of the tile on the board.
   */
  public NormalTile(int transX, int transY) {
    super(transX, transY);
    score = 50; 
    String[] p1 = {"src/img/gemBlue.png", "src/img/gemGreen.png",
        "src/img/gemOrange.png", "src/img/gemPurple.png",
        "src/img/gemRed.png", "src/img/gemWhite.png",
        "src/img/gemYellow.png"};
    paths = p1;
  }
  
  /**
   * get the type of the tile, only for printing purposes.
   * @return type as a string
   */
  public String getType() {
    return "Normal"; 
  } 

  /**
   * Create clone of this object.
   * @param coordinateX col index.
   * @param coordinateY row index.
   * @return clone of this.
   */
  public Tile clone(int coordinateX, int coordinateY) {
    Tile tile = new NormalTile(coordinateX,coordinateY);
    tile.index = this.index;
    tile.image = new ImageIcon(paths[index]);
    tile.delete = this.delete;
    tile.setNextType(this.nextType);
    return tile;
  }
  
  /**
   * Returns true iff the compared with object is a normaltile
   * and has the same index, X-co and Y-co.
   * @param obj an objet to be compared
   * @return a boolean, true iff the two objects are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof NormalTile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index && tile.getX() == this.getX() && tile.getY() == this.getY());
  }
  
  /**
   * Get the score.
   */
  public int getScore() {
    return score;
  }
  
}
