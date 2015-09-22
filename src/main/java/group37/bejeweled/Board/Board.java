package main.java.group37.bejeweled.Board;

import java.awt.Graphics;

import main.java.group37.bejeweled.view.Main;
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
    if(validBorders(xi, yi)){
      if (getTileAt(xi, yi) == null) {
        return true;
      }
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
   * Method to get all tiles from a certain color.
   * @param index integer that determines the color
   * @return a list with tiles that have the same color/index number
   */

  public List<Tile> sameColors(int index) {
    List<Tile> res = new ArrayList<Tile>();
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (index == getTileAt(row, col).getIndex()) {
          res.add(getTileAt(row, col));
        }
      }    
    }
    return res;
  }
}
