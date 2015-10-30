package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.board.TileFactory;
import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.view.Main;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Class that represents the current game.
 * @author group37
 */
public class Game {
  
  private Board board = null;
  
  public static final int SIZE = 8;

  /**
   * Create game object.
   * @param main object for GUI.
   */
  public Game(Main main) {
    this.board = new Board(new Tile[SIZE][SIZE]);
    generateRandomBoard();
    
    new GameLogic(this, board, main);    
    new SwapHandler(board, main);
  }

  /**
   * Create a board of random jewels without a sequence of 3 or more tiles with the same color.
   */
  public void generateRandomBoard() {
    Logger.log("Create new board");
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board.setTileAt(setRandomTile(i,j), i , j);
      }

      //Redo column if a sequence has been detected
      if (hasSequence(i)) {
        i--;
      }
    }
  }
  
  /**
   * makes a random tile.
   * @param xi x coordinate of the new random tile
   * @param yi y coordinate of the new random tile
   * @return a random tile as a Tile object
   */
  public Tile setRandomTile(int xi, int yi) { 
    Tile tile = TileFactory.generateTile(Type.NORMAL,xi,yi);
    Random random = new Random();
    tile.setIndex(random.nextInt(7));
    tile.setImage(new ImageIcon(tile.paths[tile.getIndex()]));
    return tile;
  }
  
  /**
   * Set tile to special tile.
   * @param xi x coordinate of the new random tile
   * @param yi y coordinate of the new random tile
   * @param type type of special tile.
   * @return tile object.
   */
  public Tile setSpecialTile(int xi, int yi, Type type) {
    Logger.log("Creating special tile " + type + " at " + xi + "," + yi);
    Tile tile = null;
    tile = TileFactory.generateTile(type, xi, yi);
    tile.setIndex(board.getTileAt(xi, yi).getIndex());
    tile.setImage(new ImageIcon(tile.paths[tile.getIndex()]));
    Logger.log("setSpecialTile: " + tile.remove);
    return tile;
  }

  /**
   * Checks if column i that just has been added doesn't create a sequence of 3 or more colours.
   * @param row column to check for sequences.
   * @return true iff there is found a sequence of three or more jewels.
   */
  private boolean hasSequence(int row) {
    int sum = 0;
    //Find sequence in row i
    for (int j = 1; j < SIZE; j++) {
      if (board.getTileAt(row, j).equalsColor(board.getTileAt(row, j - 1))) {
        sum++;
      } else {
        sum = 0;
      }

      if (sum >= 2) {
        return true;
      }
    }

    //If there are only 1 or 2 rows created, don't check for horizontal sequences
    if (row <= 1) {
      return false; 
    }

    //Find horizonal sequences
    for (int j = 0; j < SIZE; j++) {
      sum = 0;
      sum += (board.getTileAt(row - 1, j).equalsColor(board.getTileAt(row, j)) ? 1 : 0);
      sum += (board.getTileAt(row - 2, j).equalsColor(board.getTileAt(row, j)) ? 1 : 0);

      if (sum == 2) {
        Logger.log("i,j: " + row + "," + j);
        return true;
      }
    }
    return false;
  }


  /**
   * Method to check whether there are possible moves left in the game
   * @return true if there are possible moves, false if there are none.
   */
  public boolean possibleMove() {
    boolean possiblemove = false;
    Tile t0 = null;
    Tile t1 = null;

    //check combinations in x direction
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < 7; j++) {
        t0 = board.getTileAt(j, i);
        t1 = board.getTileAt(j + 1, i);
        if (t0 instanceof HypercubeTile || t1 instanceof HypercubeTile) {
          return true;
        }
        if (!possiblemove) { 
          possiblemove = SwapHandler.createsCombination(t0,t1);
        }
      }
    }

    //check combinations in y direction
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < 7; j++) {
        t0 = board.getTileAt(i, j);
        t1 = board.getTileAt(i, j + 1);
        if (!possiblemove) {
          possiblemove = SwapHandler.createsCombination(t0,t1);
        }
      }
    }
    return possiblemove;
  }

  /**
   * Get board object.
   * @return the board
   */
  public Board getBoard() {
    return this.board;
  }
  
  /**
   * set board object.
   * @return the board
   */
  public void setBoard(Board bo) {
    this.board = bo;
  }
  
}