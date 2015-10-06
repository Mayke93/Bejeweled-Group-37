package main.java.group37.bejeweled.board;

import javax.swing.ImageIcon;

public class NormalTile extends Tile {

  //private int score;
  
  /**
   * Constructor for a normaltile.
   * @param transX the x-co of the tile on the board.
   * @param transY the y-co of the tile on the board.
   */
  public NormalTile(int transX, int transY) {
    super(transX, transY);
    //score = 50; ?
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
  
}
