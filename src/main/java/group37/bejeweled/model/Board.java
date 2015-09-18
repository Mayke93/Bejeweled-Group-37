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
	public Tile[][] board;

	/**
	 * Creates a new board.
	 * 
	 * @param bi
	 *            matrix with tiles
	 */
	public Board(Tile[][] bi) {
		assert bi != null;
		this.board = bi;
	}

	/**
	 * gets the amount of columns.
	 * 
	 * @return The width of this board
	 */
	public int getWidth() {
		return board.length;
	}

	/**
	 * gets the amount of rows.
	 * 
	 * @return The height of this board
	 */
	public int getHeight() {
		return board[0].length;
	}

	/**
	 * get the tile at coordinates x and y.
	 * 
	 * @param xi
	 *            column position of the tile
	 * @param yi
	 *            row position of the tile
	 * @return the tile at (x,y)
	 */
	public Tile getTileAt(int xi, int yi) {
		assert validBorders(xi, yi);
		Tile result = board[xi][yi];
		return result;
	}

	/**
	 * methos to set a tile in a specific place.
	 * 
	 * @param tile
	 *            the tile to be placed at position (x,y)
	 * @param x
	 *            column position of the tile
	 * @param y
	 *            row position of the tile
	 */
	public void setTileAt(Tile tile, int xi, int yi) {
		board[xi][yi] = tile;
	}

	/**
	 * checks if the given coordinates are on the board.
	 * 
	 * @param xi
	 *            integer position column
	 * @param yi
	 *            integer position row
	 * @return true iff the coordinates exist on the board
	 */
	public boolean validBorders(int xi, int yi) {
		return (xi >= 0 && xi < getWidth() && yi >= 0 && yi < getHeight());
	}

}
