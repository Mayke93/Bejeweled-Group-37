package main.java.group37.bejeweled.Board;

//import java.awt.Graphics;

//import main.java.group37.bejeweled.view.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * board object for the tiles.
 * 
 * @author Group 37
 *
 */
public class Board {
  
  /**
   * The grid of squares with board[x][y] being the square at column x, row y.
   */
  public Tile[][] board;
  
  /**
   * Creates a new board.
   * @param bi matrix with tiles
   */
  public Board(Tile[][] bi) {
    assert bi != null;
    this.board = bi;
  }
  
  /**
   * gets the amount of columns.
   * @return The width of this board
   */
  public int getWidth() {
    return board.length;
  }

  /**
   * gets the amount of rows.
   * @return The height of this board
   */
  public int getHeight() {
    return board[0].length;
  }
  
  /**
   * get the tile at coordinates x and y.
   * @param xi column position of the tile
   * @param yi row position of the tile
   * @return the tile at (x,y)
   */
  public Tile getTileAt(int xi, int yi) {
    Tile result = null;
    if  (validBorders(xi, yi)) {
      result = board[xi][yi];
    }
    return result;
  }
  
  /**
   * methos to set a tile in a specific place.
   * @param tile the tile to be placed at position (x,y)
   * @param xi column position of the tile
   * @param yi row position of the tile
   */
  public void setTileAt(Tile tile, int xi, int yi) {
    if  (validBorders(xi, yi)) {
      board[xi][yi] = tile;
    }
  }
  
  /**
   * checks if the given coordinates are on the board.
   * @param xi integer position column
   * @param yi integer position row
   * @return true iff the coordinates exist on the board
   */
  public boolean validBorders(int xi, int yi) {
    return (xi >= 0 && xi < getWidth() && yi >= 0 && yi < getHeight());
  }
  
  /**
   * checks if a square on the board is empty.
   * @param xi x coordinate to be checked
   * @param yi y coordinate to be checked
   * @return true iff there is no tile on coordinates (x,y)
   */
  public boolean isEmpty(int xi, int yi) {
    if (getTileAt(xi, yi) == null || !(validBorders(xi, yi))) {
      return true;
    }
    return false;
  }
  
  /**
   * removes a tile from the board.
   * @param xi x coordinate of the tile
   * @param yi y coordinate of the tile
   */
  public void clear(int xi, int yi) {
    board[xi][yi] = null;
  }
  
  /**
   * Gets the tiles that need to be deleted due to the detonating of the flame gem.
   * @param t1 the flame gem
   * @return tiles, the list of tiles to be deleted.
   */
  public List<Tile> getTilesToDeleteFlame(Tile t1) {
    List<Tile> tiles = new ArrayList<Tile>();
    
    tiles.add(t1);                                        //add the flame tile to the list.
    tiles.add(getTileAt(t1.getX() - 1, t1.getY()));       //links van flame tile
    tiles.add(getTileAt(t1.getX() + 1, t1.getY()));       //rechts van flame tile
    tiles.add(getTileAt(t1.getX() - 1, t1.getY() + 1));   //linksonder van flame tile
    tiles.add(getTileAt(t1.getX() - 1, t1.getY() - 1));   //linksboven van flame tile
    tiles.add(getTileAt(t1.getX() + 1, t1.getY() + 1));   //rechtsonder van flame tile
    tiles.add(getTileAt(t1.getX() + 1, t1.getY() - 1));   //rechtsboven van flame tile
    tiles.add(getTileAt(t1.getX(), t1.getY() - 1));       //boven van flame tile
    tiles.add(getTileAt(t1.getX(), t1.getY() + 1));       //onder van flame tile
    
    return tiles;
  }
  
  /**
   * Gets the tiles that need to be deleted due to the detonating of the hypercube gem.
   * @param t1 the hypercube gem
   * @return tiles, the list of tiles to be deleted.
   */
  public List<Tile> getTilesToDeleteHypercube(Tile t1) {
    List<Tile> tiles = new ArrayList<Tile>();
    int index = t1.getIndex();
    
    for (int row = 0; row < board.length; row++) {        //loop through board
      for (int col = 0; col < board[0].length; col++) {
        if (index == getTileAt(row, col).getIndex()) {    //add tile tile if colors are the same
          tiles.add(getTileAt(row, col));
        }
      }    
    }
    return tiles;
  }
  
  /**
   * Gets the tiles that need to be deleted due to the detonating of the hypercube gem.
   * @param t1 the hypercube gem
   * @return tiles, the list of tiles to be deleted.
   */
  public List<Tile> getTilesToDeleteStar(Tile t1) {
    List<Tile> tiles = new ArrayList<Tile>();
    tiles.add(t1);
    
    for (int i = 1; t1.getX() - i >= 0; i++) {          //get the tiles left from the star tile
      tiles.add(getTileAt(t1.getX() - i, t1.getY()));
    }
    for (int i = 1; t1.getX() + i < 8; i++) {           //get the tiles right from the star tile
      tiles.add(getTileAt(t1.getX() + i, t1.getY()));
    }
    for (int i = 1; t1.getY() - i >= 0; i++) {          //get the tiles above the star tile
      tiles.add(getTileAt(t1.getX(), t1.getY() - i));
    }
    for (int i = 1; t1.getY() + i < 8; i++) {           //get the tiles below the star tile
      tiles.add(getTileAt(t1.getX(), t1.getY() + i));
    }
    
    return tiles;
  }
  
  
}
