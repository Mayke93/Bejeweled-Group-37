package main.java.group37.bejeweled.model;

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
  private final Tile[][] board;
  
  /**
   * Creates a new board.
   * @param b matrix with tiles
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
   * @param x column position of the tile
   * @param y row position of the tile
   * @return the tile at (x,y)
   */
  public Tile getTileAt(int xi, int yi) {
    assert validBorders(xi, yi);
    Tile result = board[xi][yi];
    return result;
  }
  
  /**
   * checks if the given coordinates are on the board.
   * @param x integer position column
   * @param y integer position row
   * @return true iff the coordinates exist on the board
   */
  public boolean validBorders(int xi, int yi) {
    return (xi >= 0 && xi < getWidth() && yi >= 0 && yi < getHeight());
  }

}
