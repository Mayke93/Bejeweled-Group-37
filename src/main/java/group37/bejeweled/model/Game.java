package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.BoardFactory;
import main.java.group37.bejeweled.board.FlameTile;
import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.NormalTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.board.TileFactory;
import main.java.group37.bejeweled.combination.Combination;
import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.combination.CombinationFinder;
import main.java.group37.bejeweled.view.Main;
import main.java.group37.bejeweled.view.StatusPanel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

//TODO tile do not drop down anymore, but are replaced by random tiles.
/**
 * Class that represents the current game.
 * @author group37
 */
public class Game {
  
  private Board board = null;
  private SwapHandler swapHandler;
  public BoardFactory boardFactory;
  public List<Tile> swapTiles;
  private Tile[] swappedTiles; //Use this for special gems
  private Main boardPanel;
  private CombinationFinder finder;
  
  public GameLogic logic;
  public static final int SIZE = Main.SIZE;

  /**
   * Create game object.
   * @param boardPanel object for GUI.
   * @param panel object for updating the labels.
   */
  public Game(Main boardPanel,StatusPanel panel) {
    this.boardPanel = boardPanel;
    this.boardFactory = new BoardFactory(this);
    this.board = new Board(new Tile[Main.SIZE][Main.SIZE]); 
    this.finder = new CombinationFinder(board);

    this.logic = new GameLogic(this);
    this.logic.setFinder(finder);
    this.logic.setBoard(board);
    this.logic.setBoardPanel(boardPanel);
    
    swapTiles = new ArrayList<Tile>();
    swappedTiles = new Tile[2];
    generateRandomBoard();
    
    swapHandler = new SwapHandler(board, swapTiles, swappedTiles);
  }

  /**
   * Add tile to swapTiles based on location from the mouseEvent.
   * @param loc location of tile
   */
  public void addTile(Point loc) {
    int col = loc.x;
    int row = loc.y;
    if (!swapTiles.contains(board.getTileAt(col, row))) {
      swapTiles.add(board.getTileAt(col, row));
      boardPanel.setFocus(loc);
      if (swapTiles.size() == 2 && swapHandler.canSwap()) {              
        boardPanel.swapTiles(swapTiles);
        swapTiles.clear();
      }
    }
  }

  /**
   * Prints the combinations obtained by getAllCombinationsOnBoard().
   */
  public void printCombinations() {
    List<Combination> res = finder.getAllCombinationsOnBoard();
    System.out.println("chains: " + res.size());
    for (Combination combi : res) {
      System.out.println("\tType: " + combi.getType());
      System.out.println("\t" + combi.getTiles());
    }
  }

  /**
   * Create a board of random jewels without a sequence of 3 or more tiles with the same color.
   */
  public void generateRandomBoard() {
    Logger.log("Create new board");
    for (int i = 0; i < Main.SIZE; i++) {
      for (int j = 0; j < Main.SIZE; j++) {
        board.setTileAt(setRandomTile(i,j), i , j);
      }

      //Redo column if a sequence has been detected
      if (hasSequence(i)) {
        i--;
      }
    }
    finder.setBoard(this.board);
  }
  
  /**
   * makes a random tile.
   * @param xi x coordinate of the new random tile
   * @param yi y coordinate of the new random tile
   * @return a random tile as a Tile object
   */
  public Tile setRandomTile(int xi, int yi) { 
    Tile tile = new NormalTile(xi, yi);
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
    Logger.log("$$$$$$ setSpecialTile: " + tile.remove);
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
    for (int j = 1; j < Main.SIZE; j++) {
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
    for (int j = 0; j < Main.SIZE; j++) {
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
   * Check if two tiles can be swapped and
   * what kind of jewel should be created based on the size of the found sequence.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   * @return true iff swapping tiles t0 and t1 results in a valid combination.
   */
  public boolean createsCombination(Tile t0, Tile t1) {
    boolean res = false;;
    String c1 = Tile.colors[board.getTileAt(t0.getX(), t0.getY()).getIndex()];
    String c2 = Tile.colors[board.getTileAt(t1.getX(), t1.getY()).getIndex()];
    Tile tile = null;
    String color = null;
    //swap tiles to look in the rows where the tile will be in case it can be switched
    swapHandler.swapTiles(t0,t1);

    for (int i = 1; i < 3; i++) {
      if (i == 1) {
        tile = t0;
        color = c1;
      }
      if (i == 2) {
        tile = t1;
        color = c2;
      }

      //check x direction
      int sum = 1;
      for (int q = tile.getX() + 1; q < SIZE; q++) {
        if (Tile.colors[board.getTileAt(q, tile.getY()).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }
      for (int q = tile.getX() - 1; q >= 0; q--) {
        if (Tile.colors[board.getTileAt(q, tile.getY()).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }
      if (sum > 2 && sum < 6) {
        res = true;
      }

      //check y direction
      sum = 1;
      for (int q = tile.getY() + 1; q < SIZE; q++) {
        if (Tile.colors[board.getTileAt(tile.getX(), q).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }
      for (int q = tile.getY() - 1; q >= 0; q--) {
        if (Tile.colors[board.getTileAt(tile.getX(), q).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }

      if (sum > 2 && sum < 6) {
        res = true;
      }
    }
    //swap the tiles back to original position
    swapHandler.swapTiles(t0,t1);
    return res;
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
        possiblemove = createsCombination(t0,t1);;
      }
    }

    //check combinations in y direction
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < 7; j++) {
        t0 = board.getTileAt(i, j);
        t1 = board.getTileAt(i, j + 1);
        if (!possiblemove) {
          possiblemove = createsCombination(t0,t1);;
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
  
  public SwapHandler getSwapHandler() {
    return swapHandler;
  }
   
  /**
   * Get the list with the selected tiles to swap.
   * @return the list swapTiles
   */
  public List<Tile> getSwaptiles() {
    return this.swapTiles;
  }
}