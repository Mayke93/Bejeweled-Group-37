package main.java.group37.bejeweled.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import main.java.group37.bejeweled.Board.Board;
import main.java.group37.bejeweled.Board.FlameTile;
import main.java.group37.bejeweled.Board.HypercubeTile;
import main.java.group37.bejeweled.Board.Tile;
import main.java.group37.bejeweled.model.Combination.Type;
//import main.java.group37.bejeweled.model.Tile.State;
import main.java.group37.bejeweled.view.Animation;
import main.java.group37.bejeweled.view.Main;
import main.java.group37.bejeweled.view.StatusPanel;

//import javax.swing.JLabel;
//import Tile.State;
//TODO tile do not drop down anymore, but are replaced by random tiles.
/**
 * Class that represents the current game.
 * @author group37
 */
public class Game {
  private Board board = null;
  public List<Tile> swapTiles;
  private int score = 0;
  private Main boardPanel;
  private StatusPanel panel;
  private CombinationFinder finder;
  private static final int SIZE = Main.SIZE;

  /**
   * Create game object.
   * @param boardPanel object for GUI.
   * @param panel object for updating the labels.
   */
  public Game(Main boardPanel,StatusPanel panel) {
    this.boardPanel = boardPanel;
    this.panel = panel;
    this.finder = new CombinationFinder(board);
    swapTiles = new ArrayList<Tile>();
    generateRandomBoard();
  }

  /**
   * Add tile to swapTiles bases on location from the mouseEvent.
   * @param loc location of tile
   */
  public void addTile(Point loc) {
    int col = loc.x;
    int row = loc.y;
    if (!swapTiles.contains(board.getTileAt(col, row))) {
      swapTiles.add(board.getTileAt(col, row));
      boardPanel.setFocus(loc);
      if (swapTiles.size() == 2 && canSwap()) {              
        boardPanel.swapTiles(swapTiles);
        swapTiles.clear();
      }
    }
  }
  
  /**
   * method for adding a special gem.
   * @param xi x coordinate
   * @param yi y cordinate 
   * @param type type of combination
   */
  public void addSpecialGem(int xi, int yi, Type type) {
    switch (type) {
      case FLAME: //add tile to board.
        break;
      case HYPERCUBE:
        break;
      case STAR:
        break;
      default: //do nothing
        break;
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
   * Delete all tiles that form a combination on the current board.
   */
  public void deleteTiles() {
    List<Combination> chains = finder.getAllCombinationsOnBoard();
    Logger.log("Found " + chains.size() + " chains");
    for (Combination comb: chains) {
      Logger.log("Tile State: " + comb.getType());
    }
    List<Tile> tiles = new ArrayList<Tile>();
    for (int row = SIZE - 1; row >= 0; row-- ) {
      for (int col = 0; col < SIZE; col++) {

        if (containsTile(board.getTileAt(col, row), chains)) {
          Logger.log("Delete Tile: " + board.getTileAt(col, row));
          board.getTileAt(col, row).delete = true;
          tiles.add(board.getTileAt(col, row));
          for (int i = row - 1; i >= 0; i--) {
            board.getTileAt(col, i).increaseLevel();
          }
        }
      }
    }
    boardPanel.animations.setType(Animation.Type.REMOVE);
    boardPanel.animations.startRemove(tiles);
    printCombinations();

    Tile tile = null;
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        tile = board.getTileAt(j, i);
        System.out.print(tile.getLevel() + " ");
      }
      System.out.println();
    }
  }


 
  
