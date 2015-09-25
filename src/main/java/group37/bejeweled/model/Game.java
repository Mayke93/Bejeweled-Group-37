package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.BoardFactory;
import main.java.group37.bejeweled.board.FlameTile;
import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.StarTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.Combination.Type;
import main.java.group37.bejeweled.view.Main;
import main.java.group37.bejeweled.view.StatusPanel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

//import javax.swing.JLabel;
//import Tile.State;
//TODO tile do not drop down anymore, but are replaced by random tiles.
/**
 * Class that represents the current game.
 * @author group37
 */
public class Game {
  private Board board = null;
  public BoardFactory boardFactory;
  public List<Tile> swapTiles;
  private int score = 0;
  private int newlevel = 1;
  private Tile[] swappedTiles; //Use this for special gems
  private Main boardPanel;
  private StatusPanel panel;
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
    this.panel = panel;
    this.finder = new CombinationFinder(board);

    this.logic = new GameLogic(this);
    this.logic.setFinder(finder);
    this.logic.setBoard(board);
    this.logic.setBoardPanel(boardPanel);
    
    swapTiles = new ArrayList<Tile>();
    swappedTiles = new Tile[2];
    generateRandomBoard();
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
      if (swapTiles.size() == 2 && canSwap()) {              
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
    Tile tile = new Tile(xi, yi);
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
    Tile tile = null;
    if (type == Type.NORMAL) {
      tile = new Tile(xi,yi);
    } else if (type == Type.FLAME) {
      tile = new FlameTile(xi,yi);
    } else if (type == Type.STAR) {
      tile = new StarTile(xi,yi);
    } else if (type == Type.HYPERCUBE) {
      tile = new HypercubeTile(xi,yi);
    }
    tile.setIndex(board.getTileAt(xi, yi).getIndex());
    Logger.log(type.toString() + " " + tile.getLoc());
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

      if (sum == 3) {
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

    int xc = t0.getX();
    int yc = t0.getY();
    t0.setLoc(t1.getX(),t1.getY());
    t1.setLoc(xc, yc);
    
    swappedTiles[0] = t0;
    swappedTiles[1] = t1;
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
    Combination combiX0 = finder.getSingleCombinationX(t0);
    Combination combiX1 = finder.getSingleCombinationX(t1);
    Combination combiY0 = finder.getSingleCombinationY(t0);
    Combination combiY1 = finder.getSingleCombinationY(t1);
    swapTiles(t0,t1);

    Type type = null;
    if (!combiX0.getTiles().isEmpty()) {
      type = combiX0.getType();
      System.out.println("in1");
    } else if (!combiX1.getTiles().isEmpty()) {
      type = combiX1.getType();
      System.out.println("in2");
    } else if (!combiY0.getTiles().isEmpty()) {
      type = combiY0.getType();
      System.out.println("in3");
    } else if (!combiY1.getTiles().isEmpty()) {
      type = combiY1.getType();
      System.out.println("in4");
    }

    if (t0 instanceof HypercubeTile || t1 instanceof HypercubeTile) {
      return true;
    }

    if (type == null) {
      return false;
    }

    if (!isNeighbour(t0,t1)) {
      Logger.error("t0 and t1 are not neighbours.");
      return false;
    }
    System.out.println(type);
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
  public void updateScore(Type type) {
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
   * Update the score in the view for points obtained by special gems.
   * @param combi the combination containing the special gem
   * @param tiles all the tiles deleted by the special gem
   */
  public void updateScoreSpecialGem(Combination combi, List<Tile> tiles) {
    if (combi.containsSpecialGem() instanceof FlameTile) {
      for (int i = 0; i < tiles.size(); i++) {
        this.score += 20;
      }
      Logger.log("Add score: " + tiles.size() + "*" + 20);
    }
    if (combi.containsSpecialGem() instanceof StarTile 
        || combi.containsSpecialGem() instanceof HypercubeTile) {
      for (int i = 0; i < tiles.size(); i++) {
        this.score += 50;
      }
      Logger.log("Add score: " + tiles.size() + "*" + 50);
    }
    Logger.log("Total Score: " + this.score);
    panel.setScore(this.score);
  }

  /**
   * Increase the levelnumber when a certain score is reached.
   */
  private void updateLevel() {
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
   * set board object.
   * @return the board
   */
  public void setBoard(Board bo) {
    this.board = bo;
  }
  
  public int getScore(){
    return this.score;
  }
  
  public void setScore(int score){
    this.score = score;
  }
  
  public int getLevel(){
    return this.newlevel;
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
  
  /**
   * sets the CombinationFinder
   * @param cf CombinationFinder object
   * @return a CombinationFinder object
   */
  public void setFinder(CombinationFinder cf) {
    this.finder = cf;
  }

  public void setLevel(Integer level1) {
    this.newlevel = level1; 
  }
}