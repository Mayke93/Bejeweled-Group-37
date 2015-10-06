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
  
}