  /**
   * If there are empty spaces, this method 'drops' the tile above this space into this space.
   */
  public void dropTiles() {    
    int level = 0;
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        level = board.getTileAt(col, row).getLevel();
        Tile curr = board.getTileAt(col, row);
        if (level > 0) {
          board.setTileAt(curr, col, row - 1);
          board.setTileAt(board.getTileAt(col, row).clone(col, row + level), col, row + level);
          board.getTileAt(col, row + level).setLevel(0);
          board.getTileAt(col, row).setLevel(0);
        }
      }
    }
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        if (board.isEmpty(col, row) 
            || board.getTileAt(col, row).delete) {
          board.setTileAt(setRandomTile(col,row), col, row);
          board.getTileAt(col, row).delete = false;
        }
        if (board.getTileAt(col, row).remove) {
          board.getTileAt(col, row).remove = false;
        }
      }
    }
    boardPanel.repaint();
    
    List<Combination> chains = finder.getAllCombinationsOnBoard();
    if (chains.size() != 0) {
      deleteTiles();
      
    }
  }

  /**
   * Check if chains contains the tile t.
   * @param tile tile to check for in chains.
   * @param chains this list of combinatians.
   * @return true if chains contains tile.
   */
  private boolean containsTile(Tile tile, List<Combination> chains) {
    for (Combination c: chains) {
      if (c.getTiles().contains(tile)) {
        return true;
      }
    }
    return false;
  }


  /**
   * Create a board of random jewels without a sequence of 3 or more tiles with the same color.
   */
  public void generateRandomBoard() {
    board = new Board(new Tile[Main.SIZE][Main.SIZE]); 
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
    Tile tile = new Tile(xi, yi);
    Random random = new Random();
    //this.state = State.NORMAL;
    tile.setIndex(random.nextInt(7));
    tile.setImage(new ImageIcon(tile.paths[tile.getIndex()]));
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
        System.out.println("i,j: " + row + "," + j);
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
  public Tile checktype(Tile t0, Tile t1) {
    //Tile.State res = null;
    Tile res = null;
    String c1 = Tile.colors[board.getTileAt(t0.getX(), t0.getY()).getIndex()];
    String c2 = Tile.colors[board.getTileAt(t1.getX(), t1.getY()).getIndex()];
    Tile tile = null;
    String color = null;
    //swap tiles to look in the rows where the tile will be in case it can be switched
    swapTiles(t0,t1);

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
          System.out.println("1e " + sum);
        } else {
          break;
        }
      }
      for (int q = tile.getX() - 1; q >= 0; q--) {
        if (Tile.colors[board.getTileAt(q, tile.getY()).getIndex()].equals(color)) {
          sum++;
          System.out.println("2e " + sum);
        } else {
          break;
        }
      }

      //TODO which tile changes into special tile? atm I've put in tile t0.
      //TODO Startile?
      if (sum == 3) {
        //res = Tile.State.NORMAL;
        res = new Tile(t0.getX(), t0.getY());
      }
      if (sum == 4) {
       // res = Tile.State.FLAME;
        
        res = new FlameTile(t0.getX(), t0.getY());
      }
      if (sum == 5) {
        res = new HypercubeTile(t0.getX(), t0.getY());
      }

      //check y direction
      sum = 1;
      for (int q = tile.getY() + 1; q < SIZE; q++) {
        if (Tile.colors[board.getTileAt(tile.getX(), q).getIndex()].equals(color)) {
          sum++;
          System.out.println("3e " + sum);
        } else {
          break;
        }
      }
      for (int q = tile.getY() - 1; q >= 0; q--) {
        if (Tile.colors[board.getTileAt(tile.getX(), q).getIndex()].equals(color)) {
          sum++;
          System.out.println("4e " + sum);
        } else {
          break;
        }
      }

      if (sum == 3) {
       // res = Tile.State.NORMAL;
       res = new Tile(t0.getX(), t0.getY());
      }
      if (sum == 4) {
        //res = Tile.State.FLAME;
        res = new FlameTile(t0.getX(), t0.getY());
      }
      if (sum == 5) {
        res = new HypercubeTile(t0.getX(), t0.getY());
      }
    }
    //swap the tiles back to original position
    swapTiles(t0,t1);
    System.out.println(res);
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
        if (!(checktype(t0,t1) == null)) {
          possiblemove = true;
        }
      }
    }

    //check combinations in y direction
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < 7; j++) {
        t0 = board.getTileAt(i, j);
        t1 = board.getTileAt(i, j + 1);
        if (!(checktype(t0,t1) == null)) {
          possiblemove = true;
        }
      }
    }
    return possiblemove;
  }

  /**
   * Switch tile t0 and t1 on the board.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   */
  public void swapTiles(Tile t0, Tile t1) {
    Tile temp = board.getTileAt(t0.getX(), t0.getY());
    board.setTileAt(board.getTileAt(t1.getX(), t1.getY()), t0.getX(), t0.getY());
    board.setTileAt(temp, t1.getX(), t1.getY());

    Point tiles = (Point) t0.getLoc().clone();
    t0.setLoc(t1.getX(),t1.getY());
    t1.setLoc(tiles);
  }

  /**
   * Swap two tiles if it result in a sequence of 3 of more tiles with the same color.
   */
  public boolean canSwap() {
    /*System.out.println(swapTiles.get(0).getX() + "," + swapTiles.get(0).getY());
      System.out.println(swapTiles.get(1).getX() + "," + swapTiles.get(1).getY());*/

    Tile t0 = board.getTileAt(swapTiles.get(0).getX(), swapTiles.get(0).getY());
    Tile t1 = board.getTileAt(swapTiles.get(1).getX(), swapTiles.get(1).getY());

    swapTiles(t0,t1);
    Combination l1 = finder.getSingleCombinationX(t0);
    Combination l2 = finder.getSingleCombinationX(t1);
    Combination l3 = finder.getSingleCombinationY(t0);
    Combination l4 = finder.getSingleCombinationY(t1);
    swapTiles(t0,t1);

    Type type = null;
    if (!l1.getTiles().isEmpty()) {
      type = l1.getType();
      System.out.println("in1");
    } else if (!l2.getTiles().isEmpty()) {
      type = l2.getType();
      System.out.println("in2");
    } else if (!l3.getTiles().isEmpty()) {
      type = l3.getType();
      System.out.println("in3");
    } else if (!l4.getTiles().isEmpty()) {
      type = l4.getType();
      System.out.println("in4");
    }

    //Tile.State type = checktype(t0,t1);

    if (type == null) {
      return false;
    }

    if (!isNeighbour(t0,t1)) {
      Logger.error("t0 and t1 are not neighbours.");
      return false;
    }
    System.out.println(type);
    updateScore(type);
    updateLevel();
    return true;
  }

  /**
   * Return true if t0 and t1 are neighbours.
   * @param t0 tile 1.
   * @param t1 tile 2.
   * @return true if t0 and t1 are next to each other.
   */
  public boolean isNeighbour(Tile t0, Tile t1) {
    /*System.out.println(t0.getX() + "," + t0.getY());
      System.out.println(t1.getX() + "," + t1.getY());*/
    if (Math.abs(t0.getX() - t1.getX()) == 1 && Math.abs(t0.getY() - t1.getY()) == 0) {
      return true;
    }
    if (Math.abs(t0.getX() - t1.getX()) == 0 && Math.abs(t0.getY() - t1.getY()) == 1) {
      return true;
    }
    return false;
  }

  /**
   * Update score in the view.
   * @param type change score based on the value of type.
   */
  private void updateScore(Type type) {
    int score = 0;
    switch (type) {
      case NORMAL:
        score = 50;
        break;
      case FLAME:
        score = 150;
        break;
      case HYPERCUBE:
        score = 500;
        break;
      case STAR:
        score = 150;
        break;
      default:
        break;
    }
    this.score += score;
    Logger.log("Add score: " + score);
    Logger.log("Total Score: " + this.score);
    panel.setScore(this.score);
  }

  /**
   * Increase the levelnumber when a certain score is reached.
   */
  private void updateLevel() {
    int newlevel = 1;
    if (this.score >= 1000 && this.score < 3500) {
      newlevel = 2;
    }
    if (this.score >= 3500 && this.score < 5500) {
      newlevel = 3;
    }
    if (this.score >= 5500 && this.score < 8000) {
      newlevel = 4;
    }
    if (this.score >= 8000 && this.score < 11000) {
      newlevel = 5;
    }
    panel.setLevel(newlevel);
    //Logger.log("Level: " + newlevel);
  }
  
  /**
   * End game if there is no possible combination.
   
  public void endGame() {
    JLabel label1 = new JLabel("No possible combination",JLabel.CENTER);
    label1.setVerticalTextPosition(JLabel.TOP);
    label1.setHorizontalTextPosition(JLabel.CENTER);
    if (!(possibleMove())) {
      this.boardPanel.add(label1);
      return;
    }
  }
*/
  /**
   * Reset game.
   */
  public void reset() {
    this.score = 0;
    swapTiles = new ArrayList<Tile>();
    generateRandomBoard();
    boardPanel.repaint();
  }

  /**
   * Get board object.
   * @return the board
   */
  public Board getBoard() {
    return this.board;
  }

  /**
   * Get the list with the selected tiles to swap.
   * @return the list swapTiles
   */
  public List<Tile> getSwaptiles() {
    return this.swapTiles;
  }
  
  /**
   * Get the CombinationFinder of the game.
   * @return CombinationFinder
   */
  public CombinationFinder getFinder() {
    return finder;
  }
  
}